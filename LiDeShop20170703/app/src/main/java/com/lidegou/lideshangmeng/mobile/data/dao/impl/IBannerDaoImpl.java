package com.lidegou.lideshangmeng.mobile.data.dao.impl;


import com.lidegou.lideshangmeng.mobile.data.HttpInterface;
import com.lidegou.lideshangmeng.mobile.data.HttpUtil;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IBannerDao;
import com.lidegou.lideshangmeng.mobile.data.entity.Banner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Y on 2016/8/10.
 */

public class IBannerDaoImpl extends HttpUtil implements IBannerDao {

    @Override
    public void getBannderList(final ResponseCallback<List<Banner>> responseCallback) {
        GET(HttpInterface.getBanner(), new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    JSONArray jsonArray = object.optJSONArray("data");
                    List<Banner> bannerList = new ArrayList<Banner>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.optJSONObject(i);
                        if (jsonObject == null) {
                            break;
                        }
                        Banner banner = getObj(jsonObject, Banner.class);
                        bannerList.add(banner);
                    }
                    responseCallback.onSuccess(bannerList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }
}
