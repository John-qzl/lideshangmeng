package com.lidegou.lideshangmeng.mobile.fragment.home.aera;

import android.view.View;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.adapter.TabFragmentAdapter;
import com.lidegou.lideshangmeng.mobile.data.entity.Place;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.ui.register.fragment.FragmentFour;
import com.lidegou.lideshangmeng.mobile.ui.register.fragment.FragmentThree;
import com.lidegou.lideshangmeng.mobile.ui.register.fragment.FragmentTwo;
import com.lidegou.lideshangmeng.mobile.util.view.TabViewPager;

import java.util.ArrayList;
import java.util.List;

public class AeraActivity extends BaseActivity implements FragmentTwo.ProvinceIDCallBackCallBack, FragmentThree.CityIDCallBackCallBack, FragmentFour.AreaIDCallBackCallBack, AeraContract.View {
    private TextView tvNowPlace;
    private TabViewPager menuViewPager;
    public List<BaseFragment> fragmentList;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;
    private FragmentFour fragmentFour;
    private String province, city, area;
    private int provinceid, cityid, areaid;

    private AeraContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_aera;
    }

    @Override
    protected void init() {
        tvNowPlace = (TextView) findViewById(R.id.tv_now_place);
        menuViewPager = (TabViewPager) findViewById(R.id.view_pager);
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
            fragmentTwo = new FragmentTwo(this);
            fragmentThree = new FragmentThree(this);
            fragmentFour = new FragmentFour(this);
            fragmentList.add(fragmentTwo);
            fragmentList.add(fragmentThree);
            fragmentList.add(fragmentFour);
        }
        TabFragmentAdapter tabFragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragmentList);
        menuViewPager.setAdapter(tabFragmentAdapter);

        findViewById(R.id.lin_back).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter = new AeraPresenter(this);
        presenter.start();
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
    public void provinceIDCallBack(int id, String name) {
        menuViewPager.setCurrentItem(1);
        fragmentThree.setData(id);
        province = name;
        provinceid = id;
    }

    @Override
    public void cityIDCallBack(int id, String name) {
        menuViewPager.setCurrentItem(2);
        fragmentFour.setData(id);
        city = name;
        cityid = id;
    }

    @Override
    public void areaIDCallBack(int id, String name) {
//        menuViewPager.setCurrentItem(0, false);
        areaid = id;
        area = name;
        presenter.choosePlace();
    }

    @Override
    public String province() {
        return provinceid + "";
    }

    @Override
    public String city() {
        return cityid + "";
    }

    @Override
    public String district() {
        return areaid + "";
    }

    @Override
    public void getNowPlaceSuccess(Place data) {
        tvNowPlace.setText(data.getName());
    }

    @Override
    public void callbackChoosePlaceSuccess(String msg) {
        showToast(msg);
        finish();
        App.getApp().setShopsStoreFirst();
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }
}
