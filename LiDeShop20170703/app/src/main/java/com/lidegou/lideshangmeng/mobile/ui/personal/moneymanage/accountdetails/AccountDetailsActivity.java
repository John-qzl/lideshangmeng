package com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.accountdetails;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.AccountDetails;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 账户明细
 */
public class AccountDetailsActivity extends BaseActivity implements AccountDetailsContract.View, PullToRefreshBase.OnRefreshListener2<ListView> {

    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.listView)
    PullToRefreshListView listView;
    private AccountDetailsContract.Presenter presenter;
    private AccountDetailsApapter accountDetailsApapter;
    private AccountDetails detials;
    private List<AccountDetails.DataBean> detailsList = new ArrayList<>();
    private int page = 1;
    private boolean isClear;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_details;
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listView.setOnRefreshListener(this);
        accountDetailsApapter = new AccountDetailsApapter(this);
        linBack.setOnClickListener(this);
        accountDetailsApapter.setData(detailsList);
        listView.setAdapter(accountDetailsApapter);
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
    protected void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new AccountDetailsPresenter(this);
        }
        presenter.start();
    }

    @Override
    public int page() {
        return page;
    }

    @Override
    public void callbackAccountDetailsSuccess(AccountDetails detials) {
        if (isClear) {
            detailsList.clear();
            isClear = false;
        }
        page++;
        this.detials = detials;
        detailsList.addAll(detials.getData());
        accountDetailsApapter.setData(detailsList);
        accountDetailsApapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }


    @Override
    public void onPullDownToRefresh(final PullToRefreshBase<ListView> refreshView) {
        isClear = true;
        page = 1;
        presenter.accountDetails();
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
        if (detials == null || detials.getTotalPage() >= page) {
            presenter.accountDetails();
        } else {
            showToast("没有更多数据");
        }

    }
}
