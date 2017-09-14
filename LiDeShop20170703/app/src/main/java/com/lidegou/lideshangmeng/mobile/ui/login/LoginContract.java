package com.lidegou.lideshangmeng.mobile.ui.login;

import com.lidegou.lideshangmeng.mobile.data.dao.IUserInfoDao;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface LoginContract {

    interface View extends BaseView {

        String getVersion();

        String getRemember();

        String getMark();

        String getTelephone();

        String getPassword();

        boolean isAutomatic();

        void callbackLoginSuccess(IUserInfoDao ii);
    }

    interface CheckView extends BaseView {
        void callbackCheckUsersSuccess(int code);

    }

    interface Presenter extends BasePresenter {
        void login();

        void CheckUsers();
    }

}
