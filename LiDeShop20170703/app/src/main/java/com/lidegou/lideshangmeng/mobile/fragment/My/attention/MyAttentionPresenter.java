package com.lidegou.lideshangmeng.mobile.fragment.My.attention;


import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.ICommodityDao;
import com.lidegou.lideshangmeng.mobile.data.dao.IUserInfoDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.ICommodityDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IUserDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.Commodity;
import com.lidegou.lideshangmeng.mobile.data.entity.ShopsStore;

import java.util.List;

/**
 * Created by Y on 2016/8/17.
 */

public class MyAttentionPresenter implements MyAttentionContract.Presenter {

    private MyAttentionContract.View view;

    private List<Commodity.Data> commodityList;
    private ICommodityDao iCommodityDao;
    private IUserInfoDao iUserInfoDao;

    public MyAttentionPresenter(MyAttentionContract.View view) {
        this.view = view;
        iCommodityDao = new ICommodityDaoImpl();
        iUserInfoDao = new IUserDaoImpl();
    }

    @Override
    public void start() {
    }

    @Override
    public void myAttention() {
        if (view.getApp().getUid() == null) {
            view.showError("请先登录");
            return;
        }
        iCommodityDao.selectAttentionCommodity(view.getApp().getUid(), view.getPage(), new ResponseCallback<ShopsStore.Attention>() {
            @Override
            public void onSuccess(ShopsStore.Attention data) {
                if (data != null) {
                    view.callbackAttentionList(data);
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
    public void deleteAttention() {
        if (view.getApp().getUid() == null) {
            view.showError("请先登录");
            return;
        }
        iCommodityDao.clearAttentionCommodity(view.getApp().getUid(), view.getShopId(), new ResponseCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                view.callbackdeleteSuccess();
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
