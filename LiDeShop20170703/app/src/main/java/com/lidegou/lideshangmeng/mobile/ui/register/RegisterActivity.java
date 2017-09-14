package com.lidegou.lideshangmeng.mobile.ui.register;

import android.view.View;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.adapter.TabFragmentAdapter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.ui.register.fragment.FragmentFour;
import com.lidegou.lideshangmeng.mobile.ui.register.fragment.FragmentOne;
import com.lidegou.lideshangmeng.mobile.ui.register.fragment.FragmentThree;
import com.lidegou.lideshangmeng.mobile.ui.register.fragment.FragmentTwo;
import com.lidegou.lideshangmeng.mobile.util.view.TabViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Y on 2016/12/1.
 */
public class RegisterActivity extends BaseActivity implements FragmentOne.ProvinceCallBackCallBack, FragmentTwo.ProvinceIDCallBackCallBack, FragmentThree.CityIDCallBackCallBack, FragmentFour.AreaIDCallBackCallBack {

    private TabViewPager menuViewPager;
    public List<BaseFragment> fragmentList;
    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;
    private FragmentFour fragmentFour;
    private String province, city, area;
    private int provinceid, cityid, areaid;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    protected void init() {

        findViewById(R.id.lin_back).setOnClickListener(this);
        menuViewPager = (TabViewPager) findViewById(R.id.view_pager_register);
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
            fragmentOne = new FragmentOne(this);
            fragmentTwo = new FragmentTwo(this);
            fragmentThree = new FragmentThree(this);
            fragmentFour = new FragmentFour(this);
            fragmentList.add(fragmentOne);
            fragmentList.add(fragmentTwo);
            fragmentList.add(fragmentThree);
            fragmentList.add(fragmentFour);
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
        switch (view.getId()) {
            case R.id.lin_back:
                int now = menuViewPager.getCurrentItem();
                if (now == 0) {
                    finish();
                } else if (now == 1) {
                    menuViewPager.setCurrentItem(0);
                } else if (now == 2) {
                    menuViewPager.setCurrentItem(1);
                } else if (now == 3) {
                    menuViewPager.setCurrentItem(2);
                }
                break;
        }
    }


    @Override
    public void areaIDCallBack(int id, String name) {
        menuViewPager.setCurrentItem(0, false);
        areaid = id;
        area = name;
        fragmentOne.setAddress(provinceid, cityid, areaid, province, city, area);
    }


    @Override
    public void provinceCallBack(int data) {
        menuViewPager.setCurrentItem(1);
    }

    @Override
    public void cityIDCallBack(int id, String name) {
        menuViewPager.setCurrentItem(3);
        fragmentFour.setData(id);
        city = name;
        cityid = id;
    }

    @Override
    public void provinceIDCallBack(int id, String name) {
        menuViewPager.setCurrentItem(2);
        fragmentThree.setData(id);
        province = name;
        provinceid = id;
    }


}
