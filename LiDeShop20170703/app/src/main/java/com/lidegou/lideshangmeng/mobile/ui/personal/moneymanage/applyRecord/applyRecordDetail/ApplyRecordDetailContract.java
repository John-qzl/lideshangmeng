package com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.applyRecord.applyRecordDetail;

import com.lidegou.lideshangmeng.mobile.data.entity.MoneyManage;
import com.lidegou.lideshangmeng.mobile.data.entity.OtherPayEntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface ApplyRecordDetailContract {

    interface View extends BaseView {

        String id();

        String payment_id();

        void rechargeMoneySuccess(OtherPayEntity entity);

        void cancleSuccess(Boolean cancel);

        void callbackApplyRecordDetailSuccess(MoneyManage.ApplyRecord.DataBean dataBean);
    }

    interface Presenter extends BasePresenter {
        void ApplyRecordDetail();

        void recharge();

        void cancle();
    }

}
