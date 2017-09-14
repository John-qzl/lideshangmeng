package com.lidegou.lideshangmeng.mobile.ui.login;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IUserInfoDao;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.forgotpass.ForgotPasswordActivity;
import com.lidegou.lideshangmeng.mobile.ui.register.RegisterActivity;
import com.lidegou.lideshangmeng.mobile.ui.register.RegisterIdCordActivity;

/**
 * Created by Y on 2016/12/1.
 */
public class LoginActivity extends BaseActivity implements LoginContract.View, View.OnFocusChangeListener {
    public Intent intent;
    public Button btn_login;
    public LinearLayout ll_login_to_register;
    public TextView txt_forgot_password;

    private EditText ed_phone, ed_password;

    private LoginContract.Presenter presenter;
    private ImageView automatic_login, iv_loginuser01, iv_loginpass01;

    public static final int SUCCESS = 2001;
    public static final int ERROR = 2002;

    private boolean isAutomatic = true;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    protected void init() {
        findViewById(R.id.lin_back).setOnClickListener(this);
        intent = new Intent();
        btn_login = (Button) findViewById(R.id.btn_login);
        ll_login_to_register = (LinearLayout) findViewById(R.id.ll_login_to_register);
        txt_forgot_password = (TextView) findViewById(R.id.txt_forgot_password);

        ed_phone = (EditText) findViewById(R.id.ed_phone);
        ed_password = (EditText) findViewById(R.id.ed_password);
        automatic_login = (ImageView) findViewById(R.id.automatic_login);

        iv_loginuser01 = (ImageView) findViewById(R.id.iv_loginuser01);
        iv_loginpass01 = (ImageView) findViewById(R.id.iv_loginpass01);

        btn_login.setOnClickListener(this);
        ll_login_to_register.setOnClickListener(this);
        txt_forgot_password.setOnClickListener(this);
        automatic_login.setOnClickListener(this);

        ed_phone.setOnFocusChangeListener(this);
        ed_password.setOnFocusChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new LoginPresenter(this);
        }
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.btn_login:
                presenter.start();
                break;
            case R.id.ll_login_to_register:
                intent.setClass(this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.txt_forgot_password:
                intent.setClass(this, ForgotPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.automatic_login:
                if (isAutomatic) {
                    isAutomatic = false;
                    automatic_login.setImageResource(R.drawable.automaticlogin01);
                } else {
                    isAutomatic = true;
                    automatic_login.setImageResource(R.drawable.automaticlogin02);
                }
                break;
        }
    }

    @Override
    public String getVersion() {
        PackageManager pm = getPackageManager();
        final PackageInfo pi;
        try {
            pi = pm.getPackageInfo(getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            return pi.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String getRemember() {
        if (isAutomatic) {
            return "1";
        }
        return "0";
    }

    @Override
    public String getMark() {
        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String szImei = TelephonyMgr.getDeviceId();
        return szImei;
    }

    @Override
    public String getTelephone() {
        return ed_phone.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return ed_password.getText().toString().trim();
    }


    @Override
    public boolean isAutomatic() {
        return isAutomatic;
    }

    @Override
    public void callbackLoginSuccess(IUserInfoDao ll) {
        Log.i("whfyy","可以了2");

        ll.isRegisterIdCard(App.getApp().getUid(), new ResponseCallback() {
            @Override
            public void onSuccess(Object data) {
                if ((boolean) data) {
                    finish();
                } else {
                    Intent intent = new Intent(LoginActivity.this, RegisterIdCordActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                Log.i("whfyy","code"+code+"--"+msg);
            }
        });

    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResult(ERROR);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.ed_phone:
                if (hasFocus) {
                    iv_loginuser01.setImageResource(R.drawable.loginuser02);
                    iv_loginpass01.setImageResource(R.drawable.loginpass01);
                } else {
                    iv_loginuser01.setImageResource(R.drawable.loginuser01);
                }
                break;
            case R.id.ed_password:
                if (hasFocus) {
                    iv_loginpass01.setImageResource(R.drawable.loginpass02);
                    iv_loginuser01.setImageResource(R.drawable.loginuser01);
                } else {
                    iv_loginpass01.setImageResource(R.drawable.loginpass01);
                }
                break;
        }
    }
}
