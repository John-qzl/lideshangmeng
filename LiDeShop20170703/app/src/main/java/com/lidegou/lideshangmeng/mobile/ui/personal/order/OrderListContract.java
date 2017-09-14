package com.lidegou.lideshangmeng.mobile.ui.personal.order;

import com.lidegou.lideshangmeng.mobile.data.entity.Order;
import com.lidegou.lideshangmeng.mobile.data.entity.OtherPayEntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface OrderListContract {

    interface View extends BaseView {

        String status();

        String page();

        void callbackOrderList(Order order);

        void btnClickSuccess(String msg);

        String pay_order_id();

        String payment_id();

        void callbackOrderPaySuccess( OtherPayEntity entity);
    }

    interface Presenter extends BasePresenter {
        void myorderList();

        void btn_click(String url, String order_id);

        void orderPay();
    }

}
