package com.lidegou.lideshangmeng.mobile.ui.forgotpass;

import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IUserInfoDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IUserDaoImpl;

/**
 * Created by Administrator on 2016/12/15.
 */

public class ForgotPasswordPresenter implements ForgotPasswordContract.Presenter {

    private ForgotPasswordContract.View view;

    private IUserInfoDao iUserInfoDao;
    private String phone;

    public ForgotPasswordPresenter(ForgotPasswordContract.View view) {
        this.view = view;
        this.iUserInfoDao = new IUserDaoImpl();
    }

    @Override
    public void start() {
        smsCode();
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
        iUserInfoDao.updateSmsCode(view.getData(), new ResponseCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                view.callbackSmsCodeSuccess(data);
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
    public void gainUid() {
        if (view.getTelephone() == null || view.getTelephone().length() <= 0) {
            view.showError("手机号不能为空");
            return;
        }
        if (!view.getTelephone().matches(Config.Regular.PHONE)) {
            view.showError("手机号格式不正确");
            return;
        }
        if (view.getSmsCode().length() <= 0) {
            view.showError("验证码不能为空");
            return;
        }
        iUserInfoDao.getUid(view.getTelephone(), view.getSmsCode(), new ResponseCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                view.callbackGetUidSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    view.showError(msg);
                    view.callbackGetUidError();
                }
            }
        });
    }

    @Override
    public void updatepwd() {
        iUserInfoDao.updatePwd(view.getPassword(), view.getUid(), view.getSmsCode(), new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                view.callbackUpdatePwdSuccess(data);
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
