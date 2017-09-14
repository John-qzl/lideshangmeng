package com.lidegou.lideshangmeng.mobile.ui.personal.order.fragment.orderevlustion;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.OrderEvaluationEntity;
import com.lidegou.lideshangmeng.mobile.fragment.Commodity.comments.CommodityAddCommentsActivity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.util.dialog.LoadingDialog;
import com.lidegou.lideshangmeng.mobile.util.dialog.PromptDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 评价页面
 */
public class OrderEvaluationActivity extends BaseActivity implements OrderEvaluationContract.View, OrderEvluationAdapter.OrderEvluationListener, PullToRefreshBase.OnRefreshListener2<ListView> {
    private PullToRefreshListView lv;
    private OrderEvluationAdapter orderEvluationAdapter;
    private OrderEvaluationPresenter presenter;
    private boolean isClear = false;
    private int page = 1;

    //提示框
    private PromptDialog promptDialog;
    private LoadingDialog loadingDialog;
    private TextView zanwu;
    private OrderEvaluationEntity entity;


    private List<OrderEvaluationEntity.Data> dataBeanList = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_tab_common;
    }

    @Override
    protected void init() {
        lv = (PullToRefreshListView) findViewById(R.id.commodity_order_recycler_view);
        lv.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        lv.setOnRefreshListener(this);
        orderEvluationAdapter = new OrderEvluationAdapter(this, dataBeanList);
        orderEvluationAdapter.setOnOrderEvluationListener(this);
        lv.setAdapter(orderEvluationAdapter);

        zanwu = (TextView) findViewById(R.id.zanwu);
        if (promptDialog == null) {
            promptDialog = new PromptDialog(getContext());
        }
        loadingDialog = new LoadingDialog(getContext());

        findViewById(R.id.lin_back).setOnClickListener(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new OrderEvaluationPresenter(this);
        }
        page = 1;
        isClear = true;
        presenter.waitComment();
    }


    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
        }
    }


    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void callbackOrderList(OrderEvaluationEntity entity) {
        this.entity = entity;
        if (isClear) {
            dataBeanList.clear();
            isClear = false;
        }
        page++;

        this.dataBeanList.addAll(entity.getData());
        orderEvluationAdapter.notifyDataSetChanged();

    }


    @Override
    public String page() {
        return page + "";
    }


    @Override
    public void onPullDownToRefresh(final PullToRefreshBase<ListView> refreshView) {
        isClear = true;
        page = 1;
        presenter.waitComment();
        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshView.onRefreshComplete();
            }
        }, 500);
    }

    @Override
    public void onPullUpToRefresh(final PullToRefreshBase<ListView> refreshView) {
        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshView.onRefreshComplete();
            }
        }, 500);
        if (entity == null || entity.getTotalPage() >= page) {
            presenter.waitComment();
        } else {
            showToast("没有更多数据");
        }

    }


    @Override
    public void btnClick(String order_id, String good_id, String good_name, String good_image) {
        Intent intent = new Intent(getContext(), CommodityAddCommentsActivity.class);
        intent.putExtra("orderId", order_id);
        intent.putExtra("shopId", good_id);
        intent.putExtra("shopName", good_name);
        intent.putExtra("shopPic", good_image);
        startActivity(intent);
    }
}
