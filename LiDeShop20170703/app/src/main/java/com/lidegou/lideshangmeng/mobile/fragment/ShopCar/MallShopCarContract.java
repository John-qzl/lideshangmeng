package com.lidegou.lideshangmeng.mobile.fragment.ShopCar;

import com.lidegou.lideshangmeng.mobile.data.entity.ShoppingCart;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/12/15.
 */

public interface MallShopCarContract {

    interface View extends BaseView {
        void callbackCarListSuccess(ShoppingCart shoppingCart);

        void callbackDeleteShopCarListSuccess(String msg);


        String rec_id();

        String batchID();

        String number();

        String arr();

        void callbackUpdateGoodNumebrSuccess(String msg);

        void isAddressSuccess(boolean isAddress);
    }

    interface Presenter extends BasePresenter {
        void carList();

        void deleteShopCar();

        void batchDeleteShopCar();

        void updateGoodNumebr();

        void isAddress();
    }

}
