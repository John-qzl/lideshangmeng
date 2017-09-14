package com.lidegou.lideshangmeng.mobile.fragment.Class;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Classify;
import com.lidegou.lideshangmeng.mobile.event.OnRecyclerItemClickListener;
import com.lidegou.lideshangmeng.mobile.fragment.Search.SearchAllActivity;
import com.lidegou.lideshangmeng.mobile.fragment.SearchCommodity.SearchCommodityActivity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Y on 2016/12/1.
 * 分类
 */
public class MallClassifyFragment extends BaseFragment implements MallClassifyContract.View, OnRecyclerItemClickListener {

    private List<Classify.CatID> catIDList = new ArrayList<>();
    private ListView lvClassify;
    private MallClassifyAdaper mallClassifyAdaper;

    private MallClassifyContract.Presenter presenter;

    private RecyclerView rv_sonclass;
    private MallSubClassifyApater mallSubClassifyApater;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mall_classify;
    }

    @Override
    protected void init() {
        getRootView().findViewById(R.id.ed_sousuo).setOnClickListener(this);
        lvClassify = (ListView) getRootView().findViewById(R.id.lv_classify);
        rv_sonclass = (RecyclerView) getRootView().findViewById(R.id.rv_sonclass);
        rv_sonclass.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mallSubClassifyApater = new MallSubClassifyApater(getContext());
        mallSubClassifyApater.setOnRecyclerItemClickListener(this);
        if (presenter == null) {
            presenter = new MallClassifyPresenter(this);
        }

        presenter.start();
    }


    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.ed_sousuo:
                Intent intent = new Intent(getContext(), SearchAllActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void lazyLoad() {
    }


    @Override
    public void callbackClassifyListSuccess(final List<Classify> classifyList) {
        mallClassifyAdaper = new MallClassifyAdaper(getContext(), classifyList);
        mallSubClassifyApater.setDataList(classifyList.get(0).getCatIDList());
        catIDList = classifyList.get(0).getCatIDList();
        lvClassify.setAdapter(mallClassifyAdaper);
        lvClassify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                catIDList = classifyList.get(position).getCatIDList();
                mallSubClassifyApater.setDataList(catIDList);
                mallClassifyAdaper.changeSelected(position);
            }
        });
        rv_sonclass.setAdapter(mallSubClassifyApater);
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }


    @Override
    public void recyclerItemClick(View view, int position) {
        Classify.CatID catID = catIDList.get(position);
        Intent intent = new Intent(getContext(), SearchCommodityActivity.class);
        intent.putExtra("catID", catID.getId());
        startActivity(intent);
    }
}