package com.lidegou.lideshangmeng.mobile.ui.personal.notice.fragment.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.adapter.MineOrderNoticeAdapter;
import com.lidegou.lideshangmeng.mobile.data.entity.MessageOrderntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.ui.personal.notice.order.MineOrderMessageDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MineOrderMessageFragment extends BaseFragment implements MineOrderMessageContract.View, PullToRefreshBase.OnRefreshListener2<ListView>, AdapterView.OnItemClickListener {
    private PullToRefreshListView listOrder;
    private List<MessageOrderntity.Data> messageList = new ArrayList<>();
    private MineOrderNoticeAdapter mineNoticeAdapter;
    private int page = 1;
    private MineOrderMessageContract.Presenter presenter;
    private MessageOrderntity entity;
    private boolean isClear = true;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_message;
    }

    @Override
    protected void init() {
        messageList = new ArrayList<>();
        listOrder = (PullToRefreshListView) getRootView().findViewById(R.id.list_order);
        listOrder.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listOrder.setOnRefreshListener(this);
        listOrder.setOnItemClickListener(this);
        mineNoticeAdapter = new MineOrderNoticeAdapter(getContext());
        mineNoticeAdapter.setDataList(messageList);
        listOrder.setAdapter(mineNoticeAdapter);

        presenter = new MineOrderMessagePresenter(this);
        presenter.orderMsg();
    }

    @Override
    protected void viewClick(View view) {

    }

    @Override
    protected void lazyLoad() {

    }


    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void OrderMsgSuccess(MessageOrderntity entity) {
        if (isClear) {
            messageList.clear();
            isClear = false;
        }
        if (entity != null) {
            this.entity = entity;
            page++;
            messageList.addAll(entity.getData());
            mineNoticeAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public String page() {
        return page + "";
    }

    @Override
    public void onPullDownToRefresh(final PullToRefreshBase<ListView> refreshView) {
        page = 1;
        isClear = true;
        presenter.orderMsg();
        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshView.onRefreshComplete();
            }
        }, 1000);
    }

    @Override
    public void onPullUpToRefresh(final PullToRefreshBase<ListView> refreshView) {
        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshView.onRefreshComplete();
            }
        }, 1000);
        if (entity.getTotalPage() >= page) {
            presenter.orderMsg();
        } else {
            showToast("没有更多数据");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MessageOrderntity.Data data = messageList.get(position - 1);
        data.setStatus("1");
        mineNoticeAdapter.notifyDataSetChanged();

        Intent intent = new Intent(getActivity(), MineOrderMessageDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
