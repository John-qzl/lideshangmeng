package com.lidegou.lideshangmeng.mobile.ui.forgotpass;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.util.EncryptUtils;

/**
 * Created by Y on 2016/12/3.
 * 忘记密码
 */
public class ForgotPasswordActivity extends BaseActivity implements ForgotPasswordContract.View, View.OnFocusChangeListener {

    private EditText forget_phone;
    private EditText ed_smsCode;
    private EditText forget_pwd;
    private Button btn_next;
    private Button btn_send_code;

    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private RelativeLayout relativeLayout1;
    //倒计时
    private TimeCount timeCount;

    private int show = 1;

    private int uid;

    private ForgotPasswordContract.Presenter presenter;

    private ImageView iv_loginuser01, img_pass, iv_loginpass01;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forgot_password;
    }

    protected void init() {
        findViewById(R.id.lin_back).setOnClickListener(this);
        forget_phone = (EditText) findViewById(R.id.forget_phone);
        ed_smsCode = (EditText) findViewById(R.id.ed_smsCode);
        forget_pwd = (EditText) findViewById(R.id.forget_pwd);
        btn_next = (Button) findViewById(R.id.btn_next);
        linearLayout1 = (LinearLayout) findViewById(R.id.LinearLayout1);
        linearLayout2 = (LinearLayout) findViewById(R.id.LinearLayout2);
        relativeLayout1 = (RelativeLayout) findViewById(R.id.RelativeLayout1);
        btn_send_code = (Button) findViewById(R.id.btn_send_code);

        iv_loginuser01 = (ImageView) findViewById(R.id.iv_loginuser01);
        img_pass = (ImageView) findViewById(R.id.img_pass);
        iv_loginpass01 = (ImageView) findViewById(R.id.iv_loginpass01);

        btn_next.setOnClickListener(this);
        btn_send_code.setOnClickListener(this);

        forget_phone.setOnFocusChangeListener(this);
        ed_smsCode.setOnFocusChangeListener(this);
        forget_pwd.setOnFocusChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new ForgotPasswordPresenter(this);
        }
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.btn_send_code:
                presenter.smsCode();
                break;
            case R.id.btn_next:
                if (show == 1) {
                    if (forget_phone.getText() == null || forget_phone.getText().length() <= 0) {
                        showToast("手机号不能为空");
                    } else {
                        show = 2;
                        linearLayout1.setVisibility(View.GONE);
                        linearLayout2.setVisibility(View.GONE);
                        relativeLayout1.setVisibility(View.VISIBLE);
                    }
                } else if (show == 2) {
                    if (ed_smsCode.getText().toString().length() > 0) {

                        presenter.gainUid();
                    }
                } else {
                    presenter.updatepwd();
                }
                break;
        }
    }


    @Override
    public String getData() {
        return EncryptUtils.AES_Encrypt(forget_phone.getText().toString().trim());
    }

    @Override
    public String getTelephone() {
        return forget_phone.getText().toString().trim();
    }

    @Override
    public String getSmsCode() {
        return ed_smsCode.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return forget_pwd.getText().toString();
    }

    @Override
    public String getUid() {
        return uid + "";
    }

    @Override
    public void callbackSmsCodeSuccess(int code) {
        if (timeCount == null) {
            timeCount = new TimeCount(60000, 1000);
        }
        timeCount.start();
        btn_send_code.setClickable(false);
        showToast("验证码发送成功");
    }

    @Override
    public void callbackGetUidSuccess(int uid) {
        this.uid = uid;
        show = 3;
        linearLayout1.setVisibility(View.GONE);
        linearLayout2.setVisibility(View.VISIBLE);
        relativeLayout1.setVisibility(View.GONE);
        btn_next.setText("确认修改");
    }

    @Override
    public void callbackUpdatePwdSuccess(String data) {
        showToast(data);
        finish();
    }

    @Override
    public void callbackGetUidError() {
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.forget_phone:
                if (hasFocus) {
                    iv_loginuser01.setImageResource(R.drawable.loginuser02);
                } else {
                    iv_loginuser01.setImageResource(R.drawable.loginuser01);
                }
                break;
            case R.id.ed_smsCode:
                if (hasFocus) {
                    img_pass.setImageResource(R.drawable.registerverificationcode02);
                } else {
                    img_pass.setImageResource(R.drawable.registerverificationcode01);
                }
                break;
            case R.id.forget_pwd:
                if (hasFocus) {
                    iv_loginpass01.setImageResource(R.drawable.loginpass02);
                } else {
                    iv_loginpass01.setImageResource(R.drawable.loginpass01);
                }
                break;
        }
    }

    /**
     * 倒计时工具类
     */
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btn_send_code.setText((millisUntilFinished / 1000) + "秒后重新获取");
        }

        @Override
        public void onFinish() {
            btn_send_code.setClickable(true);
            btn_send_code.setText("验证码");
        }
    }
}
