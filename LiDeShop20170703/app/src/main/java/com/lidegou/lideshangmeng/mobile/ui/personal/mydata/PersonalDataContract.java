package com.lidegou.lideshangmeng.mobile.ui.personal.mydata;

import com.lidegou.lideshangmeng.mobile.data.entity.UserInfo;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/12/15.
 */

public interface PersonalDataContract {

    interface View extends BaseView{

        void callbackLoginoutSuccess();
        void callbackUserInfoSuccess(UserInfo.UserInfoBean userInfoBean);


        void callbackUpdateLogoSuccess();
    }

    interface Presenter extends BasePresenter {
        void getUserInfo();
        void loginout();

        void updateLogo(String user_picture);
    }

}
