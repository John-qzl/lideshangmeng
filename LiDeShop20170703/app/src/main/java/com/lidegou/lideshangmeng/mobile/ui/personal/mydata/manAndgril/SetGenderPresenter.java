package com.lidegou.lideshangmeng.mobile.ui.personal.mydata.manAndgril;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IUserInfoDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IUserDaoImpl;

/**
 * Created by Administrator on 2016/12/15.
 */

public class SetGenderPresenter implements SetGenderContract.Presenter {

    private SetGenderContract.View view;

    private IUserInfoDao iUserInfoDao;

    public SetGenderPresenter(SetGenderContract.View view) {
        this.view = view;
        this.iUserInfoDao = new IUserDaoImpl();
    }

    @Override
    public void start() {

    }


    @Override
    public void setGender() {
        if (view.getApp().getUid()==null){
            view.showError("请先登录");
            return;
        }
        iUserInfoDao.setGender(view.getApp().getUid(), view.sex(), new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                view.callbackSetGenderSuccess(data);
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
