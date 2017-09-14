package com.lidegou.lideshangmeng.mobile.fragment.My.attention;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.ShopsStore;
import com.lidegou.lideshangmeng.mobile.event.OnRecyclerItemClickListener;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.details.ShopsStoreDetailsActivity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.login.LoginActivity;
import com.lidegou.lideshangmeng.mobile.ui.login.LoginContract;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的关注
 *
 * @author Y
 * @version 0.1
 * @date 2016年8月22日09:12:32
 */

public class MyAttentionActivity extends BaseActivity implements MyAttentionContract.View, LoginContract.CheckView, OnRecyclerItemClickListener, MyAttentionAdapter.OnAttentionListener, PullToRefreshBase.OnRefreshListener2<ListView> {
    public static final String TAG = MyAttentionActivity.class.getSimpleName();
    //Presenter
    private MyAttentionContract.Presenter presenter;

    private PullToRefreshListView lv_attention;
    private MyAttentionAdapter myAttentionAdapter;
    private int page = 1;
    private String shopId;
    private int totalPage;

    private List<ShopsStore.Attention.StoreListBean> storeListBeanList = new ArrayList<>();
    private boolean isClear = true;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_attention;
    }

    @Override
    protected void init() {
        findViewById(R.id.lin_back).setOnClickListener(this);
        lv_attention = (PullToRefreshListView) findViewById(R.id.lv_attention);
        lv_attention.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        myAttentionAdapter = new MyAttentionAdapter(this, storeListBeanList);
        myAttentionAdapter.setOnRecyclerItemClickListener(this);
        myAttentionAdapter.setOnAttentionListener(this);
        lv_attention.setAdapter(myAttentionAdapter);
        lv_attention.setOnRefreshListener(this);
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
            presenter = new MyAttentionPresenter(this);
        }
        page = 1;
        storeListBeanList.clear();
        presenter.myAttention();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (LOGIN_REQUEST == requestCode) {
            switch (resultCode) {
                case LoginActivity.SUCCESS:
                    presenter.myAttention();
                    break;
                default:
                    finish();
                    break;
            }
        }
    }


    @Override
    public void callbackAttentionList(ShopsStore.Attention attention) {
        page++;
        if (isClear) {
            storeListBeanList.clear();
            isClear = false;
        }
        storeListBeanList.addAll(attention.getStore_list());
        myAttentionAdapter.notifyDataSetChanged();
        this.totalPage = attention.getTotalPage();
    }


    @Override
    public void callbackdeleteSuccess() {
        showToast("删除成功");
        page = 1;
        storeListBeanList.clear();
        presenter.myAttention();
    }

    @Override
    public String getPage() {
        return page + "";
    }

    @Override
    public String getShopId() {
        return shopId;
    }

    @Override
    public void callbackCheckUsersSuccess(int code) {
        if (code == 100) {
            presenter.myAttention();
        } else {
            startActivityForResult(new Intent(getContext(), LoginActivity.class), LOGIN_REQUEST);
        }
    }


    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void recyclerItemClick(View view, int position) {

    }

    @Override
    public void attentionListDelete(View view, int position) {
        if (storeListBeanList.size() > position) {
            shopId = storeListBeanList.get(position).getShop_id();
            presenter.deleteAttention();
        }
    }

    @Override
    public void itemMyAttention(int position) {
        ShopsStore.Attention.StoreListBean storeListBean = storeListBeanList.get(position);
        ShopsStore shopsStore = new ShopsStore();
        shopsStore.setUser_id(storeListBean.getShop_id());
        shopsStore.setLatitude(storeListBean.getLatitude());
        shopsStore.setLongitude(storeListBean.getLongitude());
        shopsStore.setShop_name(storeListBean.getStore_name());
        shopsStore.setGaze_status("1");
        Intent intent = new Intent(getContext(), ShopsStoreDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("shopsStore", shopsStore);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Override
    public void onPullDownToRefresh(final PullToRefreshBase<ListView> refreshView) {
        page = 1;
        presenter.start();
        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshView.onRefreshComplete();
            }
        }, 500);
    }

    @Override
    public void onPullUpToRefresh(final PullToRefreshBase<ListView> refreshView) {
        if (totalPage >= page) {
            page++;
            if (presenter != null) {
                presenter.start();
            }
        } else {
            showToast("暂无更多");
        }
        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshView.onRefreshComplete();
            }
        }, 500);
    }
}