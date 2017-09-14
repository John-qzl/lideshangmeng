package com.lidegou.lideshangmeng.mobile.ui.personal.order;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IOrderDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IOrderDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.Order;
import com.lidegou.lideshangmeng.mobile.data.entity.OtherPayEntity;

/**
 * Created by Administrator on 2016/8/26.
 */

public class OrderListPresenter implements OrderListContract.Presenter {
    private OrderListContract.View view;
    private IOrderDao iOrderDao;

    public OrderListPresenter(OrderListContract.View view) {
        this.view = view;
        iOrderDao = new IOrderDaoImpl();
    }

    @Override
    public void start() {
        myorderList();
    }

    @Override
    public void myorderList() {
        if (view.getApp().getUid() == null) {
            view.showError("请先登录");
            return;
        }
        iOrderDao.getOrderList(view.getApp().getUid(), view.status(), view.page(), new ResponseCallback<Order>() {
            @Override
            public void onSuccess(Order data) {
                if (data != null) {
                    view.callbackOrderList(data);
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
    public void btn_click(String url, String order_id) {
        iOrderDao.btnClick(url, App.getApp().getUid(), order_id, new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                view.btnClickSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                view.showError(msg);
            }
        });
    }

    @Override
    public void orderPay() {
        iOrderDao.confirmOrderPay(view.getApp().getUid(), view.pay_order_id(), view.payment_id(), new ResponseCallback<OtherPayEntity>() {
            @Override
            public void onSuccess(OtherPayEntity data) {
                view.callbackOrderPaySuccess(data);
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
