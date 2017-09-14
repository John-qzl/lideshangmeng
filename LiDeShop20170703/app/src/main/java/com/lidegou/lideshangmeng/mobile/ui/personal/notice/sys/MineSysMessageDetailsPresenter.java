package com.lidegou.lideshangmeng.mobile.ui.personal.notice.sys;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IMyDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IMyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.MessageSysEntity;

/**
 * Created by Administrator on 2016/12/15.
 */

public class MineSysMessageDetailsPresenter implements MineSysMessageDetailsContract.Presenter {

    private MineSysMessageDetailsContract.View view;

    private IMyDao iMyDao;

    public MineSysMessageDetailsPresenter(MineSysMessageDetailsContract.View view) {
        this.view = view;
        iMyDao = new IMyDaoImpl();
    }

    @Override
    public void start() {

    }

    @Override
    public void sysMsgDes() {
        iMyDao.SysMsgDes(App.getApp().getUid(), view.Id(), new ResponseCallback<MessageSysEntity.Data>() {
            @Override
            public void onSuccess(MessageSysEntity.Data data) {
                view.sysMsgDesSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {

            }
        });
    }
}
