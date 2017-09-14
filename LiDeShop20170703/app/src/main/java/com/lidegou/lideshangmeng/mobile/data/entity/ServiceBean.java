package com.lidegou.lideshangmeng.mobile.data.entity;

import java.util.List;

/**
 * Created by Y on 2017/1/24.
 */

public class ServiceBean {
    public List<Data> data;
    public InfoBean info;
    private int totalPage;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public static class Data {
        private String re_user_name;
        private String re_user_email;
        private String re_msg_time;
        private String re_msg_content;
        private String msg_content;
        private String msg_time;
        private String msg_type;
        private String msg_title;
        private String message_img;
        private String order_id;

        public String getRe_user_name() {
            return re_user_name;
        }

        public void setRe_user_name(String re_user_name) {
            this.re_user_name = re_user_name;
        }

        public String getRe_user_email() {
            return re_user_email;
        }

        public void setRe_user_email(String re_user_email) {
            this.re_user_email = re_user_email;
        }

        public String getRe_msg_time() {
            return re_msg_time;
        }

        public void setRe_msg_time(String re_msg_time) {
            this.re_msg_time = re_msg_time;
        }

        public String getRe_msg_content() {
            return re_msg_content;
        }

        public void setRe_msg_content(String re_msg_content) {
            this.re_msg_content = re_msg_content;
        }

        public String getMsg_content() {
            return msg_content;
        }

        public void setMsg_content(String msg_content) {
            this.msg_content = msg_content;
        }

        public String getMsg_time() {
            return msg_time;
        }

        public void setMsg_time(String msg_time) {
            this.msg_time = msg_time;
        }

        public String getMsg_type() {
            return msg_type;
        }

        public void setMsg_type(String msg_type) {
            this.msg_type = msg_type;
        }

        public String getMsg_title() {
            return msg_title;
        }

        public void setMsg_title(String msg_title) {
            this.msg_title = msg_title;
        }

        public String getMessage_img() {
            return message_img;
        }

        public void setMessage_img(String message_img) {
            this.message_img = message_img;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

    }

    public static class InfoBean {
        private String rank_name;
        private String username;
        private String shop_name;
        private String integral;
        private int is_validate;
        private String credit_line;
        private String formated_credit_line;
        private String user_picture;
        private String last_time;
        private String surplus;
        private String bonus;
        private String email;
        private String mobile_phone;
        private String user_money;
        private String bonus_money;
        private String sex;
        private String order_count;
        private List<Object> shipped_order;

        public String getRank_name() {
            return rank_name;
        }

        public void setRank_name(String rank_name) {
            this.rank_name = rank_name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public int getIs_validate() {
            return is_validate;
        }

        public void setIs_validate(int is_validate) {
            this.is_validate = is_validate;
        }

        public String getCredit_line() {
            return credit_line;
        }

        public void setCredit_line(String credit_line) {
            this.credit_line = credit_line;
        }

        public String getFormated_credit_line() {
            return formated_credit_line;
        }

        public void setFormated_credit_line(String formated_credit_line) {
            this.formated_credit_line = formated_credit_line;
        }

        public String getUser_picture() {
            return user_picture;
        }

        public void setUser_picture(String user_picture) {
            this.user_picture = user_picture;
        }

        public String getLast_time() {
            return last_time;
        }

        public void setLast_time(String last_time) {
            this.last_time = last_time;
        }

        public String getSurplus() {
            return surplus;
        }

        public void setSurplus(String surplus) {
            this.surplus = surplus;
        }

        public String getBonus() {
            return bonus;
        }

        public void setBonus(String bonus) {
            this.bonus = bonus;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile_phone() {
            return mobile_phone;
        }

        public void setMobile_phone(String mobile_phone) {
            this.mobile_phone = mobile_phone;
        }

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getBonus_money() {
            return bonus_money;
        }

        public void setBonus_money(String bonus_money) {
            this.bonus_money = bonus_money;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getOrder_count() {
            return order_count;
        }

        public void setOrder_count(String order_count) {
            this.order_count = order_count;
        }

        public List<Object> getShipped_order() {
            return shipped_order;
        }

        public void setShipped_order(List<Object> shipped_order) {
            this.shipped_order = shipped_order;
        }
    }
}
