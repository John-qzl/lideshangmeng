package com.lidegou.lideshangmeng.mobile.ui.personal.dosingle.fragment;

import android.view.View;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Single;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.ui.personal.dosingle.MineSingleContract;
import com.lidegou.lideshangmeng.mobile.ui.personal.dosingle.MineSinglePresenter;
import com.lidegou.lideshangmeng.mobile.ui.personal.dosingle.adapter.MineDoSingleApater;

import java.util.ArrayList;
import java.util.List;


public class WaitPaySingleFragment extends BaseFragment implements MineSingleContract.SingleList, PullToRefreshBase.OnRefreshListener2<ListView> {

    private List<Single.Data> singleList = new ArrayList<>();
    private MineDoSingleApater mineDoSingleApater;
    private MineSingleContract.Presenter presenter;
    private PullToRefreshListView lv_waitsingle;
    private int page = 1;
    private boolean isClear = false;
    private Single single;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_waitpaysingle;
    }

    @Override
    protected void init() {
        lv_waitsingle = (PullToRefreshListView) getRootView().findViewById(R.id.lv_waitsingle);
        lv_waitsingle.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        lv_waitsingle.setOnRefreshListener(this);
        mineDoSingleApater = new MineDoSingleApater(getContext());
        mineDoSingleApater.setDataList(singleList);
        lv_waitsingle.setAdapter(mineDoSingleApater);
        if (presenter == null) {
            presenter = new MineSinglePresenter(this);
        }
        presenter.getSingleList();
    }


    @Override
    protected void viewClick(View view) {

    }

    @Override
    protected void lazyLoad() {
        if (App.getApp().isPaySingleWaitSuccess()) {
            if (presenter == null) {
                presenter = new MineSinglePresenter(this);
            }
            page = 1;
            isClear = true;
            presenter.getSingleList();
            App.getApp().setPaySingleWaitSuccess(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (App.getApp().isPaySingleWaitSuccess()) {
            if (presenter == null) {
                presenter = new MineSinglePresenter(this);
            }
            page = 1;
            isClear = true;
            presenter.getSingleList();
            App.getApp().setPaySingleWaitSuccess(false);
        }
    }

    @Override
    public String page() {
        return page + "";
    }

    @Override
    public String status() {
        return "2";
    }

    @Override
    public void callbackSingleListSuccess(Single single) {
        this.single = single;
        page++;
        if (isClear) {
            isClear = false;
            singleList.clear();
        }
        singleList.addAll(single.getData());
        mineDoSingleApater.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void onPullDownToRefresh(final PullToRefreshBase<ListView> refreshView) {
        isClear = true;
        page = 1;
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
        if (single == null && single.getTotalPage() >= page) {
            presenter.getSingleList();
        } else {
            showToast("没有更多数据");
        }
    }
}
