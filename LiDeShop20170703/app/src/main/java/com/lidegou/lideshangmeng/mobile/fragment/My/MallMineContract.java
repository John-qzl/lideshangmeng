package com.lidegou.lideshangmeng.mobile.fragment.My;

import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsIn;
import com.lidegou.lideshangmeng.mobile.data.entity.UserInfo;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface MallMineContract {

    interface View extends BaseView {
        void callbackMerchantsInCheckSuccess(MerchantsIn.MerchantsInCheck merchantsInCheck);

        void callbackUserInfoSuccess(List<UserInfo> userInfoList);

        void IsSellerSuccess(String data);

    }

    interface Presenter extends BasePresenter {
        void getUserInfo();

        void MerchantsInCheck();

        void IsSeller();
    }

}
