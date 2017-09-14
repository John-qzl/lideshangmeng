package com.lidegou.lideshangmeng.mobile.ui.personal.mydata;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IUserInfoDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IUserDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.UserInfo;

/**
 * Created by Administrator on 2016/12/15.
 */

public class PersonalDataPresenter implements PersonalDataContract.Presenter {

    private PersonalDataContract.View view;

    private IUserInfoDao iUserInfoDao;

    public PersonalDataPresenter(PersonalDataContract.View view) {
        this.view = view;
        this.iUserInfoDao = new IUserDaoImpl();
    }

    @Override
    public void start() {
        getUserInfo();
    }


    @Override
    public void getUserInfo() {
        if (view.getApp().getUid() == null) {
            view.showError("请先登录");
            return;
        }
        iUserInfoDao.getUserInfo(view.getApp().getUid(), new ResponseCallback<UserInfo.UserInfoBean>() {
            @Override
            public void onSuccess(UserInfo.UserInfoBean data) {
                if (data != null) {
                    view.callbackUserInfoSuccess(data);
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
    public void loginout() {
        if (view.getApp().getUid() == null) {
            view.showError("请先登录");
            return;
        }
        iUserInfoDao.loginout(view.getApp().getUid(), new ResponseCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                view.callbackLoginoutSuccess();
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
    public void updateLogo(String user_picture) {
        if (view.getApp().getUid() == null) {
            view.showError("请先登录");
            return;
        }
        iUserInfoDao.updateLogo(view.getApp().getUid(), user_picture, new ResponseCallback() {
            @Override
            public void onSuccess(Object data) {
                view.callbackUpdateLogoSuccess();
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
