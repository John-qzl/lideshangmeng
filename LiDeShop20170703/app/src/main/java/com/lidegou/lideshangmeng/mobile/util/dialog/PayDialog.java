package com.lidegou.lideshangmeng.mobile.util.dialog;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.allinpay.appayassistex.APPayAssistEx;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.pay.tonly.PaaCreator;

import org.json.JSONObject;


/**
 * Created by Garmin-Yi on 2016/2/15.
 */
public class PayDialog extends BaseDialog {

    private View rootView;

    RelativeLayout payTreasureLayout;
    RelativeLayout phoneWechartLayout;
    RelativeLayout pay_tonly_layout;
    private OnPayClickListener onPayClickListener;

    public PayDialog(Context context) {
        super(context);
    }

    public void closePay() {
        if (isShowing()) {
            dismiss();
        }
    }

    public void setOnPayClickListener(OnPayClickListener onPayClickListener) {
        this.onPayClickListener = onPayClickListener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_pay;

    }

    @Override
    protected void init() {
        payTreasureLayout = (RelativeLayout) findViewById(R.id.pay_treasure_layout);
        phoneWechartLayout = (RelativeLayout) findViewById(R.id.pay_wechart_layout);
        pay_tonly_layout = (RelativeLayout) findViewById(R.id.pay_tonly_layout);
        payTreasureLayout.setOnClickListener(this);
        pay_tonly_layout.setOnClickListener(this);
        phoneWechartLayout.setOnClickListener(this);
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.pay_treasure_layout:
                onPayClickListener.payClick(view, 0);
                break;
            case R.id.pay_wechart_layout:
                onPayClickListener.payClick(view, 1);
                break;
            case R.id.pay_tonly_layout:
                JSONObject payData = PaaCreator.randomPaa(null);
                APPayAssistEx.startPay(getActivity(), payData.toString(), APPayAssistEx.MODE_DEBUG);
                break;
        }
    }

    public interface OnPayClickListener {
        void payClick(View view, int type);
    }


}
