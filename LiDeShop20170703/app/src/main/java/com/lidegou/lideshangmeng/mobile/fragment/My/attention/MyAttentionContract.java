package com.lidegou.lideshangmeng.mobile.fragment.My.attention;


import com.lidegou.lideshangmeng.mobile.data.entity.ShopsStore;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Y on 2016/8/17.
 */

public interface MyAttentionContract {

    interface View extends BaseView {

        void callbackAttentionList(ShopsStore.Attention attention);

        void callbackdeleteSuccess();

        String getPage();

        String getShopId();


    }

    interface Presenter extends BasePresenter {

        void myAttention();

        void deleteAttention();


    }

}
