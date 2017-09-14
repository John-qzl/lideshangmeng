package com.lidegou.lideshangmeng.mobile.data.entity;

/**
 * Created by Administrator on 2016/12/14.
 */

public class Message {

    private int id;
    private String title;
    private String message;
    private String time;
    private String messImg;

    public Message(int id, String title, String message, String time, String messImg) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.time = time;
        this.messImg = messImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessImg() {
        return messImg;
    }

    public void setMessImg(String messImg) {
        this.messImg = messImg;
    }
}
