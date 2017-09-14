package com.lidegou.lideshangmeng.mobile.pay.wechat;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.data.entity.OtherPayEntity;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * Created by Yi on 2016/5/3.
 */
public class PayWeChat {

    private Context context;
    private App application;
    //订单编号
    public static String orderNumber;
    //订单创建时间
    public static String orderTime;
    private SimpleDateFormat dateFormat;
    public static Handler payHandler;
    private Random random;
    private PayReq payReq;

    public PayWeChat(Context context, App application, Handler payHandler) {
        this.context = context;
        this.application = application;
        PayWeChat.payHandler = payHandler;

        //
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //
        random = new Random();
        payReq = new PayReq();
    }

    /**
     * 微信支付
     *
     * @param name
     * @param body
     * @param pice
     */
    public void pay(String name, String body, String pice) {
        IWXAPI iwxapi = application.getWXAPI();
        if (iwxapi != null) {
            if (!application.getWXAPI().isWXAppInstalled()) {
                Toast.makeText(context, "尚未安装微信", Toast.LENGTH_SHORT).show();
                return;
            }
            iwxapi.registerApp(Config.WX.APP_ID);
        } else {
            return;
        }
//
//        String ip = getWifiIp();
//        if (ip == "" && ip == "") {
//            ip = getLocalIpAddress();
//        }

        final String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

        List<NameValuePair> params = new ArrayList<>();

        orderNumber = genOutTradNo();
        params.add(new BasicNameValuePair("out_trade_no", orderNumber));
        params.add(new BasicNameValuePair("spbill_create_ip", "127.0.0.1"));//115.28.245.4
        params.add(new BasicNameValuePair("total_fee", pice));
        params.add(new BasicNameValuePair("appid", Config.WX.APP_ID));
        params.add(new BasicNameValuePair("body", name));//StringUtil.convertCharset(x, "UTF-8", "ISO-8859-1")
        params.add(new BasicNameValuePair("detail", body));
        params.add(new BasicNameValuePair("mch_id", Config.WX.MCH_ID));
        params.add(new BasicNameValuePair("nonce_str", getNonceStr()));
        params.add(new BasicNameValuePair("notify_url", "http://weixin.qq.com"));
        params.add(new BasicNameValuePair("trade_type", "APP"));
        params.add(new BasicNameValuePair("sign", genPackageSign(params)));

        final String xmlString = toXml(params);
        new AsyncTask<Void, Void, Map<String, String>>() {
            @Override
            protected Map<String, String> doInBackground(Void... params) {
                byte[] buff = Util.httpPost(url, xmlString);
                return decodeXml(new String(buff));
            }

            @Override
            protected void onPostExecute(Map<String, String> stringStringMap) {
                if (stringStringMap != null) {
                    Map<String, String> result = stringStringMap;

                    payReq.appId = Config.WX.APP_ID;
                    payReq.partnerId = Config.WX.MCH_ID;
                    payReq.prepayId = result.get("prepay_id");
                    payReq.nonceStr = getNonceStr();
                    payReq.timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
                    payReq.packageValue = "Sign=WXPay";

                    List<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair("appid", payReq.appId));
                    params.add(new BasicNameValuePair("noncestr", payReq.nonceStr));
                    params.add(new BasicNameValuePair("package", payReq.packageValue));
                    params.add(new BasicNameValuePair("partnerid", payReq.partnerId));
                    params.add(new BasicNameValuePair("prepayid", payReq.prepayId));
                    params.add(new BasicNameValuePair("timestamp", payReq.timeStamp));
                    payReq.sign = genAppSign(params);

                    application.getWXAPI().registerApp(Config.WX.APP_ID);
                    application.getWXAPI().sendReq(payReq);
                    //
                    orderTime = dateFormat.format(new Date(Long.parseLong(payReq.timeStamp)));
                }
            }
        }.execute();
    }

    public void payBack(OtherPayEntity.WeChatEntity entity) {
        payReq.appId = entity.getAppid();
        payReq.partnerId = entity.getPartnerid();
        payReq.prepayId = entity.getPrepayid();
        payReq.nonceStr = entity.getNoncestr();
        payReq.timeStamp = entity.getTimestamp();
        payReq.packageValue = entity.getPackageMsg();
        payReq.sign = entity.getSign();

        application.getWXAPI().registerApp(Config.WX.APP_ID);
        application.getWXAPI().sendReq(payReq);
    }


    /**
     * 生成签名
     *
     * @param params
     * @return
     */
    private String genSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Config.WX.MCH_KEY);

        return getMessageDigest(sb.toString().getBytes()).toUpperCase();
    }


    private String genAppSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.size(); i++) {

            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Config.WX.MCH_KEY);
        sb.append("sign str\n" + sb.toString() + "\n\n");
        String appSign = getMessageDigest(sb.toString().getBytes()).toUpperCase();
        Log.i("orion", appSign);
        return appSign;
    }

    /**
     * 生成签名
     */

    private String genPackageSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Config.WX.MCH_KEY);
        String packageSign = getMessageDigest(sb.toString().getBytes())
                .toUpperCase();
        Log.e("PackageSign", packageSign);
        return packageSign;
    }

    /**
     * Xml转换
     *
     * @param params
     * @return
     */
    private String toXml(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (int i = 0; i < params.size(); i++) {
            sb.append("<" + params.get(i).getName() + ">");
            sb.append(params.get(i).getValue());
            sb.append("</" + params.get(i).getName() + ">");
        }
        sb.append("</xml>");
        try {
            return new String(sb.toString().getBytes(), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * XML解析
     *
     * @param content
     * @return
     */
    public Map<String, String> decodeXml(String content) {

        try {
            Map<String, String> xml = new HashMap<String, String>();
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(new StringReader(content));
            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {

                String nodeName = parser.getName();
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:

                        break;
                    case XmlPullParser.START_TAG:

                        if ("xml".equals(nodeName) == false) {
                            xml.put(nodeName, parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                event = parser.next();
            }

            return xml;
        } catch (Exception e) {
            Log.e("orion", e.toString());
        }
        return null;
    }

    /**
     * 随机字符串
     *
     * @return
     */
    private String getNonceStr() {
        return getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    /**
     * 商户订单号
     *
     * @return
     */
    private String genOutTradNo() {
        return getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    /**
     * 随机字符生成
     *
     * @param buffer
     * @return
     */
    public final static String getMessageDigest(byte[] buffer) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(buffer);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 获取设备的ip地址
     *
     * @return
     */
    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
        }
        return null;
    }

    private String getWifiIp() {
        // 获取wifi服务
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        // 判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        return ip;
    }

    /**
     * 转化成Ip地址的格式
     *
     * @param i
     * @return
     */
    private String intToIp(int i) {

        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + (i >> 24 & 0xFF);
    }

}
