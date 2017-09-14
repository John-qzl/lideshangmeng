package com.lidegou.lideshangmeng.mobile.data.entity;

import java.util.List;

/**
 * Created by Y on 2017/5/18.
 */

public class PayText   {


    /**
     * payment_list : [{"pay_id":"9","pay_code":"balance","pay_name":"余额支付","pay_fee":"0","pay_desc":"使用帐户余额支付。只有会员才能使用，通过设置信用额度，可以透支。","pay_config":"a:0:{}","is_cod":"0","format_pay_fee":"0.00"},{"pay_id":"12","pay_code":"xingwang","pay_name":"通联付","pay_fee":"0","pay_desc":"通联支付\r\n\r\n    \u2022 一站式 连接实现跨银行、跨地区的实时支付。\r\n    \u2022 覆盖广 支持国内主要商业银行的银行卡。\r\n    \u2022 开放性 兼容已有网关银行的技术标准，使无网关的银行改造简单。\r\n    \u2022 个性化 度身设计支付结算方案，适用于各种电子商务支付业务；针对不同需求可采用不同的业务实现方式。\r\n    \u2022 全天候 支持7*24小时交易。\r\n","pay_config":"a:2:{i:0;a:3:{s:4:\"name\";s:16:\"xingwang_account\";s:4:\"type\";s:4:\"text\";s:5:\"value\";s:15:\"109374710913001\";}i:1;a:3:{s:4:\"name\";s:12:\"xingwang_key\";s:4:\"type\";s:4:\"text\";s:5:\"value\";s:10:\"1234567890\";}}","is_cod":"0","format_pay_fee":"0.00"},{"pay_id":"14","pay_code":"tonglianzhifubao","pay_name":"支付宝支付","pay_fee":"0","pay_desc":"支付宝支付","pay_config":"a:4:{i:0;a:3:{s:4:\"name\";s:24:\"tonglianzhifubao_account\";s:4:\"type\";s:4:\"text\";s:5:\"value\";s:15:\"169199048161360\";}i:1;a:3:{s:4:\"name\";s:20:\"tonglianzhifubao_key\";s:4:\"type\";s:4:\"text\";s:5:\"value\";s:8:\"lide~123\";}i:2;a:3:{s:4:\"name\";s:22:\"tonglianzhifubao_appid\";s:4:\"type\";s:4:\"text\";s:5:\"value\";s:8:\"00002153\";}i:3;a:3:{s:4:\"name\";s:20:\"tonglianzhifubao_url\";s:4:\"type\";s:4:\"text\";s:5:\"value\";s:45:\"https://vsp.allinpay.com/apiweb/unitorder/pay\";}}","is_cod":"0","format_pay_fee":"0.00"}]
     * shop_uid : null
     * user_money : 0.00
     */

    private Object shop_uid;
    private String user_money;
    private List<PaymentListBean> payment_list;

    public Object getShop_uid() {
        return shop_uid;
    }

    public void setShop_uid(Object shop_uid) {
        this.shop_uid = shop_uid;
    }

    public String getUser_money() {
        return user_money;
    }

    public void setUser_money(String user_money) {
        this.user_money = user_money;
    }

    public List<PaymentListBean> getPayment_list() {
        return payment_list;
    }

    public void setPayment_list(List<PaymentListBean> payment_list) {
        this.payment_list = payment_list;
    }

    public static class PaymentListBean {
        /**
         * pay_id : 9
         * pay_code : balance
         * pay_name : 余额支付
         * pay_fee : 0
         * pay_desc : 使用帐户余额支付。只有会员才能使用，通过设置信用额度，可以透支。
         * pay_config : a:0:{}
         * is_cod : 0
         * format_pay_fee : 0.00
         */

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
