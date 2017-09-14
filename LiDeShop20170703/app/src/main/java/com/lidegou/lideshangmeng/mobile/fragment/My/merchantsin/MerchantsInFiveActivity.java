package com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsInFiveEntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MerchantsInFiveActivity extends BaseActivity implements MerchantsInContract.ViewFive {


    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.tv_state)
    TextView tvState;
    @InjectView(R.id.tv_success_phone)
    TextView tvSuccessPhone;
    @InjectView(R.id.tv_success_name)
    TextView tvSuccessName;
    @InjectView(R.id.tv_updata)
    TextView tvUpdata;
    @InjectView(R.id.lin_success_resson)
    LinearLayout linSuccessReason;

    @InjectView(R.id.tv_success_reason)
    TextView tv_success_reason;//未审核通过的原因   Textview


    private MerchantsInContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_merchants_in_six;
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);
        linBack.setOnClickListener(this);
        presenter = new MerchantsInPresenter(this);
        presenter.MerchantsInFive();
        App.getApp().closeMerchantsInOther();
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.tv_updata:
                startActivity(new Intent(this, MerchantsInTwoActivity.class));
                finish();
                break;
        }
    }


    @Override
    public void callbackMerchantsInFiveSuccess(MerchantsInFiveEntity entity) {
        tvState.setText(entity.getText());
        tvSuccessName.setText(entity.getData().getRz_shopName());
        tvSuccessPhone.setText(entity.getData().getHopeLoginName());
        if (entity.getText().contains("未")) {
            linSuccessReason.setVisibility(View.VISIBLE);
            tv_success_reason.setText(entity.getData().getMerchants_message());
        } else {
            linSuccessReason.setVisibility(View.GONE);
        }
        if (entity.getData().getMerchants_audit().equals("2")) {
            tvUpdata.setVisibility(View.VISIBLE);
            tvUpdata.setOnClickListener(this);
        }
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }
}
