package com.lidegou.lideshangmeng.mobile.fragment.home;


import android.util.Log;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IBannerDao;
import com.lidegou.lideshangmeng.mobile.data.dao.IHomeDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IBannerDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IHomeDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.Announcement;
import com.lidegou.lideshangmeng.mobile.data.entity.Banner;
import com.lidegou.lideshangmeng.mobile.data.entity.Commodity;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsIn;
import com.lidegou.lideshangmeng.mobile.data.entity.MyLike;
import com.lidegou.lideshangmeng.mobile.data.entity.ShopsStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Y on 2016/8/10.
 */

public class MallHomePresenter implements MallHomeContract.Presenter {
    public static final String TAG = MallHomePresenter.class.getSimpleName();

    private MallHomeContract.View view;

    //轮播图
    private List<Banner> bannerList = new ArrayList<>();
    private List<MyLike> myLikeList = new ArrayList<>();
    //公告
    private List<Announcement> announcementList = new ArrayList<>();
    //商铺
    private List<ShopsStore> shopsStoreList = new ArrayList<>();
    //商品
    private List<Commodity> commodityList = new ArrayList<>();
    private IBannerDao iBannerDao;
    private IHomeDao iHomeDao;

    public MallHomePresenter(MallHomeContract.View view) {
        this.view = view;
        iHomeDao = new IHomeDaoImpl();
        iBannerDao = new IBannerDaoImpl();
    }

    @Override
    public void start() {
        banner();
        Announcement();
        mylike();
        shopsStore();
        commodity();
    }

    @Override
    public void refresh() {
        banner();
    }

    @Override
    public void banner() {
        iBannerDao.getBannderList(new ResponseCallback<List<Banner>>() {
            @Override
            public void onSuccess(List<Banner> data) {
                if (data != null) {
                    bannerList.clear();
                    bannerList.addAll(data);
                    view.callbackBanner(bannerList);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    view.showError(msg);
                }
            }
        });
    }

    @Override
    public void mylike() {
        Log.i("likePage", view.likePage());
        iHomeDao.getMyLikeList(view.likePage(), new ResponseCallback<List<MyLike>>() {
            @Override
            public void onSuccess(List<MyLike> data) {
                if (data != null) {
                    myLikeList.clear();
                    myLikeList.addAll(data);
                    view.callbackMyLikeSuccess(myLikeList);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    view.showError(msg);
                }
            }
        });
    }

    @Override
    public void Announcement() {
        iHomeDao.getAnnouncement(new ResponseCallback<List<Announcement>>() {
            @Override
            public void onSuccess(List<Announcement> data) {
                if (data != null) {
                    announcementList.clear();
                    announcementList.addAll(data);
                    view.callbackAnnouncementSuccess(announcementList);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    view.showError(msg);
                }
            }
        });
    }

    @Override
    public void shopsStore() {
        iHomeDao.getShopsStore(view.page(), new ResponseCallback<List<ShopsStore>>() {
            @Override
            public void onSuccess(List<ShopsStore> data) {
                if (data != null) {
                    shopsStoreList.clear();
                    shopsStoreList.addAll(data);
                    view.callbackShopsSuccess(shopsStoreList);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    view.showError(msg);
                }
            }
        });
    }

    @Override
    public void commodity() {
        iHomeDao.getCommodity(view.page(), new ResponseCallback<Commodity>() {
            @Override
            public void onSuccess(Commodity data) {
                if (data != null) {
                    view.callbackCommoditySuccess(data);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    view.showError(msg);
                }
            }
        });
    }

    @Override
    public void MerchantsInCheck() {
        iHomeDao.MerchantsInCheck(view.getApp().getUid(), new ResponseCallback<MerchantsIn.MerchantsInCheck>() {
            @Override
            public void onSuccess(MerchantsIn.MerchantsInCheck data) {
                if (data != null) {
                    view.callbackMerchantsInCheckSuccess(data);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    view.showError(msg);
                }
            }
        });
    }

}
