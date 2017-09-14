package com.lidegou.lideshangmeng.mobile.ui.personal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.UserInfo;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.about.AboutMyActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.contect.ContectActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.mydata.PersonalDataContract;
import com.lidegou.lideshangmeng.mobile.ui.personal.mydata.PersonalDataPresenter;
import com.lidegou.lideshangmeng.mobile.util.Prefs;
import com.lidegou.lideshangmeng.mobile.util.dialog.PromptDialog;

import java.io.File;
import java.text.DecimalFormat;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by Y on 2016/12/3.
 * 设置
 */
public class MineSetActivity extends BaseActivity implements PersonalDataContract.View {

    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.re_about_my)
    RelativeLayout reAboutMy;
    @InjectView(R.id.exit_login)
    Button exitLogin;
    @InjectView(R.id.clear_option)
    RelativeLayout clearOption;
    @InjectView(R.id.default_cache)
    TextView defaultCache;
    private AlertDialog.Builder alertDialog;
    private String url = "";

    private double cache_size;
    private DecimalFormat df;
    private PromptDialog promptDialog;
    private PersonalDataContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_set;
    }

    protected void init() {
        ButterKnife.inject(this);
        linBack.setOnClickListener(this);
        reAboutMy.setOnClickListener(this);
        exitLogin.setOnClickListener(this);
        clearOption.setOnClickListener(this);
        promptDialog = new PromptDialog(this);
        findViewById(R.id.re_contect_my).setOnClickListener(this);
        findViewById(R.id.re_service_my).setOnClickListener(this);
        findViewById(R.id.re_disclaimer_my).setOnClickListener(this);
        findViewById(R.id.re_introduction_my).setOnClickListener(this);

        url = Environment.getExternalStorageDirectory().getAbsoluteFile()
                + File.separator + "lidegou";
        cache_size = getFolderSize(new File(url));
        df = new DecimalFormat("######0.00");
        defaultCache.setHint(df.format(cache_size / 1024 / 1024) + "M");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new PersonalDataPresenter(this);
        }
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.exit_login:
                promptDialog.setMsg("是否退出登录");
                promptDialog.setOnPromptClickListener(new PromptDialog.OnPromptClickListener() {
                    @Override
                    public void promptConfirmClick(View view) {
                        presenter.loginout();
                        getApp().setUid("");
                        Config.User.STATUS = false;
                        Prefs.with(getContext()).writeBoolean(Config.User.AUTOLOGIN, false);
                    }

                    @Override
                    public void promptCancelClick(View view) {
                        promptDialog.dismiss();
                    }
                });
                promptDialog.show();
                break;
            case R.id.re_about_my:
                startActivity(new Intent(MineSetActivity.this, AboutMyActivity.class));
                break;
            case R.id.re_contect_my:
                Intent contectIntent = new Intent(MineSetActivity.this, ContectActivity.class);
                contectIntent.putExtra(ContectActivity.TYPE, ContectActivity.TYPE_CONTECT);
                contectIntent.putExtra(ContectActivity.NAME, "客服热线");
                startActivity(contectIntent);
                break;
            case R.id.re_service_my:
                Intent seriveIntent = new Intent(MineSetActivity.this, ContectActivity.class);
                seriveIntent.putExtra(ContectActivity.TYPE, ContectActivity.TYPE_SERVICE);
                seriveIntent.putExtra(ContectActivity.NAME, "服务条款");
                startActivity(seriveIntent);
                break;
            case R.id.re_disclaimer_my:
                Intent disclaimerIntent = new Intent(MineSetActivity.this, ContectActivity.class);
                disclaimerIntent.putExtra(ContectActivity.TYPE, ContectActivity.TYPE_DISCLAIMER);
                disclaimerIntent.putExtra(ContectActivity.NAME, "免责声明");
                startActivity(disclaimerIntent);
                break;
            case R.id.re_introduction_my:
                Intent introductionIntent = new Intent(MineSetActivity.this, ContectActivity.class);
                introductionIntent.putExtra(ContectActivity.TYPE, ContectActivity.TYPE_INTRODUCTION);
                introductionIntent.putExtra(ContectActivity.NAME, "功能介绍");
                startActivity(introductionIntent);
                break;
            case R.id.clear_option:
                cache_size = getFolderSize(new File(url));
                alertDialog = new AlertDialog.Builder(this)
                        .setTitle("提示：")
                        .setMessage("当前一共" + df.format(cache_size / 1024 / 1024) + "M缓存，您是否要删除？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFolderFile(url, true);
                                Toast.makeText(MineSetActivity.this, "操作完成", Toast.LENGTH_SHORT).show();
                                cache_size = getFolderSize(new File(url));
                                defaultCache.setHint(df.format(cache_size / 1024 / 1024) + "M");
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                if (!df.format(cache_size / 1024 / 1024).equals("0.00")) {
                    alertDialog.create().show();
                } else {
                    Toast.makeText(this, "当前没有缓存", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }


    /**
     * 获取文件夹大小
     *
     * @param file File实例
     * @return long
     */
    public static double getFolderSize(File file) {

        double size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);

                } else {
                    size = size + fileList[i].length();

                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //return size/1048576;
        return size;
    }

    /**
     * 删除指定目录下文件及目录
     *
     * @param deleteThisPath
     * @param
     * @return
     */
    public void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {// 处理目录
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    } else {// 目录
                        if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void callbackLoginoutSuccess() {
        showToast("注销成功");
        Config.User.STATUS = false;
        App.getApp().setLoginOut(true);
        JPushInterface.setAlias(App.getApp(), "", null);
        Prefs.with(App.getApp()).write(Config.User.UID, null);
        App.getApp().setUid(null);
        finish();
    }

    @Override
    public void callbackUserInfoSuccess(UserInfo.UserInfoBean userInfoBean) {

    }

    @Override
    public void callbackUpdateLogoSuccess() {

    }
}
