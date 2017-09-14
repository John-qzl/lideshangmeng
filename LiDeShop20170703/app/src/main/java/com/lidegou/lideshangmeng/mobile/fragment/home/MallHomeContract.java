package com.lidegou.lideshangmeng.mobile.fragment.home;


import com.lidegou.lideshangmeng.mobile.data.entity.Announcement;
import com.lidegou.lideshangmeng.mobile.data.entity.Banner;
import com.lidegou.lideshangmeng.mobile.data.entity.Commodity;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsIn;
import com.lidegou.lideshangmeng.mobile.data.entity.MyLike;
import com.lidegou.lideshangmeng.mobile.data.entity.ShopsStore;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

import java.util.List;

/**
 * Created by Y on 2016/8/10.
 */

public interface MallHomeContract {

    interface View extends BaseView {

        String page();

        String likePage();


        void callbackBanner(List<Banner> bannerList);

        void callbackAnnouncementSuccess(List<Announcement> announcementList);

        void callbackShopsSuccess(List<ShopsStore> shopsStoreList);

        void callbackCommoditySuccess(Commodity commodity);

        void callbackMyLikeSuccess(List<MyLike> myLikeList);

        void callbackMerchantsInCheckSuccess(MerchantsIn.MerchantsInCheck data);

    }


    interface Presenter extends BasePresenter {

        void refresh();

        void banner();//轮播

        void mylike();//猜你喜欢

        void Announcement();//公告

        void shopsStore();//商铺

        void commodity();//商品

        void MerchantsInCheck();

    }
}
