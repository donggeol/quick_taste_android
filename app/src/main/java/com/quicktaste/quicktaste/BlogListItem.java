package com.quicktaste.quicktaste;

import android.graphics.drawable.Drawable;

/**
 * Created by K. Park on 2017-08-12.
 */

public class BlogListItem {
    private Drawable icon;
    private String name;
    private String contents;
    private String post_date;
    private String link;

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
