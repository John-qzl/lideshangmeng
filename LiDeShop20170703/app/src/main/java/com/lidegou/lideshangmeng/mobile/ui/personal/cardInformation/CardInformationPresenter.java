package com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IMyDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IMyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.BankCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public class CardInformationPresenter implements CardInformationContract.Presenter {
    private CardInformationContract.View view;
    private IMyDao iMyDao;
    private List<BankCard> bankCardList=new ArrayList<>();
    public CardInformationPresenter(CardInformationContract.View view) {
        this.view = view;
        this.iMyDao = new IMyDaoImpl();
    }

    @Override
    public void start() {
        getCardlist();
    }


    @Override
    public void getCardlist() {
        if (view.getApp().getUid()==null){
            view.showError("请先登录");
            return;
        }
        iMyDao.getCardlist(view.getApp().getUid(), new ResponseCallback<List<BankCard>>() {
            @Override
            public void onSuccess(List<BankCard> data) {
                if (data!=null){
                    bankCardList.clear();
                    bankCardList.addAll(data);
                    view.callbackCardlistSuccess(bankCardList);
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
    public void deleteCard() {
        if (view.getApp().getUid()==null){
            view.showError("请先登录");
            return;
        }
        iMyDao.deleteCardlist(view.getApp().getUid(), view.id(), new ResponseCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                view.callbackDeleteCardSuccess();
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
