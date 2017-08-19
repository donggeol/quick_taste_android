package com.quicktaste.quicktaste;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by K. Park on 2017-08-19.
 */

public class InstagramGridAdapter extends BaseAdapter {
    /* 아이템을 세트로 담기 위한 어레이 */
    private ArrayList<InstagramGridItem> mItems = new ArrayList<>();

    @Override
    public int getCount() {
// TODO Auto-generated method stub
        return mItems.size();    //그리드뷰에 출력할 목록 수
    }

    @Override
    public InstagramGridItem getItem(int position) {
// TODO Auto-generated method stub
        return mItems.get(position);    //아이템을 호출할 때 사용하는 메소드
    }

    @Override
    public long getItemId(int position) {
// TODO Auto-generated method stub
        return position;    //아이템의 아이디를 구할 때 사용하는 메소드
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
// TODO Auto-generated method stub
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.instagram_view, parent, false);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_insta_img);
//        TextView textView = (TextView) convertView.findViewById(R.id.tv_insta_count);

        final InstagramGridItem myItem = getItem(position);

        Glide.with(context).load(myItem.getLink()).into(imageView);
//        textView.setText(myItem.getCount());


        //set layout size and padding dynamically with screen size
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int imgSize = width / 3;
        int imgMargin = imgSize / 20;
//        int height = displayMetrics.heightPixels;
//        System.out.println("width " + width + " height " + height);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(imgSize, imgSize);
        convertView.setLayoutParams(param);

        imageView.setPadding(imgMargin, imgMargin, imgMargin, imgMargin);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //이미지를 터치했을때 동작하는 곳
                Bundle extras = new Bundle();
                extras.putInt("count", myItem.getCount());
                extras.putString("link", myItem.getLink());
                extras.putStringArray("tags", myItem.getTag());

                Intent intent = new Intent(context, InstagramInstanceViewActivity.class);
                intent.putExtras(extras);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(int count, String tags[], String link) {

        InstagramGridItem mItem = new InstagramGridItem();

        /* MyItem에 아이템을 setting한다. */
        mItem.setCount(count);
        mItem.setTag(tags);
        mItem.setLink(link);

        /* mItems에 MyItem을 추가한다. */
        mItems.add(mItem);

    }
}


