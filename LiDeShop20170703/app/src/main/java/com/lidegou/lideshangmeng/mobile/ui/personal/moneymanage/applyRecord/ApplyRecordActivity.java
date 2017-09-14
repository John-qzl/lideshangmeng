package com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.applyRecord;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.MoneyManage;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.applyRecord.applyRecordDetail.ApplyRecordDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 申请记录
 */
public class ApplyRecordActivity extends BaseActivity implements ApplyRecordContract.View, AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener2<ListView> {


    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.lsitview)
    PullToRefreshListView lsitview;
    private ApplyRecordContract.Presenter presenter;
    private ApplyRecordApapter applyRecordApapter;
    private List<MoneyManage.ApplyRecord.DataBean> dataBeanList = new ArrayList<>();
    private int page = 1;
    private boolean isClear = false;
    private MoneyManage.ApplyRecord applyRecord;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_record;
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);
        linBack.setOnClickListener(this);
        applyRecordApapter = new ApplyRecordApapter(this);
        applyRecordApapter.setData(dataBeanList);
        lsitview.setAdapter(applyRecordApapter);
        lsitview.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        lsitview.setOnRefreshListener(this);
        lsitview.setOnItemClickListener(this);

        if (presenter == null) {
            presenter = new ApplyRecordPresenter(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        page = 1;
        isClear = true;
        presenter.start();
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
    public void callbackApplyRecordSuccess(MoneyManage.ApplyRecord applyRecord) {
        if (isClear) {
            isClear = false;
            dataBeanList.clear();
        }
        this.applyRecord = applyRecord;
        page++;
        dataBeanList.addAll(applyRecord.getData());
        applyRecordApapter.setData(dataBeanList);
        applyRecordApapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }


    @Override
    public void onPullDownToRefresh(final PullToRefreshBase<ListView> refreshView) {
        isClear = true;
        page = 1;
        presenter.ApplyRecord();
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
        if (applyRecord == null || applyRecord.getTotalPage() >= page) {
            presenter.ApplyRecord();
            return;
        } else {
            showToast("没有更多数据");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String dataId = dataBeanList.get(position - 1).getId();
        Intent intent = new Intent(ApplyRecordActivity.this, ApplyRecordDetailActivity.class);
        intent.putExtra("id", dataId);
        startActivity(intent);
    }
}
