package com.lidegou.lideshangmeng.mobile.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;

import java.util.List;

/**
 * Created by Garmin-Yi on 2016/1/30.
 */
public class TabFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragmentList;

    public TabFragmentAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }



    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
