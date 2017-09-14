package com.lidegou.lideshangmeng.mobile.ui.personal.mydata.address;

import com.lidegou.lideshangmeng.mobile.data.entity.Address;
import com.lidegou.lideshangmeng.mobile.data.entity.City;
import com.lidegou.lideshangmeng.mobile.data.entity.County;
import com.lidegou.lideshangmeng.mobile.data.entity.Province;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/15.
 */

public interface AddressContract {

    interface View extends BaseView{
        void callbackAddressListSuccess(Address address);
        void callbackSetdefaultAddressSuccess(String msg);
        void callbackDeleteAddressSuccess(String msg);
        String addressId();
    }

    interface AddAddress extends BaseView{
        String address_id();

        String province_region_id();
        String city_region_id();
        String district_region_id();
        String consignee();
        String mobile();
        String address();

        Integer region_id();
        Integer region_idcity();
        void callbackAddressSuccess(String msg);
        void callbackProvinceSuccess(List<Province> provinceList);
        void callbackCitySuccess(List<City> cityList);
        void callbackCountySuccess(List<County> countyList);

        void callbackUpdateInfoSuccess(Address.AddressUpdateInfo addressUpdateInfo);
        void callbackUpdateAddressSuccess(String msg);
    }


    interface Presenter extends BasePresenter {
        void addresslist();
        void addAddress();


        void province();
        void city();
        void County();

        void updateInfo();
        void updateAddress();

        void setdefaultAddress();

        void deleteAddress();
    }

}
