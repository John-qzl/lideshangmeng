package com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.applyRecord;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IMyDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IMyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.MoneyManage;

/**
 * Created by Administrator on 2016/8/26.
 */

public class ApplyRecordPresenter implements ApplyRecordContract.Presenter {
    private ApplyRecordContract.View view;
    private IMyDao iMyDao;

    public ApplyRecordPresenter(ApplyRecordContract.View view) {
        this.view = view;
        this.iMyDao = new IMyDaoImpl();
    }

    @Override
    public void start() {
        ApplyRecord();
    }


    @Override
    public void ApplyRecord() {
        iMyDao.applyRecord(view.getApp().getUid(), view.page() + "", new ResponseCallback<MoneyManage.ApplyRecord>() {
            @Override
            public void onSuccess(MoneyManage.ApplyRecord data) {
                if (data != null) {
                    view.callbackApplyRecordSuccess(data);
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

}
