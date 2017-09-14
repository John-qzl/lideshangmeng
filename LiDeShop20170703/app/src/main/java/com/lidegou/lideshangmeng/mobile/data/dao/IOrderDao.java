package com.lidegou.lideshangmeng.mobile.data.dao;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.entity.Order;
import com.lidegou.lideshangmeng.mobile.data.entity.OrderEvaluationEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.OrderSubmit;
import com.lidegou.lideshangmeng.mobile.data.entity.OtherPayEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface IOrderDao {

    /**
     * 查询 0.全部 1.待付款 2.待收货
     */

    void getOrderList(String uid, String status, String page, ResponseCallback<Order> responseCallback);


    /**
     * 待评价
     */
    void waitComment(String uid, String page, ResponseCallback<OrderEvaluationEntity> responseCallback);

    /**
     * 查看订单详情
     */
    void getOrderDetail(String uid, String order_id, ResponseCallback<Order.OrderDetail> responseCallback);

    /**
     * 下单前确认订单
     */
    void getOrderSubmit(String uid, String cart_value, ResponseCallback<OrderSubmit> responseCallback);

    /**
     * 删除订单
     */
    void deleteOrderSubmit(String uid, String orderId, ResponseCallback<String> responseCallback);


    /**
     * 确认订单
     */
    void confirmOrderSubmit(String uid, String orderId, ResponseCallback<String> responseCallback);

    /**
     * 提交订单
     */
    void confirmOrderDone(String uid, String order_id, String shipping_id, String payment_id, String postscript, ResponseCallback<ArrayList<String>> responseCallback);

    /**
     * 立即支付
     */
    void confirmOrderPay(String uid, String order_id, String payType, ResponseCallback<OtherPayEntity> responseCallback);

    /**
     * button点击
     */
    void btnClick(String url, String uid, String order_id, ResponseCallback<String> responseCallback);
}
