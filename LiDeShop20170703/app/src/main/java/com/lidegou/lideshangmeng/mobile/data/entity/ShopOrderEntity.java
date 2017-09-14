package com.lidegou.lideshangmeng.mobile.data.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/13.
 */

public class ShopOrderEntity implements Serializable {
    private String order_id;
    private Order.DataBean.OrderGoodsBean goodsBean;

    public ShopOrderEntity(String order_id, Order.DataBean.OrderGoodsBean goodsBean) {
        this.order_id = order_id;
        this.goodsBean = goodsBean;
    }

    public Order.DataBean.OrderGoodsBean getGoodsBean() {
        return goodsBean;
    }

    public String getOrder_id() {
        return order_id;
    }



}
