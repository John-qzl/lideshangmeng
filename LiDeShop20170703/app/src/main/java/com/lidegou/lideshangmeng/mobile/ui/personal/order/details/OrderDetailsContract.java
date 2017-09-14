package com.lidegou.lideshangmeng.mobile.ui.personal.order.details;


import com.lidegou.lideshangmeng.mobile.data.entity.Order;
import com.lidegou.lideshangmeng.mobile.data.entity.OrderSubmit;
import com.lidegou.lideshangmeng.mobile.data.entity.OtherPayEntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/27.
 */

public interface OrderDetailsContract {
    interface View extends BaseView {

        void isAddressSuccess(boolean isAddress);

        String cartvalue();

        void callbackOrderDetailsList(Order.OrderDetail orderDetail);

        void callbackOrderSubmitSuccess(OrderSubmit orderSubmit);

        void callbackOrderDoneSuccess(ArrayList<String> data);

        String shipping_id();

        String payment_id();

        String postscript();

        void callbackOrderPaySuccess(OtherPayEntity entity);

        void btnClickSuccess(String data);

        String done_order_id();

        String pay_order_id();

    }

    interface Presenter extends BasePresenter {

        void isAddress();

        void myorderDetailsList();

        void itemClickOrderDetalis(int position);

        void btn_click(String url, String order_id);

        void orderSubmit();//确认下单


        void orderDone();//提交订单

        void orderPay();//立即支付

    }
}
