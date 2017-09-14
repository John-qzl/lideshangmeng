package com.lidegou.lideshangmeng.mobile.ui.personal.mydata.address;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IUserInfoDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IUserDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.Address;
import com.lidegou.lideshangmeng.mobile.data.entity.City;
import com.lidegou.lideshangmeng.mobile.data.entity.County;
import com.lidegou.lideshangmeng.mobile.data.entity.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/15.
 */

public class AddressPresenter implements AddressContract.Presenter {

    private AddressContract.View view;
    private AddressContract.AddAddress addAddressview;
    private List<Province> provinceList = new ArrayList<>();
    private List<City> cityList = new ArrayList<>();
    private List<County> countyList = new ArrayList<>();
    private IUserInfoDao iUserInfoDao;

    public AddressPresenter(AddressContract.View view) {
        this.view = view;
        this.iUserInfoDao = new IUserDaoImpl();
    }

    public AddressPresenter(AddressContract.AddAddress addAddressview) {
        this.addAddressview = addAddressview;
        this.iUserInfoDao = new IUserDaoImpl();
    }



    @Override
    public void start() {
        addresslist();
    }




    @Override
    public void addresslist() {
        if (view.getApp().getUid()==null){
            view.showError("请先登录");
            return;
        }
        iUserInfoDao.addresslist(view.getApp().getUid(), "", new ResponseCallback<Address>() {
            @Override
            public void onSuccess(Address data) {
                if (data!=null) {
                    view.callbackAddressListSuccess(data);
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
    public void addAddress() {
        if (addAddressview.consignee() == null || addAddressview.consignee().length() <= 0) {
            addAddressview.showError("收货人不能为空");
            return;
        }
        if (addAddressview.mobile() == null || addAddressview.mobile().length() <= 0) {
            addAddressview.showError("手机号不能为空");
            return;
        }
        if (addAddressview.address() == null || addAddressview.address().length() <= 0) {
            addAddressview.showError("详细地址不能为空");
            return;
        }
        iUserInfoDao.Addaddress(addAddressview.getApp().getUid(), addAddressview.province_region_id(), addAddressview.city_region_id(), addAddressview.district_region_id(), addAddressview.consignee(), addAddressview.mobile(), addAddressview.address(), new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                addAddressview.callbackAddressSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    addAddressview.showError(msg);
                }
            }
        });
    }

    @Override
    public void province() {
        iUserInfoDao.province(new ResponseCallback<List<Province>>() {
            @Override
            public void onSuccess(List<Province> data) {
                if (data != null) {
                    provinceList.clear();
                    provinceList.addAll(data);
                    addAddressview.callbackProvinceSuccess(provinceList);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    addAddressview.showError(msg);
                }
            }
        });
    }

    @Override
    public void city() {
        iUserInfoDao.city(addAddressview.region_id(),new ResponseCallback<List<City>>() {
            @Override
            public void onSuccess(List<City> data) {
                if (data!=null){
                    cityList.clear();
                    cityList.addAll(data);
                    addAddressview.callbackCitySuccess(cityList);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    addAddressview.showError(msg);
                }
            }
        });
    }

    @Override
    public void County() {
        iUserInfoDao.county(addAddressview.region_idcity(),new ResponseCallback<List<County>>() {
            @Override
            public void onSuccess(List<County> data) {
                if (data!=null){
                    countyList.clear();
                    countyList.addAll(data);
                    addAddressview.callbackCountySuccess(countyList);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    addAddressview.showError(msg);
                }
            }
        });
    }

    @Override
    public void updateInfo() {
        iUserInfoDao.AddressUpdateInfo(addAddressview.getApp().getUid(), addAddressview.address_id(), new ResponseCallback<Address.AddressUpdateInfo>() {
            @Override
            public void onSuccess(Address.AddressUpdateInfo data) {
                if (data!=null){
                    addAddressview.callbackUpdateInfoSuccess(data);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    addAddressview.showError(msg);
                }
            }
        });
    }

    @Override
    public void setdefaultAddress() {
        if (view.getApp().getUid()==null){
            view.showError("请先登录");
            return;
        }
        iUserInfoDao.setdefaultAddress(view.getApp().getUid(), view.addressId(), new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                view.callbackSetdefaultAddressSuccess(data);
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
    public void deleteAddress() {
        if (view.getApp().getUid()==null){
            view.showError("请先登录");
            return;
        }
        iUserInfoDao.deleteAddress(view.getApp().getUid(), view.addressId(), new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                view.callbackDeleteAddressSuccess(data);
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
    public void updateAddress() {
        if (addAddressview.consignee() == null || addAddressview.consignee().length() <= 0) {
            addAddressview.showError("收货人不能为空");
            return;
        }
        if (addAddressview.mobile() == null || addAddressview.mobile().length() <= 0) {
            addAddressview.showError("手机号不能为空");
            return;
        }
        if (addAddressview.address() == null || addAddressview.address().length() <= 0) {
            addAddressview.showError("详细地址不能为空");
            return;
        }
        iUserInfoDao.UpdateAddress(addAddressview.getApp().getUid(),addAddressview.address_id(), addAddressview.province_region_id(), addAddressview.city_region_id(), addAddressview.district_region_id(), addAddressview.consignee(), addAddressview.mobile(), addAddressview.address(), new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                addAddressview.callbackAddressSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    addAddressview.showError(msg);
                }
            }
        });
    }
}
