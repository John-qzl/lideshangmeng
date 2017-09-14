package com.lidegou.lideshangmeng.mobile.jpush;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.ui.MallHomeActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.notice.order.MineOrderMessageDetailsActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.notice.sys.MineSysMessageDetailsActivity;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            String intentStr = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            String[] strings = intentStr.split("_");
            if (strings.length == 4) {
                if (strings[0].equals("1")) {
                    App.getApp().setNotiHomePage(0);
                    showNotification(MallHomeActivity.class, strings[2], strings[3], "0");
                } else if (strings[0].equals("2")) {
                    App.getApp().setNotiHomePage(4);
                    showNotification(MallHomeActivity.class, strings[2], strings[3], "4");
                } else if (strings[0].equals("3")) {
                    showNotification(MineSysMessageDetailsActivity.class, strings[2], strings[3], strings[1]);
                } else if (strings[0].equals("4")) {
                    showNotification(MineOrderMessageDetailsActivity.class, strings[2], strings[3], strings[1]);
                }
            }
        }
    }

    private void showNotification(Class activity, String title, String content, String id) {
        // 创建一个NotificationManager的引用
        NotificationManager notificationManager = (NotificationManager)
                App.getApp().getSystemService(android.content.Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(App.getApp());
        Notification notification = builder.setContentTitle(title)
                .setContentText(content)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                .setSmallIcon(R.drawable.ic_launcher).build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        // 设置通知的事件消息
        Intent intent = new Intent(App.getApp(), activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//这行代码会解决此问题
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("id", id);
        PendingIntent contentItent = PendingIntent.getActivity(App.getApp(), 0, intent, 0);
        notification.contentIntent = contentItent;
        // 把Notification传递给NotificationManager
        notificationManager.notify(0, notification);
    }
}
