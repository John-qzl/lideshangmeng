package com.lidegou.lideshangmeng.mobile.ui.personal.updatePwd;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.login.LoginActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 修改密码
 */
public class UpdatePwdActivity extends BaseActivity implements UpdatePwdContract.View {
    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.ed_old_pass)
    EditText edOldPass;
    @InjectView(R.id.ed_new_pass)
    EditText edNewPass;
    @InjectView(R.id.ed_confirm_pass)
    EditText edConfirmPass;
    @InjectView(R.id.btn_confirm)
    Button btnConfirm;
    private UpdatePwdContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_pwd;
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);
        linBack.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new UpdatePwdPresenter(this);
        }
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.btn_confirm:
                presenter.start();
                break;

        }
    }


    @Override
    public void showError(String msg) {
        showToast(msg);
    }


    @Override
    public String old_password() {
        return edOldPass.getText().toString();
    }

    @Override
    public String new_password1() {
        return edNewPass.getText().toString();
    }

    @Override
    public String new_password() {
        return edConfirmPass.getText().toString();
    }

    @Override
    public void callbackEditPasswordSuccess() {
        showToast("修改成功");
        startActivity(new Intent(UpdatePwdActivity.this, LoginActivity.class));
        finish();
    }


}
