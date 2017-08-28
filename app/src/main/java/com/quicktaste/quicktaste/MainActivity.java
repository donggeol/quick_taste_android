package com.quicktaste.quicktaste;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

class BackPressCloseHandler {

    private long backKeyPressedTime = 0;
    private Toast toast;

    private Activity activity;

    public BackPressCloseHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.finish();
            toast.cancel();
        }
    }

    public void showGuide() {
        toast = Toast.makeText(activity,
                "Exit by clicking \'back\' button once more.", Toast.LENGTH_SHORT);
        toast.show();
    }
}

public class MainActivity extends AppCompatActivity {

    private BackPressCloseHandler backPressCloseHandler = null;
    public static final String KEYWORD = "com.quicktaste.quicktaste.KEYWORD";
    public static final String KEYWORD_INSTA = "com.quicktaste.quicktaste.KEYWORD_INSTA";
    private ImageView iv_icon, iv_blog_icon, iv_insta_icon;


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Displaying a cover screen.
        startActivity(new Intent(this, SplashActivity.class));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_icon = (ImageView) findViewById(R.id.icon) ;
        iv_insta_icon = (ImageView) findViewById(R.id.iv_insta_icon) ;

        //Glide library로 url에서 이미지 가져와 imageview에 보여주기
//        Glide.with(this).load("https://cdn0.iconfinder.com/data/icons/flat-round-system/512/qt-512.png").into(iv_icon);
        iv_icon.setImageResource(R.drawable.icon_main_bright);
        Glide.with(this).load("http://blogfiles6.naver.net/20160512_203/xxx09xxx_1462988823834vqn3h_JPEG/3059740-slide-s-2b-an-exclusive-look-at-instagrams-new-app-icon-copy-1.jpg").into(iv_insta_icon);

        //*********************************Search Blog From MainActivity***********************************************
//        iv_blog_icon = (ImageView) findViewById(R.id.iv_blog_icon) ;
//        Glide.with(this).load("http://cafefiles.naver.net/20111226_252/gkwl1226_1324882167488HbLTU_jpg/2011-12-26_15%3B46%3B52_gkwl1226.jpg").into(iv_blog_icon);
        //*********************************Search Blog From MainActivity***********************************************


        // closer handler.
        // It makes the app closed when a user press 'back' doubly.
        backPressCloseHandler = new BackPressCloseHandler(this);


        Button insta_search_button = (Button)findViewById(R.id.btn_insta_search);
        insta_search_button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        start_instagram_search();
                    }
                }
        );

        //*********************************Search Blog From MainActivity***********************************************
        // search button listener
//        Button search_button = (Button)findViewById(R.id.search_button);
//        search_button.setOnClickListener(
//            new Button.OnClickListener() {
//                public void onClick(View v) {
//                    //start_instagram_search();
//                    start_blog_search();
//                }
//            }
//        );
        //*********************************Search Blog From MainActivity***********************************************
    }

    void start_instagram_search() {
        Intent intent = new Intent(this, InstagramSearchActivity.class);
        EditText editText = (EditText) findViewById(R.id.et_insta_search);
        String message_insta = editText.getText().toString();
        intent.putExtra(KEYWORD_INSTA, message_insta);
        startActivity(intent);
    }

    //*********************************Search Blog From MainActivity***********************************************
//    void start_blog_search() {
//        Intent intent = new Intent(this, BlogSearchActivity.class);
//        EditText editText = (EditText) findViewById(R.id.keyword_to_search);
//        String message = editText.getText().toString();
//        intent.putExtra(KEYWORD, message);
//        startActivity(intent);
//    }
    //*********************************Search Blog From MainActivity***********************************************
}
