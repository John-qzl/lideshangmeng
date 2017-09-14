package com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.chongzhi;

import com.lidegou.lideshangmeng.mobile.data.entity.OtherPayEntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface RechargeContract {

    interface View extends BaseView {
        String payment_id();

        String amount();

        String user_note();

        String process_type();

        String type();

        void payTypeSuccess(ArrayList<PayType> datas);

        void rechargeMoneySuccess( OtherPayEntity entity);
    }

    interface Presenter extends BasePresenter {
        void payType();

        void rechargeMoney();
    }

}
