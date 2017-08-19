package com.quicktaste.quicktaste;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class InstagramInstanceViewActivity extends AppCompatActivity {

    private int count;
    private String link;
    private String tags[];
    private ImageView iv_instance;
    private ConstraintLayout cl_insta_instance_tagsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_instance_view);


        getIntent().getIntExtra("count", count);
        link = getIntent().getStringExtra("link");
        tags = getIntent().getStringArrayExtra("tags");

        iv_instance = (ImageView) findViewById(R.id.iv_insta_instance) ;
        Glide.with(this).load(link).into(iv_instance);

        cl_insta_instance_tagsContainer = (ConstraintLayout) findViewById(R.id.cl_insta_instance_tagsContainer);

        TextView[] tv_tags = new TextView[tags.length];
        int lineTextCount = 0;
        int lineNum = 0;
        for(int i = 0 ; i < tags.length; i++){
            tv_tags[i] = new TextView(this);
            if(tags[i].length() > 15)
                tags[i] = tags[i].substring(0, 14) + "...";
            tv_tags[i].setText(tags[i]);
            tv_tags[i].setTextSize(20);
            tv_tags[i].setId(i);

            cl_insta_instance_tagsContainer.addView(tv_tags[i]);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(cl_insta_instance_tagsContainer);



            if(lineTextCount == 0){ // the starting part of the line
                constraintSet.connect(tv_tags[i].getId(), ConstraintSet.TOP, cl_insta_instance_tagsContainer.getId(), ConstraintSet.TOP, 10 + 15 * lineNum);
                constraintSet.connect(tv_tags[i].getId(), ConstraintSet.LEFT, cl_insta_instance_tagsContainer.getId(), ConstraintSet.LEFT, 10);
            }
            else if(lineTextCount > 0){// from 2end instance
                constraintSet.connect(tv_tags[i].getId(), ConstraintSet.TOP, tv_tags[i-1].getId(), ConstraintSet.TOP, 10);
                constraintSet.connect(tv_tags[i].getId(), ConstraintSet.LEFT, tv_tags[i-1].getId(), ConstraintSet.LEFT, 10);
            }
            else if(lineTextCount > 30) {
                lineTextCount = 0;
                lineNum++;
            }
            lineTextCount+=tags[i].length();

            constraintSet.applyTo(cl_insta_instance_tagsContainer);

        }


    }
}
