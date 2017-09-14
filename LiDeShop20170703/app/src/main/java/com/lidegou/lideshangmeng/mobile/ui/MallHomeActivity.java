package com.lidegou.lideshangmeng.mobile.ui;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.adapter.TabFragmentAdapter;
import com.lidegou.lideshangmeng.mobile.data.HttpUtil;
import com.lidegou.lideshangmeng.mobile.fragment.Class.MallClassifyFragment;
import com.lidegou.lideshangmeng.mobile.fragment.My.MallMineFragment;
import com.lidegou.lideshangmeng.mobile.fragment.My.paymoney.PaymoneyActivity;
import com.lidegou.lideshangmeng.mobile.fragment.ShopCar.MallShopCarFragment;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.MallStoreStreetFragment;
import com.lidegou.lideshangmeng.mobile.fragment.home.MallHomeFragment;
import com.lidegou.lideshangmeng.mobile.jpush.ExampleUtil;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.ui.login.LoginActivity;
import com.lidegou.lideshangmeng.mobile.util.Prefs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Y on 2016/12/1.
 */
public class MallHomeActivity extends BaseActivity {
    public ViewPager viewPager;
    public List<BaseFragment> fragmentList;
    public LinearLayout ll_home_home;
    public LinearLayout ll_home_classify;
    public LinearLayout ll_home_storestreet;
    public LinearLayout ll_home_shopcar;
    public LinearLayout ll_home_mine;
    public ImageView img_home_bottom_home;
    public ImageView img_home_bottom_classify;
    public ImageView img_home_bottom_storestreet;
    public ImageView img_home_bottom_shopcar;
    public ImageView img_home_bottom_mine;
    public static boolean isForeground = false;

    String finish;

    private boolean isFirst = true;
    private int item = 0;

    private Dialog updataDialog;

    private MallStoreStreetFragment mallStoreStreetFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mall_home;
    }

    public void init() {
//        PayTreasure payTreasure = new PayTreasure(this, new Handler());
//        payTreasure.pay("1", "1", "1");

        if (isFirst) {
            HttpUtil httpUtil = new HttpUtil();
            try {
                httpUtil.AppUpdate(this);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            isFirst = false;
        }

        if (!Config.User.STATUS) {
            autologin();
        }

        viewPager = (ViewPager) findViewById(R.id.tab_view_pager);
        img_home_bottom_home = (ImageView) findViewById(R.id.img_home_bottom_home);
        img_home_bottom_classify = (ImageView) findViewById(R.id.img_home_bottom_classify);
        img_home_bottom_storestreet = (ImageView) findViewById(R.id.img_home_bottom_storestreet);
        img_home_bottom_shopcar = (ImageView) findViewById(R.id.img_home_bottom_shopcar);
        img_home_bottom_mine = (ImageView) findViewById(R.id.img_home_bottom_mine);
        ll_home_home = (LinearLayout) findViewById(R.id.ll_home_home);
        ll_home_classify = (LinearLayout) findViewById(R.id.ll_home_classify);
        ll_home_storestreet = (LinearLayout) findViewById(R.id.ll_home_storestreet);
        ll_home_shopcar = (LinearLayout) findViewById(R.id.ll_home_shopcar);
        ll_home_mine = (LinearLayout) findViewById(R.id.ll_home_mine);


        ll_home_home.setOnClickListener(this);
        ll_home_classify.setOnClickListener(this);
        ll_home_storestreet.setOnClickListener(this);
        ll_home_shopcar.setOnClickListener(this);
        ll_home_mine.setOnClickListener(this);
        if (fragmentList == null) {
            MallShopCarFragment shopCarFragment = new MallShopCarFragment();
            shopCarFragment.setType(MallShopCarFragment.TYPE_HOME);
            mallStoreStreetFragment = new MallStoreStreetFragment();
            fragmentList = new ArrayList<>();
            fragmentList.add(new MallHomeFragment());
            fragmentList.add(new MallClassifyFragment());
            fragmentList.add(mallStoreStreetFragment);
            fragmentList.add(shopCarFragment);
            fragmentList.add(new MallMineFragment());
        }
        TabFragmentAdapter tabFragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(tabFragmentAdapter);
        viewPager.setOffscreenPageLimit(fragmentList.size());
    }

    private void autologin() {
        boolean auto = Prefs.with(getContext()).readBoolean(Config.User.AUTOLOGIN, false);
        if (auto) {
            String uid = Prefs.with(getContext()).readString(Config.User.UID);
            if (uid != null) {
                HashMap<String, String> map = new HashMap<>();
                map.put("uid", uid);
                HttpUtil httpUtil = new HttpUtil();
                httpUtil.AutoLogin(map);
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int homePage = App.getApp().getNotiHomePage();
        if (homePage != -1) {
            if (homePage == 0) {
                App.getApp().toHome();
            } else {
                App.getApp().toMy();
            }
            App.getApp().setNotiHomePage(-1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isForeground = true;
        int i = viewPager.getCurrentItem();
        switch (i) {
            case 0:
                img_home_bottom_home.setBackgroundResource(R.drawable.home02);
                img_home_bottom_classify.setBackgroundResource(R.drawable.classify01);
                img_home_bottom_storestreet.setBackgroundResource(R.drawable.storestreet01);
                img_home_bottom_shopcar.setBackgroundResource(R.drawable.shopcar01);
                img_home_bottom_mine.setBackgroundResource(R.drawable.mine01);
                break;
            case 1:
                img_home_bottom_home.setBackgroundResource(R.drawable.home01);
                img_home_bottom_classify.setBackgroundResource(R.drawable.classify02);
                img_home_bottom_storestreet.setBackgroundResource(R.drawable.storestreet01);
                img_home_bottom_shopcar.setBackgroundResource(R.drawable.shopcar01);
                img_home_bottom_mine.setBackgroundResource(R.drawable.mine01);
                break;
            case 2:
                img_home_bottom_home.setBackgroundResource(R.drawable.home01);
                img_home_bottom_classify.setBackgroundResource(R.drawable.classify01);
                img_home_bottom_storestreet.setBackgroundResource(R.drawable.storestreet02);
                img_home_bottom_shopcar.setBackgroundResource(R.drawable.shopcar01);
                img_home_bottom_mine.setBackgroundResource(R.drawable.mine01);
                break;
            case 3:
                img_home_bottom_home.setBackgroundResource(R.drawable.home01);
                img_home_bottom_classify.setBackgroundResource(R.drawable.classify01);
                img_home_bottom_storestreet.setBackgroundResource(R.drawable.storestreet01);
                img_home_bottom_shopcar.setBackgroundResource(R.drawable.shopcar02);
                img_home_bottom_mine.setBackgroundResource(R.drawable.mine01);
                break;
            case 4:
                img_home_bottom_home.setBackgroundResource(R.drawable.home01);
                img_home_bottom_classify.setBackgroundResource(R.drawable.classify01);
                img_home_bottom_storestreet.setBackgroundResource(R.drawable.storestreet01);
                img_home_bottom_shopcar.setBackgroundResource(R.drawable.shopcar01);
                img_home_bottom_mine.setBackgroundResource(R.drawable.mine02);
                break;
        }


        finish = getIntent().getStringExtra("finish");
        if (finish != null && finish.equals("finish")) {
            viewPager.setCurrentItem(3, false);
            img_home_bottom_home.setBackgroundResource(R.drawable.home01);
            img_home_bottom_classify.setBackgroundResource(R.drawable.classify01);
            img_home_bottom_storestreet.setBackgroundResource(R.drawable.storestreet01);
            img_home_bottom_shopcar.setBackgroundResource(R.drawable.shopcar02);
            img_home_bottom_mine.setBackgroundResource(R.drawable.mine01);
        }

    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_home_home:
                viewPager.setCurrentItem(0, false);
                img_home_bottom_home.setBackgroundResource(R.drawable.home02);
                img_home_bottom_classify.setBackgroundResource(R.drawable.classify01);
                img_home_bottom_storestreet.setBackgroundResource(R.drawable.storestreet01);
                img_home_bottom_shopcar.setBackgroundResource(R.drawable.shopcar01);
                img_home_bottom_mine.setBackgroundResource(R.drawable.mine01);
                break;
            case R.id.ll_home_classify:
                viewPager.setCurrentItem(1, false);
                img_home_bottom_home.setBackgroundResource(R.drawable.home01);
                img_home_bottom_classify.setBackgroundResource(R.drawable.classify02);
                img_home_bottom_storestreet.setBackgroundResource(R.drawable.storestreet01);
                img_home_bottom_shopcar.setBackgroundResource(R.drawable.shopcar01);
                img_home_bottom_mine.setBackgroundResource(R.drawable.mine01);
                break;
            case R.id.ll_home_storestreet:
                viewPager.setCurrentItem(2, false);
                img_home_bottom_home.setBackgroundResource(R.drawable.home01);
                img_home_bottom_classify.setBackgroundResource(R.drawable.classify01);
                img_home_bottom_storestreet.setBackgroundResource(R.drawable.storestreet02);
                img_home_bottom_shopcar.setBackgroundResource(R.drawable.shopcar01);
                img_home_bottom_mine.setBackgroundResource(R.drawable.mine01);
                break;
            case R.id.ll_home_shopcar:
                if (!Config.User.STATUS) {
                    startActivity(new Intent(this, LoginActivity.class));
                    return;
                }
                viewPager.setCurrentItem(3, false);
                img_home_bottom_home.setBackgroundResource(R.drawable.home01);
                img_home_bottom_classify.setBackgroundResource(R.drawable.classify01);
                img_home_bottom_storestreet.setBackgroundResource(R.drawable.storestreet01);
                img_home_bottom_shopcar.setBackgroundResource(R.drawable.shopcar02);
                img_home_bottom_mine.setBackgroundResource(R.drawable.mine01);
                break;
            case R.id.ll_home_mine:
                if (!Config.User.STATUS) {
                    startActivity(new Intent(this, LoginActivity.class));
                    return;
                }
                viewPager.setCurrentItem(4, false);
                img_home_bottom_home.setBackgroundResource(R.drawable.home01);
                img_home_bottom_classify.setBackgroundResource(R.drawable.classify01);
                img_home_bottom_storestreet.setBackgroundResource(R.drawable.storestreet01);
                img_home_bottom_shopcar.setBackgroundResource(R.drawable.shopcar01);
                img_home_bottom_mine.setBackgroundResource(R.drawable.mine02);
                break;
        }
    }


    public void changpage(int item) {
        this.item = item;
        if (item == 1) {
            viewPager.setCurrentItem(1, false);
            img_home_bottom_classify.setBackgroundResource(R.drawable.classify02);
            img_home_bottom_home.setBackgroundResource(R.drawable.home01);
        } else if (item == 2) {
            viewPager.setCurrentItem(2, false);
            img_home_bottom_storestreet.setBackgroundResource(R.drawable.storestreet02);
            img_home_bottom_home.setBackgroundResource(R.drawable.home01);
        } else if (item == 4) {
            viewPager.setCurrentItem(4, false);
            img_home_bottom_mine.setBackgroundResource(R.drawable.mine02);
            img_home_bottom_home.setBackgroundResource(R.drawable.home01);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {  //表示按返回键 时的操作
                if (updataDialog != null && updataDialog.isShowing()) {
                    return false;
                }
                exitBy2Click();
                if (item > 0) {
                    viewPager.setCurrentItem(0, false);
                    img_home_bottom_home.setBackgroundResource(R.drawable.home02);
                    img_home_bottom_classify.setBackgroundResource(R.drawable.classify01);
                    img_home_bottom_storestreet.setBackgroundResource(R.drawable.storestreet01);
                    img_home_bottom_shopcar.setBackgroundResource(R.drawable.shopcar01);
                    img_home_bottom_mine.setBackgroundResource(R.drawable.mine01);
                }
                return false;    //已处理
            }
        }
        return false;
    }

    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            showToast("再按一次退出程序");
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }


    //极光

    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.wnlc.community.o2o.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    public void AppUpdate(String data, final String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("提示：")
                .setMessage("有新版本" + data + "，您是否要更新？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        Uri content_url = Uri.parse(url);
                        intent.setData(content_url);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        updataDialog = builder.show();
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!ExampleUtil.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }
                setCostomMsg(showMsg.toString());
            }
        }
    }

    private void setCostomMsg(String msg) {

    }

    public void toShopCar() {
        viewPager.setCurrentItem(3, false);
        img_home_bottom_home.setBackgroundResource(R.drawable.home01);
        img_home_bottom_classify.setBackgroundResource(R.drawable.classify01);
        img_home_bottom_storestreet.setBackgroundResource(R.drawable.storestreet01);
        img_home_bottom_shopcar.setBackgroundResource(R.drawable.shopcar02);
        img_home_bottom_mine.setBackgroundResource(R.drawable.mine01);
    }

    public void toHome() {
        viewPager.setCurrentItem(0, false);
        img_home_bottom_home.setBackgroundResource(R.drawable.home02);
        img_home_bottom_classify.setBackgroundResource(R.drawable.classify01);
        img_home_bottom_storestreet.setBackgroundResource(R.drawable.storestreet01);
        img_home_bottom_shopcar.setBackgroundResource(R.drawable.shopcar01);
        img_home_bottom_mine.setBackgroundResource(R.drawable.mine01);
    }

    public void toMy() {
        img_home_bottom_home.setBackgroundResource(R.drawable.home01);
        img_home_bottom_classify.setBackgroundResource(R.drawable.classify01);
        img_home_bottom_storestreet.setBackgroundResource(R.drawable.storestreet01);
        img_home_bottom_shopcar.setBackgroundResource(R.drawable.shopcar01);
        img_home_bottom_mine.setBackgroundResource(R.drawable.mine02);
        viewPager.setCurrentItem(4, false);
    }

    public void setShopsStoreFirst() {
        mallStoreStreetFragment.setFirst();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mallStoreStreetFragment != null) {
            mallStoreStreetFragment.onActivityResult(requestCode, resultCode, data);
        }
        if(requestCode==789&&resultCode == RESULT_OK){
            Bundle bundle  =  data.getExtras();
            String string = bundle.getString("result");
            if(strNumber(string)){
                Intent intent = new Intent(this, PaymoneyActivity.class);
                intent.putExtra("id",string);
                startActivity(intent);
            }else{
                showToast("无效二维码");
            }

        }

    }

    public boolean  strNumber(String  str) {

        // 邮箱验证规则
        String regEx = "^1[3|4|5|8][0-9]\\d{8}$";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        return   matcher.matches();

    }
}
