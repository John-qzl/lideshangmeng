package com.lidegou.lideshangmeng.mobile.ui.forgotpass;

import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/12/15.
 */

public interface ForgotPasswordContract {

    interface View extends BaseView {
        String getData();

        String getTelephone();

        String getSmsCode();

        String getPassword();

        String getUid();

        void callbackSmsCodeSuccess(int code);

        void callbackGetUidSuccess(int uid);

        void callbackUpdatePwdSuccess(String data);

        void callbackGetUidError();
    }

    interface Presenter extends BasePresenter {

        void smsCode();

        void gainUid();

        void updatepwd();
    }

}
