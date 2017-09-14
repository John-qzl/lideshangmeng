package com.lidegou.lideshangmeng.mobile.ui.personal.notice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.notice.fragment.order.MineOrderMessageFragment;
import com.lidegou.lideshangmeng.mobile.ui.personal.notice.fragment.system.MineSystemMessageFragment;
import com.lidegou.lideshangmeng.mobile.ui.personal.order.fragment.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Y on 2016/12/3.
 * 通知消息
 */
public class MineNoticeActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private RelativeLayout re_system;
    private TextView txt_system_message;
    private ImageView iv_system_image;
    private ImageView iv_system_new;

    private RelativeLayout re_order;
    public TextView txt_order_message;
    private ImageView iv_order_image;
    private ImageView iv_order_new;

    private ViewPager vp_notice;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;

    private MineSystemMessageFragment mineSystemMessageFragment;
    private MineOrderMessageFragment mineOrderMessageFragment;

    /**
     * ViewPager的当前选中页
     */
    private int currentIndex = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_notice;
    }

    protected void init() {
        findViewById(R.id.lin_back).setOnClickListener(this);
        vp_notice = (ViewPager) findViewById(R.id.vp_notice);

        re_system = (RelativeLayout) findViewById(R.id.re_system);
        txt_system_message = (TextView) findViewById(R.id.txt_system_message);
        iv_system_image = (ImageView) findViewById(R.id.iv_system_image);
        iv_system_new = (ImageView) findViewById(R.id.iv_system_new);

        re_order = (RelativeLayout) findViewById(R.id.re_order);
        txt_order_message = (TextView) findViewById(R.id.txt_order_message);
        iv_order_image = (ImageView) findViewById(R.id.iv_order_image);
        iv_order_new = (ImageView) findViewById(R.id.iv_order_new);

        re_system.setOnClickListener(this);
        re_order.setOnClickListener(this);

        mineSystemMessageFragment = new MineSystemMessageFragment();
        mineOrderMessageFragment = new MineOrderMessageFragment();
        mFragmentList.add(mineSystemMessageFragment);
        mFragmentList.add(mineOrderMessageFragment);
        mFragmentAdapter = new FragmentAdapter(
                getSupportFragmentManager(), mFragmentList);
        vp_notice.setAdapter(mFragmentAdapter);
        vp_notice.setCurrentItem(currentIndex);
        vp_notice.setOnPageChangeListener(this);

        TextView tv = new TextView(this);
        tv.setText("");
        Intent intent = getIntent();
        if (null != intent) {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
                String content = bundle.getString(JPushInterface.EXTRA_ALERT);
                tv.setText("Title : " + title + "  " + "Content : " + content);
            }
        }
        addContentView(tv, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.re_system:
                txt_system_message.setTextColor(0xffF70606);
                iv_system_image.setImageResource(R.drawable.message_sys_select);
                txt_order_message.setTextColor(0xff000000);
                iv_order_image.setImageResource(R.drawable.message_order_normal);
                vp_notice.setCurrentItem(0, false);
                break;
            case R.id.re_order:
                txt_order_message.setTextColor(0xffF70606);
                iv_order_image.setImageResource(R.drawable.message_order_select);
                txt_system_message.setTextColor(0xff000000);
                iv_system_image.setImageResource(R.drawable.message_sys_normal);
                vp_notice.setCurrentItem(1, false);
                break;
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position == 0) {
            txt_system_message.setTextColor(0xffF70606);
            iv_system_image.setImageResource(R.drawable.message_sys_select);
            txt_order_message.setTextColor(0xff000000);
            iv_order_image.setImageResource(R.drawable.message_order_normal);
        } else {
            txt_order_message.setTextColor(0xffF70606);
            iv_order_image.setImageResource(R.drawable.message_order_select);
            txt_system_message.setTextColor(0xff000000);
            iv_system_image.setImageResource(R.drawable.message_sys_normal);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
