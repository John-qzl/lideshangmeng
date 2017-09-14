package com.lidegou.lideshangmeng.mobile.util.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.lidegou.lideshangmeng.mobile.R;

/**
 * Created by Administrator on 2017/1/12.
 */

public class MyDialog extends Dialog {
    Context context;

    public MyDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog);
    }
}
