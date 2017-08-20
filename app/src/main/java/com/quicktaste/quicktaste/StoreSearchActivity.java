package com.quicktaste.quicktaste;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class StoreSearchActivity extends AppCompatActivity {

    private static String url = "http://147.46.121.8:3000/get_store_info";
    private static String blog_store_search_sample_result = "{\"biztel\":\"02-753-1230\",\n" +
            " \"bizaddr\":\"서울 중구 명동길 55\",\n" +
            " \"mapaddr\":\"http://map.naver.com/?mapmode=0&lat=37.5642349&lng=126.9853456&pinId=32315856&dlevel=11&enc=b64&pinType=site\",\n" +
            " \"biztime\":\"매일 11:00 - 22:00\",\n" +
            " \"menu_link\":\"https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20161010_63%2Fkyhyb_1476102070310NN8jR_JPEG%2F20161007_125405_HDR.jpg&type=m1000_692&quality=95&autoRotate=true\"}";
    private TextView tv_storeName, tv_storeTel, tv_storeAddr, tv_storeTime;
    private ImageView iv_storeMenu;
    private WebView wv_store_search_map;

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

        wv_store_search_map = (WebView) findViewById(R.id.wv_store_search_map);
        iv_storeMenu = (ImageView) findViewById(R.id.iv_store_search_menu);

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

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
