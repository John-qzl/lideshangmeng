package com.lidegou.lideshangmeng.mobile.fragment.Commodity.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.adapter.CommodityEvaluationAdapter;
import com.lidegou.lideshangmeng.mobile.data.entity.Comments;
import com.lidegou.lideshangmeng.mobile.fragment.Commodity.CommodityDetailsContract;
import com.lidegou.lideshangmeng.mobile.fragment.Commodity.CommodityDetailsPresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 宝贝评价
 */
@SuppressLint("ValidFragment")
public class CommodityEvaluationfragment extends BaseFragment implements CommodityDetailsContract.EvaluationView, PullToRefreshBase.OnRefreshListener2<ListView> {
    private RelativeLayout re_all_evaluation;
    private TextView tv_all_evaluation;
    private TextView tv_all_number;

    private RelativeLayout re_good_evaluation;
    private TextView tv_good_evaluation;
    private TextView tv_goods_number;

    private RelativeLayout re_centre_evaluation;
    private TextView tv_centre_evaluation;
    private TextView tv_centre_number;

    private RelativeLayout re_poor_evaluation;
    private TextView tv_poor_evaluation;
    private TextView tv_poor_number;

    private RelativeLayout re_pic_evaluation;
    private TextView tv_pic_evaluation;
    private TextView tv_pic_number;

    private PullToRefreshListView listView;
    private CommodityEvaluationAdapter adapter;

    private int nowPage = 0;
    private int page = 1;
    private String[] ranks = {"0", "4", "2", "1", "img"};

    private CommodityDetailsContract.Presenter presenter;

    private OnBackClickListener onBackClickListener;
    private String goodId;

    private boolean isClear = true;
    private List<Comments.DataBean> datas = new ArrayList<>();

    public CommodityEvaluationfragment(OnBackClickListener onBackClickListener, String goodId) {
        this.onBackClickListener = onBackClickListener;
        this.goodId = goodId;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_commodity_comments_list;
    }

    @Override
    protected void init() {
        re_all_evaluation = (RelativeLayout) getRootView().findViewById(R.id.re_all_evaluation);
        tv_all_evaluation = (TextView) re_all_evaluation.findViewById(R.id.tv_all_evaluation);
        tv_all_number = (TextView) re_all_evaluation.findViewById(R.id.tv_all_number);

        re_good_evaluation = (RelativeLayout) getRootView().findViewById(R.id.re_good_evaluation);
        tv_good_evaluation = (TextView) re_good_evaluation.findViewById(R.id.tv_good_evaluation);
        tv_goods_number = (TextView) re_good_evaluation.findViewById(R.id.tv_goods_number);

        re_centre_evaluation = (RelativeLayout) getRootView().findViewById(R.id.re_centre_evaluation);
        tv_centre_evaluation = (TextView) re_centre_evaluation.findViewById(R.id.tv_centre_evaluation);
        tv_centre_number = (TextView) re_centre_evaluation.findViewById(R.id.tv_centre_number);

        re_poor_evaluation = (RelativeLayout) getRootView().findViewById(R.id.re_poor_evaluation);
        tv_poor_evaluation = (TextView) re_poor_evaluation.findViewById(R.id.tv_poor_evaluation);
        tv_poor_number = (TextView) re_poor_evaluation.findViewById(R.id.tv_poor_number);

        re_pic_evaluation = (RelativeLayout) getRootView().findViewById(R.id.re_pic_evaluation);
        tv_pic_evaluation = (TextView) re_pic_evaluation.findViewById(R.id.tv_pic_evaluation);
        tv_pic_number = (TextView) re_pic_evaluation.findViewById(R.id.tv_pic_number);


        re_all_evaluation.setOnClickListener(this);
        re_good_evaluation.setOnClickListener(this);
        re_centre_evaluation.setOnClickListener(this);
        re_poor_evaluation.setOnClickListener(this);
        re_pic_evaluation.setOnClickListener(this);
        getRootView().findViewById(R.id.lin_back).setOnClickListener(this);

        listView = (PullToRefreshListView) getRootView().findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listView.setOnRefreshListener(this);
        adapter = new CommodityEvaluationAdapter(getActivity());
        adapter.setData(datas);
        listView.setAdapter(adapter);
    }

    public void setData(String allNumber, String goodNumber, String centreNumber, String poorNumber, String picNumber) {
        tv_all_number.setText(allNumber);
        tv_goods_number.setText(goodNumber);
        tv_centre_number.setText(centreNumber);
        tv_poor_number.setText(poorNumber);
        tv_pic_number.setText(picNumber);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new CommodityDetailsPresenter(this);
        }
        page = 1;
        isClear = true;
        presenter.Evaluation();
        setTab();
    }

    @Override
    protected void viewClick(View view) {
        if (view.getId() == R.id.lin_back) {
            onBackClickListener.callbackEvaluation();
            return;
        }
        switch (view.getId()) {
            case R.id.re_all_evaluation:
                if (nowPage == 0) {
                    return;
                }
                nowPage = 0;
                isClear = true;
                break;
            case R.id.re_good_evaluation:
                if (nowPage == 1) {
                    return;
                }
                nowPage = 1;
                isClear = true;
                break;
            case R.id.re_centre_evaluation:
                if (nowPage == 2) {
                    return;
                }
                nowPage = 2;
                break;
            case R.id.re_poor_evaluation:
                if (nowPage == 3) {
                    return;
                }
                nowPage = 3;
                isClear = true;
                break;
            case R.id.re_pic_evaluation:
                if (nowPage == 4) {
                    return;
                }
                nowPage = 4;
                isClear = true;
                break;
        }
        page = 1;
        setTab();
        presenter.Evaluation();
    }

    private void setTab() {
        if (nowPage == 0) {
            tv_all_evaluation.setTextColor(Color.RED);
            tv_all_number.setTextColor(Color.RED);
            tv_good_evaluation.setTextColor(Color.BLACK);
            tv_goods_number.setTextColor(Color.BLACK);
            tv_centre_evaluation.setTextColor(Color.BLACK);
            tv_centre_number.setTextColor(Color.BLACK);
            tv_poor_evaluation.setTextColor(Color.BLACK);
            tv_poor_number.setTextColor(Color.BLACK);
            tv_pic_evaluation.setTextColor(Color.BLACK);
            tv_pic_number.setTextColor(Color.BLACK);
        } else if (nowPage == 1) {
            tv_all_evaluation.setTextColor(Color.BLACK);
            tv_all_number.setTextColor(Color.BLACK);
            tv_good_evaluation.setTextColor(Color.RED);
            tv_goods_number.setTextColor(Color.RED);
            tv_centre_evaluation.setTextColor(Color.BLACK);
            tv_centre_number.setTextColor(Color.BLACK);
            tv_poor_evaluation.setTextColor(Color.BLACK);
            tv_poor_number.setTextColor(Color.BLACK);
            tv_pic_evaluation.setTextColor(Color.BLACK);
            tv_pic_number.setTextColor(Color.BLACK);
        } else if (nowPage == 2) {
            tv_all_evaluation.setTextColor(Color.BLACK);
            tv_all_number.setTextColor(Color.BLACK);
            tv_good_evaluation.setTextColor(Color.BLACK);
            tv_goods_number.setTextColor(Color.BLACK);
            tv_centre_evaluation.setTextColor(Color.RED);
            tv_centre_number.setTextColor(Color.RED);
            tv_poor_evaluation.setTextColor(Color.BLACK);
            tv_poor_number.setTextColor(Color.BLACK);
            tv_pic_evaluation.setTextColor(Color.BLACK);
            tv_pic_number.setTextColor(Color.BLACK);
        } else if (nowPage == 3) {
            tv_all_evaluation.setTextColor(Color.BLACK);
            tv_all_number.setTextColor(Color.BLACK);
            tv_good_evaluation.setTextColor(Color.BLACK);
            tv_goods_number.setTextColor(Color.BLACK);
            tv_centre_evaluation.setTextColor(Color.BLACK);
            tv_centre_number.setTextColor(Color.BLACK);
            tv_poor_evaluation.setTextColor(Color.RED);
            tv_poor_number.setTextColor(Color.RED);
            tv_pic_evaluation.setTextColor(Color.BLACK);
            tv_pic_number.setTextColor(Color.BLACK);
        } else if (nowPage == 4) {
            tv_all_evaluation.setTextColor(Color.BLACK);
            tv_all_number.setTextColor(Color.BLACK);
            tv_good_evaluation.setTextColor(Color.BLACK);
            tv_goods_number.setTextColor(Color.BLACK);
            tv_centre_evaluation.setTextColor(Color.BLACK);
            tv_centre_number.setTextColor(Color.BLACK);
            tv_poor_evaluation.setTextColor(Color.BLACK);
            tv_poor_number.setTextColor(Color.BLACK);
            tv_pic_evaluation.setTextColor(Color.RED);
            tv_pic_number.setTextColor(Color.RED);
        }
    }


    @Override
    protected void lazyLoad() {
    }

    @Override
    public String id() {
        return goodId;
    }

    @Override
    public String page() {
        return page + "";
    }

    @Override
    public String rank() {
        return ranks[nowPage];
    }

    @Override
    public void callbackEvaluationSuccess(Comments comments) {
        if (isClear) {
            datas.clear();
            isClear = false;
        }
        if (comments != null && comments.getData() != null && comments.getData().size() != 0) {
            datas.addAll(comments.getData());
            page++;
            adapter.setData(datas);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

    }

    @Override
    public void onPullUpToRefresh(final PullToRefreshBase<ListView> refreshView) {
        presenter.Evaluation();
        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshView.onRefreshComplete();
            }
        }, 1000);
    }


    public interface OnBackClickListener {
        void callbackEvaluation();
    }
}
