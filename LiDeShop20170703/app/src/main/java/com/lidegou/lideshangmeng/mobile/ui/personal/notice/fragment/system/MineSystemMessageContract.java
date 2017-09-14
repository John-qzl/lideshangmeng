package com.lidegou.lideshangmeng.mobile.ui.personal.notice.fragment.system;

import com.lidegou.lideshangmeng.mobile.data.entity.MessageSysEntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/12/15.
 */

public interface MineSystemMessageContract {

    interface View extends BaseView {
        String page();

        void sysMsgSuccess(MessageSysEntity entity);
    }

    interface Presenter extends BasePresenter {
        void sysMsg();
    }

}
