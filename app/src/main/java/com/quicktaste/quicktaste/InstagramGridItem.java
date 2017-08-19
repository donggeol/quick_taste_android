package com.quicktaste.quicktaste;

/**
 * Created by K. Park on 2017-08-19.
 */

public class InstagramGridItem {
    private int count;
    private String link;
    private String tags[];

    public void setCount(int count){
        this.count = count;
    }
    public int getCount(){
        return count;
    }

    public void setLink(String link){
        this.link = link;
    }
    public String getLink(){
        return link;
    }

    public void setTag(String tags[]){
        this.tags = tags;
    }
    public String[] getTag(){
        return tags;
    }
}
