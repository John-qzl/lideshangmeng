package com.lidegou.lideshangmeng.mobile.fragment.Commodity;


import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.ICommodityDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.ICommodityDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.Comments;
import com.lidegou.lideshangmeng.mobile.data.entity.Commodity;

import java.util.List;

/**
 * Created by Y on 2016/8/13.
 */

public class CommodityDetailsPresenter implements CommodityDetailsContract.Presenter {

    private CommodityDetailsContract.View view;
    private CommodityDetailsContract.EvaluationView evaluationView;

    //商品详情
    private Commodity commodity;
    //商品轮播图
    private List<Commodity> bannerList;

    //收藏标识
    private boolean isCollection;
    //记录标识
    private boolean isHistory;

    private ICommodityDao iCommodityDao;

    public CommodityDetailsPresenter(CommodityDetailsContract.View view) {
        this.view = view;
        this.iCommodityDao = new ICommodityDaoImpl();
    }

    public CommodityDetailsPresenter(CommodityDetailsContract.EvaluationView evaluationView) {
        this.evaluationView = evaluationView;
        this.iCommodityDao = new ICommodityDaoImpl();
    }

    @Override
    public void start() {
        getCommodityDetails();
    }


    @Override
    public void getCommodityDetails() {
        String uid = "";
        if (App.getApp().getUid() != null) {
            uid = App.getApp().getUid();
        }
        iCommodityDao.selectCommodityDetails(uid, view.id(), new ResponseCallback<Commodity.Data.CommodityDetails>() {
            @Override
            public void onSuccess(Commodity.Data.CommodityDetails data) {
                if (data != null) {
                    view.callbackCommodityDetailsSuccess(data);
                    view.callbackCommodityBanner(data.getPictures());
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
    public void addToCat() {
        if (view.getApp().getUid() == null) {
            view.showError("请先登录");
            return;
        }
        iCommodityDao.addToCat(view.getApp().getUid(), view.warehouse_id(), view.area_id(), view.quick(), view.spec(), view.goods_id(), view.number(), new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                view.callbackAddToCatSuccess(data);
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
    public void addCollection() {
        if (view.getApp().getUid() == null) {
            view.showError("请先登录");
            return;
        }
        iCommodityDao.addCollection(view.getApp().getUid(), view.id(), new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                view.callbackAddCollectionSuccess(data);
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
    public void Evaluation() {
        String uid = App.getApp().getUid();
        if (uid == null) {
            uid = "";
        }
        iCommodityDao.Evaluation(uid, evaluationView.id(), evaluationView.page(), evaluationView.rank(), new ResponseCallback<Comments>() {
            @Override
            public void onSuccess(Comments data) {
                if (data != null) {
                    evaluationView.callbackEvaluationSuccess(data);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    evaluationView.showError(msg);
                }
            }
        });
    }
}
