package com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.recommend;

import android.util.Log;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IMyDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IMyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.RecommendEntity;

/**
 * Created by Y on 2017/5/17.
 */

public class RecommendPresenter implements RecommendContract.Presenter {
    private RecommendContract.View view;
    private IMyDao iMyDao;

    public RecommendPresenter(RecommendContract.View view) {
        this.view = view;
        this.iMyDao = new IMyDaoImpl();
    }

    @Override
    public void start() {

    }

    @Override
    public void recommend() {
        iMyDao.recommend(view.getApp().getUid(), view.page() + "", new ResponseCallback<RecommendEntity>() {
            @Override
            public void onSuccess(RecommendEntity entity) {
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
