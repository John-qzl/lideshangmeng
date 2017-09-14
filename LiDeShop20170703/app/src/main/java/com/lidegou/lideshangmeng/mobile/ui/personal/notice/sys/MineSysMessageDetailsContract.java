package com.lidegou.lideshangmeng.mobile.ui.personal.notice.sys;

import com.lidegou.lideshangmeng.mobile.data.entity.MessageSysEntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/12/15.
 */

public interface MineSysMessageDetailsContract {

    interface View extends BaseView {
        void sysMsgDesSuccess(MessageSysEntity.Data data);

        String Id();
    }

    interface Presenter extends BasePresenter {
        void sysMsgDes();
    }

}
