package com.lidegou.lideshangmeng.mobile.ui.personal.dosingle;

import com.lidegou.lideshangmeng.mobile.data.entity.MoneyManage;
import com.lidegou.lideshangmeng.mobile.data.entity.OtherPayEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.PaySinleEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.Single;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface MineSingleContract {

    interface View extends BaseView {

        String getPhone();

        String getPrice();

        String getGoodsname();

        String getGoodsnum();

        String getGoodsinfo();

        String getQingdanbianhao();

        String getIs_me();

        String getZuodan_img();

        String getPicType();

        void callbackCeilingMoneySuccess(Single.Data.CeilingMoney ceilingMoney);

        void callbackMcheckuserStringSuccess(String data);

        void callbackMcheckuserListSuccess(ArrayList<String> list);

        void callbackAddSingleSuccess(String id);


        void callbackMoneyManageSuccess(MoneyManage moneyManage);
    }

    interface SingleList extends BaseView {
        String page();

        String status();

        void callbackSingleListSuccess(Single data);
    }

    interface PaySingle extends BaseView {
        String Id();

        String payType();

        void callbackPaySingle(PaySinleEntity entity);

        void paySuccess( OtherPayEntity entity);

    }

    interface Presenter extends BasePresenter {

        void getCeilingMoney();

        boolean getMcheckuser();

        void getMcheckuser(String id);

        boolean AddSingle();

        void getSingleList();

        void getPaySingle();

        void pay();
    }

}
