package com.lidegou.lideshangmeng.mobile.data.dao.impl;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.data.HttpInterface;
import com.lidegou.lideshangmeng.mobile.data.HttpUtil;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IClassifyDao;
import com.lidegou.lideshangmeng.mobile.data.entity.Classify;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/16.
 */

public class IClassifyDaoImpl extends HttpUtil implements IClassifyDao {
    @Override
    public void getClassifyList(final ResponseCallback<List<Classify>> responseCallback) {
        GET(HttpInterface.getClassifyList(), new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    JSONArray jsonArray = object.optJSONArray("data");
                    String show_pic = object.optString("show_pic").toString();
                    App.getApp().setShow_pic(show_pic);
                    List<Classify> classifyList = new ArrayList<Classify>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.optJSONObject(i);
                        if (jsonObject == null) {
                            break;
                        }
                        List<Classify.CatID> catIDList = new ArrayList<Classify.CatID>();
                        if (jsonObject.getInt("haschild") == 1) {
                            JSONArray json = jsonObject.getJSONArray("cat_id");
                            for (int j = 0; j < json.length(); j++) {
                                JSONObject jsonObject1 = json.optJSONObject(j);
                                Classify.CatID catID = getObj(jsonObject1, Classify.CatID.class);
                                catIDList.add(catID);
                            }
                        }
                        Classify classify = getObj(jsonObject, Classify.class);
                        classify.setCatIDList(catIDList);
                        classifyList.add(classify);
                    }
                    responseCallback.onSuccess(classifyList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }
}
