package com.lidegou.lideshangmeng.mobile.fragment.SearchStoreShop;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.MallStoreStreetFragment;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;

/**
 * Created by Y on 2016/12/1.
 * 商铺
 */
public class SearchStoreShopActivity extends BaseActivity {
    private MallStoreStreetFragment streetFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopstore;
    }

    @Override
    protected void init() {
        String keyword = getIntent().getStringExtra("keyword");
        if (streetFragment == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            streetFragment = new MallStoreStreetFragment(keyword);
            ft.replace(R.id.content, streetFragment);
            ft.commit();
        }
        findViewById(R.id.back).setOnClickListener(this);
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        streetFragment.onActivityResult(requestCode, resultCode, data);
    }
}