package com.lidegou.lideshangmeng.mobile.fragment.My.collection;


import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.ICommodityDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.ICommodityDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.Commodity;

import java.util.List;

/**
 * Created by Y on 2016/8/17.
 */

public class MyCollectionPresenter implements MyCollectionContract.Presenter {

    private MyCollectionContract.View view;

    private List<Commodity> commodityList;
    private ICommodityDao iCommodityDao;

    public MyCollectionPresenter(MyCollectionContract.View view) {
        this.view = view;
        iCommodityDao = new ICommodityDaoImpl();
    }

    @Override
    public void start() {
        myCollection();
    }

    @Override
    public void myCollection() {
        iCommodityDao.selectCollectionCommodity(view.getApp().getUid(), view.getPage(), new ResponseCallback<Commodity.Data.CollectionCommodity>() {
            @Override
            public void onSuccess(Commodity.Data.CollectionCommodity data) {
                if (data != null) {
                    view.callbackCommodityList(data);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    view.showError(msg);
                }
            }
        });
    }

    @Override
    public void deleteCollection() {
        iCommodityDao.clearCollectionCommodity(view.getApp().getUid(), view.getRec_id(), new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                view.callbackdeleteSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    view.showError(msg);
                }
            }
        });
    }

}
