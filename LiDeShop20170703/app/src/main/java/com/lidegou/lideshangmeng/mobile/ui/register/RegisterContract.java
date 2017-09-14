package com.lidegou.lideshangmeng.mobile.ui.register;

import com.lidegou.lideshangmeng.mobile.data.entity.City;
import com.lidegou.lideshangmeng.mobile.data.entity.County;
import com.lidegou.lideshangmeng.mobile.data.entity.Province;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface RegisterContract {

    interface View extends BaseView {

        String getData();

        String getTelephone();

        String getSmsCode();

        String getName();

        String getPassword();

        String getCheckPassword();

        String getReferees();

        String getProvince();

        String getCity();

        String getCounty();

        Integer region_id();

        Integer region_idcity();

        String getVersion();

        void callbaclIsRegister(boolean isRegister);

        void callbackSmsCodeSuccess();

        void callbackRegisterSuccess();

        void callbackProvinceSuccess(List<Province> provinceList);

        void callbackCitySuccess(List<City> cityList);

        void callbackCountySuccess(List<County> countyList);

        String getMark();
    }

    interface Presenter extends BasePresenter {
        void isRegister();

        void smsCode();

        boolean register();

        void province();

        void city();

        void County();

    }

}
