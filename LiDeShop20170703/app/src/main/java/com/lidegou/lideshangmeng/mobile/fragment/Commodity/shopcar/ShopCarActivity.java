package com.lidegou.lideshangmeng.mobile.fragment.Commodity.shopcar;


import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.fragment.ShopCar.MallShopCarFragment;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;


public class ShopCarActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_car;
    }

    @Override
    protected void init() {
        MallShopCarFragment fragment = new MallShopCarFragment();
        fragment.setType(MallShopCarFragment.TYPE_OTHER);
        fragment.showBack();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content, fragment);
        ft.commit();
    }

    @Override
    protected void viewClick(View view) {

    }

}
