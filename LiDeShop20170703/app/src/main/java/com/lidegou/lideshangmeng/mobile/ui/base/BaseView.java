package com.lidegou.lideshangmeng.mobile.ui.base;

import android.content.Context;

import com.lidegou.lideshangmeng.mobile.App;


/**
 * Created by Y on 2016/8/10.
 */
public interface BaseView {

    App getApp();

    Context getContext();

    void showError(String msg);
}