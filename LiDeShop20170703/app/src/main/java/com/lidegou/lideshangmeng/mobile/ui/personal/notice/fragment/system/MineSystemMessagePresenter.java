package com.lidegou.lideshangmeng.mobile.ui.personal.notice.fragment.system;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IMyDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IMyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.MessageSysEntity;

/**
 * Created by Administrator on 2016/12/15.
 */

public class MineSystemMessagePresenter implements MineSystemMessageContract.Presenter {

    private MineSystemMessageContract.View view;

    private IMyDao iMyDao;

    public MineSystemMessagePresenter(MineSystemMessageContract.View view) {
        this.view = view;
        iMyDao = new IMyDaoImpl();
    }

    @Override
    public void start() {

    }

    @Override
    public void sysMsg() {
        iMyDao.SysMsg(App.getApp().getUid(), view.page(), new ResponseCallback<MessageSysEntity>() {
            @Override
            public void onSuccess(MessageSysEntity data) {
                view.sysMsgSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                view.showError(msg);
            }
        });
    }
}
