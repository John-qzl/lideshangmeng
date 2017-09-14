package com.lidegou.lideshangmeng.mobile.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public class Order implements Serializable {

    /**
     * data : [{"order_id":"105","order_sn":"2016111266031","order_time":"2016-11-12 15:06:05","order_status":"已确认,已付款,未发货","status":"","status_number":"","consignee":"郑重","main_order_id":"0","user_name":"利得超市","order_goods":[{"goods_id":"835","goods_name":"测试产品","goods_number":"1","extension_code":"","goods_price":"0.01","goods_thumb":"http://www.lidegou.com/app/themes/default/img/no_image.png","url":"/app/index.php?r=goods&id=835"}],"order_goods_num":1,"order_child":0,"no_picture":"./images/errorImg.png","delete_yes":0,"invoice_no":"","shipping_name":"顺丰速运","email":"","address_detail":"内蒙古 通辽市 科尔沁区","address":"万力城","tel":"","delivery_time":"2017-01-10 11:37:24","order_count":0,"kf_type":"0","kf_ww":"小黄人","kf_qq":"2653927629","total_fee":"0.01","handler_return":"","pay_status":"2","handler":"已确认","type":"3","btn_url":""}]
     * totalPage : 1
     */

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

    public static class DataBean implements Serializable {
        /**
         * order_id : 105
         * order_sn : 2016111266031
         * order_time : 2016-11-12 15:06:05
         * order_status : 已确认,已付款,未发货
         * status :
         * status_number :
         * consignee : 郑重
         * main_order_id : 0
         * user_name : 利得超市
         * order_goods : [{"goods_id":"835","goods_name":"测试产品","goods_number":"1","extension_code":"","goods_price":"0.01","goods_thumb":"http://www.lidegou.com/app/themes/default/img/no_image.png","url":"/app/index.php?r=goods&id=835"}]
         * order_goods_num : 1
         * order_child : 0
         * no_picture : ./images/errorImg.png
         * delete_yes : 0
         * invoice_no :
         * shipping_name : 顺丰速运
         * email :
         * address_detail : 内蒙古 通辽市 科尔沁区
         * address : 万力城
         * tel :
         * delivery_time : 2017-01-10 11:37:24
         * order_count : 0
         * kf_type : 0
         * kf_ww : 小黄人
         * kf_qq : 2653927629
         * total_fee : 0.01
         * handler_return :
         * pay_status : 2
         * handler : 已确认
         * type : 3
         * btn_url :
         */
        private String pay_id;
        private String order_id;
        private String order_sn;
        private String order_time;
        private String order_status;
        private String status;
        private String status_number;
        private String consignee;
        private String main_order_id;
        private String user_name;
        private int order_goods_num;
        private int order_child;
        private String no_picture;
        private int delete_yes;
        private String invoice_no;
        private String shipping_name;
        private String email;
        private String address_detail;
        private String address;
        private String tel;
        private String delivery_time;
        private int order_count;
        private String kf_type;
        private String kf_ww;
        private String kf_qq;
        private String total_fee;
        private String handler_return;
        private String pay_status;
        private String handler;
        private String type;
        private String btn_url;
        private List<OrderGoodsBean> order_goods;

        public String getPay_id() {
            return pay_id;
        }

        public void setPay_id(String pay_id) {
            this.pay_id = pay_id;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getOrder_time() {
            return order_time;
        }

        public void setOrder_time(String order_time) {
            this.order_time = order_time;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatus_number() {
            return status_number;
        }

        public void setStatus_number(String status_number) {
            this.status_number = status_number;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getMain_order_id() {
            return main_order_id;
        }

        public void setMain_order_id(String main_order_id) {
            this.main_order_id = main_order_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getOrder_goods_num() {
            return order_goods_num;
        }

        public void setOrder_goods_num(int order_goods_num) {
            this.order_goods_num = order_goods_num;
        }

        public int getOrder_child() {
            return order_child;
        }

        public void setOrder_child(int order_child) {
            this.order_child = order_child;
        }

        public String getNo_picture() {
            return no_picture;
        }

        public void setNo_picture(String no_picture) {
            this.no_picture = no_picture;
        }

        public int getDelete_yes() {
            return delete_yes;
        }

        public void setDelete_yes(int delete_yes) {
            this.delete_yes = delete_yes;
        }

        public String getInvoice_no() {
            return invoice_no;
        }

        public void setInvoice_no(String invoice_no) {
            this.invoice_no = invoice_no;
        }

        public String getShipping_name() {
            return shipping_name;
        }

        public void setShipping_name(String shipping_name) {
            this.shipping_name = shipping_name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress_detail() {
            return address_detail;
        }

        public void setAddress_detail(String address_detail) {
            this.address_detail = address_detail;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getDelivery_time() {
            return delivery_time;
        }

        public void setDelivery_time(String delivery_time) {
            this.delivery_time = delivery_time;
        }

        public int getOrder_count() {
            return order_count;
        }

        public void setOrder_count(int order_count) {
            this.order_count = order_count;
        }

        public String getKf_type() {
            return kf_type;
        }

        public void setKf_type(String kf_type) {
            this.kf_type = kf_type;
        }

        public String getKf_ww() {
            return kf_ww;
        }

        public void setKf_ww(String kf_ww) {
            this.kf_ww = kf_ww;
        }

        public String getKf_qq() {
            return kf_qq;
        }

        public void setKf_qq(String kf_qq) {
            this.kf_qq = kf_qq;
        }

        public String getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(String total_fee) {
            this.total_fee = total_fee;
        }

        public String getHandler_return() {
            return handler_return;
        }

        public void setHandler_return(String handler_return) {
            this.handler_return = handler_return;
        }

        public String getPay_status() {
            return pay_status;
        }

        public void setPay_status(String pay_status) {
            this.pay_status = pay_status;
        }

        public String getHandler() {
            return handler;
        }

        public void setHandler(String handler) {
            this.handler = handler;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBtn_url() {
            return btn_url;
        }

        public void setBtn_url(String btn_url) {
            this.btn_url = btn_url;
        }

        public List<OrderGoodsBean> getOrder_goods() {
            return order_goods;
        }

        public void setOrder_goods(List<OrderGoodsBean> order_goods) {
            this.order_goods = order_goods;
        }

        public static class OrderGoodsBean implements Serializable {
            /**
             * goods_id : 835
             * goods_name : 测试产品
             * goods_number : 1
             * extension_code :
             * goods_price : 0.01
             * goods_thumb : http://www.lidegou.com/app/themes/default/img/no_image.png
             * url : /app/index.php?r=goods&id=835
             */

            private String goods_id;
            private String goods_name;
            private String goods_number;
            private String extension_code;
            private String goods_price;
            private String goods_thumb;
            private String url;

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

            public String getGoods_number() {
                return goods_number;
            }

            public void setGoods_number(String goods_number) {
                this.goods_number = goods_number;
            }

            public String getExtension_code() {
                return extension_code;
            }

            public void setExtension_code(String extension_code) {
                this.extension_code = extension_code;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getGoods_thumb() {
                return goods_thumb;
            }

            public void setGoods_thumb(String goods_thumb) {
                this.goods_thumb = goods_thumb;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }


    /**
     * 订单详情
     */
    public static class OrderDetail {

        /**
         * order : {"order_id":"105","main_order_id":"0","order_sn":"2016111266031","user_id":"1221","order_status":"1","shipping_status":"未发货","pay_status":"已付款","consignee":"郑重","country":"1","province":"19","city":"265","district":"2223","address":"万力城","zipcode":"","tel":"","mobile":"15560751512","email":"","best_time":"","sign_building":"","postscript":"","shipping_id":"16","shipping_name":"顺丰速运","shipping_code":"","shipping_type":"","pay_id":"13","pay_name":"微信支付","how_oos":"等待所有商品备齐后再发","how_surplus":"","pack_name":"","card_name":"","card_message":"","inv_payee":"个人","inv_content":"不开发票","goods_amount":"0.01","shipping_fee":"0.00","insure_fee":"0.00","pay_fee":"0.00","pack_fee":"0.00","card_fee":"0.00","money_paid":"0.01","surplus":"0.00","integral":"0","integral_money":"0.00","bonus":"0.00","order_amount":"0.00","from_ad":"0","referer":"本站","add_time":"2016-11-12 15:06:05","confirm_time":"","pay_time":"","shipping_time":"","auto_delivery_time":"15","pack_id":"0","card_id":"0","bonus_id":"0","invoice_no":"","extension_code":"","extension_id":"0","to_buyer":"","pay_note":"","agency_id":"14","inv_type":"","tax":"0.00","is_separate":"0","parent_id":"0","discount":"0.00","discount_all":"0.00","is_delete":"0","is_settlement":"0","sign_time":"","is_single":"0","point_id":"0","shipping_dateStr":"","total_fee":"0.01","formated_goods_amount":"0.01","formated_discount":"0.00","formated_tax":"0.00","formated_shipping_fee":"0.00","formated_insure_fee":"0.00","formated_pay_fee":"0.00","formated_pack_fee":"0.00","formated_card_fee":"0.00","formated_total_fee":"0.01","formated_money_paid":"0.01","formated_bonus":"0.00","formated_integral_money":"0.00","formated_surplus":"0.00","formated_order_amount":"0.00","formated_add_time":"2016-11-12 15:06:05","allow_update_address":0,"exist_real_goods":true,"pay_online":"","how_oos_name":"等待所有商品备齐后再发","how_surplus_name":"","shop_name":"利得超市","shopUrl":"/app/index.php?r=store&id%5Bru_id%5D=0","handler":"已确认","type":"3","btn_url":"","status":"已确认","c":{"region_id":"1","region_name":"中国"},"detail_address":"中国内蒙古通辽科尔沁区万力城","p":{"region_id":"19","region_name":"内蒙古"},"cc":{"region_id":"265","region_name":"通辽"},"dd":{"region_id":"2223","region_name":"科尔沁区"},"point":[]}
         * goods_list : [{"rec_id":"106","goods_id":"835","ru_id":"0","goods_name":"测试产品","goods_sn":"LDG000835","market_price":"0.01","goods_number":"1","warehouse_id":"1","goods_price":"0.01","goods_attr":"","is_real":"1","parent_id":"0","is_gift":"0","subtotal":"0.01","extension_code":"","shop_price":"0.01","goods_img":"http://www.lidegou.com/app/themes/default/img/no_image.png","goods_thumb":"http://www.lidegou.com/app/themes/default/img/no_image.png","warehouse_name":"北京仓库","goods_amount":0.01,"amount":"0.01","dis_amount":0,"discount_amount":"0.00","url":"/app/index.php?r=goods&id=835","shop_name":"利得超市","shopUrl":"/app/index.php?r=store&id=0"}]
         */

        private OrderBean order;
        private List<GoodsListBean> goods_list;

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public class OrderBean {
            /**
             * order_id : 105
             * main_order_id : 0
             * order_sn : 2016111266031
             * user_id : 1221
             * order_status : 1
             * shipping_status : 未发货
             * pay_status : 已付款
             * consignee : 郑重
             * country : 1
             * province : 19
             * city : 265
             * district : 2223
             * address : 万力城
             * zipcode :
             * tel :
             * mobile : 15560751512
             * email :
             * best_time :
             * sign_building :
             * postscript :
             * shipping_id : 16
             * shipping_name : 顺丰速运
             * shipping_code :
             * shipping_type :
             * pay_id : 13
             * pay_name : 微信支付
             * how_oos : 等待所有商品备齐后再发
             * how_surplus :
             * pack_name :
             * card_name :
             * card_message :
             * inv_payee : 个人
             * inv_content : 不开发票
             * goods_amount : 0.01
             * shipping_fee : 0.00
             * insure_fee : 0.00
             * pay_fee : 0.00
             * pack_fee : 0.00
             * card_fee : 0.00
             * money_paid : 0.01
             * surplus : 0.00
             * integral : 0
             * integral_money : 0.00
             * bonus : 0.00
             * order_amount : 0.00
             * from_ad : 0
             * referer : 本站
             * add_time : 2016-11-12 15:06:05
             * confirm_time :
             * pay_time :
             * shipping_time :
             * auto_delivery_time : 15
             * pack_id : 0
             * card_id : 0
             * bonus_id : 0
             * invoice_no :
             * extension_code :
             * extension_id : 0
             * to_buyer :
             * pay_note :
             * agency_id : 14
             * inv_type :
             * tax : 0.00
             * is_separate : 0
             * parent_id : 0
             * discount : 0.00
             * discount_all : 0.00
             * is_delete : 0
             * is_settlement : 0
             * sign_time :
             * is_single : 0
             * point_id : 0
             * shipping_dateStr :
             * total_fee : 0.01
             * formated_goods_amount : 0.01
             * formated_discount : 0.00
             * formated_tax : 0.00
             * formated_shipping_fee : 0.00
             * formated_insure_fee : 0.00
             * formated_pay_fee : 0.00
             * formated_pack_fee : 0.00
             * formated_card_fee : 0.00
             * formated_total_fee : 0.01
             * formated_money_paid : 0.01
             * formated_bonus : 0.00
             * formated_integral_money : 0.00
             * formated_surplus : 0.00
             * formated_order_amount : 0.00
             * formated_add_time : 2016-11-12 15:06:05
             * allow_update_address : 0
             * exist_real_goods : true
             * pay_online :
             * how_oos_name : 等待所有商品备齐后再发
             * how_surplus_name :
             * shop_name : 利得超市
             * shopUrl : /app/index.php?r=store&id%5Bru_id%5D=0
             * handler : 已确认
             * type : 3
             * btn_url :
             * status : 已确认
             * c : {"region_id":"1","region_name":"中国"}
             * detail_address : 中国内蒙古通辽科尔沁区万力城
             * p : {"region_id":"19","region_name":"内蒙古"}
             * cc : {"region_id":"265","region_name":"通辽"}
             * dd : {"region_id":"2223","region_name":"科尔沁区"}
             * point : []
             */

            private String order_id;
            private String main_order_id;
            private String order_sn;
            private String user_id;
            private String order_status;
            private String shipping_status;
            private String pay_status;
            private String consignee;
            private String country;
            private String province;
            private String city;
            private String district;
            private String address;
            private String zipcode;
            private String tel;
            private String mobile;
            private String email;
            private String best_time;
            private String sign_building;
            private String postscript;
            private String shipping_id;
            private String shipping_name;
            private String shipping_code;
            private String shipping_type;
            private String pay_id;
            private String pay_name;
            private String how_oos;
            private String how_surplus;
            private String pack_name;
            private String card_name;
            private String card_message;
            private String inv_payee;
            private String inv_content;
            private String goods_amount;
            private String shipping_fee;
            private String insure_fee;
            private String pay_fee;
            private String pack_fee;
            private String card_fee;
            private String money_paid;
            private String surplus;
            private String integral;
            private String integral_money;
            private String bonus;
            private String order_amount;
            private String from_ad;
            private String referer;
            private String add_time;
            private String confirm_time;
            private String pay_time;
            private String shipping_time;
            private String auto_delivery_time;
            private String pack_id;
            private String card_id;
            private String bonus_id;
            private String invoice_no;
            private String extension_code;
            private String extension_id;
            private String to_buyer;
            private String pay_note;
            private String agency_id;
            private String inv_type;
            private String tax;
            private String is_separate;
            private String parent_id;
            private String discount;
            private String discount_all;
            private String is_delete;
            private String is_settlement;
            private String sign_time;
            private String is_single;
            private String point_id;
            private String shipping_dateStr;
            private String total_fee;
            private String formated_goods_amount;
            private String formated_discount;
            private String formated_tax;
            private String formated_shipping_fee;
            private String formated_insure_fee;
            private String formated_pay_fee;
            private String formated_pack_fee;
            private String formated_card_fee;
            private String formated_total_fee;
            private String formated_money_paid;
            private String formated_bonus;
            private String formated_integral_money;
            private String formated_surplus;
            private String formated_order_amount;
            private String formated_add_time;
            private int allow_update_address;
            private boolean exist_real_goods;
            private String pay_online;
            private String how_oos_name;
            private String how_surplus_name;
            private String shop_name;
            private String shopUrl;
            private String handler;
            private String type;
            private String btn_url;
            private String status;
            private CBean c;
            private String detail_address;
            private PBean p;
            private CcBean cc;
            private DdBean dd;
            private List<?> point;

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getMain_order_id() {
                return main_order_id;
            }

            public void setMain_order_id(String main_order_id) {
                this.main_order_id = main_order_id;
            }

            public String getOrder_sn() {
                return order_sn;
            }

            public void setOrder_sn(String order_sn) {
                this.order_sn = order_sn;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getOrder_status() {
                return order_status;
            }

            public void setOrder_status(String order_status) {
                this.order_status = order_status;
            }

            public String getShipping_status() {
                return shipping_status;
            }

            public void setShipping_status(String shipping_status) {
                this.shipping_status = shipping_status;
            }

            public String getPay_status() {
                return pay_status;
            }

            public void setPay_status(String pay_status) {
                this.pay_status = pay_status;
            }

            public String getConsignee() {
                return consignee;
            }

            public void setConsignee(String consignee) {
                this.consignee = consignee;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getZipcode() {
                return zipcode;
            }

            public void setZipcode(String zipcode) {
                this.zipcode = zipcode;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getBest_time() {
                return best_time;
            }

            public void setBest_time(String best_time) {
                this.best_time = best_time;
            }

            public String getSign_building() {
                return sign_building;
            }

            public void setSign_building(String sign_building) {
                this.sign_building = sign_building;
            }

            public String getPostscript() {
                return postscript;
            }

            public void setPostscript(String postscript) {
                this.postscript = postscript;
            }

            public String getShipping_id() {
                return shipping_id;
            }

            public void setShipping_id(String shipping_id) {
                this.shipping_id = shipping_id;
            }

            public String getShipping_name() {
                return shipping_name;
            }

            public void setShipping_name(String shipping_name) {
                this.shipping_name = shipping_name;
            }

            public String getShipping_code() {
                return shipping_code;
            }

            public void setShipping_code(String shipping_code) {
                this.shipping_code = shipping_code;
            }

            public String getShipping_type() {
                return shipping_type;
            }

            public void setShipping_type(String shipping_type) {
                this.shipping_type = shipping_type;
            }

            public String getPay_id() {
                return pay_id;
            }

            public void setPay_id(String pay_id) {
                this.pay_id = pay_id;
            }

            public String getPay_name() {
                return pay_name;
            }

            public void setPay_name(String pay_name) {
                this.pay_name = pay_name;
            }

            public String getHow_oos() {
                return how_oos;
            }

            public void setHow_oos(String how_oos) {
                this.how_oos = how_oos;
            }

            public String getHow_surplus() {
                return how_surplus;
            }

            public void setHow_surplus(String how_surplus) {
                this.how_surplus = how_surplus;
            }

            public String getPack_name() {
                return pack_name;
            }

            public void setPack_name(String pack_name) {
                this.pack_name = pack_name;
            }

            public String getCard_name() {
                return card_name;
            }

            public void setCard_name(String card_name) {
                this.card_name = card_name;
            }

            public String getCard_message() {
                return card_message;
            }

            public void setCard_message(String card_message) {
                this.card_message = card_message;
            }

            public String getInv_payee() {
                return inv_payee;
            }

            public void setInv_payee(String inv_payee) {
                this.inv_payee = inv_payee;
            }

            public String getInv_content() {
                return inv_content;
            }

            public void setInv_content(String inv_content) {
                this.inv_content = inv_content;
            }

            public String getGoods_amount() {
                return goods_amount;
            }

            public void setGoods_amount(String goods_amount) {
                this.goods_amount = goods_amount;
            }

            public String getShipping_fee() {
                return shipping_fee;
            }

            public void setShipping_fee(String shipping_fee) {
                this.shipping_fee = shipping_fee;
            }

            public String getInsure_fee() {
                return insure_fee;
            }

            public void setInsure_fee(String insure_fee) {
                this.insure_fee = insure_fee;
            }

            public String getPay_fee() {
                return pay_fee;
            }

            public void setPay_fee(String pay_fee) {
                this.pay_fee = pay_fee;
            }

            public String getPack_fee() {
                return pack_fee;
            }

            public void setPack_fee(String pack_fee) {
                this.pack_fee = pack_fee;
            }

            public String getCard_fee() {
                return card_fee;
            }

            public void setCard_fee(String card_fee) {
                this.card_fee = card_fee;
            }

            public String getMoney_paid() {
                return money_paid;
            }

            public void setMoney_paid(String money_paid) {
                this.money_paid = money_paid;
            }

            public String getSurplus() {
                return surplus;
            }

            public void setSurplus(String surplus) {
                this.surplus = surplus;
            }

            public String getIntegral() {
                return integral;
            }

            public void setIntegral(String integral) {
                this.integral = integral;
            }

            public String getIntegral_money() {
                return integral_money;
            }

            public void setIntegral_money(String integral_money) {
                this.integral_money = integral_money;
            }

            public String getBonus() {
                return bonus;
            }

            public void setBonus(String bonus) {
                this.bonus = bonus;
            }

            public String getOrder_amount() {
                return order_amount;
            }

            public void setOrder_amount(String order_amount) {
                this.order_amount = order_amount;
            }

            public String getFrom_ad() {
                return from_ad;
            }

            public void setFrom_ad(String from_ad) {
                this.from_ad = from_ad;
            }

            public String getReferer() {
                return referer;
            }

            public void setReferer(String referer) {
                this.referer = referer;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getConfirm_time() {
                return confirm_time;
            }

            public void setConfirm_time(String confirm_time) {
                this.confirm_time = confirm_time;
            }

            public String getPay_time() {
                return pay_time;
            }

            public void setPay_time(String pay_time) {
                this.pay_time = pay_time;
            }

            public String getShipping_time() {
                return shipping_time;
            }

            public void setShipping_time(String shipping_time) {
                this.shipping_time = shipping_time;
            }

            public String getAuto_delivery_time() {
                return auto_delivery_time;
            }

            public void setAuto_delivery_time(String auto_delivery_time) {
                this.auto_delivery_time = auto_delivery_time;
            }

            public String getPack_id() {
                return pack_id;
            }

            public void setPack_id(String pack_id) {
                this.pack_id = pack_id;
            }

            public String getCard_id() {
                return card_id;
            }

            public void setCard_id(String card_id) {
                this.card_id = card_id;
            }

            public String getBonus_id() {
                return bonus_id;
            }

            public void setBonus_id(String bonus_id) {
                this.bonus_id = bonus_id;
            }

            public String getInvoice_no() {
                return invoice_no;
            }

            public void setInvoice_no(String invoice_no) {
                this.invoice_no = invoice_no;
            }

            public String getExtension_code() {
                return extension_code;
            }

            public void setExtension_code(String extension_code) {
                this.extension_code = extension_code;
            }

            public String getExtension_id() {
                return extension_id;
            }

            public void setExtension_id(String extension_id) {
                this.extension_id = extension_id;
            }

            public String getTo_buyer() {
                return to_buyer;
            }

            public void setTo_buyer(String to_buyer) {
                this.to_buyer = to_buyer;
            }

            public String getPay_note() {
                return pay_note;
            }

            public void setPay_note(String pay_note) {
                this.pay_note = pay_note;
            }

            public String getAgency_id() {
                return agency_id;
            }

            public void setAgency_id(String agency_id) {
                this.agency_id = agency_id;
            }

            public String getInv_type() {
                return inv_type;
            }

            public void setInv_type(String inv_type) {
                this.inv_type = inv_type;
            }

            public String getTax() {
                return tax;
            }

            public void setTax(String tax) {
                this.tax = tax;
            }

            public String getIs_separate() {
                return is_separate;
            }

            public void setIs_separate(String is_separate) {
                this.is_separate = is_separate;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public String getDiscount_all() {
                return discount_all;
            }

            public void setDiscount_all(String discount_all) {
                this.discount_all = discount_all;
            }

            public String getIs_delete() {
                return is_delete;
            }

            public void setIs_delete(String is_delete) {
                this.is_delete = is_delete;
            }

            public String getIs_settlement() {
                return is_settlement;
            }

            public void setIs_settlement(String is_settlement) {
                this.is_settlement = is_settlement;
            }

            public String getSign_time() {
                return sign_time;
            }

            public void setSign_time(String sign_time) {
                this.sign_time = sign_time;
            }

            public String getIs_single() {
                return is_single;
            }

            public void setIs_single(String is_single) {
                this.is_single = is_single;
            }

            public String getPoint_id() {
                return point_id;
            }

            public void setPoint_id(String point_id) {
                this.point_id = point_id;
            }

            public String getShipping_dateStr() {
                return shipping_dateStr;
            }

            public void setShipping_dateStr(String shipping_dateStr) {
                this.shipping_dateStr = shipping_dateStr;
            }

            public String getTotal_fee() {
                return total_fee;
            }

            public void setTotal_fee(String total_fee) {
                this.total_fee = total_fee;
            }

            public String getFormated_goods_amount() {
                return formated_goods_amount;
            }

            public void setFormated_goods_amount(String formated_goods_amount) {
                this.formated_goods_amount = formated_goods_amount;
            }

            public String getFormated_discount() {
                return formated_discount;
            }

            public void setFormated_discount(String formated_discount) {
                this.formated_discount = formated_discount;
            }

            public String getFormated_tax() {
                return formated_tax;
            }

            public void setFormated_tax(String formated_tax) {
                this.formated_tax = formated_tax;
            }

            public String getFormated_shipping_fee() {
                return formated_shipping_fee;
            }

            public void setFormated_shipping_fee(String formated_shipping_fee) {
                this.formated_shipping_fee = formated_shipping_fee;
            }

            public String getFormated_insure_fee() {
                return formated_insure_fee;
            }

            public void setFormated_insure_fee(String formated_insure_fee) {
                this.formated_insure_fee = formated_insure_fee;
            }

            public String getFormated_pay_fee() {
                return formated_pay_fee;
            }

            public void setFormated_pay_fee(String formated_pay_fee) {
                this.formated_pay_fee = formated_pay_fee;
            }

            public String getFormated_pack_fee() {
                return formated_pack_fee;
            }

            public void setFormated_pack_fee(String formated_pack_fee) {
                this.formated_pack_fee = formated_pack_fee;
            }

            public String getFormated_card_fee() {
                return formated_card_fee;
            }

            public void setFormated_card_fee(String formated_card_fee) {
                this.formated_card_fee = formated_card_fee;
            }

            public String getFormated_total_fee() {
                return formated_total_fee;
            }

            public void setFormated_total_fee(String formated_total_fee) {
                this.formated_total_fee = formated_total_fee;
            }

            public String getFormated_money_paid() {
                return formated_money_paid;
            }

            public void setFormated_money_paid(String formated_money_paid) {
                this.formated_money_paid = formated_money_paid;
            }

            public String getFormated_bonus() {
                return formated_bonus;
            }

            public void setFormated_bonus(String formated_bonus) {
                this.formated_bonus = formated_bonus;
            }

            public String getFormated_integral_money() {
                return formated_integral_money;
            }

            public void setFormated_integral_money(String formated_integral_money) {
                this.formated_integral_money = formated_integral_money;
            }

            public String getFormated_surplus() {
                return formated_surplus;
            }

            public void setFormated_surplus(String formated_surplus) {
                this.formated_surplus = formated_surplus;
            }

            public String getFormated_order_amount() {
                return formated_order_amount;
            }

            public void setFormated_order_amount(String formated_order_amount) {
                this.formated_order_amount = formated_order_amount;
            }

            public String getFormated_add_time() {
                return formated_add_time;
            }

            public void setFormated_add_time(String formated_add_time) {
                this.formated_add_time = formated_add_time;
            }

            public int getAllow_update_address() {
                return allow_update_address;
            }

            public void setAllow_update_address(int allow_update_address) {
                this.allow_update_address = allow_update_address;
            }

            public boolean isExist_real_goods() {
                return exist_real_goods;
            }

            public void setExist_real_goods(boolean exist_real_goods) {
                this.exist_real_goods = exist_real_goods;
            }

            public String getPay_online() {
                return pay_online;
            }

            public void setPay_online(String pay_online) {
                this.pay_online = pay_online;
            }

            public String getHow_oos_name() {
                return how_oos_name;
            }

            public void setHow_oos_name(String how_oos_name) {
                this.how_oos_name = how_oos_name;
            }

            public String getHow_surplus_name() {
                return how_surplus_name;
            }

            public void setHow_surplus_name(String how_surplus_name) {
                this.how_surplus_name = how_surplus_name;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public String getShopUrl() {
                return shopUrl;
            }

            public void setShopUrl(String shopUrl) {
                this.shopUrl = shopUrl;
            }

            public String getHandler() {
                return handler;
            }

            public void setHandler(String handler) {
                this.handler = handler;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getBtn_url() {
                return btn_url;
            }

            public void setBtn_url(String btn_url) {
                this.btn_url = btn_url;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public CBean getC() {
                return c;
            }

            public void setC(CBean c) {
                this.c = c;
            }

            public String getDetail_address() {
                return detail_address;
            }

            public void setDetail_address(String detail_address) {
                this.detail_address = detail_address;
            }

            public PBean getP() {
                return p;
            }

            public void setP(PBean p) {
                this.p = p;
            }

            public CcBean getCc() {
                return cc;
            }

            public void setCc(CcBean cc) {
                this.cc = cc;
            }

            public DdBean getDd() {
                return dd;
            }

            public void setDd(DdBean dd) {
                this.dd = dd;
            }

            public List<?> getPoint() {
                return point;
            }

            public void setPoint(List<?> point) {
                this.point = point;
            }

            public class CBean {
                /**
                 * region_id : 1
                 * region_name : 中国
                 */

                private String region_id;
                private String region_name;

                public String getRegion_id() {
                    return region_id;
                }

                public void setRegion_id(String region_id) {
                    this.region_id = region_id;
                }

                public String getRegion_name() {
                    return region_name;
                }

                public void setRegion_name(String region_name) {
                    this.region_name = region_name;
                }
            }

            public class PBean {
                /**
                 * region_id : 19
                 * region_name : 内蒙古
                 */

                private String region_id;
                private String region_name;

                public String getRegion_id() {
                    return region_id;
                }

                public void setRegion_id(String region_id) {
                    this.region_id = region_id;
                }

                public String getRegion_name() {
                    return region_name;
                }

                public void setRegion_name(String region_name) {
                    this.region_name = region_name;
                }
            }

            public class CcBean {
                /**
                 * region_id : 265
                 * region_name : 通辽
                 */

                private String region_id;
                private String region_name;

                public String getRegion_id() {
                    return region_id;
                }

                public void setRegion_id(String region_id) {
                    this.region_id = region_id;
                }

                public String getRegion_name() {
                    return region_name;
                }

                public void setRegion_name(String region_name) {
                    this.region_name = region_name;
                }
            }

            public class DdBean {
                /**
                 * region_id : 2223
                 * region_name : 科尔沁区
                 */

                private String region_id;
                private String region_name;

                public String getRegion_id() {
                    return region_id;
                }

                public void setRegion_id(String region_id) {
                    this.region_id = region_id;
                }

                public String getRegion_name() {
                    return region_name;
                }

                public void setRegion_name(String region_name) {
                    this.region_name = region_name;
                }
            }
        }

        public class GoodsListBean {
            /**
             * rec_id : 106
             * goods_id : 835
             * ru_id : 0
             * goods_name : 测试产品
             * goods_sn : LDG000835
             * market_price : 0.01
             * goods_number : 1
             * warehouse_id : 1
             * goods_price : 0.01
             * goods_attr :
             * is_real : 1
             * parent_id : 0
             * is_gift : 0
             * subtotal : 0.01
             * extension_code :
             * shop_price : 0.01
             * goods_img : http://www.lidegou.com/app/themes/default/img/no_image.png
             * goods_thumb : http://www.lidegou.com/app/themes/default/img/no_image.png
             * warehouse_name : 北京仓库
             * goods_amount : 0.01
             * amount : 0.01
             * dis_amount : 0
             * discount_amount : 0.00
             * url : /app/index.php?r=goods&id=835
             * shop_name : 利得超市
             * shopUrl : /app/index.php?r=store&id=0
             */

            private String rec_id;
            private String goods_id;
            private String ru_id;
            private String goods_name;
            private String goods_sn;
            private String market_price;
            private String goods_number;
            private String warehouse_id;
            private String goods_price;
            private String goods_attr;
            private String is_real;
            private String parent_id;
            private String is_gift;
            private String subtotal;
            private String extension_code;
            private String shop_price;
            private String goods_img;
            private String goods_thumb;
            private String warehouse_name;
            private double goods_amount;
            private String amount;
            private int dis_amount;
            private String discount_amount;
            private String url;
            private String shop_name;
            private String shopUrl;

            public String getRec_id() {
                return rec_id;
            }

            public void setRec_id(String rec_id) {
                this.rec_id = rec_id;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getRu_id() {
                return ru_id;
            }

            public void setRu_id(String ru_id) {
                this.ru_id = ru_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_sn() {
                return goods_sn;
            }

            public void setGoods_sn(String goods_sn) {
                this.goods_sn = goods_sn;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public String getGoods_number() {
                return goods_number;
            }

            public void setGoods_number(String goods_number) {
                this.goods_number = goods_number;
            }

            public String getWarehouse_id() {
                return warehouse_id;
            }

            public void setWarehouse_id(String warehouse_id) {
                this.warehouse_id = warehouse_id;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getGoods_attr() {
                return goods_attr;
            }

            public void setGoods_attr(String goods_attr) {
                this.goods_attr = goods_attr;
            }

            public String getIs_real() {
                return is_real;
            }

            public void setIs_real(String is_real) {
                this.is_real = is_real;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getIs_gift() {
                return is_gift;
            }

            public void setIs_gift(String is_gift) {
                this.is_gift = is_gift;
            }

            public String getSubtotal() {
                return subtotal;
            }

            public void setSubtotal(String subtotal) {
                this.subtotal = subtotal;
            }

            public String getExtension_code() {
                return extension_code;
            }

            public void setExtension_code(String extension_code) {
                this.extension_code = extension_code;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public String getGoods_img() {
                return goods_img;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
            }

            public String getGoods_thumb() {
                return goods_thumb;
            }

            public void setGoods_thumb(String goods_thumb) {
                this.goods_thumb = goods_thumb;
            }

            public String getWarehouse_name() {
                return warehouse_name;
            }

            public void setWarehouse_name(String warehouse_name) {
                this.warehouse_name = warehouse_name;
            }

            public double getGoods_amount() {
                return goods_amount;
            }

            public void setGoods_amount(double goods_amount) {
                this.goods_amount = goods_amount;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public int getDis_amount() {
                return dis_amount;
            }

            public void setDis_amount(int dis_amount) {
                this.dis_amount = dis_amount;
            }

            public String getDiscount_amount() {
                return discount_amount;
            }

            public void setDiscount_amount(String discount_amount) {
                this.discount_amount = discount_amount;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public String getShopUrl() {
                return shopUrl;
            }

            public void setShopUrl(String shopUrl) {
                this.shopUrl = shopUrl;
            }
        }
    }


}
