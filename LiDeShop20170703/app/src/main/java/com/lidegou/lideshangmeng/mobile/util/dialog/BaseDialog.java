package com.lidegou.lideshangmeng.mobile.util.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.R;


/**
 * Created by Y on 2016/8/16.
 */

public abstract class BaseDialog extends Dialog implements View.OnClickListener {
    public static final String TAG = BaseDialog.class.getSimpleName();

    private App app;
    private DisplayMetrics metrics;

    private Activity activity;

    public BaseDialog(Context context) {
        super(context, R.style.custom_dialog);
        this.activity = (Activity) context;

        app = (App) ((Activity) context).getApplication();
        metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);

        setContentView(getLayoutId());
        init();
    }

    protected abstract int getLayoutId();

    protected abstract void init();

    protected abstract void viewClick(View view);

    @Override
    public void onClick(View view) {
        viewClick(view);
    }

    public App getApp() {
        return app;
    }

    public DisplayMetrics getMetrics() {
        return metrics;
    }

    public Activity getActivity() {
        return activity;
    }

    public void showToast(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }
}
