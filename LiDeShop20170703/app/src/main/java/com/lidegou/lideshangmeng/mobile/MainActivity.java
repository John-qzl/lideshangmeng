package com.lidegou.lideshangmeng.mobile;

import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.lidegou.lideshangmeng.mobile.ui.MallHomeActivity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {
    private Handler handler;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, MallHomeActivity.class));
                finish();
            }
        }, 2000);
    }

    @Override
    protected void viewClick(View view) {

    }


}
