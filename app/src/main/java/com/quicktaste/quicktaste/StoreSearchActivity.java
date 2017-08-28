package com.quicktaste.quicktaste;

import android.app.ActivityManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapOverlay;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapCalloutOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import static com.nhn.android.maps.NMapView.isValidLocation;

public class StoreSearchActivity extends NMapActivity  {
    private static String url = "http://147.46.121.8:3000/get_store_info";
    private static String blog_store_search_sample_result = "{\"biztel\":\"02-753-1230\",\n" +
            " \"bizaddr\":\"서울 중구 명동길 55\",\n" +
            " \"mapaddr\":\"http://map.naver.com/?mapmode=0&lat=37.5642349&lng=126.9853456&pinId=32315856&dlevel=11&enc=b64&pinType=site\",\n" +
            " \"biztime\":\"매일 11:00 - 22:00\",\n" +
            " \"menu_link\":\"https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20161010_63%2Fkyhyb_1476102070310NN8jR_JPEG%2F20161007_125405_HDR.jpg&type=m1000_692&quality=95&autoRotate=true\"}";

    private String store_name;
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

    NMapViewerResourceProvider mMapViewerResourceProvider = null; //NMapViewerResourceProvider 클래스 상속
    NMapOverlayManager mOverlayManager; //지도 위에 표시되는 오버레이 객체를 관리한다.
    NMapPOIdataOverlay.OnStateChangeListener onPOIdataStateChangeListener = null; //POI 아이템의 선택 상태 변경 시 호출되는 콜백 인터페이스를 정의한다.
    NMapOverlayManager.OnCalloutOverlayListener onCalloutOverlayListener; //말풍선 오버레이 객체 생성 시 호출되는 콜백 인터페이스를 정의한다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_search);

        Intent intent = getIntent();
        String keyword = intent.getStringExtra(BlogSearchActivity.STORE_KEYWORD);
        store_name = keyword;

        // Capture the layout's TextView and set the string as its text
        tv_storeName = (TextView) findViewById(R.id.tv_store_search_name);
        tv_storeName.setText(keyword);

        tv_storeTel = (TextView) findViewById(R.id.tv_store_search_tel);
        tv_storeTime = (TextView) findViewById(R.id.tv_store_search_time);
//        tv_storeTime.setWidth(350);
        tv_storeAddr = (TextView) findViewById(R.id.tv_store_search_addr);

        iv_storeMenu = (ImageView) findViewById(R.id.iv_store_search_menu);

        // 네이버 지도를 넣기 위한 LinearLayout 컴포넌트
        MapContainer = (LinearLayout) findViewById(R.id.ll_MapContainer);

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


        //create resource provider(오버레이 객체)
        mMapViewerResourceProvider = new NMapViewerResourceProvider(this); //NMapViewerResourceProvider 클래스 상속
        mOverlayManager = new NMapOverlayManager(this, mMapView, mMapViewerResourceProvider); //오버레이 객체를 화면에 표시하기 위하여 NMapResourceProvider 클래스를 상속받은 resourceProvider 객체를 전달한다
        mOverlayManager.setOnCalloutOverlayListener(onCalloutOverlayListener); //말풍선 오버레이 객체 생성 시 호출되는 콜백 인터페이스를 설정한다.






        ContentValues param = new ContentValues();

        param.put("keyword", keyword);

        StoreSearchActivity.NetworkTask networkTask = new StoreSearchActivity.NetworkTask(url, param);
        networkTask.execute();

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

            //TODO 검색 결과 없을 때 예외 처리
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


            if(menuLink!=null)
                Glide.with(this).load(menuLink).into(iv_storeMenu);


            //네이버 지도 위치 검색 결과로 변경
            Location location = findGeoPoint(this, bizaddr);
            double lon = location.getLongitude();
            double lat = location.getLatitude();
            if(isValidLocation(lon, lat)){
                mMapController.setMapCenter(new NGeoPoint(lon, lat), 11);
                System.out.println("DEBUG: valid location   lon : " + lon + " lat : " + lat);
            }
            else{
                System.out.println("DEBUG: not valid location  lon : " + lon + " lat : " + lat);
                String[] parts = mapaddrLink.split("&", 4);
                String dummy = parts[0];
                lat = Double.parseDouble(parts[1].substring(4));
                lon = Double.parseDouble(parts[2].substring(4));
                System.out.println("DEBUG: Parsed by MapLink data : lat = " + lat + " lon = " + lon);
            }

            //네이버 지도 오버레이 변경
            int markerId = NMapPOIflagType.PIN;
            NMapPOIdata poiData = new NMapPOIdata(2, mMapViewerResourceProvider); //전체 POI 아이템의 개수와 NMapResourceProvider를 상속받은 클래스를 인자로 전달한다.
            poiData.beginPOIdata(2); //POI 아이템 추가를 시작한다.

            NMapPOIitem item1 = poiData.addPOIitem(lon, lat, store_name, markerId, 0); //POI아이템 설정
            item1.setRightAccessory(true, NMapPOIflagType.CLICKABLE_ARROW); //마커 선택 시 표시되는 말풍선의 오른쪽 아이콘을 설정한다.
            item1.hasRightAccessory(); //마커 선택 시 표시되는 말풍선의 오른쪽 아이콘 설정 여부를 반환한다.
            item1.setRightButton(true); //마커 선택 시 표시되는 말풍선의 오른쪽 버튼을 설정한다.
            item1.showRightButton(); //마커 선택 시 표시되는 말풍선의 오른쪽 버튼 설정 여부를 반환한다.

//            poiData.addPOIitem(126.872772, 37.546848, "KB국민은행 염창역 지점 앞", markerId, 0); //경도위도 좌표 입력해주면, 그 좌표가 표시됨
            poiData.endPOIdata(); //POI 아이템 추가를 종료한다.

            NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null); //POI 데이터를 인자로 전달하여 NMapPOIdataOverlay 객체를 생성한다.
            poiDataOverlay.showAllPOIdata(0); //POI 데이터가 모두 화면에 표시되도록 지도 축척 레벨 및 지도 중심을 변경한다. zoomLevel이 0이 아니면 지정한 지도 축척 레벨에서 지도 중심만 변경한다.
            poiDataOverlay.setOnStateChangeListener(onPOIdataStateChangeListener); //POI 아이템의 선택 상태 변경 시 호출되는 콜백 인터페이스를 설정한다.

            // set data provider listener
//        super.setMapDataProviderListener(onDataProviderListener); //지도 라이브러리에서 제공하는 서버 API 호출 시 응답에 대한 콜백 인터페이스를 설정한다.

//            // enable built in app control;
//            mMapView.executeNaverMap();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void onMapInitHandler(NMapView mapView, NMapError errorInfo) {
        if (errorInfo == null) { // success
            mMapController.setMapCenter(new NGeoPoint(126.9853456, 37.5642349), 11);
        } else { // fail
//            Log.e(LOG_TAG, "onMapInitHandler: error=" + errorInfo.toString());
        }
    }

    public static Location findGeoPoint(Context mcontext, String address) {
        Location loc = new Location("");
        Geocoder coder = new Geocoder(mcontext);
        List<Address> addr = null;// 한좌표에 대해 두개이상의 이름이 존재할수있기에 주소배열을 리턴받기 위해 설정

        try {
            addr = coder.getFromLocationName(address, 5);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// 몇개 까지의 주소를 원하는지 지정 1~5개 정도가 적당
        if (addr != null) {
            for (int i = 0; i < addr.size(); i++) {
                Address lating = addr.get(i);
                double lat = lating.getLatitude(); // 위도가져오기
                double lon = lating.getLongitude(); // 경도가져오기
                loc.setLatitude(lat);
                loc.setLongitude(lon);
            }
        }
        return loc;
    }


    //TODO 오버레이 말풍선 아이템 클릭하면 네이버 지도 앱으로 넘어가도록
    public void onCalloutClick(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item) {
        // [[TEMP]] handle a click event of the callout
        Toast.makeText(StoreSearchActivity.this, "onCalloutClick: " + item.getTitle(), Toast.LENGTH_LONG).show();
        System.out.println("DEBUG: " + "onCalloutClick: " + item.getTitle());
    }

    public void onFocusChanged(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item) {
        if (item != null) {
            Toast.makeText(StoreSearchActivity.this, "onFocusChanged: " + item.toString(), Toast.LENGTH_LONG).show();
            System.out.println("DEBUG: " + "onFocusChanged: " + item.toString());
        } else {
            Toast.makeText(StoreSearchActivity.this, "onFocusChanged", Toast.LENGTH_LONG).show();
            System.out.println("DEBUG: " + "onFocusChanged");
        }
    }

    public NMapCalloutOverlay onCreateCalloutOverlay(NMapOverlay itemOverlay, NMapOverlayItem overlayItem, Rect itemBounds) {
        // set your callout overlay
        Toast.makeText(StoreSearchActivity.this, "onCreateCalloutOverlay: " + overlayItem.toString(), Toast.LENGTH_LONG).show();
        return new NMapCalloutBasicOverlay(itemOverlay, overlayItem, itemBounds);
    }

}
