package com.lidegou.lideshangmeng.mobile.fragment.My.customerService;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.ServiceBean;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.lidegou.lideshangmeng.mobile.R.id.tv_msg_title;

/**
 * 客户服务
 */
public class CustomerServiceActivity extends BaseActivity implements CustomerServiceContract.View, PullToRefreshBase.OnRefreshListener2<ListView> {
    @InjectView(R.id.lv_service)
    PullToRefreshListView lvService;
    @InjectView(R.id.btn_confirm)
    Button btnConfirm;
    @InjectView(tv_msg_title)
    EditText tvMsgTitle;
    @InjectView(R.id.lin_back)
    LinearLayout linBack;

    private ListView listView;
    private CustomerServiceAdapter adapter;
    private CustomerServiceContract.Presenter presenter;
    private List<ServiceBean.Data> classifyList = new ArrayList<>();
    private ServiceBean serviceBean;
    private int page = 1;
    private boolean isBottom = true;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_service;
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);
        linBack.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        listView = lvService.getRefreshableView();
        lvService.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        lvService.setOnRefreshListener(this);
        adapter = new CustomerServiceAdapter(this);
        adapter.setData(classifyList);
        lvService.setAdapter(adapter);
        if (presenter == null) {
            presenter = new CustomerServicePresenter(this);
        }
        presenter.messagelist();
    }


    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.btn_confirm:
                if (!tvMsgTitle.getText().toString().equals("")) {
                    presenter.addmessage(tvMsgTitle.getText().toString());
                } else {
                    showToast("请输入发送内容");
                }
                break;
        }
    }


    @Override
    public String page() {
        return page + "";
    }

    @Override
    public void callbackAddMessageSuccess(String data) {
        page = 1;
        showToast(data);
        isBottom = true;
        classifyList.clear();
        presenter.messagelist();
        tvMsgTitle.setText("");
    }

    @Override
    public void callbackMessageListSuccess(ServiceBean serviceBean) {
        page++;
        this.serviceBean = serviceBean;
        List<ServiceBean.Data> AllClassifyList = new ArrayList<>();
        List<ServiceBean.Data> newClassifyList = serviceBean.getData();
        if (newClassifyList != null) {
            for (ServiceBean.Data data : newClassifyList) {
                AllClassifyList.add(data);
            }
        }
        for (ServiceBean.Data data : classifyList) {
            AllClassifyList.add(data);
        }
        classifyList = AllClassifyList;
        adapter.setData(AllClassifyList);

        adapter.setHeadImage(serviceBean.getInfo().getUser_picture());
        adapter.notifyDataSetChanged();
        if (listView != null && isBottom) {
            isBottom = false;
            listView.setSelection(lvService.getBottom());
        }
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void onPullDownToRefresh(final PullToRefreshBase<ListView> refreshView) {
        if (serviceBean != null && serviceBean.getTotalPage() < page) {
            refreshView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    refreshView.onRefreshComplete();
                }
            }, 500);
            showToast("没有更多数据");
            return;
        }
        presenter.messagelist();
        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshView.onRefreshComplete();
            }
        }, 2000);
    }

    @Override
    public void onPullUpToRefresh(final PullToRefreshBase<ListView> refreshView) {
    }
}
