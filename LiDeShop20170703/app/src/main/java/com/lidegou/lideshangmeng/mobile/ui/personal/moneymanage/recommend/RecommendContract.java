package com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.recommend;

import com.lidegou.lideshangmeng.mobile.data.entity.RecommendEntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface RecommendContract {

    interface View extends BaseView {
        int page();

        void callbackIntegralSuccess(RecommendEntity entity);

    }

    interface Presenter extends BasePresenter {
        void recommend();
    }

}
