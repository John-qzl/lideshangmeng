package com.lidegou.lideshangmeng.mobile.ui.personal.updatePwd;

import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/12/15.
 */

public interface UpdatePwdContract {

    interface View extends BaseView{

        String old_password();
        String new_password1();
        String new_password();

        void callbackEditPasswordSuccess();

    }

    interface Presenter extends BasePresenter {

        void editPassword();
    }

}
