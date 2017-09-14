package com.lidegou.lideshangmeng.mobile.fragment.Commodity.comments;

import com.lidegou.lideshangmeng.mobile.data.entity.Comments;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

import java.util.List;

/**
 * Created by Y on 2016/8/17.
 */

public interface CommodityCommentsContract {

    interface View extends BaseView {
        String id();
        String getComments();
        void callbaclCommentsList(List<Comments> commentsList);
    }

    interface AddView extends BaseView{
        String comment_rank();
        String content();
        String order_id();
        String goods_id();
        String pic();
        String img_type();
        void callbackAddCommentsSuccess(String msg);
    }

    interface Presenter extends BasePresenter {

        void comments();

        void addComments();

    }

}
