package com.lidegou.lideshangmeng.mobile.data.entity;

/**
 * 轮播图
 */
public class Banner {

    private int ad_id;//图片id
    private String position_id;
    private String media_type;
    private String ad_link;//跳转地址
    private String ad_code;
    private String ad_name;//图片名称
    private String ad_width;
    private String ad_height;
    private String position_style;
    private String rnd;
    private String src;//图片地址
    private String data;


    public int getAd_id() {
        return ad_id;
    }

    public void setAd_id(int ad_id) {
        this.ad_id = ad_id;
    }

    public String getPosition_id() {
        return position_id;
    }

    public void setPosition_id(String position_id) {
        this.position_id = position_id;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getAd_link() {
        return ad_link;
    }

    public void setAd_link(String ad_link) {
        this.ad_link = ad_link;
    }

    public String getAd_code() {
        return ad_code;
    }

    public void setAd_code(String ad_code) {
        this.ad_code = ad_code;
    }

    public String getAd_name() {
        return ad_name;
    }

    public void setAd_name(String ad_name) {
        this.ad_name = ad_name;
    }

    public String getAd_width() {
        return ad_width;
    }

    public void setAd_width(String ad_width) {
        this.ad_width = ad_width;
    }

    public String getAd_height() {
        return ad_height;
    }

    public void setAd_height(String ad_height) {
        this.ad_height = ad_height;
    }

    public String getPosition_style() {
        return position_style;
    }

    public void setPosition_style(String position_style) {
        this.position_style = position_style;
    }

    public String getRnd() {
        return rnd;
    }

    public void setRnd(String rnd) {
        this.rnd = rnd;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
