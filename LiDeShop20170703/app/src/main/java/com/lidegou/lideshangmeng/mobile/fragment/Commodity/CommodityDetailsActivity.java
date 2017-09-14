package com.lidegou.lideshangmeng.mobile.fragment.Commodity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.adapter.TabFragmentAdapter;
import com.lidegou.lideshangmeng.mobile.fragment.Commodity.fragment.CommodityDetailedfragment;
import com.lidegou.lideshangmeng.mobile.fragment.Commodity.fragment.CommodityDetailsFragment;
import com.lidegou.lideshangmeng.mobile.fragment.Commodity.fragment.CommodityEvaluationfragment;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.ui.login.LoginActivity;
import com.lidegou.lideshangmeng.mobile.util.view.TabViewPager;

import java.util.ArrayList;
import java.util.List;


public class CommodityDetailsActivity extends BaseActivity implements CommodityDetailsFragment.CommodityDetilsLister, CommodityDetailedfragment.OnBackClickListener, CommodityEvaluationfragment.OnBackClickListener {
    private TabViewPager menuViewPager;
    public List<BaseFragment> fragmentList;
    private CommodityDetailsFragment commodityDetailsFragment;
    private CommodityDetailedfragment commodityDetailedfragment;
    private CommodityEvaluationfragment commodityEvaluationfragment;
    private String goodid;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_commodity_details;
    }

    protected void init() {
        goodid = getIntent().getStringExtra("goodid");
        menuViewPager = (TabViewPager) findViewById(R.id.view_pager_details);
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
            commodityDetailsFragment = new CommodityDetailsFragment(this, goodid);//商品详情
            commodityDetailedfragment = new CommodityDetailedfragment(this);//商品参数介绍
            commodityEvaluationfragment = new CommodityEvaluationfragment(this, goodid);//宝贝评价
            fragmentList.add(commodityDetailsFragment);
            fragmentList.add(commodityDetailedfragment);
            fragmentList.add(commodityEvaluationfragment);
        }
        TabFragmentAdapter tabFragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragmentList);
        menuViewPager.setAdapter(tabFragmentAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void viewClick(View view) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            int item = menuViewPager.getCurrentItem();
            if (item == 0) {
                finish();
            } else {
                menuViewPager.setCurrentItem(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void callbackItemLister(String desc) {
        menuViewPager.setCurrentItem(1);
        commodityDetailedfragment.setDesc(desc);
    }

    @Override
    public void callbackOnClickLister() {
        finish();
    }

    @Override
    public void callbackShopsCar(String msg) {
        if (!msg.equals("") && msg != null) {
            if (msg.equals("去登录")) {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

    @Override
    public void callbackEvaluateLister() {
        menuViewPager.setCurrentItem(2);
    }

    @Override
    public void callback() {
        menuViewPager.setCurrentItem(0);
    }

    @Override
    public void callbackEvaluation() {
        menuViewPager.setCurrentItem(0);
    }

}
