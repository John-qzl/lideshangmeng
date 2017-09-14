package com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.tixian;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IMyDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IMyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.MoneyManage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public class WithdrawalPresenter implements WithdrawalContract.Presenter {
    private WithdrawalContract.View view;
    private IMyDao iMyDao;
    private List<MoneyManage.Accountraply> accountraplyList = new ArrayList<>();

    public WithdrawalPresenter(WithdrawalContract.View view) {
        this.view = view;
        this.iMyDao = new IMyDaoImpl();
    }

    @Override
    public void start() {
        accountraply();
    }


    @Override
    public void accountraply() {
        iMyDao.accounTraply(view.getApp().getUid(), new ResponseCallback<MoneyManage.Accountraply>() {
            @Override
            public void onSuccess(MoneyManage.Accountraply data) {
                if (data!=null){
                    view.callbackAccountraplySuccess(data);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    view.showError(msg);
                }
            }
        });
    }

    @Override
    public void submitAccounTraply() {
        if (view.amount()==null){
            view.showError("提现金额不能为空");
            return;
        }
        iMyDao.submitAccounTraply(view.getApp().getUid(), view.amount(), view.userNote(), view.bankNumber(), view.realName(), new ResponseCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                view.callbackSubmitAccounTraplySuccess();
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
