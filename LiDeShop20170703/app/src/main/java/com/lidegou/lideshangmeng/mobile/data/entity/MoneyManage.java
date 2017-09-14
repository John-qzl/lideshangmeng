package com.lidegou.lideshangmeng.mobile.data.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/26.
 */

public class MoneyManage {


    /**
     * bonus_money : 0.00
     * frozen_money : 0.00
     * surplus_amount : 0.00
     * drp_card : 0
     * turn_money : 0
     */

    private String bonus_money;
    private String frozen_money;
    private String surplus_amount;
    private String drp_card;
    private String turn_money;

    public String getBonus_money() {
        return bonus_money;
    }

    public void setBonus_money(String bonus_money) {
        this.bonus_money = bonus_money;
    }

    public String getFrozen_money() {
        return frozen_money;
    }

    public void setFrozen_money(String frozen_money) {
        this.frozen_money = frozen_money;
    }

    public String getSurplus_amount() {
        return surplus_amount;
    }

    public void setSurplus_amount(String surplus_amount) {
        this.surplus_amount = surplus_amount;
    }

    public String getDrp_card() {
        return drp_card;
    }

    public void setDrp_card(String drp_card) {
        this.drp_card = drp_card;
    }

    public String getTurn_money() {
        return turn_money;
    }

    public void setTurn_money(String turn_money) {
        this.turn_money = turn_money;
    }

    public class AccountDetails {//账户明细

    }

    public static class Accountraply {//提现
        /**
         * notice : <div> <p>利得商盟提现规则：</p> <p>（1）       注册时，请您如实填写“注册姓名”；</p> <p>（2）       为了保障您的积分安全，填写银行账号时，您提供的银行账户开户姓名需要和您在本网站注册的姓名一致，否则无法处理您的提现申请；</p> <p>（3）       提现申请间隔不少于7天；</p> <p>（4）       每次提现积分为100的整数倍，如：100积分、200积分依此类推；</p> <p>（5）       由于银行机构周末和法定假日不处理批量付款业务，因此遇到周末及节假日顺延。</p>   <p>感谢您的理解和支持，有任何疑问请您联系我们：400-9979-100</p> </div>
         * bank : [{"id":"657","bank_code":"中国工商银行","bank_name":"","bank_card":"6222021310001986266","bank_region":"工商银行北京昌平润丰欣赏支行","bank_user_name":"张晓正","user_id":"2285"},{"id":"661","bank_code":"中国交通银行","bank_name":"","bank_card":"6222620110024275769","bank_region":"交通北京支行","bank_user_name":"张晓正","user_id":"2285"},{"id":"660","bank_code":"中国农业银行","bank_name":"","bank_card":"6228480246006580061","bank_region":"农业银行北京支行","bank_user_name":"张晓正","user_id":"2285"}]
         * surplus_amount : 0.00
         */
        private int code;
        private String msg;
        private String notice;
        private String surplus_amount;
        private List<BankBean> bank;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public String getSurplus_amount() {
            return surplus_amount;
        }

        public void setSurplus_amount(String surplus_amount) {
            this.surplus_amount = surplus_amount;
        }

        public List<BankBean> getBank() {
            return bank;
        }

        public void setBank(List<BankBean> bank) {
            this.bank = bank;
        }

        public class BankBean {
            /**
             * id : 657
             * bank_code : 中国工商银行
             * bank_name :
             * bank_card : 6222021310001986266
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

            @Override
            public String toString() {
                return bank_code;
            }
        }
    }

    //申请记录
    public static class ApplyRecord {

        /**
         * data : [{"id":"3886","user_id":"2285","admin_user":"","amount":"200.00","add_time":"2016-12-27","paid_time":"0","admin_note":"","user_note":"123","process_type":"1","payment":"","is_paid":"0","cancel":"0","short_admin_note":"N/A","short_user_note":"123","pay_status":"未确认","type":"提现","pay_fee":"","pay_desc":""}]
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

        public static class DataBean {
            /**
             * id : 3886
             * user_id : 2285
             * admin_user :
             * amount : 200.00
             * add_time : 2016-12-27
             * paid_time : 0
             * admin_note :
             * user_note : 123
             * process_type : 1
             * payment :
             * is_paid : 0
             * cancel : 0
             * short_admin_note : N/A
             * short_user_note : 123
             * pay_status : 未确认
             * type : 提现
             * pay_fee :
             * pay_desc :
             */

            private String id;
            private String user_id;
            private String admin_user;
            private String amount;
            private String add_time;
            private String paid_time;
            private String admin_note;
            private String user_note;
            private String process_type;
            private String payment;
            private String is_paid;
            private String cancel;
            private String short_admin_note;
            private String short_user_note;
            private String pay_status;
            private String type;
            private String pay_fee;
            private String pay_desc;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getAdmin_user() {
                return admin_user;
            }

            public void setAdmin_user(String admin_user) {
                this.admin_user = admin_user;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getPaid_time() {
                return paid_time;
            }

            public void setPaid_time(String paid_time) {
                this.paid_time = paid_time;
            }

            public String getAdmin_note() {
                return admin_note;
            }

            public void setAdmin_note(String admin_note) {
                this.admin_note = admin_note;
            }

            public String getUser_note() {
                return user_note;
            }

            public void setUser_note(String user_note) {
                this.user_note = user_note;
            }

            public String getProcess_type() {
                return process_type;
            }

            public void setProcess_type(String process_type) {
                this.process_type = process_type;
            }

            public String getPayment() {
                return payment;
            }

            public void setPayment(String payment) {
                this.payment = payment;
            }

            public String getIs_paid() {
                return is_paid;
            }

            public void setIs_paid(String is_paid) {
                this.is_paid = is_paid;
            }

            public String getCancel() {
                return cancel;
            }

            public void setCancel(String cancel) {
                this.cancel = cancel;
            }

            public String getShort_admin_note() {
                return short_admin_note;
            }

            public void setShort_admin_note(String short_admin_note) {
                this.short_admin_note = short_admin_note;
            }

            public String getShort_user_note() {
                return short_user_note;
            }

            public void setShort_user_note(String short_user_note) {
                this.short_user_note = short_user_note;
            }

            public String getPay_status() {
                return pay_status;
            }

            public void setPay_status(String pay_status) {
                this.pay_status = pay_status;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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
        }
    }

}
