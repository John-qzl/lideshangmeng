package com.lidegou.lideshangmeng.mobile.fragment.SearchCommodity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.adapter.TabFragmentAdapter;
import com.lidegou.lideshangmeng.mobile.data.entity.Classify;
import com.lidegou.lideshangmeng.mobile.fragment.Commodity.CommodityDetailsActivity;
import com.lidegou.lideshangmeng.mobile.fragment.Search.SearchAllActivity;
import com.lidegou.lideshangmeng.mobile.fragment.SearchCommodity.fragment.FragmentOne;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.fragment.FragmentFour;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.fragment.FragmentThree;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.fragment.FragmentTwo;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.util.dialog.LoadingDialog;
import com.lidegou.lideshangmeng.mobile.util.view.TabViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索商品
 * 2016-12-07
 */
public class SearchCommodityActivity extends BaseActivity implements SearchCommodityContract.View, FragmentOne.FragmentOneCallBack, FragmentOne.ProvinceCallBackCallBack, FragmentTwo.ProvinceIDCallBackCallBack, FragmentThree.CityIDCallBackCallBack, FragmentFour.AreaIDCallBackCallBack, AdapterView.OnItemClickListener {

    //推荐商品
    List<Classify.Products> productsListlist = new ArrayList<>();
    private PullToRefreshScrollView scrollView;
    private ListView listView;
    private GridView gridView;
    private SearchCommodityAdapter listAdapter;
    private SearchCommodityAdapter gridAdapter;
    private boolean isExid = true;
    private ImageView list_and_grid;
    private SlidingMenu slidingMenu;
    private int catID;
    private SearchCommodityContract.Presenter presenter;
    private String sort = "good_id";//综合
    private String order = "DESC";//升降
    private String isself = "0";//自营
    private String hasgoods = "0";//仅看有货
    private String promotion = "0";//促销
    private String priceMin = "0";//价格最低
    private String priceMax = "0";//价格最高
    private String brandId = "0";//品牌

    private Button search_rbn_index, search_rbn_pio, search_rbn_sales, search_rbn_price, search_rbn_screen;
    private ImageView index_arrow_color, price_arrow_color;
    private boolean indexbool, pricebool = true;
    private int page = 1;
    private String typeSelete;
    private String keyword;

    private LoadingDialog loadingDialog;
    private TabViewPager menuViewPager;
    public List<BaseFragment> fragmentList;
    private String shopsid = "0";

    private String province, city, area;
    private int provinceid, cityid, areaid;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_commodity;
    }

    protected void init() {
        loadingDialog = new LoadingDialog(this);
        catID = getIntent().getIntExtra("catID", 0);
        typeSelete = getIntent().getStringExtra("typeSelete");
        keyword = getIntent().getStringExtra("keyword");
        findViewById(R.id.backs).setOnClickListener(this);
        TextView tv_sousuo = (TextView) findViewById(R.id.tv_sousuo);
        if (keyword != null) {
            tv_sousuo.setText(keyword);
        }
        tv_sousuo.setOnClickListener(this);
        findViewById(R.id.im_suosou).setOnClickListener(this);
        index_arrow_color = (ImageView) findViewById(R.id.index_arrow_color);
        price_arrow_color = (ImageView) findViewById(R.id.price_arrow_color);
        search_rbn_index = (Button) findViewById(R.id.search_rbn_index);
        search_rbn_pio = (Button) findViewById(R.id.search_rbn_pio);
        search_rbn_sales = (Button) findViewById(R.id.search_rbn_sales);
        search_rbn_price = (Button) findViewById(R.id.search_rbn_price);
        search_rbn_screen = (Button) findViewById(R.id.search_rbn_screen);
        list_and_grid = (ImageView) findViewById(R.id.list_and_grid);
        list_and_grid.setOnClickListener(this);
        search_rbn_index.setOnClickListener(this);
        search_rbn_pio.setOnClickListener(this);
        search_rbn_sales.setOnClickListener(this);
        search_rbn_price.setOnClickListener(this);
        search_rbn_screen.setOnClickListener(this);
        scrollView = (PullToRefreshScrollView) findViewById(R.id.scrollview);
        scrollView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listView = (ListView) findViewById(R.id.listView);
        gridView = (GridView) findViewById(R.id.gridView);
        listAdapter = new SearchCommodityAdapter(this, false);
        gridAdapter = new SearchCommodityAdapter(this, true);
        listView.setAdapter(listAdapter);
        gridView.setAdapter(gridAdapter);
        listView.setOnItemClickListener(this);
        gridView.setOnItemClickListener(this);
        setRefreshListener();

        SearchGoods();
        sildindmenu();
        if (presenter == null) {
            presenter = new SearchCommodityPresenter(this);
        }
        presenter.start();
    }


    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.backs:
                finish();
                break;
            case R.id.list_and_grid:
                if (isExid) {
                    isExid = false;
                    SearchGoods();
                    list_and_grid.setImageResource(R.drawable.list);
                } else {
                    isExid = true;
                    SearchGoods();
                    list_and_grid.setImageResource(R.drawable.grid);
                }
                break;
            case R.id.search_rbn_index:
                backColor();
                search_rbn_index.setTextColor(Color.parseColor("#FF4949"));
                sort = "good_id";
                if (indexbool) {
                    index_arrow_color.setImageResource(R.drawable.down_arrow_color);
                    indexbool = false;
                    order = "DESC";
                } else {
                    index_arrow_color.setImageResource(R.drawable.up_arrow_color);
                    indexbool = true;
                    order = "ASC";
                }
                presenter.getRefresh();
                break;
            case R.id.search_rbn_pio:
                backColor();
                search_rbn_pio.setTextColor(Color.parseColor("#FF4949"));
                sort = "last_update";
                presenter.getRefresh();
                break;
            case R.id.search_rbn_sales:
                backColor();
                search_rbn_sales.setTextColor(Color.parseColor("#FF4949"));
                sort = "sales_volume";
                presenter.getRefresh();
                break;
            case R.id.search_rbn_price:
                backColor();
                search_rbn_price.setTextColor(Color.parseColor("#FF4949"));
                sort = "shop_price";
                if (pricebool) {
                    price_arrow_color.setImageResource(R.drawable.down_arrow_color);
                    pricebool = false;
                    order = "DESC";
                } else {
                    price_arrow_color.setImageResource(R.drawable.up_arrow_color);
                    pricebool = true;
                    order = "ASC";
                }
                presenter.getRefresh();
                break;
            case R.id.search_rbn_screen:
                slidingMenu.showMenu();//打开侧滑
                break;
            case R.id.tv_sousuo:
                Intent intent = new Intent(getContext(), SearchAllActivity.class);
                intent.putExtra("keyword", keyword);
                startActivity(intent);
                break;
        }
    }


    //推荐商品
    public void SearchGoods() {
        if (isExid) {
            gridView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
            gridAdapter.setData(productsListlist);
            gridAdapter.notifyDataSetChanged();
        } else {
            gridView.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            listAdapter.setData(productsListlist);
            listAdapter.notifyDataSetChanged();
        }
    }

    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;
    private FragmentFour fragmentFour;

    public void sildindmenu() {
        slidingMenu = new SlidingMenu(SearchCommodityActivity.this);
        slidingMenu.setMode(SlidingMenu.RIGHT);
        slidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_offset);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        slidingMenu.setFadeEnabled(true);//是否使用侧滑渐入渐出效果
        slidingMenu.setOffsetFadeDegree(0.4f);
        slidingMenu.attachToActivity(SearchCommodityActivity.this, SlidingMenu.SLIDING_CONTENT);
        //slidingMenu.setBehindScrollScale(0);//改变效果
        slidingMenu.setMenu(R.layout.slidingmenu);


        menuViewPager = (TabViewPager) slidingMenu.findViewById(R.id.menu_view_pager_good);
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
            fragmentOne = new FragmentOne(this, this);
            fragmentTwo = new FragmentTwo(this);
            fragmentThree = new FragmentThree(this);
            fragmentFour = new FragmentFour(this);
            fragmentList.add(fragmentOne);
            fragmentList.add(fragmentTwo);
            fragmentList.add(fragmentThree);
            fragmentList.add(fragmentFour);
        }
        TabFragmentAdapter tabFragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragmentList);
        menuViewPager.setAdapter(tabFragmentAdapter);

    }

    @Override
    public String page() {
        return page + "";
    }

    @Override
    public String brand() {
        return brandId;
    }

    @Override
    public String price_min() {
        return priceMin + "";
    }

    @Override
    public String price_max() {
        return priceMax + "";
    }

    @Override
    public String filter_attr() {
        return 0 + "";
    }

    @Override
    public String sort() {
        return sort;
    }

    @Override
    public String order() {
        return order;
    }

    @Override
    public String keyword() {
        if (keyword == null) {
            return "";
        } else {
            return keyword;
        }
    }

    @Override
    public String isself() {
        return isself;
    }

    @Override
    public String size() {
        return 10 + "";
    }

    @Override
    public String id() {
        return catID + "";
    }

    @Override
    public String hasgoods() {
        return hasgoods;
    }

    @Override
    public String promotion() {
        return promotion;
    }

    @Override
    public String typeSelect() {
        return typeSelete;
    }

    @Override
    public void callbackProductsSuccess(List<Classify.Products> productsList) {
        for (Classify.Products products : productsList) {
            for (Classify.Products products1 : productsListlist) {
                if (products.getGoods_id().equals(products1.getGoods_id())) {
                    showToast("暂无更多");
                    loadingDialog.dismiss();
                    return;
                }
            }
            Classify.Products products1 = new Classify.Products();
            products1.setGoods_id(products.getGoods_id());
            products1.setGoods_name(products.getGoods_name());
            products1.setOrg_price(products.getOrg_price());
            products1.setGoods_img(products.getGoods_img());
            products1.setIs_promote(products.getIs_promote());
            products1.setGoods_number(products.getGoods_number());
            products1.setSales_volume(products.getSales_volume());
            products1.setShop_price(products.getShop_price());
            products1.setMarket_price(products.getMarket_price());
            productsListlist.add(products1);
        }
        if (isExid) {
            gridAdapter.setData(productsListlist);
            gridAdapter.notifyDataSetChanged();
        } else {
            listAdapter.setData(productsListlist);
            listAdapter.notifyDataSetChanged();
        }
        if (productsList.size() <= 0) {
            showToast("暂无更多");
        }
        loadingDialog.dismiss();
    }

    @Override
    public void callbackRefreshProductsSuccess(List<Classify.Products> productsList) {
        if (isExid) {
            gridAdapter.setData(productsList);
            gridAdapter.notifyDataSetChanged();
        } else {
            listAdapter.setData(productsList);
            listAdapter.notifyDataSetChanged();
        }
        this.productsListlist.addAll(productsList);
        loadingDialog.dismiss();
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    public void backColor() {
        search_rbn_index.setTextColor(Color.parseColor("#000000"));
        index_arrow_color.setImageResource(R.drawable.down_arrow);
        search_rbn_pio.setTextColor(Color.parseColor("#000000"));
        search_rbn_sales.setTextColor(Color.parseColor("#000000"));
        search_rbn_price.setTextColor(Color.parseColor("#000000"));
        price_arrow_color.setImageResource(R.drawable.down_arrow);
    }

    @Override
    public void confirmCallBack(String msg) {
        if (msg.equals("yes")) {
            productsListlist.clear();
            slidingMenu.toggle(false);
            loadingDialog.show("");
            search_rbn_screen.setTextColor(Color.parseColor("#ff0000"));
            presenter.getRefresh();
        } else {
            slidingMenu.toggle(false);
            search_rbn_screen.setTextColor(Color.parseColor("#000000"));
        }
    }

    @Override
    public void IsselfCallBack(String isself) {
        this.isself = isself;
    }

    @Override
    public void OnlyCallBack(String hasgoods) {
        this.hasgoods = hasgoods;
    }

    @Override
    public void PromotionCallBack(String promotion) {
        this.promotion = promotion;
    }

    @Override
    public void PriceMinCallBack(String min) {
        this.priceMin = min;
    }

    @Override
    public void PriceMaxCallBack(String max) {
        this.priceMax = max;
    }

    @Override
    public void BrandCallBack(String brandId) {
        this.brandId = brandId;
    }

    @Override
    public void provinceCallBack(int data) {
        menuViewPager.setCurrentItem(data);
    }


    @Override
    public void provinceIDCallBack(int id, String name) {
        menuViewPager.setCurrentItem(2);
        fragmentThree.setData(id);
        province = name;
        provinceid = id;
    }

    @Override
    public void provinceCancel() {
        menuViewPager.setCurrentItem(0, false);
    }

    @Override
    public void cityIDCallBack(int id, String name) {
        menuViewPager.setCurrentItem(3);
        fragmentFour.setData(id);
        city = name;
        cityid = id;
    }

    @Override
    public void cityConfirm() {
        productsListlist.clear();
        city = "";
        area = "";
        cityid = 0;
        areaid = 0;
        slidingMenu.toggle(false);
        fragmentOne.setAddress(province, city, area);
        presenter.getProducts();
    }

    @Override
    public void cityCancel() {
        menuViewPager.setCurrentItem(1, false);
    }

    @Override
    public void areaIDCallBack(int id, String name) {
        menuViewPager.setCurrentItem(0, false);
        areaid = id;
        area = name;
        fragmentOne.setAddress(province, city, area);

    }

    @Override
    public void areaConfirm() {
        productsListlist.clear();
        menuViewPager.setCurrentItem(0, false);
        area = "";
        areaid = 0;
        slidingMenu.toggle(false);
        fragmentOne.setAddress(province, city, area);
        presenter.getProducts();
    }

    @Override
    public void areaCancel() {
        menuViewPager.setCurrentItem(2, false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Classify.Products products = productsListlist.get(position);
        Intent intent = new Intent(SearchCommodityActivity.this, CommodityDetailsActivity.class);
        intent.putExtra("goodid", products.getGoods_id());
        startActivity(intent);
    }


    private void setRefreshListener() {
        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
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
                }, 500);
                page++;
                presenter.getProducts();
            }
        });
    }

}
