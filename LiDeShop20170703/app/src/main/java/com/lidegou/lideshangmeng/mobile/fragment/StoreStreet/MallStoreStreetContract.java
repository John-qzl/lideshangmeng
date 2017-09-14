package com.lidegou.lideshangmeng.mobile.fragment.StoreStreet;

import com.lidegou.lideshangmeng.mobile.data.entity.County;
import com.lidegou.lideshangmeng.mobile.data.entity.Place;
import com.lidegou.lideshangmeng.mobile.data.entity.Province;
import com.lidegou.lideshangmeng.mobile.data.entity.ShopsStore;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface MallStoreStreetContract {

    interface View extends BaseView {


        String page();

        String keyword();

        String id();

        String province_id();

        String city_id();

        String district_id();

        String longitude();

        String latitude();

        String type();

        void callbackStoreSuccess(List<ShopsStore> shopsStoreList);

        void callbackTabsSuccess(List<ShopsStore.Tabs> tabsList);

        void callbackFocusShopsSuccess(String msg);

        void getNowPlaceSuccess(Place data);
    }

    interface ShopsClassify extends BaseView {
        void callbackShopsClassSuccess(List<ShopsStore.ShopsStoreClassify> shopsStoreClassifyList);

    }

    interface City extends BaseView {
        Integer region_id();

        Integer region_idcity();

        void callbackProvinceSuccess(List<Province> provinceList);

        void callbackCitySuccess(List<com.lidegou.lideshangmeng.mobile.data.entity.City> cityList);

        void callbackCountySuccess(List<County> countyList);
    }

    interface Presenter extends BasePresenter {

        void getNowPlace();

        void getStore();

        void getShopsClass();

        void getTabs();

        void province();

        void city();

        void County();

        void FocusShops(String shopUid);
    }

}
