package com.lidegou.lideshangmeng.mobile.fragment.My.collection;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Commodity;
import com.lidegou.lideshangmeng.mobile.fragment.Commodity.CommodityDetailsActivity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.login.LoginActivity;
import com.lidegou.lideshangmeng.mobile.ui.login.LoginContract;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的收藏
 *
 * @author Y
 * @version 0.1
 * @date 2016年8月22日09:12:32
 */

public class MyCollectionActivity extends BaseActivity implements MyCollectionContract.View, LoginContract.CheckView, PullToRefreshBase.OnRefreshListener2<ListView>, MyCollectionAdapter.OnCollectionClickListener {
    public static final String TAG = MyCollectionActivity.class.getSimpleName();
    //Presenter
    private MyCollectionContract.Presenter presenter;
    private LoginContract.Presenter loginpresenter;
    private PullToRefreshListView rvCollection;
    private MyCollectionAdapter myCollectionAdapter;
    private List<Commodity.Data.CollectionCommodity.DataBean> dataBeanList = new ArrayList<>();
    private boolean isClear = true;

    private int page = 1;
    private String rec_id;
    private int totalPage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_collection;
    }

    @Override
    protected void init() {
        findViewById(R.id.lin_back).setOnClickListener(this);
        rvCollection = (PullToRefreshListView) findViewById(R.id.rv_collection);
        rvCollection.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        rvCollection.setOnRefreshListener(this);
        myCollectionAdapter = new MyCollectionAdapter(this);
        myCollectionAdapter.setOnCollectionClickListener(this);
        rvCollection.setAdapter(myCollectionAdapter);
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
            presenter = new MyCollectionPresenter(this);
        }
        page = 1;
        dataBeanList.clear();
        presenter.myCollection();
    }


    @Override
    public void callbackCommodityList(Commodity.Data.CollectionCommodity collectionCommodity) {
        if (isClear) {
            dataBeanList.clear();
            isClear = false;
        }
        dataBeanList.addAll(collectionCommodity.getData());
        myCollectionAdapter.setData(dataBeanList);
        myCollectionAdapter.notifyDataSetChanged();
        this.totalPage = collectionCommodity.getTotalPage();
    }


    @Override
    public void callbackdeleteSuccess(String msg) {
        showToast(msg);
        isClear = true;
        page = 1;
        presenter.myCollection();
    }

    @Override
    public String getPage() {
        return page + "";
    }

    @Override
    public String getRec_id() {
        return rec_id;
    }


    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void deleteCollectionCommodity(View view, int position) {
        Commodity.Data.CollectionCommodity.DataBean dataBean = dataBeanList.get(position);
        this.rec_id = dataBean.getRec_id();
        presenter.deleteCollection();
    }

    @Override
    public void itemCommodityDetails(int position) {
        Commodity.Data.CollectionCommodity.DataBean dataBean = dataBeanList.get(position);
        Intent intent = new Intent(this, CommodityDetailsActivity.class);
        intent.putExtra("goodid", dataBean.getGoods_id());
        startActivity(intent);
    }

    @Override
    public void callbackCheckUsersSuccess(int code) {
        if (code == 100) {
            presenter.myCollection();
        } else {
            startActivityForResult(new Intent(getContext(), LoginActivity.class), LOGIN_REQUEST);
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

    }

    @Override
    public void onPullUpToRefresh(final PullToRefreshBase<ListView> refreshView) {
        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshView.onRefreshComplete();
            }
        }, 500);
        if (totalPage >= page) {
            page++;
            if (presenter != null) {
                presenter.myCollection();
            }
        } else {
            showToast("暂无更多");
        }
    }
}
