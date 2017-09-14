package com.lidegou.lideshangmeng.mobile.data.entity;

import java.util.List;

/**
 * Created by Y on 2017/5/17.
 */

public class RecommendEntity {


    private int totalPage;
    private List<DataBean> data;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_id : 2316
         * user_name : 13864021893
         * xm : null
         * status : 会员
         * shopname : null
         */

        private String user_id;
        private String user_name;
        private Object xm;
        private String status;
        private Object shopname;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public Object getXm() {
            return xm;
        }

        public void setXm(Object xm) {
            this.xm = xm;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getShopname() {
            return shopname;
        }

        public void setShopname(Object shopname) {
            this.shopname = shopname;
        }
    }
}
