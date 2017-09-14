package com.lidegou.lideshangmeng.mobile.ui.register;

import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IUserInfoDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IUserDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.City;
import com.lidegou.lideshangmeng.mobile.data.entity.County;
import com.lidegou.lideshangmeng.mobile.data.entity.Province;
import com.lidegou.lideshangmeng.mobile.data.entity.UserId;
import com.lidegou.lideshangmeng.mobile.util.Prefs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View view;
    private IUserInfoDao iUserInfoDao;
    private List<Province> provinceList = new ArrayList<>();
    private List<City> cityList = new ArrayList<>();
    private List<County> countyList = new ArrayList<>();

    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
        this.iUserInfoDao = new IUserDaoImpl();
    }

    @Override
    public void start() {
        province();
        city();
    }


    @Override
    public void isRegister() {
        if (view.getTelephone() == null || view.getTelephone().length() <= 0) {
            view.showError("手机号不能为空");
            return;
        }
        if (!view.getTelephone().matches(Config.Regular.PHONE)) {
            view.showError("手机号格式不正确");
            return;
        }
        iUserInfoDao.IsRegister(view.getTelephone(), new ResponseCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                view.callbaclIsRegister(data);
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
    public void smsCode() {
        if (view.getTelephone() == null || view.getTelephone().length() <= 0) {
            view.showError("手机号不能为空");
            return;
        }
        if (!view.getTelephone().matches(Config.Regular.PHONE)) {
            view.showError("手机号格式不正确");
            return;
        }
        iUserInfoDao.registerSmsCode(view.getData(), new ResponseCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                view.callbackSmsCodeSuccess();
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
    public boolean register() {
        if (view.getTelephone() == null || view.getTelephone().length() <= 0) {
            view.showError("手机号不能为空");
            return false;
        }
        if (!view.getTelephone().matches(Config.Regular.PHONE)) {
            view.showError("手机号格式不正确");
            return false;
        }

        if (view.getSmsCode().length() <= 0) {
            view.showError("验证码不能为空");
            return false;
        }
        if (view.getName() == null || view.getName().length() <= 0) {
            view.showError("姓名不能为空");
            return false;
        }
        if (view.getPassword() == null || view.getPassword().length() <= 0) {
            view.showError("密码不能为空");
            return false;
        }
        if (!view.getPassword().equals(view.getCheckPassword())) {
            view.showError("两次密码不一致");
            return false;
        }
        if (view.getReferees() == null || view.getReferees().length() <= 0) {
            view.showError("推荐人不能为空");
            return false;
        }
        iUserInfoDao.register(view.getVersion(), view.getTelephone(), view.getSmsCode(), view.getName(), view.getPassword(), view.getCheckPassword(), view.getReferees(), view.getProvince(), view.getCity(), view.getCounty(), view.getMark(), new ResponseCallback<UserId>() {
            @Override
            public void onSuccess(UserId userId) {

                view.getApp().setUid(userId.getUid());
                Config.User.STATUS = true;
                Prefs.with(view.getContext()).write(Config.User.ACCOUNT, view.getTelephone());
                Prefs.with(view.getContext()).write(Config.User.PASSWORD, view.getPassword());

               view.callbackRegisterSuccess();
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    view.showError(msg);
                }
            }
        });
        return true;
    }

    @Override
    public void province() {
        iUserInfoDao.province(new ResponseCallback<List<Province>>() {
            @Override
            public void onSuccess(List<Province> data) {
                if (data != null) {
                    provinceList.clear();
                    provinceList.addAll(data);
                    view.callbackProvinceSuccess(provinceList);
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
    public void city() {
        iUserInfoDao.city(view.region_id(), new ResponseCallback<List<City>>() {
            @Override
            public void onSuccess(List<City> data) {
                if (data != null) {
                    cityList.clear();
                    cityList.addAll(data);
                    view.callbackCitySuccess(cityList);
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
    public void County() {
        iUserInfoDao.county(view.region_idcity(), new ResponseCallback<List<County>>() {
            @Override
            public void onSuccess(List<County> data) {
                if (data != null) {
                    countyList.clear();
                    countyList.addAll(data);
                    view.callbackCountySuccess(countyList);
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
