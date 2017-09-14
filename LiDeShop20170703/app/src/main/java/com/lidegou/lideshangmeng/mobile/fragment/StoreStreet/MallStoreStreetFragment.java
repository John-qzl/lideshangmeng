package com.lidegou.lideshangmeng.mobile.fragment.StoreStreet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.adapter.MallStoreStreetAdapter;
import com.lidegou.lideshangmeng.mobile.adapter.TabFragmentAdapter;
import com.lidegou.lideshangmeng.mobile.data.entity.Place;
import com.lidegou.lideshangmeng.mobile.data.entity.ShopsStore;
import com.lidegou.lideshangmeng.mobile.fragment.Search.SearchAllActivity;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.details.ShopsStoreDetailsActivity;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.fragment.FragmentFour;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.fragment.FragmentOne;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.fragment.FragmentThree;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.fragment.FragmentTwo;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.ui.login.LoginActivity;
import com.lidegou.lideshangmeng.mobile.util.view.TabViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Y on 2016/12/1.
 * 商铺
 */
public class MallStoreStreetFragment extends BaseFragment implements MallStoreStreetContract.View, MallStoreStreetTabsAdapter.OnClickListener, FragmentOne.FragmentOneCallBack, FragmentOne.ProvinceCallBackCallBack, FragmentTwo.ProvinceIDCallBackCallBack, FragmentThree.CityIDCallBackCallBack, FragmentFour.AreaIDCallBackCallBack, MallStoreStreetAdapter.IGotoCallBack, PullToRefreshBase.OnRefreshListener2<ListView> {
    //推荐商品
    private TextView tv_sousuo;
    private PullToRefreshListView listView;
    private MallStoreStreetAdapter mallStoreStreetAdapter;
    private SlidingMenu slidingMenu;
    private MallStoreStreetContract.Presenter presenter;
    private String type = "1";
    private ImageView index_arrow_color, price_arrow_color;
    private int page = 1;

    private MallStoreStreetTabsAdapter mallStoreStreetTabsAdapter;
    private GridView gr_tabs;
    private List<ShopsStore.Tabs> tabsList = new ArrayList<>();
    List<ShopsStore> shopsStoreList1 = new ArrayList<>();

    private TabViewPager menu_view_pager;
    public List<BaseFragment> fragmentList;
    private String shopsid = "0";

    private String province, city, area;
    private int provinceid, cityid, areaid;

    public LocationClient mLocationClient = null;
    public MyLocationListener myListener = new MyLocationListener();
    private double mylatitude = -1;
    private double myLongitude = -1;

    private boolean isLoc = false;
    private boolean isOnResume = false;
    private boolean isLaz = false;
    private boolean isClear = false;

    private boolean isFirst = true;

    private String shopUid;

    private String keyword = "";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mall_storestreet;
    }


    public MallStoreStreetFragment() {

    }

    public MallStoreStreetFragment(String keyword) {
        this.keyword = keyword;
    }

    @Override
    protected void init() {
        initLocation();
        tv_sousuo = (TextView) getRootView().findViewById(R.id.tv_sousuo);
        tv_sousuo.setOnClickListener(this);
        tv_sousuo.setText(keyword);
        getRootView().findViewById(R.id.search_rbn_screen).setOnClickListener(this);
        index_arrow_color = (ImageView) getRootView().findViewById(R.id.index_arrow_color);
        price_arrow_color = (ImageView) getRootView().findViewById(R.id.price_arrow_color);
        gr_tabs = (GridView) getRootView().findViewById(R.id.gr_tabs);

        listView = (PullToRefreshListView) getRootView().findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listView.setOnRefreshListener(this);

        mallStoreStreetAdapter = new MallStoreStreetAdapter(getActivity());
        mallStoreStreetAdapter.setGotoCallBack(this);
        mallStoreStreetAdapter.setData(shopsStoreList1);
        listView.setAdapter(mallStoreStreetAdapter);

        sildindmenu();

        if (presenter == null) {
            presenter = new MallStoreStreetPresenter(this);
        }
        presenter.start();
    }


    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
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


    @Override
    protected void lazyLoad() {
        Log.i("shopstore", "lazyLoad");
        if (Config.User.STATUS) {
            if (isFirst) {
                provinceid = -1;
                cityid = -1;
                areaid = -1;
                presenter.getNowPlace();
                isFirst = false;
            }
            if (presenter == null) {
                presenter = new MallStoreStreetPresenter(this);
            }
            page = 1;
            isLaz = true;
            presenter.getStore();

        } else if (!Config.User.STATUS) {
            for (ShopsStore shopsStore : shopsStoreList1) {
                shopsStore.setGaze_status("0");
            }
            mallStoreStreetAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Config.User.STATUS && isFirst) {
            if (presenter == null) {
                presenter = new MallStoreStreetPresenter(this);
            }
            provinceid = -1;
            cityid = -1;
            areaid = -1;
            page = 1;
            isOnResume = true;
            presenter.getStore();
            presenter.getNowPlace();
            isFirst = false;
        } else if (!Config.User.STATUS) {
            for (ShopsStore shopsStore : shopsStoreList1) {
                shopsStore.setGaze_status("0");
            }
            mallStoreStreetAdapter.notifyDataSetChanged();
            isFirst = true;
        }
    }

    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;
    private FragmentFour fragmentFour;

    public void sildindmenu() {
        slidingMenu = new SlidingMenu(getContext());
        slidingMenu.setMode(SlidingMenu.RIGHT);
        slidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_offset);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        slidingMenu.setFadeEnabled(true);//是否使用侧滑渐入渐出效果
        slidingMenu.setOffsetFadeDegree(0.4f);
        slidingMenu.attachToActivity(getActivity(), SlidingMenu.SLIDING_CONTENT);
        //slidingMenu.setBehindScrollScale(0);//改变效果
        slidingMenu.setMenu(R.layout.shopslidingmenu);

        menu_view_pager = (TabViewPager) slidingMenu.findViewById(R.id.menu_view_pager);
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
        TabFragmentAdapter tabFragmentAdapter = new TabFragmentAdapter(getFragmentManager(), fragmentList);
        menu_view_pager.setOffscreenPageLimit(fragmentList.size());
        menu_view_pager.setAdapter(tabFragmentAdapter);

    }


    @Override
    public String page() {
        return page + "";
    }

    @Override
    public String keyword() {
        return keyword;
    }

    @Override
    public String id() {
        return shopsid;
    }

    @Override
    public String province_id() {
        return provinceid + "";
    }

    @Override
    public String city_id() {
        return cityid + "";
    }

    @Override
    public String district_id() {
        return areaid + "";
    }

    @Override
    public String longitude() {
        if (myLongitude == -1) {
            return "";
        }
        return myLongitude + "";
    }

    @Override
    public String latitude() {
        if (mylatitude == -1) {
            return "";
        }
        return mylatitude + "";
    }

    @Override

    public String type() {
        return type;
    }


    @Override
    public void callbackStoreSuccess(List<ShopsStore> shopsStoreList) {
        page++;
        if (isClear) {
            shopsStoreList1.clear();
            shopsStoreList1.addAll(shopsStoreList);
            page = 2;
            isClear = false;
        } else if (isLoc) {
            shopsStoreList1.clear();
            shopsStoreList1.addAll(shopsStoreList);
            page = 2;
            isLoc = false;
        } else if (isOnResume) {
            shopsStoreList1.clear();
            shopsStoreList1.addAll(shopsStoreList);
            page = 2;
            isOnResume = false;
        } else if (isLaz) {
            shopsStoreList1.clear();
            shopsStoreList1.addAll(shopsStoreList);
            page = 2;
            isLaz = false;
        } else {
            shopsStoreList1.addAll(shopsStoreList);
        }
        mallStoreStreetAdapter.setData(shopsStoreList1);
        Log.i("shopsStoreList1", "shopsStoreList1 size = " + shopsStoreList1.size());
        mallStoreStreetAdapter.notifyDataSetChanged();
    }

    @Override
    public void callbackTabsSuccess(List<ShopsStore.Tabs> tabsList) {
        mallStoreStreetTabsAdapter = new MallStoreStreetTabsAdapter(getContext(), tabsList);
        mallStoreStreetTabsAdapter.setOnClickListener(this);
        gr_tabs.setAdapter(mallStoreStreetTabsAdapter);
        this.tabsList = tabsList;
    }

    @Override
    public void callbackFocusShopsSuccess(String msg) {
        showToast(msg);
        for (ShopsStore shopsStore : shopsStoreList1) {
            if (shopsStore.getUser_id().equals(shopUid)) {
                if (shopsStore.getGaze_status().equals("0")) {
                    shopsStore.setGaze_status("1");
                } else {
                    shopsStore.setGaze_status("0");
                }
            }
        }
        mallStoreStreetAdapter.notifyDataSetChanged();
    }

    @Override
    public void getNowPlaceSuccess(Place data) {
        if (data != null) {
            if (data.getName() != null && !data.getName().equals("请选择地址")) {
                provinceid = Integer.parseInt(data.getProvince());
                cityid = Integer.parseInt(data.getCity());
                areaid = Integer.parseInt(data.getDistrict());
                fragmentOne.setAddress(data.getName());
            }
        }
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void tabs(View view, int i) {
        page = 1;
        isClear = true;
        mallStoreStreetAdapter.notifyDataSetChanged();
        ShopsStore.Tabs tabs = tabsList.get(i);
        type = tabs.getType();
        presenter.getStore();
    }

    @Override
    public void oneCallBack(String shopid) {
        page = 1;
        isClear = true;
        shopsid = shopid;
        slidingMenu.toggle(false);
        presenter.getStore();
    }

    @Override
    public void clearAddress() {
        provinceid = 0;
        cityid = 0;
        areaid = 0;
        page = 1;
        isClear = true;
        presenter.getStore();
        slidingMenu.toggle(false);
    }

    @Override
    public void provinceCallBack(int data) {
        menu_view_pager.setCurrentItem(data);
    }


    @Override
    public void provinceIDCallBack(int id, String name) {
        menu_view_pager.setCurrentItem(2);
        fragmentThree.setData(id);
        fragmentThree.setName(name);
        province = name;
        provinceid = id;
    }


    @Override
    public void provinceCancel() {
        menu_view_pager.setCurrentItem(0, false);
    }

    @Override
    public void cityIDCallBack(int id, String name) {
        menu_view_pager.setCurrentItem(3);
        fragmentFour.setData(id);
        fragmentFour.setName(province, name);
        city = name;
        cityid = id;
    }

    @Override
    public void cityConfirm() {
        menu_view_pager.setCurrentItem(0, false);
        city = "";
        area = "";
        cityid = 0;
        areaid = 0;
        page = 1;
        isClear = true;
        fragmentOne.setAddress(province, city, area);
        presenter.getStore();
        slidingMenu.toggle(false);
    }

    @Override
    public void cityCancel() {
        menu_view_pager.setCurrentItem(1, false);
    }

    @Override
    public void areaIDCallBack(int id, String name) {
        page = 1;
        isClear = true;
        menu_view_pager.setCurrentItem(0, false);
        areaid = id;
        area = name;
        fragmentOne.setAddress(province, city, area);
        presenter.getStore();
        slidingMenu.toggle(false);
    }

    @Override
    public void areaConfirm() {
        isClear = true;
        menu_view_pager.setCurrentItem(0, false);
        area = "";
        areaid = 0;
        fragmentOne.setAddress(province, city, area);
        page = 1;
        presenter.getStore();
        slidingMenu.toggle(false);
    }

    @Override
    public void areaCancel() {
        menu_view_pager.setCurrentItem(2, false);
    }

    @Override
    public void go(String name, String longitude, String latitude) {
        Intent intent = new Intent(getActivity(), StoreStreetMapActivity.class);
        intent.putExtra("longitude", longitude);
        intent.putExtra("latitude", latitude);
        intent.putExtra("name", name);
        startActivity(intent);
    }

    @Override
    public void attention(String shopUid) {
        this.shopUid = shopUid;
        if (Config.User.STATUS) {
            presenter.FocusShops(shopUid);
        } else {
            showToast("请先登录");
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }

    @Override
    public void onItemClick(int position) {
        if (shopsStoreList1.size() > position) {
            ShopsStore shopsStore = shopsStoreList1.get(position);
            Intent intent = new Intent(getContext(), ShopsStoreDetailsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("shopsStore", shopsStore);
            bundle.putBoolean("isResult", true);
            intent.putExtras(bundle);
            startActivityForResult(intent, 100);
        }
    }

    private void initLocation() {
        mLocationClient = new LocationClient(getActivity());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 2000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);

        //添加设置结果
        mLocationClient.setLocOption(option);

        //开始真正的定位
        mLocationClient.start();

    }

    public void setFirst() {
        isFirst = true;
    }


    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (mylatitude == -1) {
                myLongitude = location.getLongitude();
                mylatitude = location.getLatitude();
                isLoc = true;
                page = 1;
                presenter.getStore();
            } else {
                myLongitude = location.getLongitude();
                mylatitude = location.getLatitude();
            }
        }

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

    }

    @Override
    public void onPullUpToRefresh(final PullToRefreshBase<ListView> refreshView) {
        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshView.onRefreshComplete();
            }
        }, 500);
        int size = shopsStoreList1.size();
        if (size > 0) {
            if (shopsStoreList1.get(size - 1).getTotalPage() < page) {
                showToast("沒有更多数据");
                return;
            }
        }
        presenter.getStore();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1) {
            String id = data.getStringExtra("id");
            if (id != null) {
                for (ShopsStore shopsStore : shopsStoreList1) {
                    if (shopsStore.getUser_id().equals(id)) {
                        if (shopsStore.getGaze_status().equals("0")) {
                            shopsStore.setGaze_status("1");
                        } else {
                            shopsStore.setGaze_status("0");
                        }
                        mallStoreStreetAdapter.setData(shopsStoreList1);
                        mallStoreStreetAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }
}