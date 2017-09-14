package com.lidegou.lideshangmeng.mobile.fragment.SearchCommodity;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.ICommodityDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.ICommodityDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.Classify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public class SearchCommodityPresenter implements SearchCommodityContract.Presenter {
    private SearchCommodityContract.View view;
    private ICommodityDao iCommodityDao;
    private List<Classify.Products> productsList = new ArrayList<>();

    public SearchCommodityPresenter(SearchCommodityContract.View view) {
        this.view = view;
        this.iCommodityDao = new ICommodityDaoImpl();
    }

    @Override
    public void start() {
        getProducts();
    }


    @Override
    public void getProducts() {
        iCommodityDao.ClassSubProductsCommodity(view.page(), view.brand(), view.price_min(), view.price_max(), view.filter_attr(), view.sort(), view.order(), view.keyword(), view.isself(), view.size(), view.id(), view.hasgoods(), view.promotion(), new ResponseCallback<List<Classify.Products>>() {
            @Override
            public void onSuccess(List<Classify.Products> data) {
                if (data != null) {
                    productsList.clear();
                    productsList.addAll(data);
                    view.callbackProductsSuccess(productsList);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                view.showError(msg);
            }
        });
    }

    @Override
    public void getRefresh() {
        iCommodityDao.ClassSubProductsCommodity(view.page(), view.brand(), view.price_min(), view.price_max(), view.filter_attr(), view.sort(), view.order(), view.keyword(), view.isself(), view.size(), view.id(), view.hasgoods(), view.promotion(), new ResponseCallback<List<Classify.Products>>() {
            @Override
            public void onSuccess(List<Classify.Products> data) {
                if (data != null) {
                    productsList.clear();
                    productsList.addAll(data);
                    view.callbackRefreshProductsSuccess(productsList);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                view.showError(msg);
            }
        });
    }
}
