package com.lidegou.lideshangmeng.mobile.fragment.My.customerService;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IMyDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IMyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.ServiceBean;

/**
 * Created by Administrator on 2016/8/26.
 */

public class CustomerServicePresenter implements CustomerServiceContract.Presenter {
    private CustomerServiceContract.View view;
    private IMyDao iMyDao;

    public CustomerServicePresenter(CustomerServiceContract.View view) {
        this.view = view;
        this.iMyDao = new IMyDaoImpl();
    }

    @Override
    public void start() {
        messagelist();
    }

    @Override
    public void addmessage(String msg_title) {
        if (view.getApp().getUid() == null) {
            view.showError("请先登录");
            return;
        }
        iMyDao.addmessage(view.getApp().getUid(), msg_title, "1", new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                view.callbackAddMessageSuccess(data);
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
    public void messagelist() {
        iMyDao.messageList(view.getApp().getUid(), view.page(), new ResponseCallback<ServiceBean>() {
            @Override
            public void onSuccess(ServiceBean serviceBean) {
                view.callbackMessageListSuccess(serviceBean);
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
