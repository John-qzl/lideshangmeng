package com.lidegou.lideshangmeng.mobile.fragment.Search;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.ICommodityDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.ICommodityDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.Classify;
import com.lidegou.lideshangmeng.mobile.data.entity.Search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public class SearchAllPresenter implements SearchAllContract.Presenter{
    private SearchAllContract.View view;
    private ICommodityDao iCommodityDao;
    private List<Classify.Products> productsList = new ArrayList<>();
    public SearchAllPresenter(SearchAllContract.View view) {
        this.view = view;
        this.iCommodityDao = new ICommodityDaoImpl();
    }

    @Override
    public void start() {
        Hotkeyword();
    }


    @Override
    public void Hotkeyword() {
        iCommodityDao.SearchHotKeyWord(new ResponseCallback<Search.HotKeyWord>() {
            @Override
            public void onSuccess(Search.HotKeyWord data) {
                if (data != null) {
                    view.callbackSearchHotkeywordSuccess(data);
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
