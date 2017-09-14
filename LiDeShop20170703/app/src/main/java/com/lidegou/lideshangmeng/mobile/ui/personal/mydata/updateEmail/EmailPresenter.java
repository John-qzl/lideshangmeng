package com.lidegou.lideshangmeng.mobile.ui.personal.mydata.updateEmail;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IUserInfoDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IUserDaoImpl;

/**
 * Created by Administrator on 2016/12/15.
 */

public class EmailPresenter implements EmailContract.Presenter {

    private EmailContract.View view;

    private IUserInfoDao iUserInfoDao;

    public EmailPresenter(EmailContract.View view) {
        this.view = view;
        this.iUserInfoDao = new IUserDaoImpl();
    }

    @Override
    public void start() {
        updateEmail();
    }


    @Override
    public void updateEmail() {
        if (view.email() == null || view.email().length() < 0) {
            view.showError("邮箱不能为空");
            return;
        }
//        if (view.email().matches(Config.Regular.EMAIL)){
//            view.showError("邮箱格式不正确");
//            return;
//        }
        if (view.getApp().getUid() == null) {
            view.showError("请先登录");
            return;
        }
        iUserInfoDao.updateEmail(view.getApp().getUid(), view.email(), new ResponseCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                view.callbackUpdateEmailSuccess();
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
