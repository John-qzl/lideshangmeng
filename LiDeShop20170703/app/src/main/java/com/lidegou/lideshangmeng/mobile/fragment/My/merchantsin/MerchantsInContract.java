package com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin;

import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsIn;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsInFiveEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsInFourEntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface MerchantsInContract {

    interface ViewOne extends BaseView {

        void callbackMerchantsInOneSuccess(MerchantsIn.MerchantsInProtocol merchantsInProtocol);

        void callbackMerchantsInOnedoneSuccess(String msg);
    }

    interface ViewTwo extends BaseView {
        void callbackMerchantsInTwoSuccess(MerchantsIn.MerchantsInContact merchantsInContact);

        void callbackMerchantsInTwodoneSuccess(String msg);
    }


    interface ViewThree extends BaseView {
        void callbackMerchantsInThreeSuccess(MerchantsIn.MerchantsInCompany merchantsInCompany);

        void callbackMerchantsInThreedoneSuccess(String msg);
    }

    interface ViewFour extends BaseView {
        void callbackMerchantsInFourSuccess(MerchantsInFourEntity entity);

        void callbackMerchantsInFourdoneSuccess(String msg);
    }

    interface ViewFive extends BaseView {
        void callbackMerchantsInFiveSuccess(MerchantsInFiveEntity entity);

    }


    interface Presenter extends BasePresenter {


        void MerchantsInOne();

        void MerchantsInOnedone();

        void MerchantsInTwo();

        void MerchantsInTwodone(String contactXinbie, String contactPhone, String contactName);

        void MerchantsInThree();

        void MerchantsInThreedone(String companyName, String business_license_id, String legal_person, String busines_scope, String license_fileImg, String company_adress, String company_contactTel, String province, String city, String district, String longitude, String latitude);

        void MerchantsInFour();

        void MerchantsInFourdone(String shop_categoryMain, String cat_id, String shop_class_keyWords, String rz_shopName);

        void MerchantsInFive();
    }

}
