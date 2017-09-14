package com.lidegou.lideshangmeng.mobile.ui.personal.notice.order;

import com.lidegou.lideshangmeng.mobile.data.entity.MessageOrderntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/12/15.
 */

public interface MineOrderMessageDetailsContract {

    interface View extends BaseView {
        String Id();

        void OrderMsgDesuccess(MessageOrderntity.Data data);
    }

    interface Presenter extends BasePresenter {
        void OrderMsgDes();
    }

}
