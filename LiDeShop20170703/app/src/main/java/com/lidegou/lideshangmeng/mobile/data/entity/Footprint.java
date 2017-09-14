package com.lidegou.lideshangmeng.mobile.data.entity;

/**
 * Created by Administrator on 2016/12/17.
 */

public class Footprint {
    private String goods_id;
    private String goods_name;
    private String short_name;
    private String goods_thumb;
    private String shop_price;
    private String url;

    public Footprint(String goods_thumb, String shop_price, String goods_name) {
        this.goods_thumb = goods_thumb;
        this.shop_price = shop_price;
        this.goods_name = goods_name;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getGoods_thumb() {
        return goods_thumb;
    }

    public void setGoods_thumb(String goods_thumb) {
        this.goods_thumb = goods_thumb;
    }

    public String getShop_price() {
        return shop_price;
    }

    public void setShop_price(String shop_price) {
        this.shop_price = shop_price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
