package com.lidegou.lideshangmeng.mobile.ui.personal.order;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.order.fragment.OrderListFragment;

/**
 * Created by Y on 2017/2/10.
 */

public class OrderListActivity extends BaseActivity {
    private TextView title;
    private OrderListFragment mChatFg;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_list;
    }

    protected void init() {
        int state = getIntent().getIntExtra("state", 0);
        title = (TextView) findViewById(R.id.title);
        findViewById(R.id.iv_back).setOnClickListener(this);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        mChatFg = new OrderListFragment(state + "");
        ft.replace(R.id.content, mChatFg);
        ft.commit();
        if (state == 0) {
            title.setText("全部订单");
        } else if (state == 1) {
            title.setText("待付款订单");
        } else if (state == 2) {
            title.setText("待收货订单");
        }
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mChatFg.onActivityResult(requestCode, resultCode, data);
    }
}
