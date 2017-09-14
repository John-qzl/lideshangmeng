package com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.MoneyManage;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.CardInformationActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.addCard.AddCardActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.chongzhi.RechargeActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.tixian.WithdrawalActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.accountdetails.AccountDetailsActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.applyRecord.ApplyRecordActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.integral.IntegralActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.recommend.RecommendActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.lidegou.lideshangmeng.mobile.R.id.rl_account_details;

/**
 * 资金管理
 */
public class MoneyManageActivity extends BaseActivity implements MoneyManageContract.View {

    @InjectView(R.id.tv_money)
    TextView tvMoney;
    @InjectView(R.id.frozen_money)
    TextView frozenMoney;
    @InjectView(R.id.drp_card)
    TextView drpCard;
    @InjectView(R.id.turn_money)
    TextView turnMoney;
    @InjectView(rl_account_details)
    RelativeLayout rlAccountDetails;
    @InjectView(R.id.application_record)
    RelativeLayout applicationRecord;
    @InjectView(R.id.application_recommend)
    RelativeLayout application_recommend;
    @InjectView(R.id.lin_card)
    LinearLayout linCard;
    @InjectView(R.id.lin_recharge)
    LinearLayout linRecharge;
    @InjectView(R.id.lin_withdrawal)
    LinearLayout linWithdrawal;
    @InjectView(R.id.lin_wallet)
    LinearLayout linWallet;
    @InjectView(R.id.number)
    TextView number;
    private MoneyManageContract.Presenter presenter;

    private boolean isAddCard = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_money_manage;
    }

    protected void init() {
        findViewById(R.id.backs).setOnClickListener(this);
        findViewById(R.id.application_integral).setOnClickListener(this);
        ButterKnife.inject(this);

        rlAccountDetails.setOnClickListener(this);
        linRecharge.setOnClickListener(this);
        linCard.setOnClickListener(this);
        linWallet.setOnClickListener(this);
        linWithdrawal.setOnClickListener(this);
        applicationRecord.setOnClickListener(this);
        application_recommend.setOnClickListener(this);
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.backs:
                finish();
                break;
            case rl_account_details:
                startActivity(new Intent(MoneyManageActivity.this, AccountDetailsActivity.class));
                break;
            case R.id.lin_card:
                startActivity(new Intent(MoneyManageActivity.this, CardInformationActivity.class));
                break;
            case R.id.lin_recharge:
                startActivity(new Intent(MoneyManageActivity.this, RechargeActivity.class));
                break;
            case R.id.lin_withdrawal:
                isAddCard = false;
                presenter.isHasCard();
                break;
            case R.id.lin_wallet:
                presenter.bonusTurn();
                break;
            case R.id.application_record:
                startActivity(new Intent(MoneyManageActivity.this, ApplyRecordActivity.class));
                break;
            case R.id.application_integral:
                startActivity(new Intent(MoneyManageActivity.this, IntegralActivity.class));
                break;
            case R.id.application_recommend:
                startActivity(new Intent(MoneyManageActivity.this, RecommendActivity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new MoneyManagePresenter(this);
        }
        presenter.start();
        if (isAddCard) {
            presenter.isHasCard();
        }
    }


    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void callbackMoneyManageSuccess(MoneyManage moneyManage) {
        if (!moneyManage.getSurplus_amount().equals("") || moneyManage.getSurplus_amount() != null) {
            tvMoney.setText(moneyManage.getSurplus_amount());
        }
        if (!moneyManage.getFrozen_money().equals("") || moneyManage.getFrozen_money() != null) {
            frozenMoney.setText("冻结余额：" + moneyManage.getFrozen_money());
        }
        drpCard.setText(moneyManage.getDrp_card() + "");
        turnMoney.setText(moneyManage.getTurn_money() + "");
    }

    @Override
    public void isHasCardSuccess(boolean isHasCard) {
        if (isHasCard) {
            startActivity(new Intent(this, WithdrawalActivity.class));
            isAddCard = false;
        } else if (!isAddCard) {
            isAddCard = true;
            showToast("请先添加银行卡");
            startActivity(new Intent(this, AddCardActivity.class));
        }
    }

    @Override
    public void bonusTurnSuccess(String msg) {
        showToast(msg);
        presenter.moneyManage();
    }

    @Override
    public void setNumber(String num) {
        int a  =Integer.parseInt(num);
        if(a>0){
            number.setVisibility(View.VISIBLE);
            number.setText(num);
        }

    }
}
