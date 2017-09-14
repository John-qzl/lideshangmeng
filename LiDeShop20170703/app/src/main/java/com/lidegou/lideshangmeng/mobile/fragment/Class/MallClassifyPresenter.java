package com.lidegou.lideshangmeng.mobile.fragment.Class;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IClassifyDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IClassifyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.Classify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public class MallClassifyPresenter implements MallClassifyContract.Presenter {

    private List<Classify> classifyList = new ArrayList<>();
    private MallClassifyContract.View view;
    private IClassifyDao iClassifyDao;

    public MallClassifyPresenter(MallClassifyContract.View view) {
        this.view = view;
        this.iClassifyDao = new IClassifyDaoImpl();
    }

    @Override
    public void start() {
        getClassifyList();
    }

    @Override
    public void getClassifyList() {
        iClassifyDao.getClassifyList(new ResponseCallback<List<Classify>>() {
            @Override
            public void onSuccess(List<Classify> data) {
                if (data != null) {
                    classifyList.clear();
                    classifyList.addAll(data);
                    view.callbackClassifyListSuccess(classifyList);
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
