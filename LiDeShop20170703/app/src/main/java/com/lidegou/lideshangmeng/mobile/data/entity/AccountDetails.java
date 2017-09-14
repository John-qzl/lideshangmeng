package com.lidegou.lideshangmeng.mobile.data.entity;

import java.util.List;

/**
 * Created by Y on 2017/1/22.
 */

public class AccountDetails {//账户明细
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
        private String log_id;
        private String user_id;
        private String user_money;
        private String frozen_money;
        private String rank_points;
        private String pay_points;
        private String change_time;
        private String change_desc;
        private String change_type;
        private String type;
        private String short_change_desc;
        private String amount;

        public String getLog_id() {
            return log_id;
        }

        public void setLog_id(String log_id) {
            this.log_id = log_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getFrozen_money() {
            return frozen_money;
        }

        public void setFrozen_money(String frozen_money) {
            this.frozen_money = frozen_money;
        }

        public String getRank_points() {
            return rank_points;
        }

        public void setRank_points(String rank_points) {
            this.rank_points = rank_points;
        }

        public String getPay_points() {
            return pay_points;
        }

        public void setPay_points(String pay_points) {
            this.pay_points = pay_points;
        }

        public String getChange_time() {
            return change_time;
        }

        public void setChange_time(String change_time) {
            this.change_time = change_time;
        }

        public String getChange_desc() {
            return change_desc;
        }

        public void setChange_desc(String change_desc) {
            this.change_desc = change_desc;
        }

        public String getChange_type() {
            return change_type;
        }

        public void setChange_type(String change_type) {
            this.change_type = change_type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getShort_change_desc() {
            return short_change_desc;
        }

        public void setShort_change_desc(String short_change_desc) {
            this.short_change_desc = short_change_desc;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

}