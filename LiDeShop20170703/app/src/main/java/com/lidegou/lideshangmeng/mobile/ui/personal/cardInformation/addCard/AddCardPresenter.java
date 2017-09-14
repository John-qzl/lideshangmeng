package com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.addCard;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IMyDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IMyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.BankCard;

/**
 * Created by Administrator on 2016/8/26.
 */

public class AddCardPresenter implements AddCardContract.Presenter {
    private AddCardContract.View view;
    private IMyDao iMyDao;

    public AddCardPresenter(AddCardContract.View view) {
        this.view = view;
        this.iMyDao = new IMyDaoImpl();
    }

    @Override
    public void start() {
        addcardinfo();
    }


    @Override
    public void addcard() {
        iMyDao.addCard(view.getApp().getUid(), view.bankCode(), view.bankCard(), view.bankUserName(), view.bankRegion(), new ResponseCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                view.callbackAddCardSuccess();
            }

            @Override
            public void onFailure(int code, String msg) {
                view.showError(msg);
            }
        });
    }

    @Override
    public void addcardinfo() {
        iMyDao.addCardInfo(view.getApp().getUid(), new ResponseCallback<BankCard.AddCardInfo>() {
            @Override
            public void onSuccess(BankCard.AddCardInfo data) {
                if (data!=null){
                    view.callbackAddcardinfoSuccess(data);
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
}
