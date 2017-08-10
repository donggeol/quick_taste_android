package com.quicktaste.quicktaste;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class BlogSearchActivity extends AppCompatActivity {

    private TextView result_tv;
    public int test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_search);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String keyword = intent.getStringExtra(MainActivity.KEYWORD);

        // Capture the layout's TextView and set the string as its text
        TextView result_tv = (TextView) findViewById(R.id.result);
        result_tv.setText("keyword : " + keyword);

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
            TextView result_tv = (TextView) findViewById(R.id.result);
            result_tv.setText(s);

        }
    }
}
