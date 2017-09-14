package com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.details;

import com.lidegou.lideshangmeng.mobile.data.entity.ShopsStore;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface ShopsStoreDetailsContract {

    interface View extends BaseView {
        String ShopUserid();

        void callbackFocusShopsSuccess(String msg);

        void callbackStoreDetailsSuccess(ShopsStore.ShopsStoreDetails shopsStoreDetails);
    }

    interface Presenter extends BasePresenter {
        void FocusShops();

        void StoreDetails();
    }

}
