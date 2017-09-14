package com.lidegou.lideshangmeng.mobile.fragment.SearchCommodity.fragment;

import com.lidegou.lideshangmeng.mobile.data.entity.PPEntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface SearchFragmentOneContract {

    interface View extends BaseView {
        void BrandsListSuccess(ArrayList<PPEntity> entities);

    }

    interface Presenter extends BasePresenter {
        void BrandsList();
    }

}
