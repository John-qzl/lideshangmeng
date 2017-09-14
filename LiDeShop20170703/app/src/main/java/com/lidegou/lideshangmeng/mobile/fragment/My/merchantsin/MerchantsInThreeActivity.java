package com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.adapter.TabFragmentAdapter;
import com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin.fragment.MerchantsInThreeFragment;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.ui.register.fragment.FragmentFour;
import com.lidegou.lideshangmeng.mobile.ui.register.fragment.FragmentThree;
import com.lidegou.lideshangmeng.mobile.ui.register.fragment.FragmentTwo;
import com.lidegou.lideshangmeng.mobile.util.view.TabViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MerchantsInThreeActivity extends BaseActivity implements MerchantsInThreeFragment.ProvinceCallBackCallBack, FragmentTwo.ProvinceIDCallBackCallBack, FragmentThree.CityIDCallBackCallBack, FragmentFour.AreaIDCallBackCallBack {


    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.tab_view_pager)
    TabViewPager tabViewPager;
    public List<BaseFragment> fragmentList;
    private MerchantsInThreeFragment merchantsInThreeFragment;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;
    private FragmentFour fragmentFour;
    private String province, city, area;
    private int provinceid, cityid, areaid;
    private MerchantsInContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_merchants_in_three;
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);
        linBack.setOnClickListener(this);
        tabViewPager = (TabViewPager) findViewById(R.id.tab_view_pager);
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
            merchantsInThreeFragment = new MerchantsInThreeFragment(this);
            fragmentTwo = new FragmentTwo(this);
            fragmentThree = new FragmentThree(this);
            fragmentFour = new FragmentFour(this);
            fragmentList.add(merchantsInThreeFragment);
            fragmentList.add(fragmentTwo);
            fragmentList.add(fragmentThree);
            fragmentList.add(fragmentFour);
        }
        TabFragmentAdapter tabFragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragmentList);
        tabViewPager.setAdapter(tabFragmentAdapter);
        tabViewPager.setOffscreenPageLimit(tabFragmentAdapter.getCount());
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                int now = tabViewPager.getCurrentItem();
                if (now == 0) {
                    finish();
                } else if (now == 1) {
                    tabViewPager.setCurrentItem(0);
                } else if (now == 2) {
                    tabViewPager.setCurrentItem(1);
                } else if (now == 3) {
                    tabViewPager.setCurrentItem(2);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        merchantsInThreeFragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void areaIDCallBack(int id, String name) {
        tabViewPager.setCurrentItem(0, false);
        areaid = id;
        area = name;
        merchantsInThreeFragment.setAddress(provinceid, cityid, areaid, province, city, area);
    }


    @Override
    public void provinceCallBack(int data) {
        tabViewPager.setCurrentItem(1);
    }

    @Override
    public void cityIDCallBack(int id, String name) {
        tabViewPager.setCurrentItem(3);
        fragmentFour.setData(id);
        city = name;
        cityid = id;
    }

    @Override
    public void provinceIDCallBack(int id, String name) {
        tabViewPager.setCurrentItem(2);
        fragmentThree.setData(id);
        province = name;
        provinceid = id;
    }
}
