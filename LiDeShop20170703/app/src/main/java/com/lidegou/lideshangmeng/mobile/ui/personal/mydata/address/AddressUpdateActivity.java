package com.lidegou.lideshangmeng.mobile.ui.personal.mydata.address;

import android.view.View;
import android.widget.LinearLayout;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.adapter.TabFragmentAdapter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.ui.personal.mydata.address.fragment.FragmentUpdateAddress;
import com.lidegou.lideshangmeng.mobile.ui.register.fragment.FragmentFour;
import com.lidegou.lideshangmeng.mobile.ui.register.fragment.FragmentThree;
import com.lidegou.lideshangmeng.mobile.ui.register.fragment.FragmentTwo;
import com.lidegou.lideshangmeng.mobile.util.view.TabViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AddressUpdateActivity extends BaseActivity implements FragmentUpdateAddress.ProvinceCallBackCallBack, FragmentTwo.ProvinceIDCallBackCallBack, FragmentThree.CityIDCallBackCallBack, FragmentFour.AreaIDCallBackCallBack {

    @InjectView(R.id.lin_back)
    LinearLayout linBack;

    String address_id;
    private TabViewPager menuViewPager;
    public List<BaseFragment> fragmentList;
    private FragmentUpdateAddress fragmentUpdateAddress;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;
    private FragmentFour fragmentFour;
    private String province, city, area;
    private int provinceid, cityid, areaid;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_update;
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);
        linBack.setOnClickListener(this);
        address_id = getIntent().getStringExtra("address_id");
        menuViewPager = (TabViewPager) findViewById(R.id.view_pager_register);
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
            fragmentUpdateAddress = new FragmentUpdateAddress(this, address_id);
            fragmentTwo = new FragmentTwo(this);
            fragmentThree = new FragmentThree(this);
            fragmentFour = new FragmentFour(this);
            fragmentList.add(fragmentUpdateAddress);
            fragmentList.add(fragmentTwo);
            fragmentList.add(fragmentThree);
            fragmentList.add(fragmentFour);
        }
        TabFragmentAdapter tabFragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragmentList);
        menuViewPager.setAdapter(tabFragmentAdapter);
        menuViewPager.setOffscreenPageLimit(fragmentList.size());
    }


    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
        }
    }

    @Override
    public void provinceCallBack(int data) {
        menuViewPager.setCurrentItem(1);
    }

    @Override
    public void areaIDCallBack(int id, String name) {
        menuViewPager.setCurrentItem(0, false);
        areaid = id;
        area = name;
        fragmentUpdateAddress.setAddress(provinceid, cityid, areaid, province, city, area);
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



