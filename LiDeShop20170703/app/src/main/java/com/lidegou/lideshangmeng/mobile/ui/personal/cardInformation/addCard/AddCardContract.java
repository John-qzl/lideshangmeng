package com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.addCard;

import com.lidegou.lideshangmeng.mobile.data.entity.BankCard;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface AddCardContract {

    interface View extends BaseView {

        String bankCode();
        String bankCard();
        String bankUserName();
        String bankRegion();

        void callbackAddCardSuccess();
        void callbackAddcardinfoSuccess(BankCard.AddCardInfo addCardInfo);
    }

    interface Presenter extends BasePresenter {

        void addcard();

        void addcardinfo();

    }

}
