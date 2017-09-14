package com.lidegou.lideshangmeng.mobile.wxapi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lidegou.lideshangmeng.mobile.App;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;

/**
 * 微信相关的分享和授权需要有该activity，必须写在包名+.wxapi包下，类名必须为WXEntryActivity
 * @author youtui
 */
public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private App app;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (App) getApplication();
        app.getWXAPI().handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        finish();
    }

    @Override
    public void onResp(BaseResp baseResp) {
        finish();
    }
}
