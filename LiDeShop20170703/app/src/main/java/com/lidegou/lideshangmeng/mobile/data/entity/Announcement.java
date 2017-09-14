package com.lidegou.lideshangmeng.mobile.data.entity;

/**
 * Created by Administrator on 2016/12/16.
 */

public class Announcement {

    private String id;
    private String title;
    private String short_title;
    private String cat_name;
    private String add_time;
    private String url;
    private String cat_url;

    public Announcement(String title, String short_title) {
        this.title = title;
        this.short_title = short_title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_title() {
        return short_title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCat_url() {
        return cat_url;
    }

    public void setCat_url(String cat_url) {
        this.cat_url = cat_url;
    }

    @Override
    public String toString() {
        return title;
    }
}
