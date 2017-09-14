package com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.details;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.ICommodityDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.ICommodityDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.ShopsStore;

/**
 * Created by Administrator on 2016/8/26.
 */

public class ShopsStoreDetailsPresenter implements ShopsStoreDetailsContract.Presenter {
    private ShopsStoreDetailsContract.View view;
    private ICommodityDao iCommodityDao;

    public ShopsStoreDetailsPresenter(ShopsStoreDetailsContract.View view) {
        this.view = view;
        this.iCommodityDao = new ICommodityDaoImpl();
    }

    @Override
    public void start() {

    }


    @Override
    public void FocusShops() {
        iCommodityDao.FocusShops(view.getApp().getUid(), view.ShopUserid(), new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                view.callbackFocusShopsSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                view.showError(msg);
            }
        });
    }

    @Override
    public void StoreDetails() {
        String uid = view.getApp().getUid();
        if (uid == null) {
            uid = "";
        }
        iCommodityDao.selectStoreDetails(uid, view.ShopUserid(), new ResponseCallback<ShopsStore.ShopsStoreDetails>() {
            @Override
            public void onSuccess(ShopsStore.ShopsStoreDetails data) {
                if (data != null) {
                    view.callbackStoreDetailsSuccess(data);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                view.showError(msg);
            }
        });
    }
}
