package com.lidegou.lideshangmeng.mobile.ui.personal.dosingle.paySingle;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.allinpay.appayassistex.APPayAssistEx;
import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.OtherPayEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.PaySinleEntity;
import com.lidegou.lideshangmeng.mobile.pay.tonly.PaaCreator;
import com.lidegou.lideshangmeng.mobile.pay.treasure.PayTreasure;
import com.lidegou.lideshangmeng.mobile.pay.wechat.PayWeChat;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.dosingle.MineSingleContract;
import com.lidegou.lideshangmeng.mobile.ui.personal.dosingle.MineSinglePresenter;
import com.lidegou.lideshangmeng.mobile.util.dialog.LoadingDialog;
import com.lidegou.lideshangmeng.mobile.util.dialog.select.SelectDialog;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 做单支付
 */
public class SinlePayActivity extends BaseActivity implements MineSingleContract.PaySingle {

    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.tv_integral)
    TextView tvIntegral;
    @InjectView(R.id.tv_price)
    TextView tvPrice;
    @InjectView(R.id.tv_zhifumoney)
    TextView tvZhifumoney;
    @InjectView(R.id.tv_zhifumoney_other)
    TextView tvZhifumoneyOther;
    @InjectView(R.id.btn_single_pay)
    Button btnSinglePay;
    @InjectView(R.id.scrollView)
    ScrollView scrollView;
    @InjectView(R.id.tv_pay_name)
    TextView tvPayName;
    @InjectView(R.id.tv_pay_fee)
    TextView tvPayFee;
    @InjectView(R.id.re_pay)
    RelativeLayout repay;

    private MineSingleContract.Presenter presenter;
    String id;

    LoadingDialog loadingDialog;
    private PayHandler payHandler;

    private SelectDialog payTypeDialog;
    private PaySinleEntity entity;
    private String payType = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sinle_pay;
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);
        linBack.setOnClickListener(this);
        repay.setOnClickListener(this);
        btnSinglePay.setOnClickListener(this);
        id = getIntent().getStringExtra("id");
        loadingDialog = new LoadingDialog(this);
        payHandler = new PayHandler();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new MineSinglePresenter(this);
        }
        presenter.getPaySingle();
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.btn_single_pay:
                presenter.pay();
                break;
            case R.id.re_pay:
                if (entity == null || entity.getPayment_list() == null) {
                    return;
                }
                ArrayList<String> payTypeList = new ArrayList<>();
                for (PaySinleEntity.PayMent payMent : entity.getPayment_list()) {
                    payTypeList.add(payMent.getPay_name() + " 手续费:" + payMent.getPay_fee() + "元");
                }
                payTypeDialog = new SelectDialog(this);
                payTypeDialog.show("选择支付方式", payTypeList, payTypeClick);
                break;
        }
    }

    AdapterView.OnItemClickListener payTypeClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            setPayTypeId(position);
            payTypeDialog.dismiss();
        }
    };

    private void setPayTypeId(int position) {
        PaySinleEntity.PayMent payMent = entity.getPayment_list().get(position);
        tvPayName.setText(payMent.getPay_name());
        tvPayFee.setText(" 手续费:" + payMent.getPay_fee() + "元");
        payType = payMent.getPay_id();
    }

    @Override
    public String Id() {
        if (id != null) {
            return id;
        } else {
            return "";
        }
    }

    @Override
    public String payType() {
        return payType;
    }

    @Override
    public void callbackPaySingle(PaySinleEntity entity) {
        if (entity != null) {
            this.entity = entity;
            tvIntegral.setText(entity.getUser_money() + "");
            if (entity.getPayinfo() != null) {
                tvPrice.setText(entity.getPayinfo().getJine() + "");
                tvZhifumoney.setText(entity.getPayinfo().getZhifumoney() + "");
                tvZhifumoneyOther.setText(entity.getPayinfo().getZhifumoney_other() + "");
                if (entity.getPayment_list().size() > 0) {
                    tvPayName.setText(entity.getPayment_list().get(0).getPay_name());
                    if (entity.getPayment_list().get(0).getPay_fee().equals("免运费")) {
                        tvPayFee.setText(" " + entity.getPayment_list().get(0).getPay_fee());
                    } else {
                        tvPayFee.setText(" 手续费:" + entity.getPayment_list().get(0).getPay_fee() + "元");
                    }
                    payType = entity.getPayment_list().get(0).getPay_id();
                }
            }
        }
    }

    @Override
    public void paySuccess(OtherPayEntity entity) {
        if (payType.equals("9")) {
            showToast("支付成功");
            setPaySuccess();
            finish();
        }
        if (payType.equals("12")) {
            OtherPayEntity.PaaCreatorEntity paaCreatorEntity = entity.getPaaCreatorEntity();
            if (paaCreatorEntity != null) {
                JSONObject payData = PaaCreator.randomPaa(paaCreatorEntity);
                APPayAssistEx.startPay(this, payData.toString(), APPayAssistEx.MODE_PRODUCT);
            }
        }
        if (payType.equals("13")) {
            OtherPayEntity.WeChatEntity weChatEntity = entity.getWeChatEntity();
            if (weChatEntity != null) {
                PayWeChat payWeChat = new PayWeChat(this, App.getApp(), new PayHandler());
                payWeChat.payBack(weChatEntity);
            }
        }
        if (payType.equals("14")) {
            OtherPayEntity.TreasureEntity treasureEntity = entity.getTreasureEntity();
            if (treasureEntity != null) {
                PayTreasure payTreasure = new PayTreasure(this, new PayHandler());
                payTreasure.payBack(treasureEntity.getPayInfo());
            }
        }
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }


    public class PayHandler extends Handler {
        @Override
        public void handleMessage(final Message msg) {
            Bundle bundle = msg.getData();
            String status = bundle.getString(Config.Pay.PAY_STAUTS);
            if (status.equals(Config.Pay.PAY_SUCCESS)) {
                showToast("支付成功");
                setPaySuccess();
                finish();
            } else {
                showToast("支付失败");
            }

        }
    }

    private void setPaySuccess() {
        App.getApp().setPaySingleALLSuccess(true);
        App.getApp().setPaySingleYesSuccess(true);
        App.getApp().setPaySingleWaitSuccess(true);
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
                    showToast("支付成功");
                    setPaySuccess();
                    finish();
                } else {
                    showToast("支付失败！");
                }
            }
        }
    }

    public void showAppayRes(String res) {
        new AlertDialog.Builder(this)
                .setMessage(res)
                .setPositiveButton("确定", null)
                .show();
    }
}
