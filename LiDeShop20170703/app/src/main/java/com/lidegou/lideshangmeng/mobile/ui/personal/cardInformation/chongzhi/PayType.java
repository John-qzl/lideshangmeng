package com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.chongzhi;

/**
 * Created by Y on 2017/1/21.
 */

public class PayType {
    private String pay_id;
    private String pay_code;
    private String pay_name;
    private String pay_fee;
    private String pay_desc;

    public PayType(String pay_id, String pay_code, String pay_name, String pay_fee, String pay_desc) {
        this.pay_id = pay_id;
        this.pay_code = pay_code;
        this.pay_name = pay_name;
        this.pay_fee = pay_fee;
        this.pay_desc = pay_desc;
    }

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
}
