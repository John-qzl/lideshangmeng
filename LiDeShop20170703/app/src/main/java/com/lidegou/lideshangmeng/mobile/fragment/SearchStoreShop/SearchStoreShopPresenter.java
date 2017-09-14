package com.lidegou.lideshangmeng.mobile.fragment.SearchStoreShop;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.ICommodityDao;
import com.lidegou.lideshangmeng.mobile.data.dao.IHomeDao;
import com.lidegou.lideshangmeng.mobile.data.dao.IUserInfoDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.ICommodityDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IHomeDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IUserDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.City;
import com.lidegou.lideshangmeng.mobile.data.entity.Classify;
import com.lidegou.lideshangmeng.mobile.data.entity.County;
import com.lidegou.lideshangmeng.mobile.data.entity.Province;
import com.lidegou.lideshangmeng.mobile.data.entity.ShopsStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public class SearchStoreShopPresenter implements SearchStoreShopContract.Presenter {
    private SearchStoreShopContract.View view;
    private SearchStoreShopContract.ShopsClassify shopsClassifyView;
    private SearchStoreShopContract.City cityView;
    private ICommodityDao iCommodityDao;
    private IHomeDao iHomeDao;
    private List<Classify.Products> productsList = new ArrayList<>();
    private List<ShopsStore.ShopsStoreClassify> shopsStoreClassifyList = new ArrayList<>();
    private List<ShopsStore> shopsStoreList = new ArrayList<>();
    private List<ShopsStore.Tabs> tabsList = new ArrayList<>();

    private IUserInfoDao iUserInfoDao;
    private List<Province> provinceList = new ArrayList<>();
    private List<City> cityList = new ArrayList<>();
    private List<County> countyList = new ArrayList<>();

    public SearchStoreShopPresenter(SearchStoreShopContract.View view) {
        this.view = view;
        this.iHomeDao = new IHomeDaoImpl();
        this.iCommodityDao = new ICommodityDaoImpl();
    }

    public SearchStoreShopPresenter(SearchStoreShopContract.ShopsClassify shopsClassifyView) {
        this.shopsClassifyView = shopsClassifyView;
        this.iCommodityDao = new ICommodityDaoImpl();
    }


    public SearchStoreShopPresenter(SearchStoreShopContract.City cityView) {
        this.cityView = cityView;
        this.iUserInfoDao = new IUserDaoImpl();
    }

    @Override
    public void start() {
        getStore();
        getTabs();
    }

    @Override
    public void getStore() {
        String uid = App.getApp().getUid();
        if (uid == null) {
            uid = "";
        }
        iCommodityDao.getShopsStore(uid, view.keyword(), view.page(), view.id(), view.province_id(), view.city_id(), view.district_id(), view.longitude(), view.latitude(), view.type(), new ResponseCallback<List<ShopsStore>>() {
            @Override
            public void onSuccess(List<ShopsStore> data) {
                if (data != null) {
                    shopsStoreList.clear();
                    shopsStoreList.addAll(data);
                    view.callbackStoreSuccess(shopsStoreList);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    view.showError(msg);
                }
            }
        });
    }

    @Override
    public void getShopsClass() {
        iCommodityDao.getShopsClassify("", new ResponseCallback<List<ShopsStore.ShopsStoreClassify>>() {
            @Override
            public void onSuccess(List<ShopsStore.ShopsStoreClassify> data) {
                if (data != null) {
                    shopsStoreClassifyList.clear();
                    shopsStoreClassifyList.addAll(data);
                    shopsClassifyView.callbackShopsClassSuccess(shopsStoreClassifyList);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                shopsClassifyView.showError(msg);
            }
        });
    }

    @Override
    public void getTabs() {
        iCommodityDao.getTabs(new ResponseCallback<List<ShopsStore.Tabs>>() {
            @Override
            public void onSuccess(List<ShopsStore.Tabs> data) {
                if (data != null) {
                    tabsList.clear();
                    tabsList.addAll(data);
                    view.callbackTabsSuccess(tabsList);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                view.showError(msg);
            }
        });
    }

    @Override
    public void province() {
        iUserInfoDao.province(new ResponseCallback<List<Province>>() {
            @Override
            public void onSuccess(List<Province> data) {
                if (data != null) {
                    provinceList.clear();
                    provinceList.addAll(data);
                    cityView.callbackProvinceSuccess(provinceList);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    cityView.showError(msg);
                }
            }
        });
    }

    @Override
    public void city() {
        iUserInfoDao.city(cityView.region_id(), new ResponseCallback<List<City>>() {
            @Override
            public void onSuccess(List<City> data) {
                if (data != null) {
                    cityList.clear();
                    cityList.addAll(data);
                    cityView.callbackCitySuccess(cityList);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    cityView.showError(msg);
                }
            }
        });
    }

    @Override
    public void County() {
        iUserInfoDao.county(cityView.region_idcity(), new ResponseCallback<List<County>>() {
            @Override
            public void onSuccess(List<County> data) {
                if (data != null) {
                    countyList.clear();
                    countyList.addAll(data);
                    cityView.callbackCountySuccess(countyList);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    cityView.showError(msg);
                }
            }
        });
    }

    @Override
    public void FocusShops(String shopUid) {
        iCommodityDao.FocusShops(view.getApp().getUid(), shopUid, new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                view.callbackFocusShopsSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                view.showError(msg);
            }
        });
    }
}
