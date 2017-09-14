package com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.applyRecord.applyRecordDetail;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allinpay.appayassistex.APPayAssistEx;
import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.MoneyManage;
import com.lidegou.lideshangmeng.mobile.data.entity.OtherPayEntity;
import com.lidegou.lideshangmeng.mobile.pay.tonly.PaaCreator;
import com.lidegou.lideshangmeng.mobile.pay.treasure.PayTreasure;
import com.lidegou.lideshangmeng.mobile.pay.wechat.PayWeChat;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;

import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 申请记录详情
 */
public class ApplyRecordDetailActivity extends BaseActivity implements ApplyRecordDetailContract.View {


    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.tv_type)
    TextView tvType;
    @InjectView(R.id.tv_time)
    TextView tvTime;
    @InjectView(R.id.tv_price)
    TextView tvPrice;
    @InjectView(R.id.tv_status)
    TextView tvStatus;
    @InjectView(R.id.pay_way)
    TextView payWay;
    @InjectView(R.id.short_user_note)
    TextView shortUserNote;
    @InjectView(R.id.short_admin_note)
    TextView shortAdminNote;
    @InjectView(R.id.btn_operation)
    TextView btnOperation;


    private ApplyRecordDetailContract.Presenter presenter;
    private String id;
    private String payment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_record_detail;
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);
        linBack.setOnClickListener(this);
        id = getIntent().getStringExtra("id");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new ApplyRecordDetailPresenter(this);
        }
        presenter.start();
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
        }
    }

    @Override
    public String payment_id() {
        return payment;
    }


    @Override
    public String id() {
        return id;
    }

    @Override
    public void rechargeMoneySuccess(OtherPayEntity entity) {
        if (payment.equals("12")) {
            OtherPayEntity.PaaCreatorEntity paaCreatorEntity = entity.getPaaCreatorEntity();
            if (paaCreatorEntity != null) {
                JSONObject payData = PaaCreator.randomPaa(paaCreatorEntity);
                APPayAssistEx.startPay(this, payData.toString(), APPayAssistEx.MODE_PRODUCT);
            }
        }
        if (payment.equals("13")) {
            OtherPayEntity.WeChatEntity weChatEntity = entity.getWeChatEntity();
            if (weChatEntity != null) {
                PayWeChat payWeChat = new PayWeChat(this, App.getApp(), new PayHandler());
                payWeChat.payBack(weChatEntity);
            }
        }
        if (payment.equals("14")) {
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
    public void cancleSuccess(Boolean cancel) {
        if (cancel) {
            showToast("取消成功");
            finish();
        } else {
            showToast("取消失败");
        }
    }

    @Override
    public void callbackApplyRecordDetailSuccess(MoneyManage.ApplyRecord.DataBean dataBean) {
        if (dataBean.getPayment().contains("通联")) {
            payment = "12";
        }
        if (dataBean.getPayment().contains("微信")) {
            payment = "13";
        }
        if (dataBean.getPayment().contains("支付宝")) {
            payment = "14";
        }
        tvType.setText(dataBean.getType());
        tvTime.setText(dataBean.getAdd_time());
        tvPrice.setText(dataBean.getAmount());
        tvStatus.setText(dataBean.getPay_status());
        payWay.setText(dataBean.getPayment());
        shortUserNote.setText(dataBean.getShort_user_note());
        shortAdminNote.setText(dataBean.getShort_admin_note());
        if (dataBean.getType().equals("充值")) {
            if (dataBean.getPay_status().equals("已完成")) {
                btnOperation.setText("已完成");
            } else {
                btnOperation.setText("付款");
                btnOperation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.recharge();
                    }
                });
            }

        } else {
            if (dataBean.getPay_status().equals("已完成")) {
                btnOperation.setText("已完成");
            } else {
                btnOperation.setText("取消");
                btnOperation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.cancle();
                    }
                });
            }
        }
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
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
                    showToast("充值成功！");
                    finish();
                } else {
                    showToast("充值失败！");
                }
            }
        }
    }
}
