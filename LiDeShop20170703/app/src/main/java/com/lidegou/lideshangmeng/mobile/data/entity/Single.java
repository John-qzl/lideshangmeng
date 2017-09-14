package com.lidegou.lideshangmeng.mobile.data.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class Single {
    private int totalPage;
    private List<Data> data;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        /**
         * id : 2099
         * shop_user_id : 2285
         * buy_user_id : 1221
         * jine : 20
         * real_pay : 0.00
         * goodsname : 11
         * goodsnum : 11
         * qingdanbianhao : 11
         * goodsinfo : 11
         * is_me : 1
         * img : /data/zuodan/
         * is_pay : 0
         * is_separate : 0
         * is_cancel : 0
         * addtime : 1482991867
         * paytime :
         * canceltime :
         * shop_name :
         * order_status : 未支付
         * handler :
         * buy_name : 15560751512
         * jifen : 2
         * adddate : 2016-12-29 14:11
         */

        private String id;
        private String shop_user_id;
        private String buy_user_id;
        private String jine;
        private String real_pay;
        private String goodsname;
        private String goodsnum;
        private String qingdanbianhao;
        private String goodsinfo;
        private String is_me;
        private String img;
        private String is_pay;
        private String is_separate;
        private String is_cancel;
        private String addtime;
        private String paytime;
        private String canceltime;
        private String shop_name;
        private String order_status;
        private String handler;
        private String buy_name;
        private double jifen;
        private String adddate;
        private String is_auditing;
        private String remark;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShop_user_id() {
            return shop_user_id;
        }

        public void setShop_user_id(String shop_user_id) {
            this.shop_user_id = shop_user_id;
        }

        public String getBuy_user_id() {
            return buy_user_id;
        }

        public void setBuy_user_id(String buy_user_id) {
            this.buy_user_id = buy_user_id;
        }

        public String getJine() {
            return jine;
        }

        public void setJine(String jine) {
            this.jine = jine;
        }

        public String getReal_pay() {
            return real_pay;
        }

        public void setReal_pay(String real_pay) {
            this.real_pay = real_pay;
        }

        public String getGoodsname() {
            return goodsname;
        }

        public void setGoodsname(String goodsname) {
            this.goodsname = goodsname;
        }

        public String getGoodsnum() {
            return goodsnum;
        }

        public void setGoodsnum(String goodsnum) {
            this.goodsnum = goodsnum;
        }

        public String getQingdanbianhao() {
            return qingdanbianhao;
        }

        public void setQingdanbianhao(String qingdanbianhao) {
            this.qingdanbianhao = qingdanbianhao;
        }

        public String getGoodsinfo() {
            return goodsinfo;
        }

        public void setGoodsinfo(String goodsinfo) {
            this.goodsinfo = goodsinfo;
        }

        public String getIs_me() {
            return is_me;
        }

        public void setIs_me(String is_me) {
            this.is_me = is_me;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(String is_pay) {
            this.is_pay = is_pay;
        }

        public String getIs_separate() {
            return is_separate;
        }

        public void setIs_separate(String is_separate) {
            this.is_separate = is_separate;
        }

        public String getIs_cancel() {
            return is_cancel;
        }

        public void setIs_cancel(String is_cancel) {
            this.is_cancel = is_cancel;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getPaytime() {
            return paytime;
        }

        public void setPaytime(String paytime) {
            this.paytime = paytime;
        }

        public String getCanceltime() {
            return canceltime;
        }

        public void setCanceltime(String canceltime) {
            this.canceltime = canceltime;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getHandler() {
            return handler;
        }

        public void setHandler(String handler) {
            this.handler = handler;
        }

        public String getBuy_name() {
            return buy_name;
        }

        public void setBuy_name(String buy_name) {
            this.buy_name = buy_name;
        }

        public double getJifen() {
            return jifen;
        }

        public void setJifen(double jifen) {
            this.jifen = jifen;
        }

        public String getAdddate() {
            return adddate;
        }

        public void setAdddate(String adddate) {
            this.adddate = adddate;
        }

        public String getIs_auditing() {
            return is_auditing;
        }

        public void setIs_auditing(String is_auditing) {
            this.is_auditing = is_auditing;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public class CeilingMoney {

            /**
             * user_money : 0.00
             * money_limit : 50000
             */

            private String user_money;
            private String money_limit;
            private String text;

            public String getUser_money() {
                return user_money;
            }

            public void setUser_money(String user_money) {
                this.user_money = user_money;
            }

            public String getMoney_limit() {
                return money_limit;
            }

            public void setMoney_limit(String money_limit) {
                this.money_limit = money_limit;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }

        public static class PaySingle {

            /**
             * user_money : 0.00
             * need_pay_money : 2.4
             * payinfo : {"jine":"20","total_zhifu":2.4,"zhifumoney":"2.00","zhifumoney_other":"0.40"}
             * id : 2099
             */

            private String user_money;
            private double need_pay_money;
            private PayinfoBean payinfo;
            private String id;

            public String getUser_money() {
                return user_money;
            }

            public void setUser_money(String user_money) {
                this.user_money = user_money;
            }

            public double getNeed_pay_money() {
                return need_pay_money;
            }

            public void setNeed_pay_money(double need_pay_money) {
                this.need_pay_money = need_pay_money;
            }

            public PayinfoBean getPayinfo() {
                return payinfo;
            }

            public void setPayinfo(PayinfoBean payinfo) {
                this.payinfo = payinfo;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public class PayinfoBean {
                /**
                 * jine : 20
                 * total_zhifu : 2.4
                 * zhifumoney : 2.00
                 * zhifumoney_other : 0.40
                 */

                private String jine;
                private double total_zhifu;
                private String zhifumoney;
                private String zhifumoney_other;

                public String getJine() {
                    return jine;
                }

                public void setJine(String jine) {
                    this.jine = jine;
                }

                public double getTotal_zhifu() {
                    return total_zhifu;
                }

                public void setTotal_zhifu(double total_zhifu) {
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
        }
    }
}