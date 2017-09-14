package com.lidegou.lideshangmeng.mobile.fragment.Commodity.comments;


import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.ICommodityDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.ICommodityDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.Comments;

import java.util.List;

/**
 * Created by Y on 2016/8/17.
 */

public class CommodityCommentsPresenter implements CommodityCommentsContract.Presenter {

    private CommodityCommentsContract.View view;
    private CommodityCommentsContract.AddView addView;
    private List<Comments> commentsList;
    private ICommodityDao iCommodityDao;

    public CommodityCommentsPresenter(CommodityCommentsContract.View view) {
        this.view = view;
        iCommodityDao = new ICommodityDaoImpl();
    }

    public CommodityCommentsPresenter(CommodityCommentsContract.AddView addView) {
        this.addView = addView;
        iCommodityDao = new ICommodityDaoImpl();
    }

    @Override
    public void start() {
        comments();
    }

    @Override
    public void comments() {
    }

    @Override
    public void addComments() {
        iCommodityDao.addEvaluation(addView.getApp().getUid(), addView.comment_rank(), addView.content(), addView.order_id(), addView.goods_id(), addView.pic(), addView.img_type(), new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                addView.callbackAddCommentsSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                addView.showError(msg);
            }
        });
    }
}
