package com.lidegou.lideshangmeng.mobile.util;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.Button;

/**
 * Created by Administrator on 2016/11/3.
 */

public class TimeCount extends CountDownTimer {

    private Context context;
    private Button btnSmsCode;

    public TimeCount(long millisInFuture, long countDownInterval, Button btnSmsCode) {
        super(millisInFuture, countDownInterval);
        this.btnSmsCode = btnSmsCode;
    }

    public TimeCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        btnSmsCode.setText((millisUntilFinished / 1000) + "秒后重新获取");
    }

    @Override
    public void onFinish() {
        btnSmsCode.setClickable(true);
        btnSmsCode.setText("验证码");
    }
}
