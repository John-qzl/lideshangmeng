package com.lidegou.lideshangmeng.mobile.fragment.My.footprint;

import android.util.Log;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IMyDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IMyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.Footprint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public class FootPrintPresenter implements FootPrintContract.Presenter {
    private FootPrintContract.View view;
    private IMyDao iMyDao;
    private List<Footprint> footprintList = new ArrayList<>();

    public FootPrintPresenter(FootPrintContract.View view) {
        this.view = view;
        this.iMyDao = new IMyDaoImpl();
    }

    @Override
    public void start() {
        getFootprint();
    }


    @Override
    public void getFootprint() {
        if (view.getApp().getUid() == null) {
            return;
        }
        iMyDao.getFootprint(view.getApp().getUid(), new ResponseCallback<List<Footprint>>() {
            @Override
            public void onSuccess(List<Footprint> data) {
                if (data != null) {
                    footprintList.clear();
                    footprintList.addAll(data);
                    view.callbackFootprintSuccess(footprintList);
                }
                Log.e("data", data.size() + "");
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
    public void clearFootprint() {
        iMyDao.clearFootprint(App.getApp().getUid(), new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                view.callbackClearFootprintSuccess(data);
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
