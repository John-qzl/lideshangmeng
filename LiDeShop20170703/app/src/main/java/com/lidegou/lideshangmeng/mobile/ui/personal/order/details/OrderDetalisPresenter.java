package com.lidegou.lideshangmeng.mobile.ui.personal.order.details;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.ICommodityDao;
import com.lidegou.lideshangmeng.mobile.data.dao.IOrderDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.ICommodityDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IOrderDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.Order;
import com.lidegou.lideshangmeng.mobile.data.entity.OrderSubmit;
import com.lidegou.lideshangmeng.mobile.data.entity.OtherPayEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/27.
 */

public class OrderDetalisPresenter implements OrderDetailsContract.Presenter {
    private OrderDetailsContract.View view;
    private List<Order> orderDetalisList;
    private IOrderDao iOrderDao;
    private int orderid;
    private ICommodityDao iCommodityDao;
    //默认收货地址
    //地址列表

    public OrderDetalisPresenter(OrderDetailsContract.View view) {
        this.view = view;
        iOrderDao = new IOrderDaoImpl();
        iCommodityDao = new ICommodityDaoImpl();
    }

    @Override
    public void start() {
        myorderDetailsList();
    }


    @Override
    public void isAddress() {
        iCommodityDao.isAddress(view.getApp().getUid(), new ResponseCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean isAddress) {
                view.isAddressSuccess(isAddress);
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
    public void myorderDetailsList() {
        iOrderDao.getOrderDetail(view.getApp().getUid(), view.pay_order_id(), new ResponseCallback<Order.OrderDetail>() {
            @Override
            public void onSuccess(Order.OrderDetail data) {
                if (data != null) {
                    view.callbackOrderDetailsList(data);
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
    public void itemClickOrderDetalis(int position) {

    }

    @Override
    public void orderSubmit() {
        iOrderDao.getOrderSubmit(view.getApp().getUid(), view.cartvalue(), new ResponseCallback<OrderSubmit>() {
            @Override
            public void onSuccess(OrderSubmit data) {
                if (data != null) {
                    view.callbackOrderSubmitSuccess(data);
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
    public void orderDone() {
        iOrderDao.confirmOrderDone(view.getApp().getUid(), view.done_order_id(), view.shipping_id(), view.payment_id(), view.postscript(), new ResponseCallback<ArrayList<String>>() {
            @Override
            public void onSuccess(ArrayList<String> data) {
                view.callbackOrderDoneSuccess(data);
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
}
