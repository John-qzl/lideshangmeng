package com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.accountdetails;

import com.lidegou.lideshangmeng.mobile.data.entity.AccountDetails;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface AccountDetailsContract {

    interface View extends BaseView {

        int page();

        void callbackAccountDetailsSuccess(AccountDetails data);
    }

    interface Presenter extends BasePresenter {
        void accountDetails();
    }

}
