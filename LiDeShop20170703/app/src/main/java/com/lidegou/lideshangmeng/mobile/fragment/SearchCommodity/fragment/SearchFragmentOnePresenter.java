package com.lidegou.lideshangmeng.mobile.fragment.SearchCommodity.fragment;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.ICommodityDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.ICommodityDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.Classify;
import com.lidegou.lideshangmeng.mobile.data.entity.PPEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public class SearchFragmentOnePresenter implements SearchFragmentOneContract.Presenter {
    private SearchFragmentOneContract.View view;
    private ICommodityDao iCommodityDao;
    private List<Classify.Products> productsList = new ArrayList<>();

    public SearchFragmentOnePresenter(SearchFragmentOneContract.View view) {
        this.view = view;
        this.iCommodityDao = new ICommodityDaoImpl();
    }

    @Override
    public void start() {
    }


    @Override
    public void BrandsList() {
        iCommodityDao.BrandsList(new ResponseCallback<ArrayList<PPEntity>>() {

            @Override
            public void onSuccess(ArrayList<PPEntity> data) {
                view.BrandsListSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                view.showError(msg);
            }
        });
    }
}
