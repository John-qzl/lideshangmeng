package com.lidegou.lideshangmeng.mobile.fragment.home.aera;


import com.lidegou.lideshangmeng.mobile.data.entity.Place;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Y on 2016/8/10.
 */

public interface AeraContract {

    interface View extends BaseView {
        String province();

        String city();

        String district();

        void getNowPlaceSuccess(Place data);

        void callbackChoosePlaceSuccess(String data);
    }


    interface Presenter extends BasePresenter {
        void getNowPlace();

        void choosePlace();
    }
}
