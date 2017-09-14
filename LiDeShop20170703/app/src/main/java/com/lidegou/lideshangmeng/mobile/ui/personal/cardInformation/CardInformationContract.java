package com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation;

import com.lidegou.lideshangmeng.mobile.data.entity.BankCard;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface CardInformationContract {

    interface View extends BaseView {

        String id();

        void callbackCardlistSuccess(List<BankCard> bankCardList);

        void callbackDeleteCardSuccess();

    }

    interface Presenter extends BasePresenter {
        void getCardlist();

        void deleteCard();
    }

}
