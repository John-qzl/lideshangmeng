package com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.chongzhi;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IMyDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IMyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.MoneyManage;
import com.lidegou.lideshangmeng.mobile.data.entity.OtherPayEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public class RechargePresenter implements RechargeContract.Presenter {
    private RechargeContract.View view;
    private IMyDao iMyDao;
    private List<MoneyManage.Accountraply> accountraplyList = new ArrayList<>();

    public RechargePresenter(RechargeContract.View view) {
        this.view = view;
        this.iMyDao = new IMyDaoImpl();
    }

    @Override
    public void start() {

    }


    @Override
    public void payType() {
        iMyDao.payType(new ResponseCallback<ArrayList<PayType>>() {
            @Override
            public void onSuccess(ArrayList<PayType> data) {
                view.payTypeSuccess(data);
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
    public void rechargeMoney() {
        if (view.amount() == null) {
            view.showError("充值金额不能为空");
            return;
        }
        iMyDao.rechargeMoney(view.getApp().getUid(), view.payment_id(), view.amount(), view.user_note(), view.process_type(), view.type(), new ResponseCallback<OtherPayEntity>() {
            @Override
            public void onSuccess(OtherPayEntity entity) {
                view.rechargeMoneySuccess(entity);
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
