package com.lidegou.lideshangmeng.mobile.ui.login;

import android.util.Log;

import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IUserInfoDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IUserDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.UserId;
import com.lidegou.lideshangmeng.mobile.util.Prefs;

/**
 * Created by Administrator on 2016/8/26.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private LoginContract.CheckView checkView;
    private IUserInfoDao iUserInfoDao;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.iUserInfoDao = new IUserDaoImpl();
    }


    public LoginPresenter(LoginContract.CheckView checkView) {
        this.checkView = checkView;
        this.iUserInfoDao = new IUserDaoImpl();
    }

    @Override
    public void start() {
        login();
    }


    @Override
    public void login() {
        if (view.getTelephone() == null || view.getTelephone().length() <= 0) {
            view.showError("手机号不能为空");
            return;
        }
        if (!view.getTelephone().matches(Config.Regular.PHONE)) {
            view.showError("手机号格式不正确");
            return;
        }
        if (view.getPassword() == null || view.getPassword().length() <= 0) {
            view.showError("密码不能为空");
            return;
        }
        iUserInfoDao.login(view.getVersion(), view.getTelephone(), view.getPassword(), view.getMark(), view.getRemember(), new ResponseCallback<UserId>() {
            @Override
            public void onSuccess(UserId userId) {
                Log.i("whfyy","可以了");

                Log.e("UID", userId.getUid() + "");
                view.getApp().setUid(userId.getUid());
                Config.User.STATUS = true;
                Prefs.with(view.getContext()).write(Config.User.ACCOUNT, view.getTelephone());
                Prefs.with(view.getContext()).write(Config.User.PASSWORD, view.getPassword());
                Prefs.with(view.getContext()).writeBoolean(Config.User.AUTOLOGIN, view.isAutomatic());
                view.callbackLoginSuccess(iUserInfoDao);
            }

            @Override
            public void onFailure(int code, String msg) {
                view.showError(msg);
            }
        });
    }

    @Override
    public void CheckUsers() {
        iUserInfoDao.CheckUsers(view.getApp().getUid(), new ResponseCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                checkView.callbackCheckUsersSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    checkView.showError(msg);
                }
            }
        });
    }

}
