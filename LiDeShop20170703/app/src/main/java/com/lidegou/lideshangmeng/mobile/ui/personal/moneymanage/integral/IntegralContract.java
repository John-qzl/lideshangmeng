package com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.integral;

import com.lidegou.lideshangmeng.mobile.data.entity.IntegralEntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface IntegralContract {

    interface View extends BaseView {

        int page();

        void callbackIntegralSuccess(IntegralEntity entity);
    }

    interface Presenter extends BasePresenter {
        void integra();
    }

}
