package com.lidegou.lideshangmeng.mobile.data.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/1/16.
 */

public class OrderSubmit {

    /**
     * consignee : {"address_id":"98","address_name":"","user_id":"1221","consignee":"郑重","email":"","country":"1","province":"19","city":"265","district":"2223","address":"万力城","zipcode":"","tel":"","mobile":"15560751512","sign_building":"","best_time":"","audit":"0","province_name":"内蒙古","city_name":"通辽","district_name":"科尔沁区","region":"内蒙古 通辽 科尔沁区"}
     * list : [{"ru_id":0,"shipping_type":0,"ru_name":"利得超市","url":"/app/index.php?r=store&id=0","shipping":{"shipping_id":"16","shipping_name":"顺丰速运","format_shipping_fee":"0.00","shipping_fee":0,"insure_formated":"0.00","insure_disabled":true,"cod_disabled":true},"goods_list":[{"warehouse_id":"2","rec_id":"780","user_id":"1221","goods_id":"831","ru_id":"0","goods_name":"雪花啤酒（Snowbeer） 勇闯天涯330ml*24听整箱装","goods_thumb":"http://www.lidegou.com/images/201610/thumb_img/831_thumb_G_1477125510984.jpg","goods_sn":"LDG000831","goods_number":"1","default_shipping":"0","goodsWeight":"0.000","market_price":"110.88","goods_price":"105.60","goods_attr":"","is_real":"1","extension_code":"","parent_id":"0","is_gift":"0","is_shipping":"0","rec_type":"0","subtotal":"105.60","goods_attr_id":"","group_id":"","deposit":"","dis_amount":0,"discount_amount":"0.00","formated_market_price":"110.88","formated_goods_price":"105.60","formated_presale_deposit":"0.00","formated_subtotal":"105.60","region_name":"上海仓库","rec_txt":"普通","url":"/app/index.php?r=goods&id=831"}],"amount":"105.60","goods_count":1}]
     * total : {"real_goods_count":1,"gift_amount":0,"goods_price":105.6,"market_price":110.88,"discount":"","pack_fee":0,"card_fee":0,"shipping_fee":0,"shipping_insure":0,"integral_money":0,"bonus":0,"surplus":0,"cod_fee":0,"pay_fee":0,"tax":0,"presale_price":0,"saving":5.28,"save_rate":"5%","goods_price_formated":"105.60","market_price_formated":"110.88","saving_formated":"5.28","discount_formated":"0.00","tax_formated":"0.00","pack_fee_formated":"0.00","card_fee_formated":"0.00","bonus_formated":"0.00","shipping_fee_formated":"0.00","shipping_insure_formated":"0.00","amount":105.6,"surplus_formated":"0.00","integral":0,"integral_formated":"0.00","pay_fee_formated":"0.00","amount_formated":"105.60","will_get_integral":0,"will_get_bonus":"0.00","formated_goods_price":"105.60","formated_market_price":"110.0","user_money":"0.25","order":{"extension_code":"","shipping_id":0,"pay_id":0,"pack_id":0,"card_id":0,"bonus":0,"integral":0,"surplus":0,"need_inv":1,"inv_type":"","inv_payee":"个人","inv_content":"不开发票"}}
     */

    private ConsigneeBean consignee;
    private TotalBean total;
    private List<ShippingBean> shopping;
    private List<ListBean> list;
    private OrderBean order;
    private List<PaymentBean> payment_list;

    public ConsigneeBean getConsignee() {
        return consignee;
    }

    public void setConsignee(ConsigneeBean consignee) {
        this.consignee = consignee;
    }

    public TotalBean getTotal() {
        return total;
    }

    public void setTotal(TotalBean total) {
        this.total = total;
    }

    public List<ShippingBean> getShopping() {
        return shopping;
    }

    public void setShopping(List<ShippingBean> shopping) {
        this.shopping = shopping;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public void setPayment_list(List<PaymentBean> payment_list) {
        this.payment_list = payment_list;
    }

    public List<PaymentBean> getPayment_list() {
        return payment_list;
    }

    public static class ConsigneeBean {
        /**
         * address_id : 98
         * address_name :
         * user_id : 1221
         * consignee : 郑重
         * email :
         * country : 1
         * province : 19
         * city : 265
         * district : 2223
         * address : 万力城
         * zipcode :
         * tel :
         * mobile : 15560751512
         * sign_building :
         * best_time :
         * audit : 0
         * province_name : 内蒙古
         * city_name : 通辽
         * district_name : 科尔沁区
         * region : 内蒙古 通辽 科尔沁区
         */

        private String address_id;
        private String address_name;
        private String user_id;
        private String consignee;
        private String email;
        private String country;
        private String province;
        private String city;
        private String district;
        private String address;
        private String zipcode;
        private String tel;
        private String mobile;
        private String sign_building;
        private String best_time;
        private String audit;
        private String province_name;
        private String city_name;
        private String district_name;
        private String region;

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }

        public String getAddress_name() {
            return address_name;
        }

        public void setAddress_name(String address_name) {
            this.address_name = address_name;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

        public String getSign_building() {
            return sign_building;
        }

        public void setSign_building(String sign_building) {
            this.sign_building = sign_building;
        }

        public String getBest_time() {
            return best_time;
        }

        public void setBest_time(String best_time) {
            this.best_time = best_time;
        }

        public String getAudit() {
            return audit;
        }

        public void setAudit(String audit) {
            this.audit = audit;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getDistrict_name() {
            return district_name;
        }

        public void setDistrict_name(String district_name) {
            this.district_name = district_name;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }
    }

    public static class TotalBean {
        /**
         * real_goods_count : 1
         * gift_amount : 0
         * goods_price : 105.6
         * market_price : 110.88
         * discount :
         * pack_fee : 0
         * card_fee : 0
         * shipping_fee : 0
         * shipping_insure : 0
         * integral_money : 0
         * bonus : 0
         * surplus : 0
         * cod_fee : 0
         * pay_fee : 0
         * tax : 0
         * presale_price : 0
         * saving : 5.28
         * save_rate : 5%
         * goods_price_formated : 105.60
         * market_price_formated : 110.88
         * saving_formated : 5.28
         * discount_formated : 0.00
         * tax_formated : 0.00
         * pack_fee_formated : 0.00
         * card_fee_formated : 0.00
         * bonus_formated : 0.00
         * shipping_fee_formated : 0.00
         * shipping_insure_formated : 0.00
         * amount : 105.6
         * surplus_formated : 0.00
         * integral : 0
         * integral_formated : 0.00
         * pay_fee_formated : 0.00
         * amount_formated : 105.60
         * will_get_integral : 0
         * will_get_bonus : 0.00
         * formated_goods_price : 105.60
         * formated_market_price : 110.0
         * user_money : 0.25
         * order : {"extension_code":"","shipping_id":0,"pay_id":0,"pack_id":0,"card_id":0,"bonus":0,"integral":0,"surplus":0,"need_inv":1,"inv_type":"","inv_payee":"个人","inv_content":"不开发票"}
         */

        private int real_goods_count;
        private int gift_amount;
        private double goods_price;
        private double market_price;
        private String discount;
        private int pack_fee;
        private int card_fee;
        private int shipping_fee;
        private int shipping_insure;
        private int integral_money;
        private int bonus;
        private int surplus;
        private int cod_fee;
        private int pay_fee;
        private int tax;
        private int presale_price;
        private double saving;
        private String save_rate;
        private String goods_price_formated;
        private String market_price_formated;
        private String saving_formated;
        private String discount_formated;
        private String tax_formated;
        private String pack_fee_formated;
        private String card_fee_formated;
        private String bonus_formated;
        private String shipping_fee_formated;
        private String shipping_insure_formated;
        private double amount;
        private String surplus_formated;
        private int integral;
        private String integral_formated;
        private String pay_fee_formated;
        private String amount_formated;
        private int will_get_integral;
        private String will_get_bonus;
        private String formated_goods_price;
        private String formated_market_price;
        private String user_money;

        public int getReal_goods_count() {
            return real_goods_count;
        }

        public void setReal_goods_count(int real_goods_count) {
            this.real_goods_count = real_goods_count;
        }

        public int getGift_amount() {
            return gift_amount;
        }

        public void setGift_amount(int gift_amount) {
            this.gift_amount = gift_amount;
        }

        public double getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(double goods_price) {
            this.goods_price = goods_price;
        }

        public double getMarket_price() {
            return market_price;
        }

        public void setMarket_price(double market_price) {
            this.market_price = market_price;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public int getPack_fee() {
            return pack_fee;
        }

        public void setPack_fee(int pack_fee) {
            this.pack_fee = pack_fee;
        }

        public int getCard_fee() {
            return card_fee;
        }

        public void setCard_fee(int card_fee) {
            this.card_fee = card_fee;
        }

        public int getShipping_fee() {
            return shipping_fee;
        }

        public void setShipping_fee(int shipping_fee) {
            this.shipping_fee = shipping_fee;
        }

        public int getShipping_insure() {
            return shipping_insure;
        }

        public void setShipping_insure(int shipping_insure) {
            this.shipping_insure = shipping_insure;
        }

        public int getIntegral_money() {
            return integral_money;
        }

        public void setIntegral_money(int integral_money) {
            this.integral_money = integral_money;
        }

        public int getBonus() {
            return bonus;
        }

        public void setBonus(int bonus) {
            this.bonus = bonus;
        }

        public int getSurplus() {
            return surplus;
        }

        public void setSurplus(int surplus) {
            this.surplus = surplus;
        }

        public int getCod_fee() {
            return cod_fee;
        }

        public void setCod_fee(int cod_fee) {
            this.cod_fee = cod_fee;
        }

        public int getPay_fee() {
            return pay_fee;
        }

        public void setPay_fee(int pay_fee) {
            this.pay_fee = pay_fee;
        }

        public int getTax() {
            return tax;
        }

        public void setTax(int tax) {
            this.tax = tax;
        }

        public int getPresale_price() {
            return presale_price;
        }

        public void setPresale_price(int presale_price) {
            this.presale_price = presale_price;
        }

        public double getSaving() {
            return saving;
        }

        public void setSaving(double saving) {
            this.saving = saving;
        }

        public String getSave_rate() {
            return save_rate;
        }

        public void setSave_rate(String save_rate) {
            this.save_rate = save_rate;
        }

        public String getGoods_price_formated() {
            return goods_price_formated;
        }

        public void setGoods_price_formated(String goods_price_formated) {
            this.goods_price_formated = goods_price_formated;
        }

        public String getMarket_price_formated() {
            return market_price_formated;
        }

        public void setMarket_price_formated(String market_price_formated) {
            this.market_price_formated = market_price_formated;
        }

        public String getSaving_formated() {
            return saving_formated;
        }

        public void setSaving_formated(String saving_formated) {
            this.saving_formated = saving_formated;
        }

        public String getDiscount_formated() {
            return discount_formated;
        }

        public void setDiscount_formated(String discount_formated) {
            this.discount_formated = discount_formated;
        }

        public String getTax_formated() {
            return tax_formated;
        }

        public void setTax_formated(String tax_formated) {
            this.tax_formated = tax_formated;
        }

        public String getPack_fee_formated() {
            return pack_fee_formated;
        }

        public void setPack_fee_formated(String pack_fee_formated) {
            this.pack_fee_formated = pack_fee_formated;
        }

        public String getCard_fee_formated() {
            return card_fee_formated;
        }

        public void setCard_fee_formated(String card_fee_formated) {
            this.card_fee_formated = card_fee_formated;
        }

        public String getBonus_formated() {
            return bonus_formated;
        }

        public void setBonus_formated(String bonus_formated) {
            this.bonus_formated = bonus_formated;
        }

        public String getShipping_fee_formated() {
            return shipping_fee_formated;
        }

        public void setShipping_fee_formated(String shipping_fee_formated) {
            this.shipping_fee_formated = shipping_fee_formated;
        }

        public String getShipping_insure_formated() {
            return shipping_insure_formated;
        }

        public void setShipping_insure_formated(String shipping_insure_formated) {
            this.shipping_insure_formated = shipping_insure_formated;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getSurplus_formated() {
            return surplus_formated;
        }

        public void setSurplus_formated(String surplus_formated) {
            this.surplus_formated = surplus_formated;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getIntegral_formated() {
            return integral_formated;
        }

        public void setIntegral_formated(String integral_formated) {
            this.integral_formated = integral_formated;
        }

        public String getPay_fee_formated() {
            return pay_fee_formated;
        }

        public void setPay_fee_formated(String pay_fee_formated) {
            this.pay_fee_formated = pay_fee_formated;
        }

        public String getAmount_formated() {
            return amount_formated;
        }

        public void setAmount_formated(String amount_formated) {
            this.amount_formated = amount_formated;
        }

        public int getWill_get_integral() {
            return will_get_integral;
        }

        public void setWill_get_integral(int will_get_integral) {
            this.will_get_integral = will_get_integral;
        }

        public String getWill_get_bonus() {
            return will_get_bonus;
        }

        public void setWill_get_bonus(String will_get_bonus) {
            this.will_get_bonus = will_get_bonus;
        }

        public String getFormated_goods_price() {
            return formated_goods_price;
        }

        public void setFormated_goods_price(String formated_goods_price) {
            this.formated_goods_price = formated_goods_price;
        }

        public String getFormated_market_price() {
            return formated_market_price;
        }

        public void setFormated_market_price(String formated_market_price) {
            this.formated_market_price = formated_market_price;
        }

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }


    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public static class OrderBean {
        /**
         * extension_code :
         * shipping_id : 0
         * pay_id : 0
         * pack_id : 0
         * card_id : 0
         * bonus : 0
         * integral : 0
         * surplus : 0
         * need_inv : 1
         * inv_type :
         * inv_payee : 个人
         * inv_content : 不开发票
         */

        private String extension_code;
        private String shipping_id;
        private String pay_id;
        private String pack_id;
        private String card_id;
        private String bonus;
        private String integral;
        private String surplus;
        private String need_inv;
        private String inv_type;
        private String inv_payee;
        private String inv_content;

        public String getExtension_code() {
            return extension_code;
        }

        public void setExtension_code(String extension_code) {
            this.extension_code = extension_code;
        }

        public String getShipping_id() {
            return shipping_id;
        }

        public void setShipping_id(String shipping_id) {
            this.shipping_id = shipping_id;
        }

        public String getPay_id() {
            return pay_id;
        }

        public void setPay_id(String pay_id) {
            this.pay_id = pay_id;
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

        public String getBonus() {
            return bonus;
        }

        public void setBonus(String bonus) {
            this.bonus = bonus;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getSurplus() {
            return surplus;
        }

        public void setSurplus(String surplus) {
            this.surplus = surplus;
        }

        public String getNeed_inv() {
            return need_inv;
        }

        public void setNeed_inv(String need_inv) {
            this.need_inv = need_inv;
        }

        public String getInv_type() {
            return inv_type;
        }

        public void setInv_type(String inv_type) {
            this.inv_type = inv_type;
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
    }

    public static class ListBean {
        /**
         * ru_id : 0
         * shipping_type : 0
         * ru_name : 利得超市
         * url : /app/index.php?r=store&id=0
         * shipping : {"shipping_id":"16","shipping_name":"顺丰速运","format_shipping_fee":"0.00","shipping_fee":0,"insure_formated":"0.00","insure_disabled":true,"cod_disabled":true}
         * goods_list : [{"warehouse_id":"2","rec_id":"780","user_id":"1221","goods_id":"831","ru_id":"0","goods_name":"雪花啤酒（Snowbeer） 勇闯天涯330ml*24听整箱装","goods_thumb":"http://www.lidegou.com/images/201610/thumb_img/831_thumb_G_1477125510984.jpg","goods_sn":"LDG000831","goods_number":"1","default_shipping":"0","goodsWeight":"0.000","market_price":"110.88","goods_price":"105.60","goods_attr":"","is_real":"1","extension_code":"","parent_id":"0","is_gift":"0","is_shipping":"0","rec_type":"0","subtotal":"105.60","goods_attr_id":"","group_id":"","deposit":"","dis_amount":0,"discount_amount":"0.00","formated_market_price":"110.88","formated_goods_price":"105.60","formated_presale_deposit":"0.00","formated_subtotal":"105.60","region_name":"上海仓库","rec_txt":"普通","url":"/app/index.php?r=goods&id=831"}]
         * amount : 105.60
         * goods_count : 1
         */

        private int ru_id;
        private int shipping_type;
        private String ru_name;
        private String url;
        //        private ShippingBean shipping;
        private String amount;
        private int goods_count;
        private List<GoodsListBean> goods_list;

        public int getRu_id() {
            return ru_id;
        }

        public void setRu_id(int ru_id) {
            this.ru_id = ru_id;
        }

        public int getShipping_type() {
            return shipping_type;
        }

        public void setShipping_type(int shipping_type) {
            this.shipping_type = shipping_type;
        }

        public String getRu_name() {
            return ru_name;
        }

        public void setRu_name(String ru_name) {
            this.ru_name = ru_name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

//        public ShippingBean getShipping() {
//            return shipping;
//        }
//
//        public void setShipping(ShippingBean shipping) {
//            this.shipping = shipping;
//        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public int getGoods_count() {
            return goods_count;
        }

        public void setGoods_count(int goods_count) {
            this.goods_count = goods_count;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

//        public static class ShippingBean {
//            /**
//             * shipping_id : 16
//             * shipping_name : 顺丰速运
//             * format_shipping_fee : 0.00
//             * shipping_fee : 0
//             * insure_formated : 0.00
//             * insure_disabled : true
//             * cod_disabled : true
//             */
//
//            private String shipping_id;
//            private String shipping_name;
//            private String format_shipping_fee;
//            private int shipping_fee;
//            private String insure_formated;
//            private boolean insure_disabled;
//            private boolean cod_disabled;
//
//            public String getShipping_id() {
//                return shipping_id;
//            }
//
//            public void setShipping_id(String shipping_id) {
//                this.shipping_id = shipping_id;
//            }
//
//            public String getShipping_name() {
//                return shipping_name;
//            }
//
//            public void setShipping_name(String shipping_name) {
//                this.shipping_name = shipping_name;
//            }
//
//            public String getFormat_shipping_fee() {
//                return format_shipping_fee;
//            }
//
//            public void setFormat_shipping_fee(String format_shipping_fee) {
//                this.format_shipping_fee = format_shipping_fee;
//            }
//
//            public int getShipping_fee() {
//                return shipping_fee;
//            }
//
//            public void setShipping_fee(int shipping_fee) {
//                this.shipping_fee = shipping_fee;
//            }
//
//            public String getInsure_formated() {
//                return insure_formated;
//            }
//
//            public void setInsure_formated(String insure_formated) {
//                this.insure_formated = insure_formated;
//            }
//
//            public boolean isInsure_disabled() {
//                return insure_disabled;
//            }
//
//            public void setInsure_disabled(boolean insure_disabled) {
//                this.insure_disabled = insure_disabled;
//            }
//
//            public boolean isCod_disabled() {
//                return cod_disabled;
//            }
//
//            public void setCod_disabled(boolean cod_disabled) {
//                this.cod_disabled = cod_disabled;
//            }
//        }

        public static class GoodsListBean {
            /**
             * warehouse_id : 2
             * rec_id : 780
             * user_id : 1221
             * goods_id : 831
             * ru_id : 0
             * goods_name : 雪花啤酒（Snowbeer） 勇闯天涯330ml*24听整箱装
             * goods_thumb : http://www.lidegou.com/images/201610/thumb_img/831_thumb_G_1477125510984.jpg
             * goods_sn : LDG000831
             * goods_number : 1
             * default_shipping : 0
             * goodsWeight : 0.000
             * market_price : 110.88
             * goods_price : 105.60
             * goods_attr :
             * is_real : 1
             * extension_code :
             * parent_id : 0
             * is_gift : 0
             * is_shipping : 0
             * rec_type : 0
             * subtotal : 105.60
             * goods_attr_id :
             * group_id :
             * deposit :
             * dis_amount : 0
             * discount_amount : 0.00
             * formated_market_price : 110.88
             * formated_goods_price : 105.60
             * formated_presale_deposit : 0.00
             * formated_subtotal : 105.60
             * region_name : 上海仓库
             * rec_txt : 普通
             * url : /app/index.php?r=goods&id=831
             */

            private String warehouse_id;
            private String rec_id;
            private String user_id;
            private String goods_id;
            private String ru_id;
            private String goods_name;
            private String goods_thumb;
            private String goods_sn;
            private String goods_number;
            private String default_shipping;
            private String goodsWeight;
            private String market_price;
            private String goods_price;
            private String goods_attr;
            private String is_real;
            private String extension_code;
            private String parent_id;
            private String is_gift;
            private String is_shipping;
            private String rec_type;
            private String subtotal;
            private String goods_attr_id;
            private String group_id;
            private String deposit;
            private int dis_amount;
            private String discount_amount;
            private String formated_market_price;
            private String formated_goods_price;
            private String formated_presale_deposit;
            private String formated_subtotal;
            private String region_name;
            private String rec_txt;
            private String url;


            public String getWarehouse_id() {
                return warehouse_id;
            }

            public void setWarehouse_id(String warehouse_id) {
                this.warehouse_id = warehouse_id;
            }

            public String getRec_id() {
                return rec_id;
            }

            public void setRec_id(String rec_id) {
                this.rec_id = rec_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
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

            public String getGoods_thumb() {
                return goods_thumb;
            }

            public void setGoods_thumb(String goods_thumb) {
                this.goods_thumb = goods_thumb;
            }

            public String getGoods_sn() {
                return goods_sn;
            }

            public void setGoods_sn(String goods_sn) {
                this.goods_sn = goods_sn;
            }

            public String getGoods_number() {
                return goods_number;
            }

            public void setGoods_number(String goods_number) {
                this.goods_number = goods_number;
            }

            public String getDefault_shipping() {
                return default_shipping;
            }

            public void setDefault_shipping(String default_shipping) {
                this.default_shipping = default_shipping;
            }

            public String getGoodsWeight() {
                return goodsWeight;
            }

            public void setGoodsWeight(String goodsWeight) {
                this.goodsWeight = goodsWeight;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
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

            public String getExtension_code() {
                return extension_code;
            }

            public void setExtension_code(String extension_code) {
                this.extension_code = extension_code;
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

            public String getIs_shipping() {
                return is_shipping;
            }

            public void setIs_shipping(String is_shipping) {
                this.is_shipping = is_shipping;
            }

            public String getRec_type() {
                return rec_type;
            }

            public void setRec_type(String rec_type) {
                this.rec_type = rec_type;
            }

            public String getSubtotal() {
                return subtotal;
            }

            public void setSubtotal(String subtotal) {
                this.subtotal = subtotal;
            }

            public String getGoods_attr_id() {
                return goods_attr_id;
            }

            public void setGoods_attr_id(String goods_attr_id) {
                this.goods_attr_id = goods_attr_id;
            }

            public String getGroup_id() {
                return group_id;
            }

            public void setGroup_id(String group_id) {
                this.group_id = group_id;
            }

            public String getDeposit() {
                return deposit;
            }

            public void setDeposit(String deposit) {
                this.deposit = deposit;
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

            public String getFormated_market_price() {
                return formated_market_price;
            }

            public void setFormated_market_price(String formated_market_price) {
                this.formated_market_price = formated_market_price;
            }

            public String getFormated_goods_price() {
                return formated_goods_price;
            }

            public void setFormated_goods_price(String formated_goods_price) {
                this.formated_goods_price = formated_goods_price;
            }

            public String getFormated_presale_deposit() {
                return formated_presale_deposit;
            }

            public void setFormated_presale_deposit(String formated_presale_deposit) {
                this.formated_presale_deposit = formated_presale_deposit;
            }

            public String getFormated_subtotal() {
                return formated_subtotal;
            }

            public void setFormated_subtotal(String formated_subtotal) {
                this.formated_subtotal = formated_subtotal;
            }

            public String getRegion_name() {
                return region_name;
            }

            public void setRegion_name(String region_name) {
                this.region_name = region_name;
            }

            public String getRec_txt() {
                return rec_txt;
            }

            public void setRec_txt(String rec_txt) {
                this.rec_txt = rec_txt;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }

    public static class PaymentBean {
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

    public static class ShippingBean {
        private String shopping_id;
        private String shopping_name;
        private String shopping_fee;

        public String getShopping_id() {
            return shopping_id;
        }

        public void setShopping_id(String shopping_id) {
            this.shopping_id = shopping_id;
        }

        public String getShopping_name() {
            return shopping_name;
        }

        public void setShopping_name(String shopping_name) {
            this.shopping_name = shopping_name;
        }

        public String getShopping_fee() {
            return shopping_fee;
        }

        public void setShopping_fee(String shopping_fee) {
            this.shopping_fee = shopping_fee;
        }
    }
}
