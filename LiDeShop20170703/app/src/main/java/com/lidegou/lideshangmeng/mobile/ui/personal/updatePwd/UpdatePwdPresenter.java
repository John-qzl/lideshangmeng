package com.lidegou.lideshangmeng.mobile.ui.personal.updatePwd;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IUserInfoDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IUserDaoImpl;

/**
 * Created by Administrator on 2016/12/15.
 */

public class UpdatePwdPresenter implements UpdatePwdContract.Presenter {

    private UpdatePwdContract.View view;

    private IUserInfoDao iUserInfoDao;

    public UpdatePwdPresenter(UpdatePwdContract.View view) {
        this.view = view;
        this.iUserInfoDao = new IUserDaoImpl();
    }

    @Override
    public void start() {
        editPassword();
    }


    @Override
    public void editPassword() {
        if (view.old_password() == null || view.old_password().length() <= 0) {
            view.showError("旧密码不能为空");
            return;
        }
        if (view.new_password1() == null || view.new_password1().length() <= 0) {
            view.showError("新密码不能为空");
            return;
        }
        if (view.new_password() == null || view.new_password().length() <= 0) {
            view.showError("确认密码不能为空");
            return;
        }
        iUserInfoDao.updatePassword(view.getApp().getUid(), view.old_password(), view.new_password1(), view.new_password(), new ResponseCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                view.callbackEditPasswordSuccess();
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
