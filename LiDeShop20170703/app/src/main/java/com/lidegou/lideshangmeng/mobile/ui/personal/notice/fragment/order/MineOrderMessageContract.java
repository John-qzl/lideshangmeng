package com.lidegou.lideshangmeng.mobile.ui.personal.notice.fragment.order;

import com.lidegou.lideshangmeng.mobile.data.entity.MessageOrderntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/12/15.
 */

public interface MineOrderMessageContract {

    interface View extends BaseView {
        void OrderMsgSuccess(MessageOrderntity entity);

        String page();
    }

    interface Presenter extends BasePresenter {
        void orderMsg();
    }

}
