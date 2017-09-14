package com.lidegou.lideshangmeng.mobile.ui.personal.dosingle;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.login.LoginActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.dosingle.fragment.AllSingleFragment;
import com.lidegou.lideshangmeng.mobile.ui.personal.dosingle.fragment.WaitPaySingleFragment;
import com.lidegou.lideshangmeng.mobile.ui.personal.dosingle.fragment.YesPaySingleFragment;
import com.lidegou.lideshangmeng.mobile.ui.personal.order.fragment.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 做单列表
 */
public class MineListSingleActivity extends BaseActivity {

    private ViewPager viewPager;
    private List<Fragment> mFragmentList;
    private FragmentAdapter mFragmentAdapter;
    /**
     * ViewPager的当前选中页
     */
    private int currentIndex = 0;

    private AllSingleFragment allSingleFragment;
    private YesPaySingleFragment yesPaySingleFragment;
    private WaitPaySingleFragment waitPaySingleFragment;

    private TextView id_all_tv, id_yet_tv, id_wait_tv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_list_single;
    }

    @Override
    protected void init() {
        findViewById(R.id.lin_back).setOnClickListener(this);
        viewPager = (ViewPager) findViewById(R.id.single_list_vp);
        findViewById(R.id.id_tab_allsingle).setOnClickListener(this);
        findViewById(R.id.id_tab_yetsingle).setOnClickListener(this);
        findViewById(R.id.id_tab_waitsingle).setOnClickListener(this);

        id_all_tv = (TextView) findViewById(R.id.id_all_tv);
        id_yet_tv = (TextView) findViewById(R.id.id_yet_tv);
        id_wait_tv = (TextView) findViewById(R.id.id_wait_tv);

        if (mFragmentList == null) {
            mFragmentList = new ArrayList<Fragment>();
            allSingleFragment = new AllSingleFragment();
            yesPaySingleFragment = new YesPaySingleFragment();
            waitPaySingleFragment = new WaitPaySingleFragment();
            mFragmentList.add(allSingleFragment);
            mFragmentList.add(yesPaySingleFragment);
            mFragmentList.add(waitPaySingleFragment);
        }

        mFragmentAdapter = new FragmentAdapter(
                getSupportFragmentManager(), mFragmentList);
        viewPager.setAdapter(mFragmentAdapter);
        viewPager.setCurrentItem(currentIndex);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }

            /**
             * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
             * offsetPixels:当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float offset,
                                       int offsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        id_all_tv.setTextColor(Color.parseColor("#FF8C1B"));
                        break;
                    case 1:
                        id_yet_tv.setTextColor(Color.parseColor("#FF8C1B"));
                        break;
                    case 2:
                        id_wait_tv.setTextColor(Color.parseColor("#FF8C1B"));
                        break;

                }
                currentIndex = position;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Config.User.STATUS) {
            init();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.id_tab_allsingle:
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.id_tab_yetsingle:
                viewPager.setCurrentItem(1, false);
                break;
            case R.id.id_tab_waitsingle:
                viewPager.setCurrentItem(2, false);
                break;
        }
    }

    /**
     * 重置颜色
     */
    private void resetTextView() {
        id_all_tv.setTextColor(Color.BLACK);
        id_yet_tv.setTextColor(Color.BLACK);
        id_wait_tv.setTextColor(Color.BLACK);
    }

}
