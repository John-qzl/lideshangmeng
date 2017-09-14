package com.lidegou.lideshangmeng.mobile.ui.personal.mydata.updateEmail;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 修改邮箱
 */
public class EmailActivity extends BaseActivity implements EmailContract.View {

    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.ed_email)
    EditText edEmail;
    @InjectView(R.id.btn_confirm)
    Button btnConfirm;
    private EmailContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_email;
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);
        linBack.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.btn_confirm:
                presenter.updateEmail();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new EmailPresenter(this);
        }
    }

    @Override
    public String email() {
        return edEmail.getText().toString();
    }

    @Override
    public void callbackUpdateEmailSuccess() {
        showToast("修改成功");
        finish();
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }
}
