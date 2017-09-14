package com.lidegou.lideshangmeng.mobile.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.util.NetUtil;


/**
 * 网络状态监听器
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Config.Net.STATUS = NetUtil.isNetworkConnected(context);
        Config.Net.TYPE = NetUtil.getNetworkType(context);
    }
}
