package com.quicktaste.quicktaste;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

        // closer handler.
        // It makes the app closed when a user press 'back' doubly.
        backPressCloseHandler = new BackPressCloseHandler(this);

        // search button listener
        Button search_button = (Button)findViewById(R.id.search_button);
        search_button.setOnClickListener(
            new Button.OnClickListener() {
                public void onClick(View v) {
                    //start_instagram_search();
                    start_blog_search();
                }
            }
        );
    }

    void start_instagram_search() {
        Intent intent = new Intent(this, InstagramSearchActivity.class);
        EditText editText = (EditText) findViewById(R.id.keyword_to_search);
        String message = editText.getText().toString();
        intent.putExtra(KEYWORD, message);
        startActivity(intent);
    }

    void start_blog_search() {
        Intent intent = new Intent(this, BlogSearchActivity.class);
        EditText editText = (EditText) findViewById(R.id.keyword_to_search);
        String message = editText.getText().toString();
        intent.putExtra(KEYWORD, message);
        startActivity(intent);
    }
}
