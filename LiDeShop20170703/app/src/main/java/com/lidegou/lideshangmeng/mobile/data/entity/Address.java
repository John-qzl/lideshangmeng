package com.lidegou.lideshangmeng.mobile.data.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/1/3.
 */

public class Address {


    /**
     * consignee_list : [{"address_id":"123","address_name":"","user_id":"2285","consignee":"44","email":"","country":"1","province":"2","city":"52","district":"500","address":"北京北京东城区 515651651","zipcode":"","tel":"","mobile":"18801387478","sign_building":"","best_time":"","audit":"0","url":"/app/index.php?r=user/user/edit_address&id=123"}]
     * address_id : 0
     */

    private String address_id;
    private List<ConsigneeListBean> consignee_list;

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public List<ConsigneeListBean> getConsignee_list() {
        return consignee_list;
    }

    public void setConsignee_list(List<ConsigneeListBean> consignee_list) {
        this.consignee_list = consignee_list;
    }

    public static class ConsigneeListBean {
        /**
         * address_id : 123
         * address_name :
         * user_id : 2285
         * consignee : 44
         * email :
         * country : 1
         * province : 2
         * city : 52
         * district : 500
         * address : 北京北京东城区 515651651
         * zipcode :
         * tel :
         * mobile : 18801387478
         * sign_building :
         * best_time :
         * audit : 0
         * url : /app/index.php?r=user/user/edit_address&id=123
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
        private String url;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }


    public class AddressUpdateInfo{

        /**
         * address_id : 123
         * address_name :
         * user_id : 2285
         * consignee : 44
         * email :
         * country : 1
         * province : 北京
         * city : 北京
         * district : 东城区
         * address : 515651651
         * zipcode :
         * tel :
         * mobile : 18801387478
         * sign_building :
         * best_time :
         * audit : 0
         * province_id : 2
         * city_id : 52
         * district_id : 500
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
        private String province_id;
        private String city_id;
        private String district_id;

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

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getDistrict_id() {
            return district_id;
        }

        public void setDistrict_id(String district_id) {
            this.district_id = district_id;
        }
    }
}
