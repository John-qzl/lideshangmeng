package com.lidegou.lideshangmeng.mobile.data.dao.impl;

import android.util.Log;

import com.google.gson.Gson;
import com.jiongbull.jlog.JLog;
import com.lidegou.lideshangmeng.mobile.data.HttpInterface;
import com.lidegou.lideshangmeng.mobile.data.HttpUtil;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IMyDao;
import com.lidegou.lideshangmeng.mobile.data.entity.AccountDetails;
import com.lidegou.lideshangmeng.mobile.data.entity.BankCard;
import com.lidegou.lideshangmeng.mobile.data.entity.Footprint;
import com.lidegou.lideshangmeng.mobile.data.entity.IntegralEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsIn;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsInFiveEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsInFourEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.MessageOrderntity;
import com.lidegou.lideshangmeng.mobile.data.entity.MessageSysEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.MoneyManage;
import com.lidegou.lideshangmeng.mobile.data.entity.OtherPayEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.PaySinleEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.PayText;
import com.lidegou.lideshangmeng.mobile.data.entity.RecommendEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.ServiceBean;
import com.lidegou.lideshangmeng.mobile.data.entity.SetEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.Single;
import com.lidegou.lideshangmeng.mobile.data.entity.UserInfo;
import com.lidegou.lideshangmeng.mobile.fragment.My.paymoney.PaymoneyActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.chongzhi.PayType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/17.
 */

public class IMyDaoImpl extends HttpUtil implements IMyDao {
    Map<String, String> map = new HashMap<>();

    @Override
    public void getUserInfo(String uid, final ResponseCallback<List<UserInfo>> responseCallback) {
        map.put("uid", uid);
        POST(HttpInterface.getUserInfo(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject object = new JSONObject(result);
                    List<UserInfo> userInfoList = new ArrayList<UserInfo>();
                    for (int i = 0; i < object.length(); i++) {
                        List<UserInfo.UserInfoBean> userInfoBeanList = new ArrayList<UserInfo.UserInfoBean>();
                        JSONObject json = object.getJSONObject("user_info");
                        for (int j = 0; j < json.length(); j++) {
                            UserInfo.UserInfoBean userInfoBean = getObj(json, UserInfo.UserInfoBean.class);
                            userInfoBeanList.add(userInfoBean);
                        }
                        UserInfo userInfo = getObj(object, UserInfo.class);
                        userInfo.setUserInfoBeanList(userInfoBeanList);
                        userInfoList.add(userInfo);
                    }
                    responseCallback.onSuccess(userInfoList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void getUserName(String id, final ResponseCallback<ArrayList<String>> responseCallback) {
        String url = "http://www.lidegou.com/app/index.php?r=user/index/GetQrInfo&qr_uid=" + id;
        GET(url, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject object = new JSONObject(result);
                    int code = object.getInt("code");
                    if (code == 100) {
                        ArrayList<String> list = new ArrayList<String>();
                        JSONObject nameObj = object.getJSONObject("name");
                        String mobile = nameObj.getString("mobile");
                        String name = nameObj.getString("name");
                        list.add(mobile);
                        list.add(name);
                        responseCallback.onSuccess(list);
                    } else {
                        responseCallback.onFailure(code, object.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void getFootprint(String uid, final ResponseCallback<List<Footprint>> responseCallback) {
        map.put("uid", uid);
        POST(HttpInterface.getFootprint(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    Log.e("result", result + "");
                    JSONArray jsonArray = new JSONArray(result);
                    List<Footprint> footprintList = new ArrayList<Footprint>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.optJSONObject(i);
                        if (jsonObject == null) {
                            break;
                        }
                        Footprint footprint = getObj(jsonObject, Footprint.class);
                        footprintList.add(footprint);
                    }
                    responseCallback.onSuccess(footprintList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void clearFootprint(String uid, final ResponseCallback<String> responseCallback) {
        map.put("uid", uid);
        POST(HttpInterface.clearFootprint(), map, new HttpUtil.Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.optInt("code");
                    if (code == 100) {
                        responseCallback.onSuccess(jsonObject.optString("msg"));
                    } else {
                        responseCallback.onFailure(code, jsonObject.optString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void getMoneyManage2(String uid, final ResponseCallback<MoneyManage> responseCallback) {
        map.put("uid", uid);
        POST(HttpInterface.getMoneyManage2(), map, new Callback() {
            @Override
            public void callback(String result) {
                Log.i("whfyy", "00000" + result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    MoneyManage moneyManage = new MoneyManage();
                    moneyManage.setSurplus_amount(jsonObject.getString("user_money"));
                    responseCallback.onSuccess(moneyManage);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, responseCallback);
    }

    @Override
    public void getMoneyManage(String uid, final ResponseCallback<MoneyManage> responseCallback) {
        map.put("uid", uid);
        POST(HttpInterface.getMoneyManage(), map, new Callback() {
            @Override
            public void callback(String result) {
                Gson gson = new Gson();
                MoneyManage moneyManage = gson.fromJson(result, MoneyManage.class);
                responseCallback.onSuccess(moneyManage);
            }
        }, responseCallback);
    }

    @Override
    public void getBayType(String uid, String pr_uid, ResponseCallback<String> responseCallback, final PaymoneyActivity activity) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("qr_uid", pr_uid);
        POST(HttpInterface.getBayType(), map, new Callback() {
            @Override
            public void callback(String result) {
                Log.i("whfyy", "----" + result);
                Gson gson = new Gson();
                PayText payText = gson.fromJson(result, PayText.class);
                activity.seSeccess(payText);
            }
        }, responseCallback);
    }

    @Override
    public void gettoPay(String usemoney, String jine, final String type, String uid, String shop_uid, ResponseCallback<String> responseCallback, final PaymoneyActivity activity) {
        Map<String, String> map = new HashMap<>();
        map.put("usemoney", usemoney);
        map.put("jine", jine);
        map.put("type", type);
        map.put("uid", uid);
        map.put("shop_uid", shop_uid);
        POST(HttpInterface.getToBay(), map, new Callback() {
            @Override
            public void callback(String result) {
                Log.i("whfyy", "支付啦" + result);


                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);

                    OtherPayEntity entity = new OtherPayEntity();
                    if (type.equals("9")) {
                        int code = jsonObject.getInt("code");
                        if (code == 100) {
                            activity.onSuccessLoast();
                        } else {
                            String msg = jsonObject.getString("msg");
                            activity.onErrorLoast(msg);
                        }
                    }

                    if (type.equals("12")) {
                        try {
                            jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            activity.onErrorLoast(msg);
                        } catch (Exception e) {
                            String orderAmount = jsonObject.getString("orderAmount");
                            String productName = jsonObject.getString("productName");
                            String ext1 = jsonObject.getString("ext1");
                            String merchantId = jsonObject.getString("merchantId");
                            String orderNo = jsonObject.getString("orderNo");
                            String receiveUrl = jsonObject.getString("receiveUrl");
                            OtherPayEntity.PaaCreatorEntity paaCreatorEntity = new OtherPayEntity.PaaCreatorEntity(orderAmount, productName, ext1, merchantId, orderNo, receiveUrl);
                            entity.setPaaCreatorEntity(paaCreatorEntity);
                            activity.onSuccess(entity);
                        }

                    }
                    if (type.equals("13")) {


                        try {
                            jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            activity.onErrorLoast(msg);
                        } catch (Exception e) {

                            String appid = jsonObject.getString("appid");
                            String noncestr = jsonObject.getString("noncestr");
                            String packageMsg = jsonObject.getString("package");
                            String partnerid = jsonObject.getString("partnerid");
                            String prepayid = jsonObject.getString("prepayid");
                            String timestamp = jsonObject.getString("timestamp");
                            String sign = jsonObject.getString("sign");
                            OtherPayEntity.WeChatEntity weChatEntity = new OtherPayEntity.WeChatEntity(appid, noncestr, packageMsg, partnerid, prepayid, timestamp, sign);
                            entity.setWeChatEntity(weChatEntity);
                            activity.onSuccess(entity);
                        }


                    }
                    if (type.equals("14")) {
                        try {
                            jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            activity.onErrorLoast(msg);
                        } catch (Exception e) {
                            int errorCode = jsonObject.getInt("errorCode");
                            String errorMsg = jsonObject.getString("errorMsg");
                            if (errorCode == 0) {
                                JSONObject responseData = jsonObject.getJSONObject("responseData");
                                String app_response = responseData.getString("app_response");
                                OtherPayEntity.TreasureEntity treasureEntity = new OtherPayEntity.TreasureEntity(app_response);
                                entity.setTreasureEntity(treasureEntity);
                                activity.onSuccess(entity);
                            } else {
                                activity.onFailure(101, errorMsg);
                            }
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, responseCallback);
    }

    @Override
    public void getAccountDetails(String uid, String page, final ResponseCallback<AccountDetails> responseCallback) {
        map.put("uid", uid);
        map.put("page", page);
        POST(HttpInterface.getAccountDetails(), map, new Callback() {
            @Override
            public void callback(String result) {
                JLog.i(result);
                Gson gson = new Gson();
                AccountDetails accountDetails = gson.fromJson(result, AccountDetails.class);
                responseCallback.onSuccess(accountDetails);

            }
        }, responseCallback);
    }

    @Override
    public void addCard(String uid, String bank_code, String bank_card, String bank_user_name, String bank_region, final ResponseCallback<Integer> responseCallback) {
        map.put("uid", uid);
        map.put("bank_code", bank_code);
        map.put("bank_card", bank_card);
        map.put("bank_user_name", bank_user_name);
        map.put("bank_region", bank_region);
        Log.i("IMyDaoImpl", "map:" + map);
        POST(HttpInterface.AddCard(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.optInt("code");
                    if (code == 100) {
                        responseCallback.onSuccess(Integer.parseInt(jsonObject.optString("code")));
                    } else {
                        responseCallback.onFailure(code, jsonObject.optString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void getCardlist(String uid, final ResponseCallback<List<BankCard>> responseCallback) {
        map.put("uid", uid);
        POST(HttpInterface.getCardlist(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    List<BankCard> bankCardList = new ArrayList<BankCard>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.optJSONObject(i);
                        if (jsonObject == null) {
                            break;
                        }
                        BankCard bankCard = getObj(jsonObject, BankCard.class);
                        bankCardList.add(bankCard);
                    }
                    responseCallback.onSuccess(bankCardList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void deleteCardlist(String uid, String Id, final ResponseCallback<Integer> responseCallback) {
        map.put("uid", uid);
        map.put("id", Id);
        POST(HttpInterface.deleteCardlist(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.optInt("code");
                    if (code == 100) {
                        responseCallback.onSuccess(Integer.parseInt(jsonObject.optString("code")));
                    } else {
                        responseCallback.onFailure(code, jsonObject.optString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void addCardInfo(String uid, final ResponseCallback<BankCard.AddCardInfo> responseCallback) {
        map.put("uid", uid);
        POST(HttpInterface.addCardInfo(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject json = jsonObject.getJSONObject("info");
                    BankCard.AddCardInfo.InfoBean infoBean = getObj(json, BankCard.AddCardInfo.InfoBean.class);
                    JSONArray jsonArray = jsonObject.getJSONArray("bank_list");
                    BankCard.AddCardInfo addCardInfo = new BankCard.AddCardInfo();
                    List<BankCard.AddCardInfo.BankListBean> bankListBeanList = new ArrayList<BankCard.AddCardInfo.BankListBean>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.optJSONObject(i);
                        if (jsonObject == null) {
                            break;
                        }
                        BankCard.AddCardInfo.BankListBean bankCard = getObj(object, BankCard.AddCardInfo.BankListBean.class);
                        bankListBeanList.add(bankCard);
                    }
                    addCardInfo.setBank_list(bankListBeanList);
                    addCardInfo.setInfo(infoBean);
                    responseCallback.onSuccess(addCardInfo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void payType(final ResponseCallback<ArrayList<PayType>> responseCallback) {
        POST(HttpInterface.payType(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONArray array = new JSONArray(result);
                    ArrayList<PayType> paytypes = new ArrayList<PayType>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        PayType payType = getObj(object, PayType.class);
                        paytypes.add(payType);
                    }
                    responseCallback.onSuccess(paytypes);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void rechargeMoney(String uid, final String payment_id, String amount, String user_note, String process_type, String type, final ResponseCallback<OtherPayEntity> responseCallback) {
        map.put("uid", uid);
        map.put("payment_id", payment_id);
        map.put("amount", amount);
        map.put("user_note", user_note);
        map.put("process_type", process_type);
        map.put("type", type);
        POST(HttpInterface.rechargeMoney(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    OtherPayEntity entity = new OtherPayEntity();
                    if (payment_id.equals("12")) {
                        String orderAmount = jsonObject.getString("orderAmount");
                        String productName = jsonObject.getString("productName");
                        String ext1 = jsonObject.getString("ext1");
                        String merchantId = jsonObject.getString("merchantId");
                        String orderNo = jsonObject.getString("orderNo");
                        String receiveUrl = jsonObject.getString("receiveUrl");
                        OtherPayEntity.PaaCreatorEntity paaCreatorEntity = new OtherPayEntity.PaaCreatorEntity(orderAmount, productName, ext1, merchantId, orderNo, receiveUrl);
                        entity.setPaaCreatorEntity(paaCreatorEntity);
                        responseCallback.onSuccess(entity);
                    }
                    if (payment_id.equals("13")) {
                        String appid = jsonObject.getString("appid");
                        String noncestr = jsonObject.getString("noncestr");
                        String packageMsg = jsonObject.getString("package");
                        String partnerid = jsonObject.getString("partnerid");
                        String prepayid = jsonObject.getString("prepayid");
                        String timestamp = jsonObject.getString("timestamp");
                        String sign = jsonObject.getString("sign");
                        OtherPayEntity.WeChatEntity weChatEntity = new OtherPayEntity.WeChatEntity(appid, noncestr, packageMsg, partnerid, prepayid, timestamp, sign);
                        entity.setWeChatEntity(weChatEntity);
                        responseCallback.onSuccess(entity);
                    }
                    if (payment_id.equals("14")) {
                        int errorCode = jsonObject.getInt("errorCode");
                        String errorMsg = jsonObject.getString("errorMsg");
                        if (errorCode == 0) {
                            JSONObject responseData = jsonObject.getJSONObject("responseData");
                            String app_response = responseData.getString("app_response");
                            OtherPayEntity.TreasureEntity treasureEntity = new OtherPayEntity.TreasureEntity(app_response);
                            entity.setTreasureEntity(treasureEntity);
                            responseCallback.onSuccess(entity);
                        } else {
                            responseCallback.onFailure(101, errorMsg);
                        }
                    }
                } catch (JSONException e) {
                    responseCallback.onFailure(101, "申请失败");
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void rechargePayMoney(String uid, String id, final String payment, final ResponseCallback<OtherPayEntity> responseCallback) {
        map.put("uid", uid);
        map.put("order_id", id);
        POST(HttpInterface.rechargePayMoney(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    OtherPayEntity entity = new OtherPayEntity();
                    if (payment.equals("12")) {
                        String orderAmount = jsonObject.getString("orderAmount");
                        String productName = jsonObject.getString("productName");
                        String ext1 = jsonObject.getString("ext1");
                        String merchantId = jsonObject.getString("merchantId");
                        String orderNo = jsonObject.getString("orderNo");
                        String receiveUrl = jsonObject.getString("receiveUrl");
                        OtherPayEntity.PaaCreatorEntity paaCreatorEntity = new OtherPayEntity.PaaCreatorEntity(orderAmount, productName, ext1, merchantId, orderNo, receiveUrl);
                        entity.setPaaCreatorEntity(paaCreatorEntity);
                        responseCallback.onSuccess(entity);
                    }
                    if (payment.equals("13")) {
                        String appid = jsonObject.getString("appid");
                        String noncestr = jsonObject.getString("noncestr");
                        String packageMsg = jsonObject.getString("package");
                        String partnerid = jsonObject.getString("partnerid");
                        String prepayid = jsonObject.getString("prepayid");
                        String timestamp = jsonObject.getString("timestamp");
                        String sign = jsonObject.getString("sign");
                        OtherPayEntity.WeChatEntity weChatEntity = new OtherPayEntity.WeChatEntity(appid, noncestr, packageMsg, partnerid, prepayid, timestamp, sign);
                        entity.setWeChatEntity(weChatEntity);
                        responseCallback.onSuccess(entity);
                    }
                    if (payment.equals("14")) {
                        int errorCode = jsonObject.getInt("errorCode");
                        String errorMsg = jsonObject.getString("errorMsg");
                        if (errorCode == 0) {
                            JSONObject responseData = jsonObject.getJSONObject("responseData");
                            String app_response = responseData.getString("app_response");
                            OtherPayEntity.TreasureEntity treasureEntity = new OtherPayEntity.TreasureEntity(app_response);
                            entity.setTreasureEntity(treasureEntity);
                            responseCallback.onSuccess(entity);
                        } else {
                            responseCallback.onFailure(101, errorMsg);
                        }
                    }
                } catch (JSONException e) {
                    responseCallback.onFailure(101, "申请失败");
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }


    @Override
    public void accounTraply(String uid, final ResponseCallback<MoneyManage.Accountraply> responseCallback) {
        map.put("uid", uid);
        POST(HttpInterface.accounTraply(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    MoneyManage.Accountraply accountraply = new MoneyManage.Accountraply();
                    int code = jsonObject.optInt("code");
                    if (code == 100) {
                        String notice = jsonObject.optString("notice");
                        String surplus_amount = jsonObject.optString("surplus_amount");
                        JSONArray jsonArray = jsonObject.getJSONArray("bank");
                        List<MoneyManage.Accountraply.BankBean> bankListBeanList = new ArrayList<MoneyManage.Accountraply.BankBean>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.optJSONObject(i);
                            if (jsonObject == null) {
                                break;
                            }
                            MoneyManage.Accountraply.BankBean bankBean = getObj(object, MoneyManage.Accountraply.BankBean.class);
                            bankListBeanList.add(bankBean);
                        }
                        accountraply.setCode(code);
                        accountraply.setNotice(notice);
                        accountraply.setSurplus_amount(surplus_amount);
                        accountraply.setBank(bankListBeanList);
                        responseCallback.onSuccess(accountraply);
                    } else if (code == 201) {
                        accountraply.setCode(code);
                        accountraply.setMsg(jsonObject.optString("msg"));
                        responseCallback.onSuccess(accountraply);
                    } else {
                        accountraply.setCode(code);
                        accountraply.setMsg(jsonObject.optString("msg"));
                        responseCallback.onSuccess(accountraply);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void submitAccounTraply(String uid, String amount, String user_note, String bank_number, String real_name, final ResponseCallback<Integer> responseCallback) {
        map.put("uid", uid);
        map.put("amount", amount);
        map.put("user_note", user_note);
        map.put("bank_number", bank_number);
        map.put("real_name", real_name);
        map.put("surplus_type", 1 + "");
        POST(HttpInterface.submitAccounTraply(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.optInt("code");
                    if (code == 100) {
                        responseCallback.onSuccess(Integer.parseInt(jsonObject.optString("code")));
                    } else {
                        responseCallback.onFailure(code, jsonObject.optString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void applyRecord(String uid, String page, final ResponseCallback<MoneyManage.ApplyRecord> responseCallback) {
        map.put("uid", uid);
        map.put("page", page);
        POST(HttpInterface.ApplyRecord(), map, new Callback() {
            @Override
            public void callback(String result) {
                Gson gson = new Gson();
                MoneyManage.ApplyRecord applyRecord = gson.fromJson(result, MoneyManage.ApplyRecord.class);
                responseCallback.onSuccess(applyRecord);
            }
        }, responseCallback);
    }

    @Override
    public void applyRecordDetail(String uid, String id, final ResponseCallback<MoneyManage.ApplyRecord.DataBean> responseCallback) {
        map.put("uid", uid);
        map.put("id", id);
        POST(HttpInterface.ApplyRecordDetail(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    MoneyManage.ApplyRecord.DataBean dataBean = getObj(object, MoneyManage.ApplyRecord.DataBean.class);
                    responseCallback.onSuccess(dataBean);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void integral(String uid, String page, final ResponseCallback<IntegralEntity> responseCallback) {
        map.put("uid", uid);
        map.put("page", page);
        POST(HttpInterface.AccountBonus(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    IntegralEntity entity = getObj(object, IntegralEntity.class);
                    responseCallback.onSuccess(entity);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void recommend(String uid, String page, final ResponseCallback<RecommendEntity> responseCallback) {
        map.put("uid", uid);
        map.put("page", page);
        POST(HttpInterface.AccountRecommend(), map, new Callback() {
            @Override
            public void callback(String result) {

                try {
                    JSONObject object = new JSONObject(result);
                    RecommendEntity entity = getObj(object, RecommendEntity.class);
                    responseCallback.onSuccess(entity);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void bonusTurn(String uid, final ResponseCallback<String> responseCallback) {
        map.put("uid", uid);
        POST(HttpInterface.BonusTurn(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    int code = object.getInt("code");
                    String msg = object.getString("msg");
                    if (code == 100) {
                        responseCallback.onSuccess(msg);
                    } else {
                        responseCallback.onFailure(code, msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void getNumber(String uid, final ResponseCallback<String> responseCallback) {
        map.put("uid", uid);
        POST(HttpInterface.getNumber(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {

                    Log.i("whfyy", result);
                    JSONObject object = new JSONObject(result);
                    JSONArray jsonObject = object.getJSONArray("tuijianNum");
                    String msg = jsonObject.getJSONObject(0).getString("count(*)");
                    responseCallback.onSuccess(msg);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void getSingleCeilingMoney(String uid, final ResponseCallback<Single.Data.CeilingMoney> responseCallback) {
        map.put("uid", uid);
        POST(HttpInterface.getCeilingMoney(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    Single.Data.CeilingMoney ceilingMoney = getObj(object, Single.Data.CeilingMoney.class);
                    responseCallback.onSuccess(ceilingMoney);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void getSingleMcheckuser(String uid, String mobile, final ResponseCallback<String> responseCallback) {
        map.put("uid", uid);
        map.put("mobile", mobile);
        POST(HttpInterface.getMcheckuser(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.getInt("code");
                    if (code == 100) {
                        String name = jsonObject.getString("name");
                        responseCallback.onSuccess(name);
                    } else {
                        responseCallback.onFailure(code, jsonObject.optString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void AddSingle(String uid, String mobile, String jine, String goodsname, String goodsnum, String goodsinfo, String qingdanbianhao, String is_me, String zuodan_img, String picType, final ResponseCallback<String> responseCallback) {
        map.put("uid", uid);
        map.put("mobile", mobile);
        map.put("jine", jine);
        map.put("goodsname", goodsname);
        map.put("goodsnum", goodsnum);
        map.put("goodsinfo", goodsinfo);
        map.put("qingdanbianhao", qingdanbianhao);
        map.put("is_me", is_me);
        map.put("zuodan_img", zuodan_img);
        map.put("img_type", picType);
        Log.i("IMyDaoImpl", "map:" + map);
        JLog.i(map.toString());
        POST(HttpInterface.AddSingle(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.getInt("code");
                    if (code == 100) {
                        String name = jsonObject.getString("id");
                        responseCallback.onSuccess(name);
                    } else {
                        responseCallback.onFailure(code, jsonObject.optString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void SingleList(String uid, String page, String status, final ResponseCallback<Single> responseCallback) {
        map.put("uid", uid);
        map.put("page", page);
        map.put("status", status);
        POST(HttpInterface.getSingleList(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject object = new JSONObject(result);
                    Single single = getObj(object, Single.class);
                    responseCallback.onSuccess(single);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void PaySingle(String uid, String id, final ResponseCallback<PaySinleEntity> responseCallback) {
        map.put("uid", uid);
        map.put("id", id);
        POST(HttpInterface.getPaySingle(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    PaySinleEntity entity = getObj(object, PaySinleEntity.class);
                    responseCallback.onSuccess(entity);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void pay(String uid, final String type, String id, final ResponseCallback<OtherPayEntity> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("type", type);
        map.put("id", id);
        POST(HttpInterface.PaySingle(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);

                    JSONObject jsonObject = new JSONObject(result);
                    if (type.equals("9")) {
                        int code = jsonObject.getInt("code");
                        if (code == 100) {
                            responseCallback.onSuccess(null);
                        } else {
                            responseCallback.onFailure(code, jsonObject.getString("msg"));
                        }
                    }
                    OtherPayEntity entity = new OtherPayEntity();
                    if (type.equals("12")) {
                        String orderAmount = jsonObject.getString("orderAmount");
                        String productName = jsonObject.getString("productName");
                        String ext1 = jsonObject.getString("ext1");
                        String merchantId = jsonObject.getString("merchantId");
                        String orderNo = jsonObject.getString("orderNo");
                        String receiveUrl = jsonObject.getString("receiveUrl");
                        OtherPayEntity.PaaCreatorEntity paaCreatorEntity = new OtherPayEntity.PaaCreatorEntity(orderAmount, productName, ext1, merchantId, orderNo, receiveUrl);
                        entity.setPaaCreatorEntity(paaCreatorEntity);
                        responseCallback.onSuccess(entity);
                    }
                    if (type.equals("13")) {
                        String appid = jsonObject.getString("appid");
                        String noncestr = jsonObject.getString("noncestr");
                        String packageMsg = jsonObject.getString("package");
                        String partnerid = jsonObject.getString("partnerid");
                        String prepayid = jsonObject.getString("prepayid");
                        String timestamp = jsonObject.getString("timestamp");
                        String sign = jsonObject.getString("sign");
                        OtherPayEntity.WeChatEntity weChatEntity = new OtherPayEntity.WeChatEntity(appid, noncestr, packageMsg, partnerid, prepayid, timestamp, sign);
                        entity.setWeChatEntity(weChatEntity);
                        responseCallback.onSuccess(entity);
                    }
                    if (type.equals("14")) {
                        int errorCode = jsonObject.getInt("errorCode");
                        String errorMsg = jsonObject.getString("errorMsg");
                        if (errorCode == 0) {
                            JSONObject responseData = jsonObject.getJSONObject("responseData");
                            String app_response = responseData.getString("app_response");
                            OtherPayEntity.TreasureEntity treasureEntity = new OtherPayEntity.TreasureEntity(app_response);
                            entity.setTreasureEntity(treasureEntity);
                            responseCallback.onSuccess(entity);
                        } else {
                            responseCallback.onFailure(101, errorMsg);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void addmessage(String uid, String msg_title, String device_type, final ResponseCallback<String> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("msg_title", msg_title);
        map.put("device_type", "1");
        POST(HttpInterface.addmessage(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.getInt("code");
                    if (code == 100) {
                        String msg = jsonObject.optString("msg");
                        responseCallback.onSuccess(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void messageList(String uid, String page, final ResponseCallback responseCallback) {
        map.put("uid", uid);
        map.put("page", page);
        POST(HttpInterface.messageList(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    ServiceBean serviceBean = getObj(object, ServiceBean.class);
                    responseCallback.onSuccess(serviceBean);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void MerchantsInCheck(String uid, final ResponseCallback<MerchantsIn.MerchantsInCheck> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        POST(HttpInterface.MerchantsInCheck(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject object = new JSONObject(result);
                    MerchantsIn.MerchantsInCheck merchantsInCheck = getObj(object, MerchantsIn.MerchantsInCheck.class);
                    responseCallback.onSuccess(merchantsInCheck);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void MerchantsInOne(String uid, final ResponseCallback<MerchantsIn.MerchantsInProtocol> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        POST(HttpInterface.MerchantsInOne(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    MerchantsIn.MerchantsInProtocol merchantsInProtocol = getObj(object, MerchantsIn.MerchantsInProtocol.class);
                    responseCallback.onSuccess(merchantsInProtocol);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void MerchantsInOnedone(String uid, final ResponseCallback<String> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        POST(HttpInterface.MerchantsInOneDone(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.getInt("code");
                    if (code == 100) {
                        String msg = jsonObject.optString("msg");
                        responseCallback.onSuccess(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void MerchantsInTwo(String uid, final ResponseCallback<MerchantsIn.MerchantsInContact> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        POST(HttpInterface.MerchantsInTwo(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    MerchantsIn.MerchantsInContact merchantsInContact = getObj(jsonObject, MerchantsIn.MerchantsInContact.class);
                    responseCallback.onSuccess(merchantsInContact);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void MerchantsInTwodone(String uid, String contactXinbie, String contactPhone, String contactName, final ResponseCallback<String> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("contactXinbie", contactXinbie);
        map.put("contactPhone", contactPhone);
        map.put("contactName", contactName);
        POST(HttpInterface.MerchantsInTwoDone(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.getInt("code");
                    if (code == 100) {
                        String msg = jsonObject.optString("msg");
                        responseCallback.onSuccess(msg);
                    } else {
                        String msg = jsonObject.optString("msg");
                        responseCallback.onFailure(code, msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void MerchantsInThree(String uid, final ResponseCallback<MerchantsIn.MerchantsInCompany> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        POST(HttpInterface.MerchantsInThree(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    MerchantsIn.MerchantsInCompany merchantsInCompany = getObj(jsonObject, MerchantsIn.MerchantsInCompany.class);
                    responseCallback.onSuccess(merchantsInCompany);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void MerchantsInThreedone(String uid, String companyName, String business_license_id, String legal_person, String busines_scope, String license_fileImg, String company_adress, String company_contactTel, String province, String city, String district, String longitude, String latitude, final ResponseCallback<String> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("companyName", companyName);
        map.put("business_license_id", business_license_id);
        map.put("legal_person", legal_person);
        map.put("busines_scope", busines_scope);
        map.put("license_fileImg", license_fileImg);
        map.put("company_adress", company_adress);
        map.put("company_contactTel", company_contactTel);
        map.put("province", province);
        map.put("city", city);
        map.put("district", district);
        map.put("longitude", longitude);
        map.put("latitude", latitude);
        Log.i("Imap", map + "");
        POST(HttpInterface.MerchantsInThreeDone(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.getInt("code");
                    String msg = jsonObject.optString("msg");
                    if (code == 100) {
                        responseCallback.onSuccess(msg);
                    } else {
                        responseCallback.onFailure(code, msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void MerchantsInFour(String uid, final ResponseCallback<MerchantsInFourEntity> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        POST(HttpInterface.MerchantsInFour(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    MerchantsInFourEntity entity = new MerchantsInFourEntity();
                    Gson gson = new Gson();

                    JSONArray cats = jsonObject.getJSONArray("cats");
                    ArrayList<MerchantsInFourEntity.CatsData> catList = new ArrayList<MerchantsInFourEntity.CatsData>();
                    for (int i = 0; i < cats.length(); i++) {
                        String object = cats.getString(i);
                        MerchantsInFourEntity.CatsData cat = gson.fromJson(object, MerchantsInFourEntity.CatsData.class);
                        catList.add(cat);
                    }

                    JSONArray category_info = jsonObject.getJSONArray("category_info");
                    List<MerchantsInFourEntity.Cats_Son> category_infos = new ArrayList<MerchantsInFourEntity.Cats_Son>();
                    for (int i = 0; i < category_info.length(); i++) {
                        String object = category_info.getString(i);
                        MerchantsInFourEntity.Cats_Son cats_son = gson.fromJson(object, MerchantsInFourEntity.Cats_Son.class);
                        category_infos.add(cats_son);
                    }
                    MerchantsInFourEntity.ShopInfo shopInfo = new MerchantsInFourEntity.ShopInfo();
                    JSONObject shop_info = jsonObject.getJSONObject("shop_info");
                    String shop_categoryMain = shop_info.getString("shop_categoryMain");
                    String rz_shopName = shop_info.getString("rz_shopName");
                    String shop_class_keyWords = shop_info.getString("shop_class_keyWords");
                    shopInfo.setShop_categoryMain(shop_categoryMain);
                    shopInfo.setRz_shopName(rz_shopName);
                    shopInfo.setShop_class_keyWords(shop_class_keyWords);
                    entity.setShop_info(shopInfo);

                    entity.setCats(catList);
                    entity.setCategory_info(category_infos);
                    String cats_son = jsonObject.getString("cats_son");
                    entity.setCats_son(cats_son);
                    responseCallback.onSuccess(entity);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void MerchantsInFourdone(String uid, String shop_class_keyWords, String rz_shopName, String shop_categoryMain, String cat_id, final ResponseCallback<String> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("shop_class_keyWords", shop_class_keyWords);
        map.put("rz_shopName", rz_shopName);
        map.put("shop_categoryMain", shop_categoryMain);
        map.put("cat_id", cat_id);
        POST(HttpInterface.MerchantsInFourDone(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.getInt("code");
                    String msg = jsonObject.getString("msg");
                    if (code == 100) {
                        responseCallback.onSuccess(msg);
                    } else {
                        responseCallback.onFailure(code, msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void MerchantsInFive(String uid, final ResponseCallback<MerchantsInFiveEntity> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        POST(HttpInterface.MerchantsInFive(), map, new Callback() {
            @Override
            public void callback(String result) {
                JLog.json(result);
                Gson gson = new Gson();
                MerchantsInFiveEntity entity = gson.fromJson(result, MerchantsInFiveEntity.class);
                responseCallback.onSuccess(entity);
            }
        }, responseCallback);
    }

    @Override
    public void isHasCard(String uid, final ResponseCallback<Boolean> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        POST(HttpInterface.isHasCard(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getInt("code") == 100) {
                        responseCallback.onSuccess(true);
                    } else {
                        responseCallback.onSuccess(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void cancelRecord(String uid, String id, final ResponseCallback<Boolean> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        POST(HttpInterface.cancelRecord(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getInt("code") == 100) {
                        responseCallback.onSuccess(true);
                    } else {
                        responseCallback.onSuccess(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void SysMsg(String uid, String page, final ResponseCallback<MessageSysEntity> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("page", page);
        POST(HttpInterface.SysMsg(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    MessageSysEntity entity = getObj(jsonObject, MessageSysEntity.class);
                    responseCallback.onSuccess(entity);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void SysMsgDes(String uid, String id, final ResponseCallback<MessageSysEntity.Data> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        POST(HttpInterface.SysMsgDes(), map, new Callback() {
            @Override
            public void callback(String result) {
                JLog.json(result);
                Gson gson = new Gson();
                MessageSysEntity.Data data = gson.fromJson(result, MessageSysEntity.Data.class);
                responseCallback.onSuccess(data);
            }
        }, responseCallback);
    }

    @Override
    public void OrderMsg(String uid, String page, final ResponseCallback<MessageOrderntity> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("page", page);
        POST(HttpInterface.OrderMsg(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    MessageOrderntity entity = getObj(jsonObject, MessageOrderntity.class);
                    responseCallback.onSuccess(entity);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void OrderMsgDes(String uid, String id, final ResponseCallback<MessageOrderntity.Data> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        POST(HttpInterface.OrderMsgDes(), map, new Callback() {
            @Override
            public void callback(String result) {
                JLog.json(result);
                Gson gson = new Gson();
                MessageOrderntity.Data data = gson.fromJson(result, MessageOrderntity.Data.class);
                responseCallback.onSuccess(data);
            }
        }, responseCallback);
    }

    @Override
    public void set(String url, final ResponseCallback<SetEntity> responseCallback) {
        GET(url, new Callback() {
            @Override
            public void callback(String result) {
                JLog.json(result);
                Gson gson = new Gson();
                SetEntity entity = gson.fromJson(result, SetEntity.class);
                responseCallback.onSuccess(entity);
            }
        }, responseCallback);
    }


    @Override
    public void IsSeller(String uid, final ResponseCallback<String> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        POST(HttpInterface.IsSeller(), map, new Callback() {
            @Override
            public void callback(String result) {
                JLog.json(result);
                responseCallback.onSuccess(result);
            }
        }, responseCallback);
    }


}
