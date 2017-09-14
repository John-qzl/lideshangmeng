package com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.tixian;

import com.lidegou.lideshangmeng.mobile.data.entity.MoneyManage;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface WithdrawalContract {

    interface View extends BaseView {
        String amount();
        String userNote();
        String bankNumber();
        String realName();


        void callbackAccountraplySuccess(MoneyManage.Accountraply accountraply);
        void callbackSubmitAccounTraplySuccess();
    }

    interface Presenter extends BasePresenter {
        void accountraply();

        void submitAccounTraply();
    }

}
