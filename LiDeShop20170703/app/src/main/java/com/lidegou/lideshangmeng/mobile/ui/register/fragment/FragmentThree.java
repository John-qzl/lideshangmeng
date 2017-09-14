package com.lidegou.lideshangmeng.mobile.ui.register.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ListView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.City;
import com.lidegou.lideshangmeng.mobile.data.entity.County;
import com.lidegou.lideshangmeng.mobile.data.entity.Province;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.MallStoreStreetContract;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.MallStoreStreetPresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.ui.register.adapter.MallCityAdapter;
import com.lidegou.lideshangmeng.mobile.util.dialog.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import static com.lidegou.lideshangmeng.mobile.R.id.lv_city;
@SuppressLint("ValidFragment")
public class FragmentThree extends BaseFragment implements MallStoreStreetContract.City, MallCityAdapter.OnClickLisener {
    private ListView lvCity;
    private MallStoreStreetContract.Presenter presenter;
    private LoadingDialog loadingDialog;
    private MallCityAdapter mallCityAdapter;
    int region_id;
    private List<City> cityList=new ArrayList<>();
    private CityIDCallBackCallBack cityIDCallBackCallBack;

    public FragmentThree(CityIDCallBackCallBack cityIDCallBackCallBack) {
        this.cityIDCallBackCallBack = cityIDCallBackCallBack;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register_three;
    }

    @Override
    protected void init() {
        loadingDialog = new LoadingDialog(getContext());
        lvCity = (ListView) getRootView().findViewById(lv_city);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new MallStoreStreetPresenter(this);
        }

    }

    @Override
    protected void viewClick(View view) {

    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    public Integer region_id() {
        return region_id;
    }

    @Override
    public Integer region_idcity() {
        return null;
    }

    @Override
    public void callbackProvinceSuccess(List<Province> provinceList) {

    }

    @Override
    public void callbackCitySuccess(List<City> cityList) {
        mallCityAdapter = new MallCityAdapter(getContext(), cityList);
        mallCityAdapter.setOnClickLisener(this);
        lvCity.setAdapter(mallCityAdapter);
        this.cityList=cityList;
    }

    @Override
    public void callbackCountySuccess(List<County> countyList) {

    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void onClickCity(View view, int i) {
        City city=cityList.get(i);
        cityIDCallBackCallBack.cityIDCallBack(city.getRegion_id(),city.getRegion_name());
    }

    public void setData(int data) {
        region_id = data;
        presenter.city();
    }

    public interface CityIDCallBackCallBack{
        void cityIDCallBack(int id, String name);
    }
}
