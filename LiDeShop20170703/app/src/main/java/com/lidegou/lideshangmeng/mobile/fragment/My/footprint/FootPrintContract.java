package com.lidegou.lideshangmeng.mobile.fragment.My.footprint;

import com.lidegou.lideshangmeng.mobile.data.entity.Footprint;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface FootPrintContract {

    interface View extends BaseView {

        void callbackFootprintSuccess(List<Footprint> footprintList);

        void callbackClearFootprintSuccess(String data);

    }

    interface Presenter extends BasePresenter {
        void getFootprint();

        void clearFootprint();
    }

}
