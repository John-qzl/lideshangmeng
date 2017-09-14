package com.lidegou.lideshangmeng.mobile.fragment.Commodity.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Commodity;
import com.lidegou.lideshangmeng.mobile.fragment.Commodity.CommodityBannerAdapter;
import com.lidegou.lideshangmeng.mobile.fragment.Commodity.CommodityDetailsContract;
import com.lidegou.lideshangmeng.mobile.fragment.Commodity.CommodityDetailsPresenter;
import com.lidegou.lideshangmeng.mobile.fragment.Commodity.shopcar.ShopCarActivity;
import com.lidegou.lideshangmeng.mobile.fragment.My.customerService.CustomerServiceActivity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.ui.login.LoginActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.notice.MineNoticeActivity;
import com.lidegou.lideshangmeng.mobile.util.dialog.PromptDialog;
import com.lidegou.lideshangmeng.mobile.util.dialog.ShopInfoDialog;
import com.lidegou.lideshangmeng.mobile.util.share.ShareDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint("ValidFragment")
public class CommodityDetailsFragment extends BaseFragment implements CommodityDetailsContract.View, ShopInfoDialog.ShopInfoCallBack {
    ViewPager vpCommodity;
    ImageView backs;
    ImageView tvShopsCar;
    ImageView ivMessage;
    ImageView ivAddcollection;
    TextView tvBannerShowCount;
    TextView tvBannerTotalCount;
    TextView tvBuyCount;
    LinearLayout linDialog;
    FrameLayout flBanner;
    TextView fuhao;
    TextView tvStandard;
    LinearLayout llShoppingCart;
    LinearLayout llBuyCart;
    TextView tvNone;
    TextView tvInventory;
    ImageView ivCommodityImg;


    ImageView ivShare;
    TextView etFreight;
    TextView etAddress;
    LinearLayout llAttribute;
    LinearLayout llEvaluate;
    LinearLayout llGoodDetails;
    LinearLayout llCollection;
    ImageView ivComment;

    private Timer timer = null;
    private List<Commodity.Data.CommodityDetails.PicturesBean> allPicturesBeanList = new ArrayList<>();

    private int nowtype = 0;
    private String number;

    private String goodid;

    private TextView commodity_name;
    private TextView tv_price;
    private boolean isExid = true;

    private CommodityBannerAdapter bannerAdapter;
    private List<Commodity> commodityList = new ArrayList<>();
    private CommodityDetailsContract.Presenter presenter;

    private Commodity.Data.CommodityDetails commodityDetails;

    private ShareDialog shareDialog;
    private PromptDialog promptDialog;
    private ShopInfoDialog dialog;
    private CommodityDetilsLister commodityDetilsLister;

    private String good_img = "";

    public CommodityDetailsFragment(CommodityDetilsLister commodityDetilsLister, String goodid) {
        this.commodityDetilsLister = commodityDetilsLister;
        this.goodid = goodid;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_commodity_details;
    }

    @Override
    protected void init() {
        shareDialog = new ShareDialog(getContext());
        promptDialog = new PromptDialog(getContext());
        tvShopsCar = (ImageView) getRootView().findViewById(R.id.tv_shops_car);
        vpCommodity = (ViewPager) getRootView().findViewById(R.id.vp_commodity);
        backs = (ImageView) getRootView().findViewById(R.id.backs);
        ivMessage = (ImageView) getRootView().findViewById(R.id.iv_message);
        tvBannerShowCount = (TextView) getRootView().findViewById(R.id.tv_banner_show_count);
        tvBannerTotalCount = (TextView) getRootView().findViewById(R.id.tv_banner_total_count);
        tvBuyCount = (TextView) getRootView().findViewById(R.id.et_buy_count);
        linDialog = (LinearLayout) getRootView().findViewById(R.id.lin_dialog);
        flBanner = (FrameLayout) getRootView().findViewById(R.id.fl_banner);
        fuhao = (TextView) getRootView().findViewById(R.id.fuhao);
        tvStandard = (TextView) getRootView().findViewById(R.id.tv_standard);
        llShoppingCart = (LinearLayout) getRootView().findViewById(R.id.ll_shopping_cart);
        llBuyCart = (LinearLayout) getRootView().findViewById(R.id.ll_buy_cart);
        tvNone = (TextView) getRootView().findViewById(R.id.tv_none);
        tvInventory = (TextView) getRootView().findViewById(R.id.tv_inventory);
        ivCommodityImg = (ImageView) getRootView().findViewById(R.id.iv_commodity_img);
        ivShare = (ImageView) getRootView().findViewById(R.id.iv_share);
        etFreight = (TextView) getRootView().findViewById(R.id.et_freight);
        etAddress = (TextView) getRootView().findViewById(R.id.et_address);
        llAttribute = (LinearLayout) getRootView().findViewById(R.id.ll_attribute);
        llEvaluate = (LinearLayout) getRootView().findViewById(R.id.ll_evaluate);
        llGoodDetails = (LinearLayout) getRootView().findViewById(R.id.ll_good_details);
        llCollection = (LinearLayout) getRootView().findViewById(R.id.ll_collection);
        ivComment = (ImageView) getRootView().findViewById(R.id.iv_comment);
        commodity_name = (TextView) getRootView().findViewById(R.id.tv_commodity_name);
        tv_price = (TextView) getRootView().findViewById(R.id.tv_price);
        ivAddcollection = (ImageView) getRootView().findViewById(R.id.iv_addcollection);
        getRootView().findViewById(R.id.backs).setOnClickListener(this);
        getRootView().findViewById(R.id.iv_message).setOnClickListener(this);


        llShoppingCart.setOnClickListener(this);
        ivShare.setOnClickListener(this);
        llAttribute.setOnClickListener(this);
        llEvaluate.setOnClickListener(this);
        llGoodDetails.setOnClickListener(this);
        ivComment.setOnClickListener(this);
        backs.setOnClickListener(this);
        tvShopsCar.setOnClickListener(this);
        llBuyCart.setOnClickListener(this);
        llCollection.setOnClickListener(this);

        getRootView().findViewById(R.id.lin_share).setOnClickListener(this);
        getRootView().findViewById(R.id.lin_dialog_message).setOnClickListener(this);
        getRootView().findViewById(R.id.lin_dialog_home).setOnClickListener(this);
        getRootView().findViewById(R.id.lin_dialog_service).setOnClickListener(this);

        setvpCommodity();
        dialog = new ShopInfoDialog(getContext(), this);

        tvBuyCount.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    private void setvpCommodity() {
        vpCommodity.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                tvBannerShowCount.setText(String.valueOf(vpCommodity.getCurrentItem() + 1));
            }
        });

        if (timer == null) {
            timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (getActivity() == null || getActivity().isFinishing()) {
                        timer.cancel();
                        return;
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (bannerAdapter != null) {
                                int size = bannerAdapter.getCount();
                                if (size > 1) {
                                    int item = vpCommodity.getCurrentItem();
                                    if ((item + 1) >= size) {
                                        item = 0;
                                    } else {
                                        item += 1;
                                    }
                                    vpCommodity.setCurrentItem(item);
                                }
                            }
                        }
                    });
                }
            };
            timer.schedule(task, 3000, 3000);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new CommodityDetailsPresenter(this);
        }
        presenter.start();
    }

    @Override
    protected void viewClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.iv_message:
                if (isExid) {
                    isExid = false;
                    hidden();
                } else {
                    isExid = true;
                    hidden();
                }
                break;
            case R.id.ll_shopping_cart:
                if (commodityDetails != null && commodityDetails.getGoods() != null) {
                    dialog.show(ShopInfoDialog.TYPE_CAR, commodityDetails.getGoods());
                }
                break;
            case R.id.ll_buy_cart:
                if (commodityDetails != null && commodityDetails.getGoods() != null) {
                    dialog.show(ShopInfoDialog.TYPE_BUY, commodityDetails.getGoods());
                }
                break;
            case R.id.iv_share:
                String name = commodityDetails.getGoods().getGoods_name();
                String imageUrl = commodityDetails.getGoods().getGoods_img();
                String url = commodityDetails.getUrl();
                shareDialog.show(name, imageUrl, url);
                break;
            case R.id.ll_attribute:
                if (commodityDetails != null && commodityDetails.getGoods() != null) {
                    dialog.show(ShopInfoDialog.TYPE_STYLE, commodityDetails.getGoods());
                }

                break;
            case R.id.ll_evaluate:
                if (commodityDetails.getGoods() != null) {
                    commodityDetilsLister.callbackEvaluateLister();
                }
                break;
            case R.id.ll_good_details:
                if (commodityDetails.getGoods() != null) {
                    commodityDetilsLister.callbackItemLister(commodityDetails.getGoods().getGoods_desc());
                }
                break;
            case R.id.iv_comment:
                if (Config.User.STATUS) {
                    startActivity(new Intent(getActivity(), CustomerServiceActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.backs:
                if (commodityDetilsLister != null) {
                    commodityDetilsLister.callbackOnClickLister();
                }
                break;
            case R.id.tv_shops_car:
                if (!Config.User.STATUS) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    return;
                }
                startActivity(new Intent(getActivity(), ShopCarActivity.class));
                break;
            case R.id.ll_collection:
                if (Config.User.STATUS) {
                    if (commodityDetails.getGoods_collect().equals("0")) {
                        presenter.addCollection();
                    } else {
                        presenter.addCollection();
                    }
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.lin_dialog_message:
                if (!Config.User.STATUS) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    return;
                }
                startActivity(new Intent(getActivity(), MineNoticeActivity.class));
                break;
            case R.id.lin_dialog_home:
                App.getApp().toHome();
                break;
            case R.id.lin_dialog_service:
                if (!Config.User.STATUS) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    return;
                }
                startActivity(new Intent(getActivity(), CustomerServiceActivity.class));
                break;
        }
    }

    @Override
    protected void lazyLoad() {
        if (presenter == null) {
            presenter = new CommodityDetailsPresenter(this);
        }
        presenter.start();
    }


    public void hidden() {
        if (isExid) {
            linDialog.setVisibility(View.INVISIBLE);
        } else {
            linDialog.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public String id() {
        if (goodid == null) {
            return "";
        }
        return goodid;
    }

    @Override
    public String warehouse_id() {
        return commodityDetails.getRegion_id();
    }

    @Override
    public String area_id() {
        return commodityDetails.getArea_id();
    }

    @Override
    public String quick() {
        return commodityDetails.getQuick() + "";
    }

    @Override
    public String spec() {
        return "";
    }

    @Override
    public String goods_id() {
        return commodityDetails.getGoods().getGoods_id();
    }

    @Override
    public String number() {
        return number;
    }

    @Override
    public void callbackCommodityDetailsSuccess(Commodity.Data.CommodityDetails commodityDetails) {
        String goodname = stringFilter(ToDBC(commodityDetails.getGoods().getGoods_name()));
        commodity_name.setText(goodname);
        tv_price.setText(commodityDetails.getGoods().getGoods_price() + "");
        tvBuyCount.setText("¥" + commodityDetails.getGoods().getMarket_price());
        tvStandard.setText("销量 " + commodityDetails.getGoods().getSales_volume() + "笔");
        tvInventory.setText("当前库存 " + commodityDetails.getGoods().getGoods_number() + "");
        etAddress.setText(commodityDetails.getBasic_info().getProvince() + commodityDetails.getBasic_info().getCity() + "");
        etFreight.setText(commodityDetails.getShippingFee().getFree_money() + "");
        this.commodityDetails = commodityDetails;
        if (commodityDetails.getGoods_collect().equals("1")) {
            ivAddcollection.setImageResource(R.drawable.shoucang_red);
        } else {
            ivAddcollection.setImageResource(R.drawable.shoucang);
        }
        if (commodityDetails.getGoods().getGoods_number().equals("0")) {
            tvNone.setVisibility(View.VISIBLE);
            llShoppingCart.setVisibility(View.GONE);
            llBuyCart.setVisibility(View.GONE);
        } else {
            tvNone.setVisibility(View.GONE);
            llShoppingCart.setVisibility(View.VISIBLE);
            llBuyCart.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void callbackCommodityBanner(final List<Commodity.Data.CommodityDetails.PicturesBean> picturesBeanList) {
        if (picturesBeanList.size() <= 0) {
            return;
        }
        this.allPicturesBeanList.clear();
        this.allPicturesBeanList.addAll(picturesBeanList);
        if (picturesBeanList.size() < 1) {
            getRootView().findViewById(R.id.fl_banner).setVisibility(View.GONE);
            ivCommodityImg.setVisibility(View.VISIBLE);
        } else {
            ivCommodityImg.setVisibility(View.GONE);
            getRootView().findViewById(R.id.fl_banner).setVisibility(View.VISIBLE);
            tvBannerShowCount.setText("1");
            tvBannerTotalCount.setText(String.valueOf(allPicturesBeanList.size()));
            bannerAdapter = new CommodityBannerAdapter(getContext(), allPicturesBeanList);
            vpCommodity.setAdapter(bannerAdapter);
        }
    }

    @Override
    public void callbackAddToCatSuccess(String msg) {
        showToast(msg);
        if (nowtype == ShopInfoDialog.TYPE_BUY) {
            startActivity(new Intent(getActivity(), ShopCarActivity.class));
        }
    }

    @Override
    public void callbackAddCollectionSuccess(String msg) {
        if (commodityDetails.getGoods_collect().equals("0")) {
            showToast(msg);
            commodityDetails.setGoods_collect("1");
            ivAddcollection.setImageResource(R.drawable.shoucang_red);
        } else {
            showToast("取消收藏成功");
            commodityDetails.setGoods_collect("0");
            ivAddcollection.setImageResource(R.drawable.shoucang);
        }
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void addCarCallBack(int type, int count) {
        if (Config.User.STATUS) {
            if (commodityDetails != null && commodityDetails.getRegion_id() != null) {
                number = count + "";
                nowtype = type;
                presenter.addToCat();
            }
        } else {
            promptDialog.setMsg("请登录后购买该商品");
            promptDialog.setOnPromptClickListener(new PromptDialog.OnPromptClickListener() {
                @Override
                public void promptConfirmClick(View view) {
                    if (commodityDetilsLister != null) {
                        commodityDetilsLister.callbackShopsCar("去登录");
                    }
                }

                @Override
                public void promptCancelClick(View view) {

                }
            });
            promptDialog.show();
        }
    }

    @Override
    public void buyCallBack(int type, int count) {
        if (Config.User.STATUS) {
            if (commodityDetails != null && commodityDetails.getRegion_id() != null) {
                number = count + "";
                nowtype = type;
                presenter.addToCat();
            }
        } else {
            promptDialog.setMsg("请登录后购买该商品");
            promptDialog.setOnPromptClickListener(new PromptDialog.OnPromptClickListener() {
                @Override
                public void promptConfirmClick(View view) {
                    if (commodityDetilsLister != null) {
                        commodityDetilsLister.callbackShopsCar("去登录");
                    }
                }

                @Override
                public void promptCancelClick(View view) {

                }
            });
            promptDialog.show();
        }
    }

    public interface CommodityDetilsLister {
        void callbackItemLister(String desc);

        void callbackOnClickLister();

        void callbackShopsCar(String msg);

        void callbackEvaluateLister();
    }


    /**
     * 半角转换为全角
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 去除特殊字符或将所有中文标号替换为英文标号
     *
     * @param str
     * @return
     */
    public static String stringFilter(String str) {
        str = str.replaceAll("【", "[").replaceAll("】", "]")
                .replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
        String regEx = "[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

}
