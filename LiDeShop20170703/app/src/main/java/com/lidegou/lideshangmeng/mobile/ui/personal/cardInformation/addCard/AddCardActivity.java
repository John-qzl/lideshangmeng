package com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.addCard;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.BankCard;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 添加银行卡
 */
public class AddCardActivity extends BaseActivity implements AddCardContract.View {
    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.sp_bank)
    Spinner spBank;
    @InjectView(R.id.ed_cardNumber)
    EditText edCardNumber;
    @InjectView(R.id.tv_Name)
    TextView tvName;
    @InjectView(R.id.ed_Branch)
    EditText edBranch;
    @InjectView(R.id.add_submit_card)
    Button addSubmitCard;
    @InjectView(R.id.activity_add_card)
    LinearLayout activityAddCard;
    private AddCardContract.Presenter presenter;
    private String bankName;
    ArrayAdapter<BankCard.AddCardInfo.BankListBean> beanArrayAdapter = null;  //银行适配器
    private List<BankCard.AddCardInfo.BankListBean> bankListBeanList = new ArrayList<>();

    private int nowPosition = -1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_card;
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);
        linBack.setOnClickListener(this);
        addSubmitCard.setOnClickListener(this);
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.add_submit_card:
                String number = edCardNumber.getText().toString();
                String info = edBranch.getText().toString();
                if (number.equals("")) {
                    showToast("请输入银行卡号");
                    return;
                }
                if (info.equals("")) {
                    showToast("请输入开户行信息");
                    return;
                }
                if (nowPosition == -1) {
                    showToast("请选择银行卡类型");
                    return;
                }
                addSubmitCard.setClickable(false);
                presenter.addcard();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new AddCardPresenter(this);
        }
        presenter.start();
    }

    @Override
    public String bankCode() {
        BankCard.AddCardInfo.BankListBean bankListBean = bankListBeanList.get(nowPosition);
        return bankListBean.getBank_code() + "_" + bankListBean.getBank_name();
    }

    @Override
    public String bankCard() {
        return edCardNumber.getText().toString();
    }

    @Override
    public String bankUserName() {
        return tvName.getText().toString();
    }

    @Override
    public String bankRegion() {
        return edBranch.getText().toString();
    }

    @Override
    public void callbackAddCardSuccess() {
        finish();
    }

    @Override
    public void callbackAddcardinfoSuccess(BankCard.AddCardInfo addCardInfo) {
        this.bankListBeanList = addCardInfo.getBank_list();
        tvName.setText(addCardInfo.getInfo().getXm());
        setSpinner();
        if (bankListBeanList.size() > 0) {
            nowPosition = 0;
        }

    }

    /*
  * 设置下拉框
  */
    private void setSpinner() {
        //绑定适配器和值
        beanArrayAdapter = new ArrayAdapter<>(AddCardActivity.this,
                android.R.layout.simple_spinner_item, bankListBeanList);
        beanArrayAdapter.setDropDownViewResource(R.layout.myspinner_dropdown);
        spBank.setAdapter(beanArrayAdapter);
        //下拉框监听
        spBank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            // 表示选项被改变的时候触发此方法，主要实现办法：动态改变地级适配器的绑定值
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                nowPosition = position;
                bankName = bankListBeanList.get(position).getBank_name();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
        addSubmitCard.setClickable(true);
    }
}
