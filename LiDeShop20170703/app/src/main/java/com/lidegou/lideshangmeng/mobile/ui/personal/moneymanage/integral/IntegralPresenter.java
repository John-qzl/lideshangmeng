package com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.integral;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IMyDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IMyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.IntegralEntity;

/**
 * Created by Administrator on 2016/8/26.
 */

public class IntegralPresenter implements IntegralContract.Presenter {
    private IntegralContract.View view;
    private IMyDao iMyDao;

    public IntegralPresenter(IntegralContract.View view) {
        this.view = view;
        this.iMyDao = new IMyDaoImpl();
    }

    @Override
    public void start() {

    }


    @Override
    public void integra() {
        iMyDao.integral(view.getApp().getUid(), view.page() + "", new ResponseCallback<IntegralEntity>() {
            @Override
            public void onSuccess(IntegralEntity entity) {
                if (entity != null) {
                    view.callbackIntegralSuccess(entity);
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
