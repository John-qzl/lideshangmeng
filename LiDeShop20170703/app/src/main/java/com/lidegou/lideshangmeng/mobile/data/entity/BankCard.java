package com.lidegou.lideshangmeng.mobile.data.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */

public class BankCard {


    /**
     * id : 657
     * bank_code : 中国工商银行
     * bank_name :
     * bank_card : 6266
     * bank_region : 工商银行北京昌平润丰欣赏支行
     * bank_user_name : 张晓正
     * user_id : 2285
     */

    private String id;
    private String bank_code;
    private String bank_name;
    private String bank_card;
    private String bank_region;
    private String bank_user_name;
    private String user_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_card() {
        return bank_card;
    }

    public void setBank_card(String bank_card) {
        this.bank_card = bank_card;
    }

    public String getBank_region() {
        return bank_region;
    }

    public void setBank_region(String bank_region) {
        this.bank_region = bank_region;
    }

    public String getBank_user_name() {
        return bank_user_name;
    }

    public void setBank_user_name(String bank_user_name) {
        this.bank_user_name = bank_user_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public static class AddCardInfo{


        /**
         * info : {"xm":"张晓正"}
         * bank_list : [{"id":"1","bank_code":"102","bank_name":"中国工商银行"},{"id":"3","bank_code":"103","bank_name":"中国农业银行"},{"id":"4","bank_code":"104","bank_name":"中国银行"},{"id":"5","bank_code":"105","bank_name":"中国建设银行"},{"id":"6","bank_code":"301","bank_name":"交通银行"},{"id":"7","bank_code":"302","bank_name":"中信银行"},{"id":"8","bank_code":"303","bank_name":"中国光大银行"},{"id":"9","bank_code":"308","bank_name":"招商银行"},{"id":"10","bank_code":"402","bank_name":"农村信用社"},{"id":"11","bank_code":"403","bank_name":"中国邮政储蓄银行"},{"id":"12","bank_code":"304","bank_name":"华夏银行"},{"id":"15","bank_code":"307","bank_name":"深圳发展银行"}]
         */

        private InfoBean info;
        private List<BankListBean> bank_list;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<BankListBean> getBank_list() {
            return bank_list;
        }

        public void setBank_list(List<BankListBean> bank_list) {
            this.bank_list = bank_list;
        }

        public static class InfoBean {
            /**
             * xm : 张晓正
             */

            private String xm;

            public String getXm() {
                return xm;
            }

            public void setXm(String xm) {
                this.xm = xm;
            }
        }

        public static class BankListBean {
            /**
             * id : 1
             * bank_code : 102
             * bank_name : 中国工商银行
             */

            private String id;
            private String bank_code;
            private String bank_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getBank_code() {
                return bank_code;
            }

            public void setBank_code(String bank_code) {
                this.bank_code = bank_code;
            }

            public String getBank_name() {
                return bank_name;
            }

            public void setBank_name(String bank_name) {
                this.bank_name = bank_name;
            }


            @Override
            public String toString() {
                return bank_name;
            }
        }
    }
}
