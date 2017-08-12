package com.quicktaste.quicktaste;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BlogSearchActivity extends AppCompatActivity {

    private LinearLayout innerContainer;
    private TextView searchKeyword;
    public int test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_search);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String keyword = intent.getStringExtra(MainActivity.KEYWORD);

        // Capture the layout's TextView and set the string as its text
        TextView searchKeyword = (TextView) findViewById(R.id.keyword);
        searchKeyword.setText("keyword : " + keyword);

        String url = "http://147.46.121.8:3000/search_naver";

        ContentValues param = new ContentValues();

        param.put("keyword", keyword);

        NetworkTask networkTask = new NetworkTask(url, param);
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

            //[TODO] need to fix this declaration.
            TextView searchKeyword = (TextView) findViewById(R.id.keyword);
            //searchKeyword.setText(s);

//            String test = "{\"rss\":\n" +
//                    "      {\"$\":{\"version\":\"2.0\"},\n" +
//                    "       \"channel\":[{\"title\":[\"Naver Open API - blog ::'pasta'\"],\n" +
//                    "                   \"link\":[\"http://search.naver.com\"],\n" +
//                    "                   \"description\":[\"Naver Search Result\"],\n" +
//                    "                   \"lastBuildDate\":[\"Sat, 05 Aug 2017 15:23:47 +0900\"],\n" +
//                    "                   \"total\":[\"75404\"],\n" +
//                    "                   \"start\":[\"1\"],\n" +
//                    "                   \"display\":[\"10\"],\n" +
//                    "                   \"item\":[{\"title\":[\"Da Guido La <b>Pasta</b> Melbourne _ 멜번 추천 맛집\"],\n" +
//                    "                            \"link\":[\"http://blog.naver.com/irisscissor?Redirect=Log&logNo=221060119658\"],\n" +
//                    "                            \"description\":[\"La <b>Pasta</b> Melbourne 정말 환상 적인 파스타 맛이다. 애들레이드에도 유명한 파스타 집이 몇군데쯤... 아,, 이래서 대도시 사나부다 라며 촌스런 말을 마구 해 대게 한 이집은, Da Guido La <b>Pasta</b> Melbourne... \"],\n" +
//                    "                            \"bloggername\":[\"Rainbow Bridge\"],\n" +
//                    "                            \"bloggerlink\":[\"http://blog.naver.com/irisscissor\"],\n" +
//                    "                            \"postdate\":[\"20170726\"]},\n" +
//                    "\n" +
//                    "                            {\"title\":[\"레시피 &quot;30분파스타에파지올리 30-minute <b>Pasta</b> e Fagioli&quot;\"],\n" +
//                    "                            \"link\":[\"http://blog.naver.com/songyh0624?Redirect=Log&logNo=221053709542\"],\n" +
//                    "                            \"description\":[\"30분파스타에파지올리 30-minute <b>Pasta</b> e Fagioli 더 푸드랩에 실린 레시피중 . '더 푸드랩' 도서... 20170702 30분 파스타 에파지올리 30 minute <b>Pasta</b> e Fagioli from 'The Food Lab' 나는 전에 싱가폴... \"],\n" +
//                    "                            \"bloggername\":[\"챨리네 다양한 생활\"],\"bloggerlink\":[\"http://blog.naver.com/songyh0624\"],\n" +
//                    "                            \"postdate\":[\"20170724\"]},\n" +
//                    "\n" +
//                    "                            {\"title\":[\"[부산/광안리/광안리맛집] 버거&amp;파스타 / Burger&amp;<b>Pasta</b>\"],\n" +
//                    "                            \"link\":[\"http://blog.naver.com/ehoajdcnd?Redirect=Log&logNo=221060951610\"],\n" +
//                    "                            \"description\":[\"[부산/광안리/광안리맛집] #Burger&amp;<b>Pasta</b> #버거&amp;파스타 때는 어느 늦은 밤이었어요. 전포동 즉석떡볶이집 동백아가씨에서 맛있게 즉떡을 흡인한후 301호 이웃의 친구 춘심이가 방문했어요. 녀석을 따라 광안리로... \"],\n" +
//                    "                            \"bloggername\":[\"이웃집 금금이의 지극히 개인적인 공간 :)♥\"],\n" +
//                    "                            \"bloggerlink\":[\"http://blog.naver.com/ehoajdcnd\"],\n" +
//                    "                            \"postdate\":[\"20170727\"]},\n" +
//                    "\n" +
//                    "                            {\"title\":[\"치앙마이 맛집 Pasti <b>Pasta</b> 로 치킨스테이크 먹으러~!!!\"],\n" +
//                    "                            \"link\":[\"http://blog.naver.com/paple21?Redirect=Log&logNo=221055895132\"],\n" +
//                    "                            \"description\":[\"치앙마이 맛집 Pasti <b>Pasta</b> 로 치킨스테이크와 파스타 먹으러~!!! 2017/07/14 금 치앙마이 맛집 Pasti <b>pasta</b>마야몰에서 도보 15분쯤? 이건 어디까지나 내 기분탓?!!! Pasti <b>Pasta</b> 의 정확한 위치는 구글로~! 거리는 개인의... \"],\n" +
//                    "                            \"bloggername\":[\"상실이집사 세계일주 ★Banat, banat, ban jai.★\"],\n" +
//                    "                            \"bloggerlink\":[\"http://blog.naver.com/paple21\"],\n" +
//                    "                            \"postdate\":[\"20170723\"]},\n" +
//                    "\n" +
//                    "                            {\"title\":[\"명동역맛집 : 명동빠네 Pane <b>Pasta</b>에서 피자앤파스타 먹방\"],\n" +
//                    "                            \"link\":[\"http://blog.naver.com/keh00047?Redirect=Log&logNo=221061771450\"],\n" +
//                    "                            \"description\":[\"&gt;-&lt; 사진메뉴 책자를 안봐서 이제야 봤어요 !! 스테이크파스타도 있네요 ㅎㅎ 다음번에 와서 이메뉴를 먹어보기로 ! 명동역맛집 : 명동빠네 : 피자 &amp; 파스타 맛집 : Pane <b>Pasta</b>\"],\n" +
//                    "                            \"bloggername\":[\"그 여자가 사는 이야기\"],\n" +
//                    "                            \"bloggerlink\":[\"http://blog.naver.com/keh00047\"],\n" +
//                    "                            \"postdate\":[\"20170728\"]},\n" +
//                    "\n" +
//                    "                            {\"title\":[\"대전 중구 맛집 : 가성비 좋은 파스타 <b>pasta</b> zip\"],\n" +
//                    "                            \"link\":[\"http://blog.naver.com/ddaa104?Redirect=Log&logNo=221052256970\"],\n" +
//                    "                            \"description\":[\"대전 중구 맛집 <b>pasta</b> zip 파스타 집에 다녀왔습니다 저렴한 가격에 이탈리안 메뉴를 먹을 수 있어서... 맛집 <b>pasta</b> zip 파스타 집은 손님이 많은데도 메뉴가 꽤 빨리 나오더군요 게살이 들어간 할라피뇨... \"],\n" +
//                    "                            \"bloggername\":[\"이푼이+육푼이\"],\n" +
//                    "                            \"bloggerlink\":[\"http://blog.naver.com/ddaa104\"],\n" +
//                    "                            \"postdate\":[\"20170715\"]},\n" +
//                    "\n" +
//                    "                            {\"title\":[\"베네치아 파스타, 젤라또 맛집☞ FRESH <b>PASTA</b> TO GO  &amp; SUSO... \"],\n" +
//                    "                            \"link\":[\"http://blog.naver.com/tak2chef?Redirect=Log&logNo=221059535454\"],\n" +
//                    "                            \"description\":[\"추천해주고 싶은 파스타집과 젤라또집 가지고 왔어요♩ 그 중 FRESH <b>PASTA</b> TO GO 이 집은 완전 �\"],\n" +
//                    "\t\t\t\t\t\t\t\"bloggername\":[\"null\"],\n" +
//                    "                            \"bloggerlink\":[\"null\"],\n" +
//                    "                            \"postdate\":[\"null\"]}\n" +
//                    "                            ]\n" +
//                    "                        }]\n" +
//                    "}}";

            StringBuffer sb = JSONParser_Parse(s);



        }

        StringBuffer JSONParser_Parse(String str){
            StringBuffer sb = new StringBuffer();
            String resultInstance;
//            String ret = "[";

            try {
                JSONObject jObject = new JSONObject(str);   // JSONArray 생성
                JSONObject rss = jObject.getJSONObject("rss");
                JSONArray channelArray = rss.getJSONArray("channel");
                JSONObject channelObject = channelArray.getJSONObject(0);
                JSONArray itemArray = channelObject.getJSONArray("item");
                for(int i=0; i < itemArray.length(); i++) {
                    JSONObject itemInstance = itemArray.getJSONObject(i);  // JSONObject 추출
                    String title = Html.fromHtml(itemInstance.getJSONArray("title").getString(0)).toString();
                    String link =  Html.fromHtml(itemInstance.getJSONArray("link").getString(0)).toString();
                    String description =  Html.fromHtml(itemInstance.getJSONArray("description").getString(0)).toString();
                    String bloggerName =  Html.fromHtml(itemInstance.getJSONArray("bloggername").getString(0)).toString();
                    String bloggerLink =  Html.fromHtml(itemInstance.getJSONArray("bloggerlink").getString(0)).toString();
                    String postDate =  Html.fromHtml(itemInstance.getJSONArray("postdate").getString(0)).toString();

                    resultInstance = "Title: " + title + "\n" +
                            "Link: " + link + "\n" +
                            "Description: " + description + "\n" +
                            "Blogger Name: " + bloggerName + "\n" +
                            "Blogger Link: " + bloggerLink + "\n" +
                            "Post Date: " + postDate + "\n";
                    addTextView(resultInstance);

//                    String jsonInstance = "{\"title\":" + title + "," +
//                                          "\"link\":" + link + "," +
//                            "\"description\":" + description + "," +
//                            "\"bloggerName\":" + bloggerName + "," +
//                            "\"bloggerLink\":" + bloggerLink + "," +
//                            "\"postDate\":" + postDate + "," + "}";

//                    ret = ret + jsonInstance;
//                    if(i < itemArray.length())
//                        ret = ret + ",";
                    sb.append(
                            "Title: " + title + "\n" +
                            "Link: " + link + "\n" +
                            "Description: " + description + "\n" +
                            "Blogger Name: " + bloggerName + "\n" +
                            "Blogger Link: " + bloggerLink + "\n" +
                            "Post Date: " + postDate + "\n"+ "\n"
                    );
                }
//                ret = ret + "]";


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return sb;
        }

        void addTextView(String s){
            innerContainer  = (LinearLayout) findViewById(R.id.InnerContainer);
            TextView tview = new TextView(BlogSearchActivity.this);
            LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            tview.setLayoutParams(lParams);
            tview.setBackgroundColor(Color.parseColor("#0099cc"));
            tview.setPadding(20, 20, 20, 20);
            tview.setTextColor(Color.parseColor("#000000"));
            tview.setTextSize(13);
            tview.setText(s);
            innerContainer.addView(tview);
        }

    }
}
