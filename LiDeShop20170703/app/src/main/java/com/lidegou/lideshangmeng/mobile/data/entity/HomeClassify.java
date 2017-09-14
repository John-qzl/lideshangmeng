package com.lidegou.lideshangmeng.mobile.data.entity;

/**
 * Created by Administrator on 2016/11/5.
 */

public class HomeClassify {

    private int id;
    private int icon;
    private String classify;
    private int code;

    public HomeClassify(int id, int icon, String classify, int code) {
        this.id = id;
        this.icon = icon;
        this.classify = classify;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
