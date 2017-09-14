package com.lidegou.lideshangmeng.mobile.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.pay.wechat.PayWeChat;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;

import static com.lidegou.lideshangmeng.mobile.App.getApp;

/**
 * Created by Yi on 2016/5/3.
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getApp().getWXAPI().handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        getApp().getWXAPI().handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Toast.makeText(this, "baseReq", Toast.LENGTH_SHORT).show();
        Log.i("WXPayEntryActivity", "baseReq:" + baseReq);
    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            String code = "7000";
            if (baseResp.errCode == 0) {
                code = "9000";
            }
            Bundle bundle = new Bundle();
            bundle.putString(Config.Pay.PAY_STAUTS, code);
            bundle.putString(Config.Pay.PAY_MSG, baseResp.errStr);
            bundle.putString(Config.Pay.PAY_ORDER_NUMBER, PayWeChat.orderNumber);
            bundle.putString(Config.Pay.PAY_ORDER_TIME, PayWeChat.orderTime);
            Message message = new Message();
            message.setData(bundle);
            PayWeChat.payHandler.sendMessage(message);
        }
        finish();
    }
}
