package com.lidegou.lideshangmeng.mobile.ui.personal.notice.fragment.order;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IMyDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IMyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.MessageOrderntity;

/**
 * Created by Administrator on 2016/12/15.
 */

public class MineOrderMessagePresenter implements MineOrderMessageContract.Presenter {

    private MineOrderMessageContract.View view;

    private IMyDao iMyDao;

    public MineOrderMessagePresenter(MineOrderMessageContract.View view) {
        this.view = view;
        iMyDao = new IMyDaoImpl();
    }

    @Override
    public void start() {

    }

    @Override
    public void orderMsg() {
        iMyDao.OrderMsg(App.getApp().getUid(), view.page(), new ResponseCallback<MessageOrderntity>() {
            @Override
            public void onSuccess(MessageOrderntity data) {
                view.OrderMsgSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                view.showError(msg);
            }
        });
    }
}
