package com.lidegou.lideshangmeng.mobile.ui.personal.order.fragment.orderevlustion;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IOrderDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IOrderDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.Order;
import com.lidegou.lideshangmeng.mobile.data.entity.OrderEvaluationEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public class OrderEvaluationPresenter implements OrderEvaluationContract.Presenter {
    private OrderEvaluationContract.View view;
    private List<Order> orderList;
    private IOrderDao iOrderDao;
    private String type;

    public OrderEvaluationPresenter(OrderEvaluationContract.View view) {
        this.view = view;
        iOrderDao = new IOrderDaoImpl();
    }

    @Override
    public void start() {
        waitComment();
    }


    @Override
    public void waitComment() {
        iOrderDao.waitComment(App.getApp().getUid(), view.page(), new ResponseCallback<OrderEvaluationEntity>() {
            @Override
            public void onSuccess(OrderEvaluationEntity data) {
                view.callbackOrderList(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                view.showError(msg);
            }
        });
    }


}
