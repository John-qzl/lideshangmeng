package com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jiongbull.jlog.JLog;
import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.ShopsStore;
import com.lidegou.lideshangmeng.mobile.fragment.My.customerService.CustomerServiceActivity;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.StoreStreetMapActivity;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.adapter.MallShopsBannerAdapter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.login.LoginActivity;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;
import com.lidegou.lideshangmeng.mobile.util.dialog.PromptDialog;

import org.xutils.x;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * 店铺详情
 */
public class ShopsStoreDetailsActivity extends BaseActivity implements ShopsStoreDetailsContract.View {

    @InjectView(R.id.iv_shops_pic)
    ImageView ivShopsPic;
    @InjectView(R.id.tv_shops_name)
    TextView tvShopsName;
    @InjectView(R.id.tv_guanzhu)
    TextView tvGuanzhu;
    @InjectView(R.id.btn_attention)
    ImageView btnAttention;
    @InjectView(R.id.vp_banner)
    ViewPager vpBanner;
    @InjectView(R.id.fl_banner)
    FrameLayout flBanner;
    @InjectView(R.id.re_customer_service)
    RelativeLayout reCustomerService;
    @InjectView(R.id.tv_freight)
    TextView tvFreight;
    @InjectView(R.id.iv_customer_service)
    ImageView ivCustomerService;
    @InjectView(R.id.chanpin)
    TextView chanpin;
    @InjectView(R.id.rl_qr_code)
    RelativeLayout rlQrCode;
    @InjectView(R.id.iv_qr_code)
    ImageView ivQrCode;
    @InjectView(R.id.re_appropriate_crowd)
    RelativeLayout reAppropriateCrowd;
    @InjectView(R.id.tv_appropriate_crowd)
    TextView tvAppropriateCrowd;
    @InjectView(R.id.iv_phone)
    ImageView ivPhone;
    @InjectView(R.id.lin_details_crowd)
    LinearLayout linDetailsCrowd;
    @InjectView(R.id.tv_common_crowd)
    TextView tvCommonCrowd;
    @InjectView(R.id.tv_details_crowd)
    TextView tvDetailsCrowd;
    @InjectView(R.id.tv_scope)
    TextView tvScope;
    @InjectView(R.id.tv_desc)
    TextView tvDesc;
    @InjectView(R.id.sl_scroll)
    ScrollView slScroll;
    @InjectView(R.id.tv_telephone)
    TextView tvTelephone;
    @InjectView(R.id.tv_qq)
    TextView tvQq;
    @InjectView(R.id.parent)
    LinearLayout parent;
    private boolean isExid = true;
    private LinearLayout lin_dialog;
    private ShopsStore shopsStore;
    private boolean isResult;
    private String isAttention;

    private ShopsStoreDetailsPresenter presenter;


    private ShopsStore.ShopsStoreDetails shopsStoreDetails;
    private MallShopsBannerAdapter bannerAdapter;
    private ShopsDetailsHandler handler;

    private PromptDialog promptDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shops_store_details;
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);
        if (presenter == null) {
            presenter = new ShopsStoreDetailsPresenter(this);
        }

        isResult = getIntent().getBooleanExtra("isResult", false);
        shopsStore = (ShopsStore) getIntent().getSerializableExtra("shopsStore");
        btnAttention.setOnClickListener(this);
        rlQrCode.setOnClickListener(this);
        tvQq.setOnClickListener(this);
        tvTelephone.setOnClickListener(this);
        reCustomerService.setOnClickListener(this);
        reAppropriateCrowd.setOnClickListener(this);
        linDetailsCrowd.setOnClickListener(this);

        presenter.StoreDetails();
        promptDialog = new PromptDialog(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (handler == null) {
            handler = new ShopsDetailsHandler(this);
        }
        if (Config.User.STATUS) {
            presenter.StoreDetails();
        } else {

        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isResult) {
                if (!shopsStore.getGaze_status().equals(isAttention)) {
                    Intent intent = new Intent();
                    intent.putExtra("id", shopsStore.getUser_id());
                    setResult(1, intent);
                } else {
                    setResult(0);
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.backs:
                finish();
                if (isResult) {
                    if (!shopsStore.getGaze_status().equals(isAttention + "")) {
                        Intent intent = new Intent();
                        intent.putExtra("id", shopsStore.getUser_id());
                        setResult(1, intent);
                    } else {
                        setResult(0);
                    }
                }
                break;
            case R.id.iv_message:
                if (isExid) {
                    isExid = false;
                    hidden();
                } else {
                    isExid = true;
                    hidden();
                }
                break;
            case R.id.btn_attention:
                if (Config.User.STATUS) {
                    presenter.FocusShops();
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.rl_qr_code:
                if (shopsStoreDetails == null) {
                    return;
                }

                final PopupWindow popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                View view1 = LayoutInflater.from(this).inflate(R.layout.dialog, null);
                view1.findViewById(R.id.other).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                ImageView im = (ImageView) view1.findViewById(R.id.iv_qr_code);
                x.image().bind(im, String.valueOf(shopsStoreDetails.getCode()));
                popupWindow.setContentView(view1);
                popupWindow.showAtLocation(findViewById(R.id.parent), Gravity.CENTER, 0, 0);
                break;
            case R.id.tv_qq:
                if (shopsStoreDetails != null && shopsStoreDetails.getShop_qq() != null) {
                    Intent qqIntent = new Intent();
                    qqIntent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=" + shopsStoreDetails.getShop_qq() + "&version=1&src_type=web&web_src=oicqzone.com");
                    qqIntent.setData(content_url);
                    startActivity(qqIntent);
                }
                break;
            case R.id.re_customer_service:
                if (Config.User.STATUS) {
                    startActivity(new Intent(this, CustomerServiceActivity.class));
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.re_appropriate_crowd:
                if (shopsStoreDetails != null && shopsStoreDetails.getShop_tel() != null) {
                    promptDialog.setMsg("是否拨打电话:" + shopsStoreDetails.getShop_tel());
                    promptDialog.setOnPromptClickListener(new PromptDialog.OnPromptClickListener() {
                        @Override
                        public void promptConfirmClick(View view) {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + shopsStoreDetails.getShop_tel()));
                            startActivity(intent);
                        }

                        @Override
                        public void promptCancelClick(View view) {
                            promptDialog.dismiss();
                        }
                    });
                    promptDialog.show();
                }
                break;
            case R.id.tv_telephone:
                if (shopsStoreDetails != null && shopsStoreDetails.getShop_tel() != null) {
                    promptDialog.setMsg("是否拨打电话:" + shopsStoreDetails.getShop_tel());
                    promptDialog.setOnPromptClickListener(new PromptDialog.OnPromptClickListener() {
                        @Override
                        public void promptConfirmClick(View view) {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + shopsStoreDetails.getShop_tel()));
                            startActivity(intent);
                        }

                        @Override
                        public void promptCancelClick(View view) {
                            promptDialog.dismiss();
                        }
                    });
                    promptDialog.show();
                }
                break;
            case R.id.lin_details_crowd:
                if (shopsStore != null) {
                    Intent intent = new Intent(this, StoreStreetMapActivity.class);
                    intent.putExtra("longitude", shopsStore.getLongitude());
                    intent.putExtra("latitude", shopsStore.getLatitude());
                    intent.putExtra("name", shopsStore.getShop_name());
                    startActivity(intent);
                    break;
                }
        }
    }

    public void hidden() {
        if (isExid) {
            lin_dialog.setVisibility(View.INVISIBLE);
        } else {
            lin_dialog.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public String ShopUserid() {
        if (shopsStore.getUser_id() != null) {
            return String.valueOf(shopsStore.getUser_id());
        } else {
            return "";
        }

    }

    @Override
    public void callbackFocusShopsSuccess(String msg) {
        showToast(msg);
        if (msg.equals("已关注")) {
            btnAttention.setImageResource(R.drawable.attention_unselect);
            isAttention = "1";
        } else {
            btnAttention.setImageResource(R.drawable.attention_select);
            isAttention = "0";
        }
    }

    @Override
    public void callbackStoreDetailsSuccess(final ShopsStore.ShopsStoreDetails shopsStoreDetails) {
//        Toast.makeText(this, shopsStoreDetails.getGaze_status(), Toast.LENGTH_SHORT).show();
        if (shopsStoreDetails != null) {
            isAttention = shopsStoreDetails.getGaze_status();
            tvShopsName.setText(String.valueOf(shopsStoreDetails.getShop_name()));
            tvGuanzhu.setText("已有" + String.valueOf(shopsStoreDetails.getCount_gaze()) + "人关注");
            tvAppropriateCrowd.setText(String.valueOf(shopsStoreDetails.getShop_tel()));
            tvCommonCrowd.setText(String.valueOf(shopsStoreDetails.getShop_name()));
            tvDetailsCrowd.setText(String.valueOf(shopsStoreDetails.getShop_address()));
            tvScope.setText(String.valueOf(shopsStoreDetails.getBusines_scope()));
            tvDesc.setMovementMethod(ScrollingMovementMethod.getInstance());//滚动
            tvDesc.setText(Html.fromHtml(String.valueOf(shopsStoreDetails.getStreet_desc())));
            MyImageLoader.getInstance().loaderImageAll(shopsStoreDetails.getShop_logo(), ivShopsPic, 1);
            this.shopsStoreDetails = shopsStoreDetails;
            if (shopsStoreDetails.getGaze_status().equals("1")) {
                btnAttention.setImageResource(R.drawable.attention_unselect);
            } else {
                btnAttention.setImageResource(R.drawable.attention_select);
            }

            if (shopsStoreDetails.getImg_arr() != null) {
                if (shopsStoreDetails.getImg_arr().size() < 1) {
                    findViewById(R.id.fl_banner).setVisibility(View.GONE);
                } else {
                    findViewById(R.id.fl_banner).setVisibility(View.VISIBLE);
                    if (bannerAdapter == null) {
                        bannerAdapter = new MallShopsBannerAdapter(getContext(), shopsStoreDetails.getImg_arr());
                        vpBanner.setAdapter(bannerAdapter);
                        vpBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {

                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (true) {
                                    try {
                                        Thread.sleep(3000);
                                    } catch (InterruptedException e) {
                                        JLog.e((e == null || e.getMessage() == null) ? "Exception is null" : e.getMessage());
                                    }

                                    if (shopsStoreDetails.getImg_arr().size() > 1) {
                                        handler.sendEmptyMessage(ShopsDetailsHandler.WHAT_NEXT_BANNER);
                                    }
                                }
                            }
                        }).start();
                    } else {
                        bannerAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }


    @Override
    public void showError(String msg) {
        showToast(msg);
    }


    /**
     * Handler
     */
    public static class ShopsDetailsHandler extends Handler {
        private final WeakReference<ShopsStoreDetailsActivity> reference;

        public ShopsDetailsHandler(ShopsStoreDetailsActivity activity) {
            reference = new WeakReference<>(activity);
        }

        public static final int WHAT_NEXT_BANNER = 11;

        @Override
        public void handleMessage(Message msg) {
            if (reference.get() == null) {
                return;
            }
            switch (msg.what) {
                case WHAT_NEXT_BANNER:
                    autoNextBanner();
                    break;
            }
        }

        void autoNextBanner() {
            int size = reference.get().bannerAdapter.getCount();
            int item = reference.get().vpBanner.getCurrentItem();
            if ((item + 1) >= size) {
                item = 0;
            } else {
                item += 1;
            }
            reference.get().vpBanner.setCurrentItem(item);
        }

    }
}
