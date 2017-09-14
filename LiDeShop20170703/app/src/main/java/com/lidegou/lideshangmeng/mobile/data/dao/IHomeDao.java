package com.lidegou.lideshangmeng.mobile.data.dao;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.entity.Announcement;
import com.lidegou.lideshangmeng.mobile.data.entity.Commodity;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsIn;
import com.lidegou.lideshangmeng.mobile.data.entity.MyLike;
import com.lidegou.lideshangmeng.mobile.data.entity.Place;
import com.lidegou.lideshangmeng.mobile.data.entity.ShopsStore;

import java.util.List;

/**
 * Created by Administrator on 2016/12/16.
 */

public interface IHomeDao {

    void getMyLikeList(String page, ResponseCallback<List<MyLike>> responseCallback);

    void getAnnouncement(ResponseCallback<List<Announcement>> responseCallback);

    void getShopsStore(String page, ResponseCallback<List<ShopsStore>> responseCallback);

    void getCommodity(String uid, ResponseCallback<Commodity> responseCallback);

    void getNowPlace(String uid, ResponseCallback<Place> responseCallback);

    void choosePlace(String uid, String province, String city, String district, ResponseCallback<String> responseCallback);

    /**
     * 店铺入驻查询申请状态
     */
    void MerchantsInCheck(String uid, ResponseCallback<MerchantsIn.MerchantsInCheck> responseCallback);

}
