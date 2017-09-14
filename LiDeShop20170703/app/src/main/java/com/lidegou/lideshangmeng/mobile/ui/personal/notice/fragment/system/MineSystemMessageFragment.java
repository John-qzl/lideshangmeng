package com.lidegou.lideshangmeng.mobile.ui.personal.notice.fragment.system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.adapter.MineSysNoticeAdapter;
import com.lidegou.lideshangmeng.mobile.data.entity.MessageSysEntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.ui.personal.notice.sys.MineSysMessageDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MineSystemMessageFragment extends BaseFragment implements MineSystemMessageContract.View, PullToRefreshBase.OnRefreshListener2<ListView>, AdapterView.OnItemClickListener {

    private PullToRefreshListView listSystem;
    private List<MessageSysEntity.Data> messageList = new ArrayList<>();
    private MineSysNoticeAdapter mineNoticeAdapter;
    private MineSystemMessageContract.Presenter presenter;
    private int page = 1;
    private boolean isClear = true;
    private MessageSysEntity entity;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_system_message;
    }

    @Override
    protected void init() {
        messageList = new ArrayList<>();
        listSystem = (PullToRefreshListView) getRootView().findViewById(R.id.list_system);
        listSystem.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listSystem.setOnRefreshListener(this);
        listSystem.setOnItemClickListener(this);
        mineNoticeAdapter = new MineSysNoticeAdapter(getContext());
        mineNoticeAdapter.setDataList(messageList);
        listSystem.setAdapter(mineNoticeAdapter);

        presenter = new MineSystemMessagePresenter(this);
        presenter.sysMsg();
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
    public String page() {
        return page + "";
    }

    @Override
    public void sysMsgSuccess(MessageSysEntity entity) {
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
    public void onPullDownToRefresh(final PullToRefreshBase<ListView> refreshView) {
        page = 1;
        isClear = true;
        presenter.sysMsg();
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
            presenter.sysMsg();
        } else {
            showToast("没有更多数据");
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MessageSysEntity.Data data = messageList.get(position - 1);
        data.setStatus("1");
        mineNoticeAdapter.notifyDataSetChanged();

        Intent intent = new Intent(getActivity(), MineSysMessageDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
