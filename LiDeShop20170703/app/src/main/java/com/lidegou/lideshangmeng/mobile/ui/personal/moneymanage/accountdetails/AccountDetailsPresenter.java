package com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.accountdetails;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IMyDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IMyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.AccountDetails;

/**
 * Created by Administrator on 2016/8/26.
 */

public class AccountDetailsPresenter implements AccountDetailsContract.Presenter {
    private AccountDetailsContract.View view;
    private IMyDao iMyDao;

    public AccountDetailsPresenter(AccountDetailsContract.View view) {
        this.view = view;
        this.iMyDao = new IMyDaoImpl();
    }

    @Override
    public void start() {
        accountDetails();
    }


    @Override
    public void accountDetails() {
        iMyDao.getAccountDetails(view.getApp().getUid(), view.page() + "", new ResponseCallback<AccountDetails>() {
            @Override
            public void onSuccess(AccountDetails data) {
                view.callbackAccountDetailsSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    view.showError(msg);
                }
            }
        });
    }
}
