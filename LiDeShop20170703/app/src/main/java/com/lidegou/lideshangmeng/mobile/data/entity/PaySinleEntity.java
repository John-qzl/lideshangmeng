package com.lidegou.lideshangmeng.mobile.data.entity;

import java.util.List;

/**
 * Created by Y on 2017/2/3.
 */

public class PaySinleEntity {
    private String user_money;
    private String need_pay_money;
    private String id;
    private PayInfo payinfo;
    private List<PayMent> payment_list;

    public String getUser_money() {
        return user_money;
    }

    public void setUser_money(String user_money) {
        this.user_money = user_money;
    }

    public String getNeed_pay_money() {
        return need_pay_money;
    }

    public void setNeed_pay_money(String need_pay_money) {
        this.need_pay_money = need_pay_money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PayInfo getPayinfo() {
        return payinfo;
    }

    public void setPayinfo(PayInfo payinfo) {
        this.payinfo = payinfo;
    }

    public List<PayMent> getPayment_list() {
        return payment_list;
    }

    public void setPayment_list(List<PayMent> payment_list) {
        this.payment_list = payment_list;
    }

    public static class PayInfo {
        public String jine;
        public String total_zhifu;
        public String zhifumoney;
        public String zhifumoney_other;

        public String getJine() {
            return jine;
        }

        public void setJine(String jine) {
            this.jine = jine;
        }

        public String getTotal_zhifu() {
            return total_zhifu;
        }

        public void setTotal_zhifu(String total_zhifu) {
            this.total_zhifu = total_zhifu;
        }

        public String getZhifumoney() {
            return zhifumoney;
        }

        public void setZhifumoney(String zhifumoney) {
            this.zhifumoney = zhifumoney;
        }

        public String getZhifumoney_other() {
            return zhifumoney_other;
        }

        public void setZhifumoney_other(String zhifumoney_other) {
            this.zhifumoney_other = zhifumoney_other;
        }
    }

    public static class PayMent {
        private String pay_id;
        private String pay_code;
        private String pay_name;
        private String pay_fee;
        private String pay_desc;
        private String pay_config;
        private String is_cod;
        private String format_pay_fee;

        public String getPay_id() {
            return pay_id;
        }

        public void setPay_id(String pay_id) {
            this.pay_id = pay_id;
        }

        public String getPay_code() {
            return pay_code;
        }

        public void setPay_code(String pay_code) {
            this.pay_code = pay_code;
        }

        public String getPay_name() {
            return pay_name;
        }

        public void setPay_name(String pay_name) {
            this.pay_name = pay_name;
        }

        public String getPay_fee() {
            return pay_fee;
        }

        public void setPay_fee(String pay_fee) {
            this.pay_fee = pay_fee;
        }

        public String getPay_desc() {
            return pay_desc;
        }

        public void setPay_desc(String pay_desc) {
            this.pay_desc = pay_desc;
        }

        public String getPay_config() {
            return pay_config;
        }

        public void setPay_config(String pay_config) {
            this.pay_config = pay_config;
        }

        public String getIs_cod() {
            return is_cod;
        }

        public void setIs_cod(String is_cod) {
            this.is_cod = is_cod;
        }

        public String getFormat_pay_fee() {
            return format_pay_fee;
        }

        public void setFormat_pay_fee(String format_pay_fee) {
            this.format_pay_fee = format_pay_fee;
        }
    }
}
