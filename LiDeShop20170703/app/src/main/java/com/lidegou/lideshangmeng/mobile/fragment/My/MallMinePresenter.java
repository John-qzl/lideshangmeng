package com.lidegou.lideshangmeng.mobile.fragment.My;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.ICommodityDao;
import com.lidegou.lideshangmeng.mobile.data.dao.IMyDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.ICommodityDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IMyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.Footprint;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsIn;
import com.lidegou.lideshangmeng.mobile.data.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public class MallMinePresenter implements MallMineContract.Presenter {
    private MallMineContract.View view;
    private IMyDao iMyDao;
    private ICommodityDao iCommodityDao;
    private List<Footprint> footprintList = new ArrayList<>();
    private List<UserInfo> userInfoList = new ArrayList<>();

    public MallMinePresenter(MallMineContract.View view) {
        this.view = view;
        this.iMyDao = new IMyDaoImpl();
        iCommodityDao = new ICommodityDaoImpl();
    }

    @Override
    public void start() {
        getUserInfo();
        IsSeller();
    }


    @Override
    public void MerchantsInCheck() {
        iMyDao.MerchantsInCheck(view.getApp().getUid(), new ResponseCallback<MerchantsIn.MerchantsInCheck>() {
            @Override
            public void onSuccess(MerchantsIn.MerchantsInCheck data) {
                if (data != null) {
                    view.callbackMerchantsInCheckSuccess(data);
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
    public void IsSeller() {
        if (view.getApp().getUid() == null) {
            return;
        }
        iMyDao.IsSeller(view.getApp().getUid(), new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                view.IsSellerSuccess(data);
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
    public void getUserInfo() {
        if (view.getApp().getUid() == null) {
            return;
        }
        iMyDao.getUserInfo(view.getApp().getUid(), new ResponseCallback<List<UserInfo>>() {
            @Override
            public void onSuccess(List<UserInfo> data) {
                if (data != null) {
                    userInfoList.clear();
                    userInfoList.addAll(data);
                    view.callbackUserInfoSuccess(userInfoList);
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

}
