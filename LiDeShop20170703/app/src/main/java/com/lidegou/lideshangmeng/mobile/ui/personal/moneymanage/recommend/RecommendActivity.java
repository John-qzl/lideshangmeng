package com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.recommend;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.RecommendEntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class RecommendActivity extends BaseActivity implements RecommendContract.View ,PullToRefreshBase.OnRefreshListener2<ListView>{
    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.lsitview)
    PullToRefreshListView lsitview;
    private int page = 1;
    private boolean isClear = false;
    private RecommendContract.Presenter presenter;
    private RecommendAdapter adapter;
    private List<RecommendEntity.DataBean> dataBeanList = new ArrayList<>();
    private RecommendEntity entity;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recommend;
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);
        linBack.setOnClickListener(this);
        adapter = new RecommendAdapter(this);
        adapter.setData(dataBeanList);
        lsitview.setAdapter(adapter);
        lsitview.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        lsitview.setOnRefreshListener(this);

        presenter = new RecommendPresenter(this);
        presenter.recommend();
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

    }

    @Override
    public void onPullDownToRefresh(final PullToRefreshBase<ListView> refreshView) {
        isClear = true;
        page = 1;
        presenter.recommend();
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
            presenter.recommend();
            return;
        } else {
            showToast("没有更多数据");
        }
    }

    @Override
    public int page() {
        return page;
    }

    @Override
    public void callbackIntegralSuccess(RecommendEntity entity) {
        this.entity = entity;
        if(page!=1&&entity.getData().size()==0){
            showToast("已经到底啦");
            return;
        }else if(page==1&&entity.getData().size()==0){

            return;
        }

        if (isClear) {
            isClear = false;
            dataBeanList.clear();
        }
        page++;
        dataBeanList.addAll(entity.getData());
        adapter.setData(dataBeanList);
        adapter.notifyDataSetChanged();
    }
}
