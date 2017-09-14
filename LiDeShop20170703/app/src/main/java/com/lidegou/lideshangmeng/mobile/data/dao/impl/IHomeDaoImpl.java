package com.lidegou.lideshangmeng.mobile.data.dao.impl;

import android.util.Log;

import com.google.gson.Gson;
import com.jiongbull.jlog.JLog;
import com.lidegou.lideshangmeng.mobile.data.HttpInterface;
import com.lidegou.lideshangmeng.mobile.data.HttpUtil;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IHomeDao;
import com.lidegou.lideshangmeng.mobile.data.entity.Announcement;
import com.lidegou.lideshangmeng.mobile.data.entity.Commodity;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsIn;
import com.lidegou.lideshangmeng.mobile.data.entity.MyLike;
import com.lidegou.lideshangmeng.mobile.data.entity.Place;
import com.lidegou.lideshangmeng.mobile.data.entity.ShopsStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/16.
 */

public class IHomeDaoImpl extends HttpUtil implements IHomeDao {
    Map<String, String> map = new HashMap<>();

    @Override
    public void getMyLikeList(String page, final ResponseCallback<List<MyLike>> responseCallback) {
        map.put("page", page);
        POST(HttpInterface.getMylike(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject object = new JSONObject(result);
                    JSONArray jsonArray = object.optJSONArray("list");
                    List<MyLike> myLikeList = new ArrayList<MyLike>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.optJSONObject(i);
                        if (jsonObject == null) {
                            break;
                        }
                        MyLike myLike = getObj(jsonObject, MyLike.class);
                        myLikeList.add(myLike);
                    }
                    responseCallback.onSuccess(myLikeList);
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void getAnnouncement(final ResponseCallback<List<Announcement>> responseCallback) {
        GET(HttpInterface.getAnnouncement(), new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    JSONArray jsonArray = object.optJSONArray("data");
                    List<Announcement> announcementList = new ArrayList<Announcement>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.optJSONObject(i);
                        if (jsonObject == null) {
                            break;
                        }
                        Announcement announcement = getObj(jsonObject, Announcement.class);
                        announcementList.add(announcement);
                    }
                    responseCallback.onSuccess(announcementList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void getShopsStore(String page, final ResponseCallback<List<ShopsStore>> responseCallback) {
        map.put("page", page);
        POST(HttpInterface.getShopStore(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    JSONArray jsonArray = object.optJSONArray("data");
                    List<ShopsStore> shopsStoreList = new ArrayList<ShopsStore>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.optJSONObject(i);
                        if (jsonObject == null) {
                            break;
                        }
                        ShopsStore shopsStore = getObj(jsonObject, ShopsStore.class);
                        shopsStoreList.add(shopsStore);
                    }
                    responseCallback.onSuccess(shopsStoreList);
                } catch (JSONException e) {

                }
            }
        }, responseCallback);
    }

    @Override
    public void getCommodity(String page, final ResponseCallback<Commodity> responseCallback) {
        map.put("page", page + "");
        Log.e("map", map + "");
        POST(HttpInterface.getCommodity(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject object = new JSONObject(result);
                    Commodity commodity = getObj(object, Commodity.class);
                    responseCallback.onSuccess(commodity);
                } catch (JSONException e) {

                }
            }
        }, responseCallback);
    }

    @Override
    public void getNowPlace(String uid, final ResponseCallback<Place> responseCallback) {
        map.put("uid", uid);
        Log.e("map", map + "");
        POST(HttpInterface.getNowPlace(), map, new Callback() {
            @Override
            public void callback(String result) {
                Gson gson = new Gson();
                Place place = gson.fromJson(result, Place.class);
                responseCallback.onSuccess(place);
            }
        }, responseCallback);
    }

    @Override
    public void choosePlace(String uid, String province, String city, String district, final ResponseCallback<String> responseCallback) {
        map.put("uid", uid);
        map.put("province", province);
        map.put("city", city);
        map.put("district", district);
        Log.e("province", province);
        Log.e("city", city);
        Log.e("district", district);
        POST(HttpInterface.choosePlace(), map, new Callback() {
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


}
