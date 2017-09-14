package com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.chongzhi;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allinpay.appayassistex.APPayAssistEx;
import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.OtherPayEntity;
import com.lidegou.lideshangmeng.mobile.pay.tonly.PaaCreator;
import com.lidegou.lideshangmeng.mobile.pay.treasure.PayTreasure;
import com.lidegou.lideshangmeng.mobile.pay.wechat.PayWeChat;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.util.popu.pay.PayPopuWindow;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class RechargeActivity extends BaseActivity implements RechargeContract.View, PayPopuWindow.PayPopuWindowCallBack {
    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.ed_price)
    EditText edPrice;
    @InjectView(R.id.ed_vipnote)
    EditText edVipnote;
    @InjectView(R.id.submit_recharge)
    Button submitRecharge;
    @InjectView(R.id.ll_recharge_style)
    LinearLayout llRechargeStyle;
    @InjectView(R.id.tv_recharge_style)
    TextView tvRechargeStyle;

    private RechargeContract.Presenter presenter;
    private double remainder;

    private String payment_id = "";

    private PayPopuWindow popuWindow;
    private ArrayList<PayType> datas;

    private String type;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new RechargePresenter(this);
        }
        if (datas == null) {
            presenter.payType();
        }
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);
        type = getIntent().getStringExtra("type");
//        Toast.makeText(this, "1111===" + type, Toast.LENGTH_LONG).show();

        linBack.setOnClickListener(this);
        submitRecharge.setOnClickListener(this);
        llRechargeStyle.setOnClickListener(this);

        popuWindow = new PayPopuWindow(this, this);

        edPrice.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable edt) {
                String temp = edt.toString();
                int posDot = temp.indexOf(".");
                if (posDot <= 0) return;
                if (temp.length() - posDot - 1 > 2) {
                    edt.delete(posDot + 3, posDot + 4);
                }
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
        });
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.submit_recharge:
                if (edPrice.getText().toString().length() > 0) {
                    double price = Double.parseDouble(edPrice.getText().toString());
                    if (price > 0 && price < 100000) {
                        presenter.rechargeMoney();
                    } else {
                        showToast("请输入大于0 小于10万的金额");
                    }
                } else {
                    showToast("请输入大于0的金额");
                }
                break;
            case R.id.ll_recharge_style:
                if (datas != null) {
                    popuWindow.show(tvRechargeStyle, datas);
                }
                break;
        }
    }

    @Override
    public String payment_id() {
        return payment_id;
    }

    @Override
    public String amount() {
        return edPrice.getText().toString();
    }

    @Override
    public String user_note() {
        return edVipnote.getText().toString();
    }

    @Override
    public String process_type() {
        return "2";
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public void payTypeSuccess(ArrayList<PayType> datas) {
        this.datas = datas;
        if (datas.size() > 0) {
            tvRechargeStyle.setText(datas.get(0).getPay_name() + " 手续费" + datas.get(0).getPay_fee() + "元");
            payment_id = datas.get(0).getPay_id();
        }

    }

    @Override
    public void rechargeMoneySuccess(OtherPayEntity entity) {
        showToast("正在支付，请稍等...");
        if (payment_id.equals("12")) {
            OtherPayEntity.PaaCreatorEntity paaCreatorEntity = entity.getPaaCreatorEntity();
            if (paaCreatorEntity != null) {
                JSONObject payData = PaaCreator.randomPaa(paaCreatorEntity);
                APPayAssistEx.startPay(this, payData.toString(), APPayAssistEx.MODE_PRODUCT);
            }
        }
        if (payment_id.equals("13")) {
            OtherPayEntity.WeChatEntity weChatEntity = entity.getWeChatEntity();
            if (weChatEntity != null) {
                PayWeChat payWeChat = new PayWeChat(this, App.getApp(), new PayHandler());
                payWeChat.payBack(weChatEntity);
            }
        }
        if (payment_id.equals("14")) {
            OtherPayEntity.TreasureEntity treasureEntity = entity.getTreasureEntity();
            if (treasureEntity != null) {
                PayTreasure payTreasure = new PayTreasure(this, new PayHandler());
                payTreasure.payBack(treasureEntity.getPayInfo());
            }
        }
    }

    public class PayHandler extends Handler {
        @Override
        public void handleMessage(final Message msg) {
            String code = msg.getData().getString(Config.Pay.PAY_STAUTS);
            if (code.equals(Config.Pay.PAY_SUCCESS)) {
                showToast("充值成功");
                finish();
            } else {
                showToast("充值失败");
            }
        }
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }


    @Override
    public void payTypeCallBack(int position) {
        payment_id = datas.get(position).getPay_id();
        PayType payType = datas.get(position);
        tvRechargeStyle.setText(payType.getPay_name() + " 手续费" + payType.getPay_fee() + "元");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (APPayAssistEx.REQUESTCODE == requestCode) {
            if (null != data) {
                String payRes = null;
                try {
                    JSONObject resultJson = new JSONObject(data.getExtras().getString("result"));
                    payRes = resultJson.getString(APPayAssistEx.KEY_PAY_RES);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (null != payRes && payRes.equals(APPayAssistEx.RES_SUCCESS)) {
                    showToast("支付成功！");
                    finish();
                } else {
                    showToast("支付失败！");
                }
            }
        }
    }
}
