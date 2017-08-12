package com.quicktaste.quicktaste;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by K. Park on 2017-08-12.
 */

public class BlogListAdapter extends BaseAdapter {
    /* 아이템을 세트로 담기 위한 어레이 */
    private ArrayList<BlogListItem> mItems = new ArrayList<>();

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public BlogListItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Context context = parent.getContext();

        /* 'blog_listview_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.blog_listview_custom, parent, false);
        }

        /* 'blog_listview_custom'에 정의된 위젯에 대한 참조 획득 */
        ImageView iv_img = (ImageView) convertView.findViewById(R.id.iv_blog_img) ;
        TextView tv_post_date = (TextView) convertView.findViewById(R.id.tv_blog_post_date);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_blog_title) ;
        TextView tv_contents = (TextView) convertView.findViewById(R.id.tv_blog_contents) ;

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        final BlogListItem myItem = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        //iv_img.setImageDrawable(myItem.getIcon());
        Glide.with(context).load(myItem.getImgLink()).into(iv_img); //Glide library로 url에서 이미지 가져와 imageview에 보여주기

        tv_post_date.setText(myItem.getPost_date());
        tv_name.setText(myItem.getName());
        tv_contents.setText(myItem.getContents());

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */
        convertView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Bundle extras = new Bundle();
                extras.putString("title", myItem.getName());
                extras.putString("link", myItem.getLink());

                Intent intent = new Intent(context, Blog_webViewActivity.class);
                intent.putExtras(extras);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(String imgLink, String name, String contents, String post_date, String link) {

        BlogListItem mItem = new BlogListItem();

        /* MyItem에 아이템을 setting한다. */
        mItem.setImgLink(imgLink);
        mItem.setName(name);
        mItem.setContents(contents);
        mItem.setPost_date(post_date);
        mItem.setLink(link);

        /* mItems에 MyItem을 추가한다. */
        mItems.add(mItem);

    }
}
