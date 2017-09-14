package com.lidegou.lideshangmeng.mobile;

import android.os.Environment;

import com.lidegou.lideshangmeng.mobile.util.NetUtil;


/**
 * App 配置
 *
 * @author Y
 * @version 0.1
 * @date 2016年8月22日09:27:57
 */
public interface Config {

    /**
     * 网络配置
     *
     * @author Y
     * @version 0.1
     * @date 2016年8月22日09:28:17
     */
    class Net {
        //网络状态信息
        public static boolean STATUS;
        //当前网络连接类型
        public static NetUtil.NetType TYPE;

    }

    /**
     * App 缓存位置
     *
     * @author Y
     * @version 0.1
     * @date 2016年8月22日09:29:38
     */
    class ROOT {
        //缓存根目录
        public static final String URL = Environment.getExternalStorageDirectory() + "/LiDeShop";

    }

    /**
     * 用户
     *
     * @author Y
     * @version 0.1
     * @date 2016年8月22日09:30:01
     */
    class User {
        //是否为自动登录
        public static boolean AUTO = true;
        //是否已登陆
        public static boolean STATUS;
        //本地缓存账号标识
        public final static String ACCOUNT = "account";
        //本地缓存密码标识
        public final static String PASSWORD = "password";
        //本地缓存自动登陆标识
        public final static String AUTOLOGIN = "auto_login";

        public final static String UID = "uid";

    }

    /**
     * 正则
     *
     * @author Y
     * @version 0.1
     * @date 2016年8月22日09:31:04
     */
    class Regular {
        //电话
        public static String PHONE = "(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}";
        //邮箱
        public static String EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        //中文
        public static String CHINESE = "[\\u4e00-\\u9fa5]";

    }

    /**
     * 微信
     *
     * @author Y
     * @version 0.1
     * @date 2016年8月22日09:31:36
     */
    class WX {

        public final static String APP_ID = "wx5f3bf01648461290";
        //wx5f3bf01648461290    微信开发平台
        //wx9854c78126c51813    微信公众平台
//        wx9854c78126c51813
        public final static String APP_SECRET = "87329a1823b022fd433b25641cdddde5";
        public static final String MCH_ID = "1432942202";
        //1432942202     微信公众平台
        //1398153302     微信开发平台
        public static final String MCH_KEY = "29c24604610357211780c392d9e97db6";
        //29c24604610357211780c392d9e97db6
    }

    /**
     * QQ
     *
     * @author Y
     * @version 0.1
     * @date 2016年8月22日09:31:48
     */
    class QQ {

        public final static String APP_ID = "1105862500";

        public final static String APP_KEY = "mzi3q73PQtAie6NA";
    }

    /**
     * 微博
     *
     * @author Y
     * @version 0.1
     * @date 2016年8月22日09:32:00
     */
    class WB {

        public final static String APP_KEY = "417763536";

        public final static String REDIRECT_URL = "http://www.sina.com";

        public final static String SCOPE = "email,direct_messages_read,direct_messages_write,friendships_groups_read,friendships_groups_write,statuses_to_me_read,follow_app_official_microblog,invitation_write";

    }


    /**
     * 支付宝
     */
    class Pay_Transure {

        //APP_ID
        public static final String APP_ID = "2016031501214713";
        // 商户PID
        public static final String PARTNER = "2088121084922599";
        // 商户收款账号
        public static final String SELLER = "cxzgbyj@chinazcl.com";
        public static final String PRIVATE_KEY = "\n" +
                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDSr+RFzbSWc84U\n" +
                "RbocPFDPd4GBhj2IrKn+qlca/tFHp3jX/q9W3U5jNylC+AvCtudasch9tfVX1e7q\n" +
                "lQFGE5zvIjkesplI/zA/4bAe/Ta90oAqnBUUYBa2skmwc8fLo1jQbHAnkCr3Q2Ki\n" +
                "ykMltCA/vQHXXvWFyVi40SdaNB7I/+5FrL0TZmNSVhjy/deCgM3RdEsb4YU96tG1\n" +
                "Ofhhi1G5osKmdXZPW5vpL+lj5iHtLBgeQ8xHpVHlxLfsxRvkNgezdY/5SzrqC8hM\n" +
                "ykDA1TYhelLuJpZmOjS4uiQ5FlVNUvVozrfE8WahHGRqGZj8V9yIezVgnART+41+\n" +
                "ocDTGFcZAgMBAAECggEBAI+lYwMAtG6b+Fa0D6curH43YbH4371u09yRMSBzuiab\n" +
                "lXXW7J9R9h8JPfWt/v68C9M3QZYMWIGRl/qSqzKnLqmQcNdwN7igXLpBWv5V8FvN\n" +
                "yJrEnk6tEO9xcXKADinWbjh0pqci0ViStg59zvhG7nHmoPnBga68z+P6nlUPEipB\n" +
                "r+c+WyuxH3wa8nR3CMLLmpiSsXTA19vjbyreYKoipBwdrzEy7tACpVzDdYfEw9St\n" +
                "+hyVZ2SYX8M7UsPXXfM8uFwEFMd7EL0LeheNvEKXNL+ggKKJkoQFEY5878xCNUcm\n" +
                "x29yTqiy66+s0HTKbtdGn+MrRuSMZvfc9szN1zTlMPECgYEA7QG56CLDJx3gB64D\n" +
                "SufWC9DgVmaAdzsUzG4ZyQ+3VD1ycSm5RehdlHiBXPSS+S4zBwvN64xFPaiGKbbF\n" +
                "g5+MIqmZPc7Bv3iV8g3KnHWAxcD/4Gsj60rZy45fyRmGY7Sk8oUlA4r2GiwsiBCS\n" +
                "0bEcF65+yVgpdMmNYekm+7SOPF0CgYEA45I1Zz3mjYZKIeM8JGDv4QN/itur9JYR\n" +
                "/nswpFSz0azJEGRm+87iRMrWmxVPmQ3MCTbPQReYOUCfxm/UJTc5h3y05LkMGKoj\n" +
                "dgMAvivhG3V69KmxcFU6Ki49g2YW29WsSI5AtBbFp0vccY2VQ57HynS1i4zzrYU3\n" +
                "lXqbENJE+e0CgYEAwbF/da+zPey0gpI8LqN50v5J2VK7+rVWMhxpBmDJuxbcyI8I\n" +
                "05ErqYMydf2LgAm5ujBzgeWJUPLBcqXklcV2iX2Bt3mZ7hlmsgODaoq9SuCIPahS\n" +
                "GqF5jVo1/evWYPXpOCqILXUL3Cft3OVul+DezJvXEkXy23c9DJ+3NE5DUCECgYBz\n" +
                "I1hNoe3yEM2VE6ksJrd3OxPep4H6v6re/GkB27EXFrqrxoCsSmIEuDOYR1H8eBSR\n" +
                "XN/DaTKlVOMBvTaylZxPxnKnflB2ci562M4ZaY39HJVXEDwsP5KsILUStCZAl30R\n" +
                "QJfQBe1+1Uz1TtMEYWNoNWBc+LWZR1t2D5BBFIG9iQKBgE1jcnhcKl1+Ql18pDwe\n" +
                "J1hzLQQcAK29PfImQYR98gtyfihC0xPgUi5ZUxIDiirHsMIFsqgg3sM9ODYoMFPM\n" +
                "VFU0/ll3Y1pBAVsXfzFCo/yZwM7EH7IsJkIjBF4eZRyCeApub9j4lDJVMB6AG8VZ\n" +
                "yalIXFXspYIlxP9ViHVb7dbY\n";
        public static final String PUBLIC_KEY =
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0q/kRc20lnPOFEW6HDxQ\n" +
                        "z3eBgYY9iKyp/qpXGv7RR6d41/6vVt1OYzcpQvgLwrbnWrHIfbX1V9Xu6pUBRhOc\n" +
                        "7yI5HrKZSP8wP+GwHv02vdKAKpwVFGAWtrJJsHPHy6NY0GxwJ5Aq90NiospDJbQg\n" +
                        "P70B1171hclYuNEnWjQeyP/uRay9E2ZjUlYY8v3XgoDN0XRLG+GFPerRtTn4YYtR\n" +
                        "uaLCpnV2T1ub6S/pY+Yh7SwYHkPMR6VR5cS37MUb5DYHs3WP+Us66gvITMpAwNU2\n" +
                        "IXpS7iaWZjo0uLokORZVTVL1aM63xPFmoRxkahmY/FfciHs1YJwEU/uNfqHA0xhX\n" +
                        "GQIDAQAB\n";
    }


    class Pay {

        //支付状态
        public static final String PAY_STAUTS = "PAY_STAUTS";
        //验证嘻嘻你
        public static final String PAY_MSG = "PAY_MSG";
        //支付订单号
        public static final String PAY_ORDER_NUMBER = "PAY_ORDER_NUMBER";
        //支付订单创建世界
        public static final String PAY_ORDER_TIME = "PAY_ORDER_TIME";

        //支付成功
        public static final String PAY_SUCCESS = "9000";
        //支付等待
        public static final String PAY_WAITING = "8000";
        //支付失败
        public static final String PAY_ERROR = "7000";

    }

}
