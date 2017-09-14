package com.lidegou.lideshangmeng.mobile.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */

public class UserInfo implements Serializable {


    /**
     * is_merchants : 1
     * totla_xiaofei : 0
     * totla_fandian : 0
     * now_fandian : 0
     * yi_fandian : 0
     * bonus_money : 0.00
     * turn_money : 0
     * todaylog_new : 0
     * user_money : 0.25
     * goods_num :
     * store_num :
     * user_info : {"user_id":"1221","aite_id":"","email":"530017831@qq.com","user_name":"15560751512","password":"9dbe6e51aeaa4b4bdbacdda8cfb8a3c4","question":"","answer":"","sex":"1","birthday":"1956-01-01","user_money":"0.25","frozen_money":"0.00","freeze_type":"0","freeze_time":"0","pay_points":"0","rank_points":"0","address_id":"98","reg_time":"1478134643","last_login":"1481788295","last_time":"0000-00-00 00:00:00","last_ip":"221.222.186.79","visit_count":"68","user_rank":"0","is_special":"0","ec_salt":"1585","salt":"0","parent_id":"155","flag":"0","alias":"","msn":"","qq":"","office_phone":"","home_phone":"","mobile_phone":"15560751512","is_validated":"0","credit_line":"0.00","passwd_question":"0","passwd_answer":"0","user_picture":"","country":"1","province":"19","city":"265","district":"2223","fan_money":"0.00","bonus_money":"0.00","totla_fandian":"0","now_fandian":"0","total_xiaofei":"0.00","yi_fandian":"0"}
     * pay_count : 0
     * confirmed_count : 1
     * not_comment : 0
     * admin_count : 0
     */

    private String is_merchants;
    private String shop_name;
    private String totla_xiaofei;
    private String totla_fandian;
    private String now_fandian;
    private String yi_fandian;
    private String bonus_money;
    private String turn_money;
    private String todaylog_new;
    private String user_money;
    private String goods_num;
    private String store_num;
    private List<UserInfoBean> userInfoBeanList;
    private String pay_count;
    private String confirmed_count;
    private String not_comment;
    private String admin_count;
    private String img;
    private String title;
    private String url;

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIs_merchants() {
        return is_merchants;
    }

    public void setIs_merchants(String is_merchants) {
        this.is_merchants = is_merchants;
    }

    public String getTotla_xiaofei() {
        return totla_xiaofei;
    }

    public void setTotla_xiaofei(String totla_xiaofei) {
        this.totla_xiaofei = totla_xiaofei;
    }

    public String getTotla_fandian() {
        return totla_fandian;
    }

    public void setTotla_fandian(String totla_fandian) {
        this.totla_fandian = totla_fandian;
    }

    public String getNow_fandian() {
        return now_fandian;
    }

    public void setNow_fandian(String now_fandian) {
        this.now_fandian = now_fandian;
    }

    public String getYi_fandian() {
        return yi_fandian;
    }

    public void setYi_fandian(String yi_fandian) {
        this.yi_fandian = yi_fandian;
    }

    public String getBonus_money() {
        return bonus_money;
    }

    public void setBonus_money(String bonus_money) {
        this.bonus_money = bonus_money;
    }

    public String getTurn_money() {
        return turn_money;
    }

    public void setTurn_money(String turn_money) {
        this.turn_money = turn_money;
    }

    public String getTodaylog_new() {
        return todaylog_new;
    }

    public void setTodaylog_new(String todaylog_new) {
        this.todaylog_new = todaylog_new;
    }

    public String getUser_money() {
        return user_money;
    }

    public void setUser_money(String user_money) {
        this.user_money = user_money;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }

    public String getStore_num() {
        return store_num;
    }

    public void setStore_num(String store_num) {
        this.store_num = store_num;
    }

    public List<UserInfoBean> getUserInfoBeanList() {
        return userInfoBeanList;
    }

    public void setUserInfoBeanList(List<UserInfoBean> userInfoBeanList) {
        this.userInfoBeanList = userInfoBeanList;
    }

    public String getPay_count() {
        return pay_count;
    }

    public void setPay_count(String pay_count) {
        this.pay_count = pay_count;
    }

    public String getConfirmed_count() {
        return confirmed_count;
    }

    public void setConfirmed_count(String confirmed_count) {
        this.confirmed_count = confirmed_count;
    }

    public String getNot_comment() {
        return not_comment;
    }

    public void setNot_comment(String not_comment) {
        this.not_comment = not_comment;
    }

    public String getAdmin_count() {
        return admin_count;
    }

    public void setAdmin_count(String admin_count) {
        this.admin_count = admin_count;
    }

    public static class UserInfoBean implements Serializable {


        /**
         * user_id : 3311
         * aite_id :
         * email :
         * user_name : 13261711612
         * password : a38b91c8f6a7d331fa3c8798cff1024f
         * question :
         * answer :
         * sex : 0
         * birthday : 0000-00-00
         * ID_card :
         * ID_name :
         * user_money : 0.00
         * frozen_money : 0.00
         * freeze_type : 0
         * freeze_time : 0
         * pay_points : 0
         * rank_points : 0
         * address_id : 0
         * reg_time : 1494412999
         * last_login : 1494471314
         * last_time : 0000-00-00 00:00:00
         * last_ip : 61.48.184.174
         * visit_count : 15
         * user_rank : 0
         * is_special : 0
         * ec_salt : 6030
         * salt : 0
         * parent_id : 840
         * flag : 0
         * alias :
         * msn :
         * qq :
         * office_phone :
         * home_phone :
         * mobile_phone : 13261711612
         * is_validated : 0
         * credit_line : 0.00
         * passwd_question : null
         * passwd_answer : null
         * user_picture : http://www.lidegou.com/data/images_user/user-image.jpg
         * country : 0
         * province : 6
         * city : 79
         * district : 716
         * need_tixian_money : 0.00
         * fan_money : 0.00
         * bonus_money : 0.00
         * totla_fandian : 0
         * now_fandian : 0
         * total_xiaofei : 0.00
         * yi_fandian : 0
         * warning : 0
         * qr_img : http://qr.liantu.com/api.php?bg=f3f3f3&fg=000&el=l&w=800&m=30&text=3311
         */

        private String user_id;
        private String aite_id;
        private String email;
        private String user_name;
        private String password;
        private String question;
        private String answer;
        private String sex;
        private String birthday;
        private String ID_card;
        private String ID_name;
        private String user_money;
        private String frozen_money;
        private String freeze_type;
        private String freeze_time;
        private String pay_points;
        private String rank_points;
        private String address_id;
        private String reg_time;
        private String last_login;
        private String last_time;
        private String last_ip;
        private String visit_count;
        private String user_rank;
        private String is_special;
        private String ec_salt;
        private String salt;
        private String parent_id;
        private String flag;
        private String alias;
        private String msn;
        private String qq;
        private String office_phone;
        private String home_phone;
        private String mobile_phone;
        private String is_validated;
        private String credit_line;
        private Object passwd_question;
        private Object passwd_answer;
        private String user_picture;
        private String country;
        private String province;
        private String city;
        private String district;
        private String need_tixian_money;
        private String fan_money;
        private String bonus_money;
        private String totla_fandian;
        private String now_fandian;
        private String total_xiaofei;
        private String yi_fandian;
        private String warning;
        private String qr_img;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getAite_id() {
            return aite_id;
        }

        public void setAite_id(String aite_id) {
            this.aite_id = aite_id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getID_card() {
            return ID_card;
        }

        public void setID_card(String ID_card) {
            this.ID_card = ID_card;
        }

        public String getID_name() {
            return ID_name;
        }

        public void setID_name(String ID_name) {
            this.ID_name = ID_name;
        }

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getFrozen_money() {
            return frozen_money;
        }

        public void setFrozen_money(String frozen_money) {
            this.frozen_money = frozen_money;
        }

        public String getFreeze_type() {
            return freeze_type;
        }

        public void setFreeze_type(String freeze_type) {
            this.freeze_type = freeze_type;
        }

        public String getFreeze_time() {
            return freeze_time;
        }

        public void setFreeze_time(String freeze_time) {
            this.freeze_time = freeze_time;
        }

        public String getPay_points() {
            return pay_points;
        }

        public void setPay_points(String pay_points) {
            this.pay_points = pay_points;
        }

        public String getRank_points() {
            return rank_points;
        }

        public void setRank_points(String rank_points) {
            this.rank_points = rank_points;
        }

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }

        public String getReg_time() {
            return reg_time;
        }

        public void setReg_time(String reg_time) {
            this.reg_time = reg_time;
        }

        public String getLast_login() {
            return last_login;
        }

        public void setLast_login(String last_login) {
            this.last_login = last_login;
        }

        public String getLast_time() {
            return last_time;
        }

        public void setLast_time(String last_time) {
            this.last_time = last_time;
        }

        public String getLast_ip() {
            return last_ip;
        }

        public void setLast_ip(String last_ip) {
            this.last_ip = last_ip;
        }

        public String getVisit_count() {
            return visit_count;
        }

        public void setVisit_count(String visit_count) {
            this.visit_count = visit_count;
        }

        public String getUser_rank() {
            return user_rank;
        }

        public void setUser_rank(String user_rank) {
            this.user_rank = user_rank;
        }

        public String getIs_special() {
            return is_special;
        }

        public void setIs_special(String is_special) {
            this.is_special = is_special;
        }

        public String getEc_salt() {
            return ec_salt;
        }

        public void setEc_salt(String ec_salt) {
            this.ec_salt = ec_salt;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getMsn() {
            return msn;
        }

        public void setMsn(String msn) {
            this.msn = msn;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getOffice_phone() {
            return office_phone;
        }

        public void setOffice_phone(String office_phone) {
            this.office_phone = office_phone;
        }

        public String getHome_phone() {
            return home_phone;
        }

        public void setHome_phone(String home_phone) {
            this.home_phone = home_phone;
        }

        public String getMobile_phone() {
            return mobile_phone;
        }

        public void setMobile_phone(String mobile_phone) {
            this.mobile_phone = mobile_phone;
        }

        public String getIs_validated() {
            return is_validated;
        }

        public void setIs_validated(String is_validated) {
            this.is_validated = is_validated;
        }

        public String getCredit_line() {
            return credit_line;
        }

        public void setCredit_line(String credit_line) {
            this.credit_line = credit_line;
        }

        public Object getPasswd_question() {
            return passwd_question;
        }

        public void setPasswd_question(Object passwd_question) {
            this.passwd_question = passwd_question;
        }

        public Object getPasswd_answer() {
            return passwd_answer;
        }

        public void setPasswd_answer(Object passwd_answer) {
            this.passwd_answer = passwd_answer;
        }

        public String getUser_picture() {
            return user_picture;
        }

        public void setUser_picture(String user_picture) {
            this.user_picture = user_picture;
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

        public String getNeed_tixian_money() {
            return need_tixian_money;
        }

        public void setNeed_tixian_money(String need_tixian_money) {
            this.need_tixian_money = need_tixian_money;
        }

        public String getFan_money() {
            return fan_money;
        }

        public void setFan_money(String fan_money) {
            this.fan_money = fan_money;
        }

        public String getBonus_money() {
            return bonus_money;
        }

        public void setBonus_money(String bonus_money) {
            this.bonus_money = bonus_money;
        }

        public String getTotla_fandian() {
            return totla_fandian;
        }

        public void setTotla_fandian(String totla_fandian) {
            this.totla_fandian = totla_fandian;
        }

        public String getNow_fandian() {
            return now_fandian;
        }

        public void setNow_fandian(String now_fandian) {
            this.now_fandian = now_fandian;
        }

        public String getTotal_xiaofei() {
            return total_xiaofei;
        }

        public void setTotal_xiaofei(String total_xiaofei) {
            this.total_xiaofei = total_xiaofei;
        }

        public String getYi_fandian() {
            return yi_fandian;
        }

        public void setYi_fandian(String yi_fandian) {
            this.yi_fandian = yi_fandian;
        }

        public String getWarning() {
            return warning;
        }

        public void setWarning(String warning) {
            this.warning = warning;
        }

        public String getQr_img() {
            return qr_img;
        }

        public void setQr_img(String qr_img) {
            this.qr_img = qr_img;
        }
    }
}