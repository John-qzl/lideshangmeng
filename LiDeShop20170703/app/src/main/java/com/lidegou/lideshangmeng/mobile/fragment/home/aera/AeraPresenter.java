package com.lidegou.lideshangmeng.mobile.fragment.home.aera;


import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IHomeDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IHomeDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.Place;

/**
 * Created by Y on 2016/8/10.
 */

public class AeraPresenter implements AeraContract.Presenter {
    public static final String TAG = AeraPresenter.class.getSimpleName();

    private AeraContract.View view;
    private IHomeDao iHomeDao;

    public AeraPresenter(AeraContract.View view) {
        this.view = view;
        iHomeDao = new IHomeDaoImpl();
    }

    @Override
    public void start() {
        getNowPlace();
    }

    @Override
    public void getNowPlace() {
        iHomeDao.getNowPlace(App.getApp().getUid(), new ResponseCallback<Place>() {
            @Override
            public void onSuccess(Place data) {
                view.getNowPlaceSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                view.showError(msg);
            }
        });
    }

    @Override
    public void choosePlace() {
        iHomeDao.choosePlace(App.getApp().getUid(), view.province(), view.city(), view.district(), new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                view.callbackChoosePlaceSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                view.showError(msg);
            }
        });
    }
}
