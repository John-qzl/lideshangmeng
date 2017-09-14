package com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin;

import android.content.Intent;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsIn;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 商家入驻需知
 */
public class MerchantsInOneActivity extends BaseActivity implements MerchantsInContract.ViewOne {


    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.tv_protocol)
    TextView tvProtocol;
    @InjectView(R.id.btn_next)
    Button btnNext;
    @InjectView(R.id.tv_title)
    TextView tvTitle;

    private MerchantsInContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_merchants_in_one;
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);
        linBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        tvProtocol.setMovementMethod(ScrollingMovementMethod.getInstance());//滚动
        presenter = new MerchantsInPresenter(this);
        presenter.MerchantsInOne();
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.btn_next:
                btnNext.setClickable(false);
                presenter.MerchantsInOnedone();
                break;
        }
    }


    @Override
    public void showError(String msg) {
        showToast(msg);
        btnNext.setClickable(true);
    }


    @Override
    public void callbackMerchantsInOneSuccess(MerchantsIn.MerchantsInProtocol merchantsInProtocol) {
        tvProtocol.setText(Html.fromHtml(merchantsInProtocol.getArticle_centent() + ""));
        tvTitle.setText(merchantsInProtocol.getProcess_title() + "");
    }

    @Override
    public void callbackMerchantsInOnedoneSuccess(String msg) {
        showToast(msg);
        startActivity(new Intent(MerchantsInOneActivity.this, MerchantsInTwoActivity.class));
        btnNext.setClickable(true);
    }
}
