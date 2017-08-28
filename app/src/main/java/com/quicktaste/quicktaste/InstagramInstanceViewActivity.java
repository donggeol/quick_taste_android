package com.quicktaste.quicktaste;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class InstagramInstanceViewActivity extends AppCompatActivity {
    public static final String HASH_KEYWORD = "com.quicktaste.quicktaste.HASH_KEYWORD";
    private int count;
    private String link;
    private String tags[];
    private ImageView iv_instance;
    private LinearLayout ll_insta_instance_tagsContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_instance_view);


        getIntent().getIntExtra("count", count);
        link = getIntent().getStringExtra("link");
        tags = getIntent().getStringArrayExtra("tags");

        iv_instance = (ImageView) findViewById(R.id.iv_insta_instance) ;
        Glide.with(this).load(link).into(iv_instance);

        ll_insta_instance_tagsContainer = (LinearLayout) findViewById(R.id.ll_insta_instance_tagsContainer);

        TextView tv_tag;
        if(tags.length == 0){
            tv_tag = new TextView(this);
            tv_tag.setText("No Hash Tags found.. \nPlz Find another one.");
            ll_insta_instance_tagsContainer.addView(tv_tag);
        }
        for(int i = 0 ; i < tags.length; i++){
            tv_tag = new TextView(this);
            if(tags[i].length() > 20)
                tags[i] = tags[i].substring(0, 19) + "...";
            tv_tag.setText(tags[i]);
            tv_tag.setTextSize(20);
            tv_tag.setId(i);

            final String tag_search_keyword = tags[i].substring(1);
//            System.out.println("keyword : " + tag_search_keyword);

            tv_tag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //태그를 터치했을때 동작하는 곳
                    Bundle extras = new Bundle();

                    extras.putString(HASH_KEYWORD, tag_search_keyword);
                    Intent intent = new Intent(InstagramInstanceViewActivity.this, BlogSearchActivity.class);
                    intent.putExtras(extras);
                    InstagramInstanceViewActivity.this.startActivity(intent);

                    //새로운 앱에 화면 띄우기
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.example.kpark.qt_smart_viewer");
                    launchIntent.putExtras(extras);
                    startActivity(launchIntent);
                }
            });

            ll_insta_instance_tagsContainer.addView(tv_tag);

        }


    }
}
