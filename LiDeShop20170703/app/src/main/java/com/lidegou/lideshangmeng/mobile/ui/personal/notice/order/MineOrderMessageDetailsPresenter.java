package com.lidegou.lideshangmeng.mobile.ui.personal.notice.order;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IMyDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IMyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.MessageOrderntity;

/**
 * Created by Administrator on 2016/12/15.
 */

public class MineOrderMessageDetailsPresenter implements MineOrderMessageDetailsContract.Presenter {

    private MineOrderMessageDetailsContract.View view;

    private IMyDao iMyDao;

    public MineOrderMessageDetailsPresenter(MineOrderMessageDetailsContract.View view) {
        this.view = view;
        iMyDao = new IMyDaoImpl();
    }

    @Override
    public void start() {

    }


    @Override
    public void OrderMsgDes() {
        iMyDao.OrderMsgDes(App.getApp().getUid(), view.Id(), new ResponseCallback<MessageOrderntity.Data>() {
            @Override
            public void onSuccess(MessageOrderntity.Data data) {
                view.OrderMsgDesuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {

            }
        });
    }
}
