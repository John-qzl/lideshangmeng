package com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsIn;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MerchantsInTwoActivity extends BaseActivity implements MerchantsInContract.ViewTwo {


    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.radioButton_man)
    RadioButton radioButtonMan;
    @InjectView(R.id.radioButton_gril)
    RadioButton radioButtonGril;
    @InjectView(R.id.ed_phone)
    EditText edPhone;
    @InjectView(R.id.ed_name)
    EditText edName;
    @InjectView(R.id.btn_next)
    Button btnNext;
    private MerchantsInContract.Presenter presenter;
    String gender = "男";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_merchants_in_two;
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);
        linBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        //根据ID找到RadioGroup实例
        RadioGroup group = (RadioGroup) findViewById(R.id.sex);
        //绑定一个匿名监听器
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override


            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                if (gender.equals("女")) {
                    gender = "男";
                } else {
                    gender = "女";
                }
            }
        });

        presenter = new MerchantsInPresenter(this);
        presenter.MerchantsInTwo();
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.btn_next:
                presenter.MerchantsInTwodone(gender, edPhone.getText().toString(), edName.getText().toString());
                break;
        }
    }

    @Override
    public void callbackMerchantsInTwoSuccess(MerchantsIn.MerchantsInContact merchantsInContact) {
        edPhone.setText(merchantsInContact.getContactPhone() + "");
        edName.setText(merchantsInContact.getContactName() + "");
    }

    @Override
    public void callbackMerchantsInTwodoneSuccess(String msg) {
        showToast(msg);
        startActivity(new Intent(this, MerchantsInThreeActivity.class));
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

}
