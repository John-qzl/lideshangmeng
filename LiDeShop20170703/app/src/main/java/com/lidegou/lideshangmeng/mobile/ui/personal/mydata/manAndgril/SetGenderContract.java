package com.lidegou.lideshangmeng.mobile.ui.personal.mydata.manAndgril;

import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/12/15.
 */

public interface SetGenderContract {

    interface View extends BaseView{
        void callbackSetGenderSuccess(String msg);
        String sex();
    }

    interface Presenter extends BasePresenter {
        void setGender();
    }

}
