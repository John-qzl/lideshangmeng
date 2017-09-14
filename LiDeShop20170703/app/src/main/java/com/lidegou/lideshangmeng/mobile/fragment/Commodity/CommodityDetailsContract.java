package com.lidegou.lideshangmeng.mobile.fragment.Commodity;


import com.lidegou.lideshangmeng.mobile.data.entity.Comments;
import com.lidegou.lideshangmeng.mobile.data.entity.Commodity;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

import java.util.List;

/**
 * Created by Y on 2016/8/13.
 */

public interface CommodityDetailsContract {

    interface View extends BaseView {
        String id();


        String warehouse_id();

        String area_id();

        String quick();

        String spec();

        String goods_id();

        String number();

        void callbackCommodityDetailsSuccess(Commodity.Data.CommodityDetails commodityDetails);

        void callbackCommodityBanner(List<Commodity.Data.CommodityDetails.PicturesBean> picturesBeanList);

        void callbackAddToCatSuccess(String msg);

        void callbackAddCollectionSuccess(String msg);
    }

    interface EvaluationView extends BaseView {
        String id();

        String page();

        String rank();

        void callbackEvaluationSuccess(Comments comments);

    }

    interface Presenter extends BasePresenter {
        void getCommodityDetails();

        void addToCat();

        void addCollection();

        void Evaluation();

    }

}
