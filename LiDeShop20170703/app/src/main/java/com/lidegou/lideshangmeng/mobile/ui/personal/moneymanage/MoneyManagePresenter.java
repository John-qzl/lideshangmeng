package com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IMyDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IMyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.MoneyManage;

/**
 * Created by Administrator on 2016/8/26.
 */

public class MoneyManagePresenter implements MoneyManageContract.Presenter {
    private MoneyManageContract.View view;
    private IMyDao iMyDao;

    public MoneyManagePresenter(MoneyManageContract.View view) {
        this.view = view;
        this.iMyDao = new IMyDaoImpl();
    }

    @Override
    public void start() {
        moneyManage();
        getNumber();
    }


    @Override
    public void moneyManage() {
        if (view.getApp().getUid() == null) {
            view.showError("请先登录");
            return;
        }
        iMyDao.getMoneyManage(view.getApp().getUid(), new ResponseCallback<MoneyManage>() {
            @Override
            public void onSuccess(MoneyManage moneyManage) {
                view.callbackMoneyManageSuccess(moneyManage);
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
    public void isHasCard() {

        if (view.getApp().getUid() == null) {
            view.showError("请先登录");
            return;
        }
        iMyDao.isHasCard(view.getApp().getUid(), new ResponseCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean isHasCard) {
                view.isHasCardSuccess(isHasCard);
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
    public void bonusTurn() {
        if (view.getApp().getUid() == null) {
            view.showError("请先登录");
            return;
        }
        iMyDao.bonusTurn(view.getApp().getUid(), new ResponseCallback<String>() {
            @Override
            public void onSuccess(String msg) {
                view.bonusTurnSuccess(msg);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    view.showError(msg);
                }
            }
        });
    }

    public void getNumber() {
        if (view.getApp().getUid() == null) {
            view.showError("请先登录");
            return;
        }
        iMyDao.getNumber(view.getApp().getUid(), new ResponseCallback<String>() {
            @Override
            public void onSuccess(String msg) {
                view.setNumber(msg);
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
