package com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage;

import com.lidegou.lideshangmeng.mobile.data.entity.MoneyManage;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface MoneyManageContract {

    interface View extends BaseView {
        void callbackMoneyManageSuccess(MoneyManage moneyManage);

        void isHasCardSuccess(boolean isHasCard);

        void bonusTurnSuccess(String msg);

        void setNumber(String num);
    }

    interface Presenter extends BasePresenter {
        void moneyManage();

        void isHasCard();

        void bonusTurn();
    }

}
