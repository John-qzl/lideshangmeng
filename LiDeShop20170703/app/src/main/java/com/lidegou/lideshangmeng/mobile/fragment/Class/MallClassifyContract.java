package com.lidegou.lideshangmeng.mobile.fragment.Class;

import com.lidegou.lideshangmeng.mobile.data.entity.Classify;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface MallClassifyContract {

    interface View extends BaseView {

        void callbackClassifyListSuccess(List<Classify> classifyList);

    }

    interface Presenter extends BasePresenter {
        void getClassifyList();
    }

}
