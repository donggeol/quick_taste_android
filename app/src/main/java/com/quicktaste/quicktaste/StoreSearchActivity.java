package com.quicktaste.quicktaste;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;

import org.json.JSONException;
import org.json.JSONObject;

public class StoreSearchActivity extends NMapActivity {

    private static String url = "http://147.46.121.8:3000/get_store_info";
    private static String blog_store_search_sample_result = "{\"biztel\":\"02-753-1230\",\n" +
            " \"bizaddr\":\"서울 중구 명동길 55\",\n" +
            " \"mapaddr\":\"http://map.naver.com/?mapmode=0&lat=37.5642349&lng=126.9853456&pinId=32315856&dlevel=11&enc=b64&pinType=site\",\n" +
            " \"biztime\":\"매일 11:00 - 22:00\",\n" +
            " \"menu_link\":\"https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20161010_63%2Fkyhyb_1476102070310NN8jR_JPEG%2F20161007_125405_HDR.jpg&type=m1000_692&quality=95&autoRotate=true\"}";
    private TextView tv_storeName, tv_storeTel, tv_storeAddr, tv_storeTime;
    private ImageView iv_storeMenu;


    // API-KEY
    public static final String clientId = "igk3XhjV8a1Ys3qi38bK";  //<---맨위에서 발급받은 본인 ClientID 넣으세요.
    // 네이버 맵 객체
    NMapView mMapView = null;
    // 맵 컨트롤러
    NMapController mMapController = null;
    // 맵을 추가할 레이아웃
    LinearLayout MapContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_search);

        Intent intent = getIntent();
        String keyword = intent.getStringExtra(BlogSearchActivity.STORE_KEYWORD);

        // Capture the layout's TextView and set the string as its text
        tv_storeName = (TextView) findViewById(R.id.tv_store_search_name);
        tv_storeName.setText(keyword);

        tv_storeTel = (TextView) findViewById(R.id.tv_store_search_tel);
        tv_storeTime = (TextView) findViewById(R.id.tv_store_search_time);
        tv_storeAddr = (TextView) findViewById(R.id.tv_store_search_addr);

        iv_storeMenu = (ImageView) findViewById(R.id.iv_store_search_menu);

        // 네이버 지도를 넣기 위한 LinearLayout 컴포넌트
        MapContainer = (LinearLayout) findViewById(R.id.ll_MapContainer);


        ContentValues param = new ContentValues();

        param.put("keyword", keyword);

        StoreSearchActivity.NetworkTask networkTask = new StoreSearchActivity.NetworkTask(url, param);
        networkTask.execute();

    }

    public void onMapInitHandler(NMapView mapView, NMapError errorInfo) {
        if (errorInfo == null) { // success
            mMapController.setMapCenter(new NGeoPoint(126.9853456, 37.5642349), 11);
        } else { // fail
//            Log.e(LOG_TAG, "onMapInitHandler: error=" + errorInfo.toString());
        }
    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result;
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values);

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("onPost", s);

            JSONParser_Parse(s);
        }

    }

    void JSONParser_Parse(String str){

        try {
            JSONObject jObject = new JSONObject(str);
            String biztel = jObject.optString("biztel", null);
            String biztime = jObject.optString("biztime", null);
            String bizaddr = jObject.optString("bizaddr", null);
            String mapaddrLink = jObject.optString("mapaddr", null);
            String menuLink = jObject.optString("menu_link", null);

            tv_storeTel.setText("Tel: \n" + biztel);
            tv_storeTime.setText("Time: \n" + biztime);
            tv_storeAddr.setText("Addr.: \n" + bizaddr);

            System.out.println("biztel : " + biztel + " biztime : " + biztime + " bizaddr : " + bizaddr + " mapaddrLink :" + mapaddrLink + " menuLink : " + menuLink);


            if(mapaddrLink=="")
                System.out.println("mapaddrLink Test NULL3");

            //TODO NULL error handling
            //TODO make it to "MapView"
//            if(mapaddrLink!=""){
//                wv_store_search_map.setWebViewClient(new WebViewClient());
//                mapaddrLink = new StringBuilder(mapaddrLink).insert(7, "m.").toString();
//                wv_store_search_map.loadUrl(mapaddrLink);
//            }

            if(menuLink!=null)
                Glide.with(this).load(menuLink).into(iv_storeMenu);


            // 네이버 지도 객체 생성
            mMapView = new NMapView(this);

            // 지도 객체로부터 컨트롤러 추출
            mMapController = mMapView.getMapController();

            // 네이버 지도 객체에 clientId 지정
            mMapView.setClientId(clientId);

            // 생성된 네이버 지도 객체를 LinearLayout에 추가시킨다.
            MapContainer.addView(mMapView);

            // 지도를 터치할 수 있도록 옵션 활성화
            mMapView.setClickable(true);

            // 확대/축소를 위한 줌 컨트롤러 표시 옵션 활성화
            mMapView.setBuiltInZoomControls(true, null);

            // 지도에 대한 상태 변경 이벤트 연결
//        mMapView.setOnMapStateChangeListener(onMapViewStateChangeListener);
//        mMapView.setOnMapViewTouchEventListener(onMapViewTouchEventListener);

            // enable built in app control;
            mMapView.executeNaverMap();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
