package com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.applyRecord;

import com.lidegou.lideshangmeng.mobile.data.entity.MoneyManage;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface ApplyRecordContract {

    interface View extends BaseView {

        int page();

        void callbackApplyRecordSuccess(MoneyManage.ApplyRecord applyRecord);
    }

    interface Presenter extends BasePresenter {
        void  ApplyRecord();
    }

}
