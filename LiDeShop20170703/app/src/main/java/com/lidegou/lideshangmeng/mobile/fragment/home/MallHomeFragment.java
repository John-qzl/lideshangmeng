package com.lidegou.lideshangmeng.mobile.fragment.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.jiongbull.jlog.JLog;
import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.adapter.MallHomeAdapter;
import com.lidegou.lideshangmeng.mobile.adapter.MallHomeGoodAdapter;
import com.lidegou.lideshangmeng.mobile.adapter.MallHomeMyLikeAdapter;
import com.lidegou.lideshangmeng.mobile.data.entity.Announcement;
import com.lidegou.lideshangmeng.mobile.data.entity.Banner;
import com.lidegou.lideshangmeng.mobile.data.entity.Commodity;
import com.lidegou.lideshangmeng.mobile.data.entity.HomeClassify;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsIn;
import com.lidegou.lideshangmeng.mobile.data.entity.MyLike;
import com.lidegou.lideshangmeng.mobile.data.entity.ShopsStore;
import com.lidegou.lideshangmeng.mobile.event.OnScrollListener;
import com.lidegou.lideshangmeng.mobile.fragment.Commodity.CommodityDetailsActivity;
import com.lidegou.lideshangmeng.mobile.fragment.My.attention.MyAttentionActivity;
import com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin.MerchantsInFiveActivity;
import com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin.MerchantsInFourActivity;
import com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin.MerchantsInOneActivity;
import com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin.MerchantsInThreeActivity;
import com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin.MerchantsInTwoActivity;
import com.lidegou.lideshangmeng.mobile.fragment.Search.SearchAllActivity;
import com.lidegou.lideshangmeng.mobile.fragment.SearchCommodity.SearchCommodityActivity;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.MallStoreStreetAdapter;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.details.ShopsStoreDetailsActivity;
import com.lidegou.lideshangmeng.mobile.fragment.home.aera.AeraActivity;
import com.lidegou.lideshangmeng.mobile.fragment.home.web.WebViewActivity;
import com.lidegou.lideshangmeng.mobile.ui.MallHomeActivity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.ui.login.LoginActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.chongzhi.RechargeActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.tixian.WithdrawalActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.notice.MineNoticeActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.order.OrderListActivity;
import com.lidegou.lideshangmeng.mobile.util.view.UPMarqueeView;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Y on 2016/12/1.
 */
@SuppressLint("ValidFragment")
public class MallHomeFragment extends BaseFragment implements MallHomeContract.View, OnScrollListener, PullToRefreshBase.OnRefreshListener2<ScrollView> {
    //Handler
    private HomeHandler handler;
    private MallHomePresenter presenter;

    private PullToRefreshScrollView scrollView;

    //轮播图
    private int bannerPosition = 0;
    private ViewPager vpBannder;
    private MallHomeBannerAdapter bannerAdapter;
    //轮播图标识
    private LinearLayout llBannerMark;

    //分类
    private List<HomeClassify> classifyList;
    private MallHomeAdapter mallHomeAdapter;
    private GridView gvClassify;

    //公告
    private List<Announcement> announcementList = new ArrayList<>();

    //猜你喜欢
    private GridView gvMylike;
    private MallHomeMyLikeAdapter mallHomeMyLikeAdapter;

    //推荐商铺
    private GridView gvRecommended;
    private MallStoreStreetAdapter mallHomeShopsStoreAdapter;

    //推荐商品
    private GridView gvGood;
    private MallHomeGoodAdapter mallHomeGoodAdapter;
    boolean isRefreshing = false;

    private EditText ed_search;
    //置顶
    private ImageView iv_fragmenthome_goTop;

    private Integer page = 1;
    private Integer likePage = 1;

    List<View> views = new ArrayList<>();
    private TextView tv1, tv2;

    private TextView tv_iv_batch;
    private ImageView iv_batch;

    private List<Commodity.Data> allCommodityList = new ArrayList<>();
    private Commodity commodity;

    //上拉
    PullToRefreshScrollView pullToRefreshScrollView;
    int height;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mall_home;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void init() {
        //加载数据
        if (presenter == null) {
            presenter = new MallHomePresenter(this);
        }
        presenter.start();


        if (handler == null) {
            handler = new HomeHandler(this);
            handler.sendEmptyMessage(HomeHandler.WHAT_INIT_DATE);
        }
        iv_fragmenthome_goTop = (ImageView) getRootView().findViewById(R.id.iv_fragmenthome_goTop);
        iv_fragmenthome_goTop.setOnClickListener(this);
        scrollView = (PullToRefreshScrollView) getRootView().findViewById(R.id.scrollView);
        scrollView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        scrollView.setOnRefreshListener(this);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (scrollView.getRefreshableView().getScrollY() == 0) {
                            iv_fragmenthome_goTop.setVisibility(View.GONE);
                        } else {
                            iv_fragmenthome_goTop.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        }, 100, 100);
        tv_iv_batch = (TextView) getRootView().findViewById(R.id.tv_iv_batch);
        iv_batch = (ImageView) getRootView().findViewById(R.id.iv_batch);
        iv_batch.setOnClickListener(this);
        tv_iv_batch.setOnClickListener(this);

        ed_search = (EditText) getRootView().findViewById(R.id.ed_search);
        ed_search.setFocusableInTouchMode(false);
        ed_search.setOnClickListener(this);
        gvClassify = (GridView) getRootView().findViewById(R.id.gv_classify);
        //分类信息列表回调
        if (mallHomeAdapter == null) {
            classifyList = new ArrayList<>();
            classifyList.add(new HomeClassify(1, R.drawable.fenlei, "全部分类", 1001));
            classifyList.add(new HomeClassify(2, R.drawable.dianpu, "店铺街", 1002));
            classifyList.add(new HomeClassify(3, R.drawable.ruzhu, "我要入驻", 1003));
            classifyList.add(new HomeClassify(4, R.drawable.geren, "个人中心", 1004));
            classifyList.add(new HomeClassify(5, R.drawable.guanzhu, "我的关注", 1005));
            classifyList.add(new HomeClassify(6, R.drawable.dingdan, "我的订单", 1006));
            classifyList.add(new HomeClassify(7, R.drawable.zhongzhi, "充值", 1007));
            classifyList.add(new HomeClassify(8, R.drawable.tixian, "换购", 1008));
            mallHomeAdapter = new MallHomeAdapter(getActivity(), classifyList);
            gvClassify.setAdapter(mallHomeAdapter);
            gvClassify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //presenter.itemClickClassify(i);
                    classItemListener(i);
                }
            });
        } else {
            mallHomeAdapter.notifyDataSetChanged();
        }

        vpBannder = (ViewPager) getRootView().findViewById(R.id.vp_banner);
        llBannerMark = (LinearLayout) getRootView().findViewById(R.id.ll_banner_mark);
        gvGood = (GridView) getRootView().findViewById(R.id.gv_good);
        getRootView().findViewById(R.id.ll_diqu).setOnClickListener(this);
        getRootView().findViewById(R.id.ll_message).setOnClickListener(this);
    }

    public void start() {
        Animation rIn = new RotateAnimation(0f, +360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rIn.setDuration(500);
        rIn.setFillAfter(true);
        iv_batch.startAnimation(rIn);
        likePage++;
        presenter.mylike();
    }

    private void initData() {
        UPMarqueeView upMarqueeView = (UPMarqueeView) getRootView().findViewById(R.id.jdadver);
        setView();
        upMarqueeView.setViews(views);
    }

    /**
     * 初始化需要循环的View
     * 为了灵活的使用滚动的View，所以把滚动的内容让用户自定义
     * 假如滚动的是三条或者一条，或者是其他，只需要把对应的布局，和这个方法稍微改改就可以了，
     */
    private void setView() {
        for (int i = 0; i < announcementList.size(); i = i + 2) {
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.item_announcement, null);
            //初始化布局的控件
            tv1 = (TextView) moreView.findViewById(R.id.title1);
            tv1.setPadding(5, 5, 5, 5);
            tv2 = (TextView) moreView.findViewById(R.id.title2);
            tv2.setPadding(5, 5, 5, 5);
            //进行对控件赋值
            tv1.setText(announcementList.get(i).getTitle() + "");
            if (announcementList.size() > i + 1) {
                //因为淘宝那儿是两条数据，但是当数据是奇数时就不需要赋值第二个，所以加了一个判断，还应该把第二个布局给隐藏掉
                tv2.setText(announcementList.get(i + 1) + "");
            } else {
                moreView.findViewById(R.id.rl2).setVisibility(View.GONE);
            }
            //添加到循环滚动数组里面去
            views.add(moreView);
            final int finalI = i;
            tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("url", announcementList.get(finalI).getUrl());
                    startActivity(intent);
                }
            });
            tv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("url", announcementList.get(finalI + 1).getUrl());
                    startActivity(intent);
                }
            });
        }
    }


    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.ed_search:
                startActivity(new Intent(getContext(), SearchAllActivity.class));
                break;
            case R.id.iv_batch:
                start();
                break;
            case R.id.tv_iv_batch:
                start();
                break;
            case R.id.ll_diqu:
                if (Config.User.STATUS) {
                    startActivity(new Intent(getActivity(), AeraActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.ll_message:
                if (Config.User.STATUS) {
                    startActivity(new Intent(getActivity(), MineNoticeActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.iv_fragmenthome_goTop:
                ScrollView scrollView1 = scrollView.getRefreshableView();
                scrollView1.fullScroll(ScrollView.FOCUS_UP);
                break;
        }
    }


    @Override
    protected void lazyLoad() {
        //初始化Presenter
    }

    //点击分类
    public void classItemListener(int position) {
        if (position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7) {
            if (!Config.User.STATUS) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                showToast("请先登陆");
                return;
            }
        }
        MallHomeActivity homeActivity = (MallHomeActivity) getActivity();
        HomeClassify classify = classifyList.get(position);
        if (classify.getId() == 1) {
            homeActivity.changpage(1);
        } else if (classify.getId() == 2) {
            homeActivity.changpage(2);
        } else if (classify.getId() == 3) {
            presenter.MerchantsInCheck();
        } else if (classify.getId() == 4) {
            homeActivity.changpage(4);
        } else if (classify.getId() == 5) {
            startActivity(new Intent(getContext(), MyAttentionActivity.class));
        } else if (classify.getId() == 6) {
            Intent intent = new Intent(getContext(), OrderListActivity.class);
            intent.putExtra("state", 0);
            startActivity(intent);
        } else if (classify.getId() == 7) {
            startActivity(new Intent(getContext(), RechargeActivity.class));
        } else if (classify.getId() == 8) {
            startActivity(new Intent(getContext(), WithdrawalActivity.class));
        }
    }


    @Override
    public String page() {
        return page + "";
    }

    @Override
    public String likePage() {
        return likePage + "";
    }


    @Override
    public void callbackBanner(final List<Banner> bannerList) {
        handler.sendEmptyMessage(HomeHandler.WHAT_CLOSE_REFRESH);

        //清空轮播图标识
        if (llBannerMark.getChildCount() > 0) {
            llBannerMark.removeAllViews();
        }
        //添加轮播图标识
        for (int i = 0; i < bannerList.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            if (i != 0) {
                params.leftMargin = (int) (5 * getMetrics().density);
                imageView.setBackgroundResource(R.drawable.view_unfocus);
            } else {
                imageView.setBackgroundResource(R.drawable.view_focus);
            }
            imageView.setLayoutParams(params);
            llBannerMark.addView(imageView);
        }
        //创建适配器
        if (bannerAdapter == null) {
            //将数据传入。
            bannerAdapter = new MallHomeBannerAdapter(getContext(), bannerList);
            //回调监听。
            bannerAdapter.setOnBannerClickListener(new MallHomeBannerAdapter.OnBannerClickListener() {
                @Override
                public void itemClickBanner(View view, int position) {
                    //回调监听得到position后获取对应的数据。
                    Banner banner = bannerList.get(position);
                    String link = banner.getAd_link();
                    if (link.equals("")) {
                        return;
                    }
                    if (link.contains("cat-")) {
                        String data = link.replace("cat-", "");
                        Intent intent = new Intent();
                        intent.setClass(getContext(), SearchCommodityActivity.class);
                        intent.putExtra("catID", data);
                        intent.putExtra("typeSelete", "2");
                        startActivity(intent);
                    } else if (link.contains("goods-")) {
                        String data = link.replace("goods-", "");
                        Intent intent = new Intent(getActivity(), CommodityDetailsActivity.class);
                        intent.putExtra("goodid", data);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("url", link);
                        startActivity(intent);
                    }
//                    1.跳转到分类 2.跳转到商品 3.跳转到浏览器  ad_link

                }
            });
            vpBannder.setAdapter(bannerAdapter);
            //简体轮播图滑动
            vpBannder.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageScrollStateChanged(int state) {
                    if (state != 2) {
                        return;
                    }
                    for (int i = 0; i < llBannerMark.getChildCount(); i++) {
                        View view = llBannerMark.getChildAt(i);
                        if (view != null) {
                            ((ImageView) view).setImageResource(R.drawable.view_focus);
                        }
                    }
                    View view = llBannerMark.getChildAt(vpBannder.getCurrentItem());
                    if (view != null) {
                        ((ImageView) view).setImageResource(R.drawable.view_unfocus);
                    }
                }
            });
            new Thread(bannerAutoNext).start();
        } else {
            bannerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void callbackAnnouncementSuccess(List<Announcement> announcementList) {
        this.announcementList = announcementList;
        initData();//公告
    }

    @Override
    public void callbackShopsSuccess(List<ShopsStore> shopsStoreList) {
        httputilRecommended(shopsStoreList);
    }

    //推荐商品
    @Override
    public void callbackCommoditySuccess(Commodity commodity) {
        if (commodity == null || commodity.getData() == null) {
            return;
        }
        page++;
        this.commodity = commodity;
        if (mallHomeGoodAdapter == null) {
            allCommodityList.addAll(commodity.getData());
            mallHomeGoodAdapter = new MallHomeGoodAdapter(getContext(), allCommodityList);
            gvGood.setAdapter(mallHomeGoodAdapter);
            gvGood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Commodity.Data data = allCommodityList.get(position);
                    Intent intent = new Intent(getActivity(), CommodityDetailsActivity.class);
                    intent.putExtra("goodid", data.getGoods_id());
                    startActivity(intent);
                }
            });
        } else {
            allCommodityList.addAll(commodity.getData());
            mallHomeGoodAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void callbackMyLikeSuccess(List<MyLike> myLikeList) {
        httputilMyLike(myLikeList);
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


    /**
     * 轮播图自动切换
     */
    private Runnable bannerAutoNext = new Runnable() {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    JLog.e((e == null || e.getMessage() == null) ? "Exception is null" : e.getMessage());
                }

                handler.sendEmptyMessage(HomeHandler.WHAT_BANNER_NEXT);
            }
        }

    };

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    //猜你喜欢
    public void httputilMyLike(final List<MyLike> myLikeList) {
        gvMylike = (GridView) getRootView().findViewById(R.id.gv_mylike);
        mallHomeMyLikeAdapter = new MallHomeMyLikeAdapter(getContext(), myLikeList);
        gvMylike.setAdapter(mallHomeMyLikeAdapter);
        gvMylike.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyLike myLike = myLikeList.get(position);
                Intent intent = new Intent(getActivity(), CommodityDetailsActivity.class);
                intent.putExtra("goodid", myLike.getGoods_id());
                startActivity(intent);

            }
        });
    }


    //推荐商铺
    public void httputilRecommended(final List<ShopsStore> shopsStoreList) {
        gvRecommended = (GridView) getRootView().findViewById(R.id.gv_recommended);
        mallHomeShopsStoreAdapter = new MallStoreStreetAdapter(getContext(), shopsStoreList);
        gvRecommended.setAdapter(mallHomeShopsStoreAdapter);
        gvRecommended.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShopsStore shopsStore = shopsStoreList.get(position);
                Intent intent = new Intent(getContext(), ShopsStoreDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("shopsStore", shopsStore);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onScrollListenner(int top) {
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {

    }

    @Override
    public void onPullUpToRefresh(final PullToRefreshBase<ScrollView> refreshView) {
        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshView.onRefreshComplete();
            }
        }, 1);
        if (commodity == null || page < commodity.getTotalPage()) {
            presenter.commodity();
        } else {
            showToast("没有更多数据");
        }
    }

    public static class HomeHandler extends Handler {
        private final WeakReference<MallHomeFragment> reference;

        public HomeHandler(MallHomeFragment fragment) {
            reference = new WeakReference<>(fragment);
        }

        public static final int WHAT_CLOSE_REFRESH = 21;

        public static final int WHAT_BANNER_NEXT = 22;

        public static final int WHAT_INIT_DATE = 23;

        public static final int WHAT_INIT_TO_UP = 24;

        public static final int WHAT_WHILE_TODAY_RECOM = 12;

        public static final int DELAY_TODAY_RECOM_TIME = 10;

        private SimpleDateFormat dateFormat;

        @Override
        public void handleMessage(Message msg) {
            if (reference.get() == null) {
                return;
            }
            switch (msg.what) {
                case WHAT_CLOSE_REFRESH:
//                    if (reference.get().srlRefresh.isRefreshing()) {
//                        reference.get().srlRefresh.setRefreshing(false);
//                    }
                    break;
                case WHAT_BANNER_NEXT:
                    if (reference.get().bannerAdapter.getCount() <= 0) {
                        return;
                    }
                    reference.get().bannerPosition++;
                    if (reference.get().bannerPosition >= reference.get().bannerAdapter.getCount()) {
                        reference.get().bannerPosition = 0;
                    }
                    reference.get().vpBannder.setCurrentItem(reference.get().bannerPosition);
                    break;

            }
        }

    }


    @Override
    public void onDestroyView() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        super.onDestroyView();
    }


}