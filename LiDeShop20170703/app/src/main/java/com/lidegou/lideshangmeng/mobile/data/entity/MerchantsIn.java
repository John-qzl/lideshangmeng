package com.lidegou.lideshangmeng.mobile.data.entity;

/**
 * Created by Administrator on 2017/1/19.
 */
public class MerchantsIn {

    /**
     * 进行中的状态
     */
    public class MerchantsInCheck {

        /**
         * steps : one
         */

        private String steps;

        public String getSteps() {
            return steps;
        }

        public void setSteps(String steps) {
            this.steps = steps;
        }
    }

    /**
     * 店铺入驻协议
     */
    public class MerchantsInProtocol {
        private String process_title;
        private String process_article;
        private String article_centent;

        public String getProcess_title() {
            return process_title;
        }

        public void setProcess_title(String process_title) {
            this.process_title = process_title;
        }

        public String getProcess_article() {
            return process_article;
        }

        public void setProcess_article(String process_article) {
            this.process_article = process_article;
        }

        public String getArticle_centent() {
            return article_centent;
        }

        public void setArticle_centent(String article_centent) {
            this.article_centent = article_centent;
        }
    }

    /**
     * 联系方式页面
     */
    public class MerchantsInContact {
        private String contactXinbie;
        private String contactPhone;
        private String contactName;

        public String getContactXinbie() {
            return contactXinbie;
        }

        public void setContactXinbie(String contactXinbie) {
            this.contactXinbie = contactXinbie;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }
    }


    /**
     * 公司信息页面
     */
    public class MerchantsInCompany {
        private String companyName;
        private String business_license_id;
        private String legal_person;
        private String busines_scope;
        private String license_fileImg;
        private String company_adress;
        private String company_contactTel;
        private String province;
        private String city;
        private String district;
        private String province_name;
        private String city_name;
        private String district_name;
        private String longitude;
        private String latitude;

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getBusiness_license_id() {
            return business_license_id;
        }

        public void setBusiness_license_id(String business_license_id) {
            this.business_license_id = business_license_id;
        }

        public String getLegal_person() {
            return legal_person;
        }

        public void setLegal_person(String legal_person) {
            this.legal_person = legal_person;
        }

        public String getBusines_scope() {
            return busines_scope;
        }

        public void setBusines_scope(String busines_scope) {
            this.busines_scope = busines_scope;
        }

        public String getLicense_fileImg() {
            return license_fileImg;
        }

        public void setLicense_fileImg(String license_fileImg) {
            this.license_fileImg = license_fileImg;
        }

        public String getCompany_adress() {
            return company_adress;
        }

        public void setCompany_adress(String company_adress) {
            this.company_adress = company_adress;
        }

        public String getCompany_contactTel() {
            return company_contactTel;
        }

        public void setCompany_contactTel(String company_contactTel) {
            this.company_contactTel = company_contactTel;
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

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }
    }


}
