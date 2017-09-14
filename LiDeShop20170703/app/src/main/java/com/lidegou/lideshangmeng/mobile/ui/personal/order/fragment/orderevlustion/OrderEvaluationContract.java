package com.lidegou.lideshangmeng.mobile.ui.personal.order.fragment.orderevlustion;

import com.lidegou.lideshangmeng.mobile.data.entity.OrderEvaluationEntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface OrderEvaluationContract {

    interface View extends BaseView {
        void callbackOrderList(OrderEvaluationEntity entity);

        String page();

    }

    interface Presenter extends BasePresenter {
        void waitComment();
    }

}
