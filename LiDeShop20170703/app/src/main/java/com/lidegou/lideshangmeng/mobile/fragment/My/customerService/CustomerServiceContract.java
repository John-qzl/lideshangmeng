package com.lidegou.lideshangmeng.mobile.fragment.My.customerService;

import com.lidegou.lideshangmeng.mobile.data.entity.ServiceBean;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface CustomerServiceContract {

    interface View extends BaseView {

        String page();

        void callbackAddMessageSuccess(String data);

        void callbackMessageListSuccess(ServiceBean serviceBean);
    }

    interface Presenter extends BasePresenter {
        void addmessage(String msg_title);

        void messagelist();

    }

}
