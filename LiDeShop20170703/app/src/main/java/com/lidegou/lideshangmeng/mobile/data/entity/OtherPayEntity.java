package com.lidegou.lideshangmeng.mobile.data.entity;

/**
 * Created by Y on 2017/1/22.
 */

public class OtherPayEntity {
    private WeChatEntity weChatEntity;
    private TreasureEntity treasureEntity;
    private PaaCreatorEntity paaCreatorEntity;

    public WeChatEntity getWeChatEntity() {
        return weChatEntity;
    }

    public void setWeChatEntity(WeChatEntity weChatEntity) {
        this.weChatEntity = weChatEntity;
    }

    public TreasureEntity getTreasureEntity() {
        return treasureEntity;
    }

    public void setTreasureEntity(TreasureEntity treasureEntity) {
        this.treasureEntity = treasureEntity;
    }

    public PaaCreatorEntity getPaaCreatorEntity() {
        return paaCreatorEntity;
    }

    public void setPaaCreatorEntity(PaaCreatorEntity paaCreatorEntity) {
        this.paaCreatorEntity = paaCreatorEntity;
    }

    public static class WeChatEntity {
        private String appid;
        private String noncestr;
        private String packageMsg;
        private String partnerid;
        private String prepayid;
        private String timestamp;
        private String sign;

        public WeChatEntity() {
        }

        public WeChatEntity(String appid, String noncestr, String packageMsg, String partnerid, String prepayid, String timestamp, String sign) {
            this.appid = appid;
            this.noncestr = noncestr;
            this.packageMsg = packageMsg;
            this.partnerid = partnerid;
            this.prepayid = prepayid;
            this.timestamp = timestamp;
            this.sign = sign;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageMsg() {
            return packageMsg;
        }

        public void setPackageMsg(String packageMsg) {
            this.packageMsg = packageMsg;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }

    public static class TreasureEntity {
        private String payInfo;

        public TreasureEntity(String payInfo) {
            this.payInfo = payInfo;
        }

        public String getPayInfo() {
            return payInfo;
        }

        public void setPayInfo(String payInfo) {
            this.payInfo = payInfo;
        }
    }

    public static class PaaCreatorEntity {
        private String orderAmount;
        private String productName;
        private String ext1;
        private String merchantId;
        private String orderNo;
        private String receiveUrl;

        public PaaCreatorEntity(String orderAmount, String productName, String ext1, String merchantId, String orderNo, String receiveUrl) {
            this.orderAmount = orderAmount;
            this.productName = productName;
            this.ext1 = ext1;
            this.merchantId = merchantId;
            this.orderNo = orderNo;
            this.receiveUrl = receiveUrl;
        }

        public String getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(String orderAmount) {
            this.orderAmount = orderAmount;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getExt1() {
            return ext1;
        }

        public void setExt1(String ext1) {
            this.ext1 = ext1;
        }

        public String getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getReceiveUrl() {
            return receiveUrl;
        }

        public void setReceiveUrl(String receiveUrl) {
            this.receiveUrl = receiveUrl;
        }
    }
}
