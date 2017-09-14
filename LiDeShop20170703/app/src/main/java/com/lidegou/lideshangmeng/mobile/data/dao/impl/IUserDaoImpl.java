package com.lidegou.lideshangmeng.mobile.data.dao.impl;


import android.util.Log;

import com.jiongbull.jlog.JLog;
import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.data.HttpInterface;
import com.lidegou.lideshangmeng.mobile.data.HttpUtil;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IUserInfoDao;
import com.lidegou.lideshangmeng.mobile.data.entity.Address;
import com.lidegou.lideshangmeng.mobile.data.entity.City;
import com.lidegou.lideshangmeng.mobile.data.entity.County;
import com.lidegou.lideshangmeng.mobile.data.entity.Province;
import com.lidegou.lideshangmeng.mobile.data.entity.UserId;
import com.lidegou.lideshangmeng.mobile.data.entity.UserInfo;
import com.lidegou.lideshangmeng.mobile.util.CryptAES;
import com.lidegou.lideshangmeng.mobile.util.Prefs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Y on 2016/8/10.
 */

public class IUserDaoImpl extends HttpUtil implements IUserInfoDao {
    private String keyStr = "UITN25LMUQC436IM";

    @Override
    public void login(String version, String account, String password, String mark, final String remember, final ResponseCallback<UserId> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("version", version);
        map.put("username", account);
        map.put("password", CryptAES.AES_Encrypt(keyStr, password));
        map.put("type", 2 + "");
        map.put("mark", mark);
        map.put("remember", remember);
        String url = HttpInterface.getLogin();
        POST(url, map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    Log.i("result", result + "");
                    int code = object.getInt("code");
                    if (code == 100) {
                        String uid = object.optString("uid");
                        UserId userId = new UserId();
                        userId.setUid(uid);
                        responseCallback.onSuccess(userId);
                        JPushInterface.setAlias(App.getApp(), uid, null);
                        Prefs.with(App.getApp()).write(Config.User.UID, uid);
                        if (remember.equals("1")) {
                            Prefs.with(App.getApp()).writeBoolean(Config.User.AUTOLOGIN, true);
                        } else {
                            Prefs.with(App.getApp()).writeBoolean(Config.User.AUTOLOGIN, false);
                        }
                    } else {
                        responseCallback.onFailure(code, object.optString("msg"));
                    }
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void registerSmsCode(String telephone, final ResponseCallback<Integer> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", telephone);
        map.put("flag", "register");
        String url = HttpInterface.getRegisterSmsCode();
        POST(url, map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    int code = object.getInt("code");
                    Log.i("IUserDaoImpl", "map:" + result);
                    if (code == 100) {
                        responseCallback.onSuccess(0);
                    } else {
                        responseCallback.onFailure(code, object.optString("msg"));
                    }
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void register(String version, String telephone, String code, String nickname, String password, String checkpassword, String referees, String province, String city, String county, String mark, final ResponseCallback<UserId> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("version", version);
        map.put("mobile", telephone);
        map.put("mobile_code", code);
        map.put("extend_field8", nickname);
        map.put("smspassword", CryptAES.AES_Encrypt(keyStr, password));
        map.put("repassword", CryptAES.AES_Encrypt(keyStr, checkpassword));
        map.put("extend_field7", referees);
        map.put("province", province);
        map.put("city", city);
        map.put("district", county);
        map.put("enabled_sms", 1 + "");
        map.put("type", 2 + "");
        map.put("mark", mark);
        Log.i("map", map + "");
        String url = HttpInterface.getRegister();
        POST(url, map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    Log.e("result1", result + "");
                    int code = object.getInt("code");
                    if (code == 100) {
                        String uid = object.optString("uid");
                        UserId userId = new UserId();
                        userId.setUid(uid);
                        Prefs.with(App.getApp()).write(Config.User.UID, uid);
                        Prefs.with(App.getApp()).writeBoolean(Config.User.AUTOLOGIN, true);
                        responseCallback.onSuccess(userId);
                    } else {
                        responseCallback.onFailure(code, object.optString("msg"));
                    }
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void province(final ResponseCallback<List<Province>> responseCallback) {
        String url = HttpInterface.getProvince();
        GET(url, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONArray jsonResult = new JSONArray(result);
                    if (jsonResult == null || jsonResult.length() <= 0) {
                        return;
                    }
                    List<Province> provinceList = new ArrayList<Province>();
                    for (int i = 0; i < jsonResult.length(); i++) {
                        JSONObject jsonObject = jsonResult.optJSONObject(i);
                        if (jsonObject == null) {
                            break;
                        }
                        Province province = getObj(jsonObject, Province.class);
                        provinceList.add(province);
                    }
                    responseCallback.onSuccess(provinceList);
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void city(int region_id, final ResponseCallback<List<City>> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("type_id", 2 + "");
        map.put("region_id", region_id + "");
        String url = HttpInterface.getCity();
        POST(url, map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONArray jsonResult = new JSONArray(result);
                    Log.e("City", result + "");
                    if (jsonResult == null || jsonResult.length() <= 0) {
                        return;
                    }
                    List<City> cityList = new ArrayList<City>();
                    for (int i = 0; i < jsonResult.length(); i++) {
                        JSONObject jsonObject = jsonResult.optJSONObject(i);
                        if (jsonObject == null) {
                            break;
                        }
                        City city = getObj(jsonObject, City.class);
                        cityList.add(city);
                    }
                    responseCallback.onSuccess(cityList);
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void county(int region_id, final ResponseCallback<List<County>> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("type_id", 3 + "");
        map.put("region_id", region_id + "");
        String url = HttpInterface.getCounty();
        POST(url, map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONArray jsonResult = new JSONArray(result);
                    Log.e("data", result + "");
                    if (jsonResult == null || jsonResult.length() <= 0) {
                        return;
                    }
                    List<County> countyList = new ArrayList<County>();
                    for (int i = 0; i < jsonResult.length(); i++) {
                        JSONObject jsonObject = jsonResult.optJSONObject(i);
                        if (jsonObject == null) {
                            break;
                        }
                        County county = getObj(jsonObject, County.class);
                        countyList.add(county);
                    }
                    responseCallback.onSuccess(countyList);
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void updateSmsCode(String telephone, final ResponseCallback<Integer> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", telephone);
        map.put("flag", "forget");
        String url = HttpInterface.getUpdatePwdCode();
        POST(url, map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    int code = object.getInt("code");
                    if (code == 100) {
                        responseCallback.onSuccess(0);
                    } else {
                        responseCallback.onFailure(code, object.optString("msg"));
                    }
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void getUid(String telephone, String smsCode, final ResponseCallback<Integer> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", telephone);
        map.put("sms_code", smsCode);
        map.put("enabled_sms", "1");
        String url = HttpInterface.getUid();
        POST(url, map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    int code = object.getInt("code");
                    if (code == 100) {
                        responseCallback.onSuccess(Integer.parseInt(object.optString("uid")));
                    } else {
                        responseCallback.onFailure(code, object.optString("msg"));
                    }
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void updatePwd(String password, String uid, String smsCode, final ResponseCallback<String> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("password", CryptAES.AES_Encrypt(keyStr, password));
        map.put("uid", uid);
        map.put("sms_code", smsCode);
        String url = HttpInterface.getUpdatePwd();
        POST(url, map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    int code = object.getInt("code");
                    if (code == 100) {
                        responseCallback.onSuccess(object.optString("msg"));
                    } else {
                        responseCallback.onFailure(code, object.optString("msg"));
                    }
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void updatePassword(String uid, String old_password, String new_password1, String new_password, final ResponseCallback<Integer> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("old_password", old_password);
        map.put("new_password1", new_password1);
        map.put("new_password", new_password);
        POST(HttpInterface.getUpdatePass(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject object = new JSONObject(result);
                    int code = object.getInt("code");
                    if (code == 100) {
                        responseCallback.onSuccess(Integer.parseInt(object.optString("code")));
                    } else {
                        responseCallback.onFailure(code, object.optString("msg"));
                    }
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void updateEmail(String uid, String email, final ResponseCallback<Integer> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("email", email);
        POST(HttpInterface.updateEmail(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    int code = object.getInt("code");
                    if (code == 100) {
                        responseCallback.onSuccess(Integer.parseInt(object.optString("code")));
                    } else {
                        responseCallback.onFailure(code, object.optString("msg"));
                    }
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void loginout(String uid, final ResponseCallback<Integer> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        POST(HttpInterface.getloginout(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    int code = object.getInt("code");
                    if (code == 100) {
                        responseCallback.onSuccess(Integer.parseInt(object.optString("code")));
                    } else {
                        responseCallback.onFailure(code, object.optString("msg"));
                    }
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void getUserInfo(String uid, final ResponseCallback<UserInfo.UserInfoBean> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        POST(HttpInterface.getUserInfoList(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {

                    JLog.json(result);
                    Log.i("whfyy",result);
                    JSONObject jsonObject = new JSONObject(result);
                    UserInfo.UserInfoBean userInfoBean = getObj(jsonObject, UserInfo.UserInfoBean.class);
                    responseCallback.onSuccess(userInfoBean);

                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void addresslist(String uid, String shopid, final ResponseCallback<Address> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("shopid", shopid);
        POST(HttpInterface.addressList(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("consignee_list");
                    Address address = new Address();
                    List<Address.ConsigneeListBean> consigneeListBeanList = new ArrayList<Address.ConsigneeListBean>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        if (jsonArray == null || jsonArray.length() <= 0) {
                            return;
                        }
                        JSONObject object = jsonArray.optJSONObject(i);
                        Address.ConsigneeListBean consigneeListBean = getObj(object, Address.ConsigneeListBean.class);
                        consigneeListBeanList.add(consigneeListBean);
                    }
                    String address_id = jsonObject.getString("address_id");
                    address.setAddress_id(address_id);
                    address.setConsignee_list(consigneeListBeanList);
                    responseCallback.onSuccess(address);
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void Addaddress(String uid, String province_region_id, String city_region_id, String district_region_id, String consignee, String mobile, String address, final ResponseCallback<String> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("province_region_id", province_region_id);
        map.put("city_region_id", city_region_id);
        map.put("district_region_id", district_region_id);
        map.put("consignee", consignee);
        map.put("mobile", mobile);
        map.put("address", address);
        POST(HttpInterface.AddAddress(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    int code = object.getInt("code");
                    if (code == 100) {
                        String msg = object.optString("msg");
                        responseCallback.onSuccess(msg);
                    } else {
                        responseCallback.onFailure(code, object.optString("msg"));
                    }
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void AddressUpdateInfo(String uid, String address_id, final ResponseCallback<Address.AddressUpdateInfo> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("address_id", address_id);
        POST(HttpInterface.UpdateAddressInfo(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject object = new JSONObject(result);
                    JSONObject jsonObject = object.getJSONObject("data");
                    if (jsonObject == null || jsonObject.length() <= 0) {
                        return;
                    }
                    Address.AddressUpdateInfo addressUpdateInfo = getObj(jsonObject, Address.AddressUpdateInfo.class);
                    responseCallback.onSuccess(addressUpdateInfo);
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void UpdateAddress(String uid, String address_id, String province_region_id, String city_region_id, String district_region_id, String consignee, String mobile, String address, final ResponseCallback<String> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("address_id", address_id);
        map.put("province_region_id", province_region_id);
        map.put("city_region_id", city_region_id);
        map.put("district_region_id", district_region_id);
        map.put("consignee", consignee);
        map.put("mobile", mobile);
        map.put("address", address);
        POST(HttpInterface.AddAddress(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    int code = object.getInt("code");
                    if (code == 100) {
                        String msg = object.optString("msg");
                        responseCallback.onSuccess(msg);
                    } else {
                        responseCallback.onFailure(code, object.optString("msg"));
                    }
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void setdefaultAddress(String uid, String address_id, final ResponseCallback<String> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("address_id", address_id);
        POST(HttpInterface.SetdefaultAddress(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    int code = object.getInt("code");
                    if (code == 100) {
                        String msg = object.optString("msg");
                        responseCallback.onSuccess(msg);
                    } else {
                        responseCallback.onFailure(code, object.optString("msg"));
                    }
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void deleteAddress(String uid, String address_id, final ResponseCallback<String> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("address_id", address_id);
        POST(HttpInterface.deleteAddress(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    int code = object.getInt("code");
                    if (code == 100) {
                        String msg = object.optString("msg");
                        responseCallback.onSuccess(msg);
                    } else {
                        responseCallback.onFailure(code, object.optString("msg"));
                    }
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void setGender(String uid, String sex, final ResponseCallback<String> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("sex", sex);
        POST(HttpInterface.SetGender(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    int code = object.getInt("code");
                    if (code == 100) {
                        String msg = object.optString("msg");
                        responseCallback.onSuccess(msg);
                    } else {
                        responseCallback.onFailure(code, object.optString("msg"));
                    }
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void updateLogo(String uid, String user_picture, final ResponseCallback responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("user_picture", user_picture);
        POST(HttpInterface.updateLogo(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    int code = object.getInt("code");
                    if (code == 100) {
                        String msg = object.optString("msg");
                        responseCallback.onSuccess(msg);
                    } else {
                        responseCallback.onFailure(code, object.optString("msg"));
                    }
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void CheckUsers(String uid, final ResponseCallback<Integer> responseCallback) {
        Map<String, String> map = new HashMap<>();
        String checkuid = uid;
        if (checkuid == null) {
            map.put("uid", "");
        } else {
            map.put("uid", checkuid);
        }
        POST(HttpInterface.CheckUsers(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject object = new JSONObject(result);
                    int code = object.getInt("code");
                    responseCallback.onSuccess(code);
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void IsRegister(String telephone, final ResponseCallback<Boolean> registerPresenter) {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", telephone);
        POST(HttpInterface.isRegister(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject object = new JSONObject(result);
                    int code = object.getInt("code");
                    if (code == 100) {
                        registerPresenter.onSuccess(false);
                    } else {
                        registerPresenter.onSuccess(true);
                    }
                } catch (JSONException e) {
                }
            }
        }, registerPresenter);

    }

    @Override
    public void RegisterIdCard(String uid, final String ID_name, String ID_card, final ResponseCallback activity) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("ID_card", ID_card);
        map.put("ID_name", ID_name);
        POST(HttpInterface.register_idcard(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject object = new JSONObject(result);
                    int code = object.getInt("code");
                    if (code == 100) {
                        activity.onSuccess(true);
                    } else {
                        activity.onSuccess(false);
                    }
                } catch (JSONException e) {
                }
            }
        }, activity);
    }

    public static String decode(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U')))
                    try {
                        retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }
        return retBuf.toString();
    }

    @Override
    public void isRegisterIdCard(String uid, final ResponseCallback activity) {

        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        POST(HttpInterface.isIdCard(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    Log.i("whfyy", result);
                    JLog.json(result);
                    JSONObject object = new JSONObject(result);

                    int code = object.getInt("code");
                    if (code == 100) {
                        activity.onSuccess(true);

                    } else {
                        activity.onSuccess(false);
                    }
                } catch (JSONException e) {
                }
            }
        }, activity);
    }
}
