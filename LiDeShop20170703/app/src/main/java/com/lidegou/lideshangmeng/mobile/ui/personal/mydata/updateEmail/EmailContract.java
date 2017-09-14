package com.lidegou.lideshangmeng.mobile.ui.personal.mydata.updateEmail;

import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/12/15.
 */

public interface EmailContract {

    interface View extends BaseView{

        String email();

        void callbackUpdateEmailSuccess();

    }

    interface Presenter extends BasePresenter {

        void updateEmail();
    }

}
