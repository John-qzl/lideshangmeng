package com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.integral;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.IntegralEntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

//积分记录
public class IntegralActivity extends BaseActivity implements IntegralContract.View, PullToRefreshBase.OnRefreshListener2<ListView> {
    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.lsitview)
    PullToRefreshListView lsitview;

    private IntegralContract.Presenter presenter;
    private IntegralApapter adapter;
    private List<IntegralEntity.Data> dataBeanList = new ArrayList<>();
    private int page = 1;
    private boolean isClear = false;
    private IntegralEntity entity;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_integral;
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);
        linBack.setOnClickListener(this);
        adapter = new IntegralApapter(this);
        adapter.setData(dataBeanList);
        lsitview.setAdapter(adapter);
        lsitview.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        lsitview.setOnRefreshListener(this);

        presenter = new IntegralPresenter(this);
        presenter.integra();
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
    public int page() {
        return page;
    }

    @Override
    public void callbackIntegralSuccess(IntegralEntity entity) {
        this.entity = entity;
        if (isClear) {
            isClear = false;
            dataBeanList.clear();
        }
        page++;
        dataBeanList.addAll(entity.getData());
        adapter.setData(dataBeanList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }


    @Override
    public void onPullDownToRefresh(final PullToRefreshBase<ListView> refreshView) {
        isClear = true;
        page = 1;
        presenter.integra();
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
            presenter.integra();
            return;
        } else {
            showToast("没有更多数据");
        }
    }
}
