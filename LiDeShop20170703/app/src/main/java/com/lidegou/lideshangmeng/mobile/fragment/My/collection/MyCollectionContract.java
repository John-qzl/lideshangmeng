package com.lidegou.lideshangmeng.mobile.fragment.My.collection;


import com.lidegou.lideshangmeng.mobile.data.entity.Commodity;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Y on 2016/8/17.
 */

public interface MyCollectionContract {

    interface View extends BaseView {

        void callbackCommodityList(Commodity.Data.CollectionCommodity collectionCommodity);

        void callbackdeleteSuccess(String msg);

        String getPage();

        String getRec_id();

    }

    interface Presenter extends BasePresenter {

        void myCollection();

        void deleteCollection();
    }

}
