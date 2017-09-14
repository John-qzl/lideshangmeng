package com.lidegou.lideshangmeng.mobile.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/2.
 */

public class MessageOrderntity implements Serializable {
    private List<Data> data;
    private int totalPage;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public static class Data implements Serializable {
        private String id;
        private String text;
        private String time;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
