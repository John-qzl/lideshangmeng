package com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.tixian;

import android.content.Intent;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.MoneyManage;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.addCard.AddCardActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 提现
 */
public class WithdrawalActivity extends BaseActivity implements WithdrawalContract.View {

    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.ed_price)
    EditText edPrice;
    @InjectView(R.id.ed_vipnote)
    EditText edVipnote;
    @InjectView(R.id.sp_bankCard)
    Spinner spBankCard;
    @InjectView(R.id.submit_withdrawal)
    Button submitWithdrawal;
    @InjectView(R.id.rules)
    TextView rules;
    @InjectView(R.id.activity_add_card)
    LinearLayout activityAddCard;
    private WithdrawalContract.Presenter presenter;
    private WithdrawalAdapter adapter;
    private List<MoneyManage.Accountraply.BankBean> bankBeanArrayList = new ArrayList<>();
    private String bank_number;
    private String real_name;
    private double remainder;
    private String getSurplus_amount;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_withdrawal;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new WithdrawalPresenter(this);
        }
        presenter.start();
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);
        linBack.setOnClickListener(this);
        submitWithdrawal.setOnClickListener(this);
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.submit_withdrawal:
                String price = edPrice.getText().toString();
                if (price.equals("") || Integer.parseInt(price) == 0) {
                    showToast("请输入金额");
                    return;
                } else {
                    submitWithdrawal.setClickable(false);
                    presenter.submitAccounTraply();
                }
                break;
        }
    }

    @Override
    public String amount() {
        if (!edPrice.getText().toString().equals("")) {
            remainder = Double.parseDouble(edPrice.getText().toString());
            if (remainder <= 0) {
                return "";
            } else {
                return edPrice.getText().toString();
            }
        }
        return "";
    }

    @Override
    public String userNote() {
        return edVipnote.getText().toString();
    }

    @Override
    public String bankNumber() {
        return bank_number;
    }

    @Override
    public String realName() {
        return real_name;
    }

    @Override
    public void callbackAccountraplySuccess(MoneyManage.Accountraply accountraply) {
        if (accountraply.getCode() == 100) {
            rules.setMovementMethod(ScrollingMovementMethod.getInstance());//滚动
            rules.setText(Html.fromHtml(accountraply.getNotice()));
            bankBeanArrayList.addAll(accountraply.getBank());
            edPrice.setHint("本次最大换购额度 " + accountraply.getSurplus_amount());
            getSurplus_amount = accountraply.getSurplus_amount();
            if (bankBeanArrayList.size() != 0) {
                setSpinner();
            }
        } else if (accountraply.getCode() == 201) {
            showToast(accountraply.getMsg());
            finish();
        } else {
            showToast(accountraply.getMsg());
            startActivity(new Intent(WithdrawalActivity.this, AddCardActivity.class));
            finish();
        }
    }

    @Override
    public void callbackSubmitAccounTraplySuccess() {
        showToast("申请成功");
        finish();
    }

    /*
 * 设置下拉框
 */
    private void setSpinner() {
        adapter = new WithdrawalAdapter(this);
        adapter.setData(bankBeanArrayList);
        spBankCard.setAdapter(adapter);
        //下拉框监听
        spBankCard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            // 表示选项被改变的时候触发此方法，主要实现办法：动态改变地级适配器的绑定值
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                bank_number = bankBeanArrayList.get(position).getBank_card();
                real_name = bankBeanArrayList.get(position).getBank_region();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
        submitWithdrawal.setClickable(true);
    }
}
