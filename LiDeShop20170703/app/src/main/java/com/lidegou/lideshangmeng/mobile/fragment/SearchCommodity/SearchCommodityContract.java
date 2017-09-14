package com.lidegou.lideshangmeng.mobile.fragment.SearchCommodity;

import com.lidegou.lideshangmeng.mobile.data.entity.Classify;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface SearchCommodityContract {

    interface View extends BaseView {

        String page();
        String brand();
        String price_min();
        String price_max();
        String filter_attr();
        String sort();
        String order();
        String keyword();
        String isself();
        String size();
        String id();
        String hasgoods();
        String promotion();
        String typeSelect();
        void callbackProductsSuccess(List<Classify.Products> productsList);

        void callbackRefreshProductsSuccess(List<Classify.Products> productsList);

    }

    interface Presenter extends BasePresenter {
        void getProducts();

        void getRefresh();
    }

}
