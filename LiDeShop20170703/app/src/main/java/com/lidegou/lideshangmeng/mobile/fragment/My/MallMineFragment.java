package com.lidegou.lideshangmeng.mobile.fragment.My;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.HttpInterface;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsIn;
import com.lidegou.lideshangmeng.mobile.data.entity.UserInfo;
import com.lidegou.lideshangmeng.mobile.fragment.My.attention.MyAttentionActivity;
import com.lidegou.lideshangmeng.mobile.fragment.My.collection.MyCollectionActivity;
import com.lidegou.lideshangmeng.mobile.fragment.My.customerService.CustomerServiceActivity;
import com.lidegou.lideshangmeng.mobile.fragment.My.footprint.FootPrintActivity;
import com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin.MerchantsInFiveActivity;
import com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin.MerchantsInFourActivity;
import com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin.MerchantsInOneActivity;
import com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin.MerchantsInThreeActivity;
import com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin.MerchantsInTwoActivity;
import com.lidegou.lideshangmeng.mobile.scancodedemo.MipcaActivityCapture;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.ui.login.LoginActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.MineSetActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.dosingle.MineDoSingleActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.dosingle.MineListSingleActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.MoneyManageActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.mydata.PersonalDataActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.notice.MineNoticeActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.order.OrderListActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.order.fragment.orderevlustion.OrderEvaluationActivity;
import com.lidegou.lideshangmeng.mobile.ui.webview.WebPageModule;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;
import com.lidegou.lideshangmeng.mobile.util.share.ShareDialog;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import static com.lidegou.lideshangmeng.mobile.R.id.merchants_in;

/**
 * Created by Y on 2016/12/1.
 */
public class MallMineFragment extends BaseFragment implements MallMineContract.View {
    private TextView username;
    private MallMineContract.Presenter presenter;
    private LinearLayout lin_single;
    private List<UserInfo> userInfoList = new ArrayList<>();
    private TextView wait_pay_bubble, wait_shouhuo_bubble, wait_common_bubble, shoucang_bubble, guanzhu_bubble;
    private TextView tv_user_money, tv_totla_xiaofei, tv_bonus_money, tv_turn_money, tv_totla_fandian, tv_now_fandian, tv_yi_fandian, tv_todaylog_new;
    private TextView merchantsIn;
    private UserInfo.UserInfoBean userInfoBean;

    private ImageView imgPersonalData;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mall_mine;
    }


    protected void init() {
        initData();
    }

    public void initdata() {
        for (UserInfo userInfo : userInfoList) {
            if (userInfo.getPay_count().equals("0") || userInfo.getPay_count().equals("")) {//待付款单数
                wait_pay_bubble.setVisibility(View.GONE);
            } else {
                wait_pay_bubble.setVisibility(View.VISIBLE);
                wait_pay_bubble.setText(userInfo.getPay_count() + "");
            }
            if (userInfo.getConfirmed_count().equals("0") || userInfo.getConfirmed_count().equals("")) {//待收货单数
                wait_shouhuo_bubble.setVisibility(View.GONE);
            } else {
                wait_shouhuo_bubble.setVisibility(View.VISIBLE);
                wait_shouhuo_bubble.setText(userInfo.getConfirmed_count() + "");
            }
            if (userInfo.getNot_comment() == null) {//待评价单数
                wait_common_bubble.setVisibility(View.GONE);
            } else {
                wait_common_bubble.setVisibility(View.VISIBLE);
                wait_common_bubble.setText(userInfo.getNot_comment() + "");
            }
            if (userInfo.getGoods_num() == null) {//收藏商品数
                shoucang_bubble.setVisibility(View.GONE);
            } else {
                shoucang_bubble.setVisibility(View.VISIBLE);
                shoucang_bubble.setText(userInfo.getGoods_num() + "");
            }
            if (userInfo.getStore_num() == null) {//关注店铺数
                guanzhu_bubble.setVisibility(View.GONE);
            } else {
                guanzhu_bubble.setVisibility(View.VISIBLE);
                guanzhu_bubble.setText(userInfo.getStore_num() + "");
            }

            tv_user_money.setText(userInfo.getUser_money() + "");
            tv_totla_xiaofei.setText(userInfo.getTotla_xiaofei() + "");
            tv_bonus_money.setText(userInfo.getBonus_money() + "");
            tv_turn_money.setText(userInfo.getTurn_money() + "");
            tv_totla_fandian.setText(userInfo.getTotla_fandian() + "");
            tv_now_fandian.setText(userInfo.getNow_fandian() + "");
            tv_yi_fandian.setText(userInfo.getYi_fandian() + "");
            tv_todaylog_new.setText(userInfo.getTodaylog_new() + "");
            if (userInfo.getUserInfoBeanList().get(0).getUser_picture() != null && !userInfo.getUserInfoBeanList().get(0).getUser_picture().equals("")) {
                MyImageLoader.getInstance().returnBitMap(userInfo.getUserInfoBeanList().get(0).getUser_picture().replace("\"", ""), imgPersonalData, 1);
            } else {
                imgPersonalData.setImageResource(R.drawable.touxianghui);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new MallMinePresenter(this);
        }
        presenter.start();

        if (App.getApp().isLoginOut()) {
            App.getApp().toHome();
            App.getApp().setLoginOut(false);
        }
    }

    public void initData() {

        merchantsIn = (TextView) getRootView().findViewById(merchants_in);
        merchantsIn.setOnClickListener(this);
        imgPersonalData = (ImageView) getRootView().findViewById(R.id.img_personal_data);
        imgPersonalData.setOnClickListener(this);
        getRootView().findViewById(R.id.re_money_manage).setOnClickListener(this);
        getRootView().findViewById(R.id.ll_mine_set).setOnClickListener(this);
        getRootView().findViewById(R.id.ll_mine_qrcode).setOnClickListener(this);
        getRootView().findViewById(R.id.ll_mine_notice).setOnClickListener(this);
        getRootView().findViewById(R.id.ll_wait_payment).setOnClickListener(this);
        getRootView().findViewById(R.id.ll_wait_goods).setOnClickListener(this);
        getRootView().findViewById(R.id.ll_wait_evaluate).setOnClickListener(this);
        getRootView().findViewById(R.id.ll_mine_do_single).setOnClickListener(this);
        getRootView().findViewById(R.id.scan_ll).setOnClickListener(this);
        getRootView().findViewById(R.id.xjk_ll).setOnClickListener(this);
        getRootView().findViewById(R.id.wdqb_ll).setOnClickListener(this);
        getRootView().findViewById(R.id.zhgl_ll).setOnClickListener(this);
        getRootView().findViewById(R.id.ll_mine_list_single).setOnClickListener(this);
        getRootView().findViewById(R.id.lin_my_footprint).setOnClickListener(this);
        getRootView().findViewById(R.id.re_collection).setOnClickListener(this);
        getRootView().findViewById(R.id.re_attention).setOnClickListener(this);
        getRootView().findViewById(R.id.ll_all_order).setOnClickListener(this);
        getRootView().findViewById(R.id.ll_service).setOnClickListener(this);
        getRootView().findViewById(R.id.ll_mine_share).setOnClickListener(this);
        username = (TextView) getRootView().findViewById(R.id.username);
        lin_single = (LinearLayout) getRootView().findViewById(R.id.lin_single);
        wait_pay_bubble = (TextView) getRootView().findViewById(R.id.wait_pay_bubble);
        wait_shouhuo_bubble = (TextView) getRootView().findViewById(R.id.wait_shouhuo_bubble);
        wait_common_bubble = (TextView) getRootView().findViewById(R.id.wait_common_bubble);
        shoucang_bubble = (TextView) getRootView().findViewById(R.id.shoucang_bubble);
        guanzhu_bubble = (TextView) getRootView().findViewById(R.id.guanzhu_bubble);
        tv_user_money = (TextView) getRootView().findViewById(R.id.tv_user_money);
        tv_totla_xiaofei = (TextView) getRootView().findViewById(R.id.tv_totla_xiaofei);
        tv_bonus_money = (TextView) getRootView().findViewById(R.id.tv_bonus_money);
        tv_turn_money = (TextView) getRootView().findViewById(R.id.tv_turn_money);
        tv_totla_fandian = (TextView) getRootView().findViewById(R.id.tv_totla_fandian);
        tv_now_fandian = (TextView) getRootView().findViewById(R.id.tv_now_fandian);
        tv_yi_fandian = (TextView) getRootView().findViewById(R.id.tv_yi_fandian);
        tv_todaylog_new = (TextView) getRootView().findViewById(R.id.tv_todaylog_new);

    }

    @Override
    protected void viewClick(View view) {
        Intent intent = new Intent();
        if (!Config.User.STATUS) {
            showToast("请先登陆");
            intent.setClass(getActivity(), LoginActivity.class);
            startActivity(intent);
            return;
        }
        switch (view.getId()) {
            case R.id.img_personal_data:
                intent.setClass(getContext(), PersonalDataActivity.class);
                if (userInfoBean != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("userInfoBean", userInfoBean);
                    intent.putExtras(bundle);
                }
                startActivity(intent);
                break;
            case R.id.ll_mine_set:

                intent.setClass(getContext(), MineSetActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_mine_notice:
                intent.setClass(getContext(), MineNoticeActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_all_order:
                intent.setClass(getContext(), OrderListActivity.class);
                intent.putExtra("state", 0);
                startActivity(intent);
                break;
            case R.id.ll_wait_payment:
                intent.setClass(getContext(), OrderListActivity.class);
                intent.putExtra("state", 1);
                startActivity(intent);
                break;
            case R.id.ll_wait_goods:
                intent.setClass(getContext(), OrderListActivity.class);
                intent.putExtra("state", 2);
                startActivity(intent);
                break;
            case R.id.ll_wait_evaluate:
                intent.setClass(getContext(), OrderEvaluationActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_mine_do_single:
                intent.setClass(getContext(), MineDoSingleActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_mine_list_single:
                intent.setClass(getContext(), MineListSingleActivity.class);
                startActivity(intent);
                break;
            case R.id.re_money_manage:

                break;
            case R.id.lin_my_footprint:
                intent.setClass(getContext(), FootPrintActivity.class);
                startActivity(intent);
                break;
            case R.id.re_collection:
                intent.setClass(getContext(), MyCollectionActivity.class);
                startActivity(intent);
                break;
            case R.id.re_attention:
                intent.setClass(getContext(), MyAttentionActivity.class);
                startActivity(intent);
                break;
            case merchants_in:
                presenter.MerchantsInCheck();
                break;
            case R.id.ll_service:
                startActivity(new Intent(getActivity(), CustomerServiceActivity.class));
                break;
            case R.id.ll_mine_share:
                String uid = App.getApp().getUid();
                RequestParams requestParams = new RequestParams(HttpInterface.getUserInfo());
                requestParams.addBodyParameter("uid", uid);
                x.http().post(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {

                        Gson gson = new Gson();
                        UserInfo userInfo = gson.fromJson(s, UserInfo.class);
                        ShareDialog shareDialog = new ShareDialog(getActivity());
                        shareDialog.show(userInfo.getTitle(), userInfo.getImg(), userInfo.getUrl());

                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {

                        Toast.makeText(getActivity(), "网络请求错误", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(CancelledException e) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
                break;
            case R.id.ll_mine_qrcode:

                Intent qrcode = new Intent(getActivity(), MipcaActivityCapture.class);
                getActivity().startActivityForResult(qrcode, 789);
                break;
            case R.id.scan_ll:

                Intent qrcode1 = new Intent(getActivity(), MipcaActivityCapture.class);
                getActivity().startActivityForResult(qrcode1, 789);
                break;
            case R.id.xjk_ll:
//                Toast.makeText(getActivity(), "小金库", Toast.LENGTH_LONG).show();
                Intent xjk = new Intent(getActivity(), WebPageModule.class);
                //不传递startUrl的情况下，默认走自动加载widget的机制，即：APICloud引擎会自动去解析assets/widget目录下的资源并加载
                xjk.putExtra("startUrl", "file:///android_asset/widget/xiaojinku-app.html");
                String userId = App.getApp().getUid();
                xjk.putExtra("userid", userId);
                startActivity(xjk);
                break;
            case R.id.wdqb_ll:
                intent.setClass(getContext(), MoneyManageActivity.class);
                startActivity(intent);
                break;
            case R.id.zhgl_ll:
//                Toast.makeText(getActivity(), "账户管理", Toast.LENGTH_LONG).show();
                Intent zhgl = new Intent(getActivity(), WebPageModule.class);
                //不传递startUrl的情况下，默认走自动加载widget的机制，即：APICloud引擎会自动去解析assets/widget目录下的资源并加载
                zhgl.putExtra("startUrl", "file:///android_asset/widget/zhanghuguanli.html");
//                intent.putExtra("startUrl", url);
                startActivity(zhgl);
                break;

        }
    }


    @Override
    public void callbackMerchantsInCheckSuccess(MerchantsIn.MerchantsInCheck merchantsInCheck) {
        if (merchantsInCheck.getSteps().equals("one")) {
            startActivity(new Intent(getActivity(), MerchantsInOneActivity.class));
        } else if (merchantsInCheck.getSteps().equals("two")) {
            startActivity(new Intent(getActivity(), MerchantsInTwoActivity.class));
        } else if (merchantsInCheck.getSteps().equals("three")) {
            startActivity(new Intent(getActivity(), MerchantsInThreeActivity.class));
        } else if (merchantsInCheck.getSteps().equals("four")) {
            startActivity(new Intent(getActivity(), MerchantsInFourActivity.class));
        } else if (merchantsInCheck.getSteps().equals("five")) {
            startActivity(new Intent(getActivity(), MerchantsInFiveActivity.class));
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (userInfoList.size() > 0)
                merchantsIn.setText("" + userInfoList.get(0).getShop_name());
            else
                handler.sendEmptyMessageDelayed(1, 1000);
        }
    };

    @Override
    public void IsSellerSuccess(String data) {
        if (data.equals("0")) {
            lin_single.setVisibility(View.GONE);
            merchantsIn.setText("申请商家入驻");
        } else {
            lin_single.setVisibility(View.VISIBLE);
            handler.sendEmptyMessage(1);
        }
    }

    @Override
    protected void lazyLoad() {
        Log.i("mine", "lazyLoad");
        if (presenter == null) {
            presenter = new MallMinePresenter(this);
        }
        presenter.start();
        if (!Config.User.STATUS) {
            imgPersonalData.setImageResource(R.drawable.touxianghui);
            username.setText("");
        }
    }


    @Override
    public void showError(String msg) {
        showToast(msg);

    }

    @Override
    public void callbackUserInfoSuccess(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
        for (UserInfo.UserInfoBean userInfoBean : userInfoList.get(0).getUserInfoBeanList()) {
            username.setText(userInfoBean.getUser_name());
            this.userInfoBean = userInfoBean;
        }
        initdata();
    }
}