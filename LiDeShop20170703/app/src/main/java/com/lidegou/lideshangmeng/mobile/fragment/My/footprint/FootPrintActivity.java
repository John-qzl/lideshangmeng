package com.lidegou.lideshangmeng.mobile.fragment.My.footprint;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Footprint;
import com.lidegou.lideshangmeng.mobile.event.OnRecyclerItemClickListener;
import com.lidegou.lideshangmeng.mobile.fragment.Commodity.CommodityDetailsActivity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * 我的足迹
 */
public class FootPrintActivity extends BaseActivity implements FootPrintContract.View, OnRecyclerItemClickListener {
    private ListView lv_footprint;
    private FootPrintAdapter footPrintAdapter;
    private FootPrintContract.Presenter presenter;
    private List<Footprint> footprintList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_foot_print;
    }

    @Override
    protected void init() {
        findViewById(R.id.lin_back).setOnClickListener(this);
        findViewById(R.id.clear_footprint).setOnClickListener(this);
        lv_footprint = (ListView) findViewById(R.id.lv_footprint);
        footPrintAdapter = new FootPrintAdapter(this, footprintList);
        footPrintAdapter.setOnRecyclerItemClickListener(this);
        lv_footprint.setAdapter(footPrintAdapter);

        presenter = new FootPrintPresenter(this);
        presenter.start();
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.clear_footprint:
                presenter.clearFootprint();
                break;
        }
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void callbackFootprintSuccess(List<Footprint> footprintList) {
        this.footprintList.addAll(footprintList);
        footPrintAdapter.notifyDataSetChanged();
    }

    @Override
    public void callbackClearFootprintSuccess(String data) {
        showToast(data);
        footprintList.clear();
        presenter.getFootprint();
    }

    @Override
    public void recyclerItemClick(View view, int position) {
        if (footprintList != null) {
            Intent intent = new Intent(this, CommodityDetailsActivity.class);
            intent.putExtra("goodid", footprintList.get(position).getGoods_id());
            startActivity(intent);
        }
    }
}
