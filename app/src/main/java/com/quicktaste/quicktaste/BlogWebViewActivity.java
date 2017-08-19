package com.quicktaste.quicktaste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BlogWebViewActivity extends AppCompatActivity {

    private WebView mWebView;
    private String title;
    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_web_view);

        mWebView = (WebView)findViewById(R.id.wv_blog);
        mWebView.setWebViewClient(new WebViewClient());

        title = getIntent().getStringExtra("title");
        link = getIntent().getStringExtra("link");

        link = new StringBuilder(link).insert(7, "m.").toString();

        System.out.println("link : " + link);

        mWebView.loadUrl(link);

    }
}
