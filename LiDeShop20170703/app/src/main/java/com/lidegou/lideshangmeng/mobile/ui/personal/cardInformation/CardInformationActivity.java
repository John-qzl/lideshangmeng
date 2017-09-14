package com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.BankCard;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.login.LoginActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.addCard.AddCardActivity;
import com.lidegou.lideshangmeng.mobile.util.dialog.PromptDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Y on 2016/12/3.
 * 银行卡信息
 */
public class CardInformationActivity extends BaseActivity implements CardInformationContract.View, CardInformationAdapter.OnClickListener {
    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.re_card_information)
    RecyclerView reCardInformation;
    @InjectView(R.id.btn_add_card)
    Button btnAddCard;
    private CardInformationContract.Presenter presenter;
    private CardInformationAdapter cardInformationAdapter;
    private List<BankCard> bankCardList = new ArrayList<>();
    private String id;
    private PromptDialog promptDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_card_information;
    }

    protected void init() {
        ButterKnife.inject(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
        btnAddCard.setOnClickListener(this);
        cardInformationAdapter = new CardInformationAdapter();
        cardInformationAdapter.setOnClickListener(this);
        reCardInformation.setLayoutManager(new LinearLayoutManager(this));
        reCardInformation.setAdapter(cardInformationAdapter);

        promptDialog = new PromptDialog(this);
    }

    @Override
    protected void viewClick(View v) {
        switch (v.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.btn_add_card:
                startActivity(new Intent(CardInformationActivity.this, AddCardActivity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Config.User.STATUS) {

        } else {
            startActivity(new Intent(CardInformationActivity.this, LoginActivity.class));
            finish();
        }
        if (presenter == null) {
            presenter = new CardInformationPresenter(this);
        }
        presenter.start();
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }


    @Override
    public String id() {
        return id;
    }

    @Override
    public void callbackCardlistSuccess(List<BankCard> bankCardList) {
        cardInformationAdapter.setDataList(bankCardList);
        this.bankCardList = bankCardList;
    }

    @Override
    public void callbackDeleteCardSuccess() {
        showToast("操作成功");
        presenter.start();
    }

    @Override
    public void cardListDelete(View view, int position) {
        BankCard bankCard = bankCardList.get(position);
        id = bankCard.getId();
        promptDialog.setMsg("是否确定删除该银行卡");
        promptDialog.setOnPromptClickListener(new PromptDialog.OnPromptClickListener() {
            @Override
            public void promptConfirmClick(View view) {
                presenter.deleteCard();
                promptDialog.dismiss();
            }

            @Override
            public void promptCancelClick(View view) {
                promptDialog.dismiss();
            }
        });
        promptDialog.show();
    }
}
