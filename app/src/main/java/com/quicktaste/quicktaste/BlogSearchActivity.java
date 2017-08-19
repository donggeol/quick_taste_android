package com.quicktaste.quicktaste;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BlogSearchActivity extends AppCompatActivity {
    private LinearLayout innerContainer;
    private ListView mListView;
    private TextView searchKeyword;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_search);

        /* get the listview object */
        mListView = (ListView)findViewById(R.id.blog_search_listview);

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
//            TextView searchKeyword = (TextView) findViewById(R.id.keyword);
            //searchKeyword.setText(s);

            JSONParser_Parse(s);



        }

        void JSONParser_Parse(String str){
            BlogListAdapter mMyAdapter = new BlogListAdapter();

            try {
                JSONObject jObject = new JSONObject(str);
                JSONObject rss = jObject.getJSONObject("rss");
                JSONArray channelArray = rss.getJSONArray("channel");
                JSONObject channelObject = channelArray.getJSONObject(0);
                JSONArray itemArray = channelObject.getJSONArray("item");
                for(int i=0; i < itemArray.length(); i++) {
                    JSONObject itemInstance = itemArray.getJSONObject(i);  // JSONObject 추출

                    String imgLink = "https://www.seeklogo.net/wp-content/uploads/2015/07/android-vector-logo.png";

                    String title = Html.fromHtml(itemInstance.getJSONArray("title").getString(0)).toString();
                    String link = Html.fromHtml(itemInstance.getJSONArray("link").getString(0)).toString();
                    String description = Html.fromHtml(itemInstance.getJSONArray("description").getString(0)).toString();
                    String bloggerName = Html.fromHtml(itemInstance.getJSONArray("bloggername").getString(0)).toString();
                    String bloggerLink = Html.fromHtml(itemInstance.getJSONArray("bloggerlink").getString(0)).toString();
                    String postDate = Html.fromHtml(itemInstance.getJSONArray("postdate").getString(0)).toString();


                    //mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), title, description, bloggerName + "\n" + postDate, link);
                    mMyAdapter.addItem(imgLink, title, description, bloggerName + "\n" + postDate, link);
                }

                /* 리스트뷰에 어댑터 등록 */
                mListView.setAdapter(mMyAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

//        void addTextView(String s){
//            innerContainer  = (LinearLayout) findViewById(R.id.InnerContainer);
//            TextView tview = new TextView(BlogSearchActivity.this);
//            LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            tview.setLayoutParams(lParams);
//            tview.setBackgroundColor(Color.parseColor("#0099cc"));
//            tview.setPadding(20, 20, 20, 20);
//            tview.setTextColor(Color.parseColor("#000000"));
//            tview.setTextSize(13);
//            tview.setText(s);
//            innerContainer.addView(tview);
//        }


    }
}
