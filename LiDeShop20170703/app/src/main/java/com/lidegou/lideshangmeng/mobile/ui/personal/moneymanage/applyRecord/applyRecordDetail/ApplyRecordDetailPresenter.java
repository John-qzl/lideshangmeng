package com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.applyRecord.applyRecordDetail;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IMyDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IMyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.MoneyManage;
import com.lidegou.lideshangmeng.mobile.data.entity.OtherPayEntity;

/**
 * Created by Administrator on 2016/8/26.
 */

public class ApplyRecordDetailPresenter implements ApplyRecordDetailContract.Presenter {
    private ApplyRecordDetailContract.View view;
    private IMyDao iMyDao;

    public ApplyRecordDetailPresenter(ApplyRecordDetailContract.View view) {
        this.view = view;
        this.iMyDao = new IMyDaoImpl();
    }

    @Override
    public void start() {
        ApplyRecordDetail();
    }


    @Override
    public void ApplyRecordDetail() {
        iMyDao.applyRecordDetail(view.getApp().getUid(), view.id(), new ResponseCallback<MoneyManage.ApplyRecord.DataBean>() {
            @Override
            public void onSuccess(MoneyManage.ApplyRecord.DataBean data) {
                if (data != null) {
                    view.callbackApplyRecordDetailSuccess(data);
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
    public void recharge() {
        iMyDao.rechargePayMoney(view.getApp().getUid(), view.id(), view.payment_id(), new ResponseCallback<OtherPayEntity>() {
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

    @Override
    public void cancle() {
        iMyDao.cancelRecord(view.getApp().getUid(), view.id(), new ResponseCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean cancel) {
                view.cancleSuccess(cancel);
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
