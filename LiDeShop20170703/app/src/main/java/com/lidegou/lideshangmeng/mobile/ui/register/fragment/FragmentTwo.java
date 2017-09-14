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
import com.lidegou.lideshangmeng.mobile.ui.register.adapter.MallProvinceAdapter;
import com.lidegou.lideshangmeng.mobile.util.dialog.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class FragmentTwo extends BaseFragment implements MallStoreStreetContract.City, MallProvinceAdapter.OnClickLisener {
    private ListView lv_province;
    private MallStoreStreetContract.Presenter presenter;
    private LoadingDialog loadingDialog;
    private MallProvinceAdapter mallProvinceAdapter;
    private ProvinceIDCallBackCallBack provinceIDCallBackCallBack;
    private List<Province> provinceList = new ArrayList<>();

    public FragmentTwo(ProvinceIDCallBackCallBack provinceIDCallBackCallBack) {
        this.provinceIDCallBackCallBack = provinceIDCallBackCallBack;
    }

    public FragmentTwo() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register_two;
    }

    @Override
    protected void init() {
        loadingDialog = new LoadingDialog(getContext());
        lv_province = (ListView) getRootView().findViewById(R.id.lv_province);
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
        if (presenter == null) {
            presenter = new MallStoreStreetPresenter(this);
        }
        presenter.province();
    }

    @Override
    public Integer region_id() {
        return null;
    }

    @Override
    public Integer region_idcity() {
        return null;
    }

    @Override
    public void callbackProvinceSuccess(List<Province> provinceList) {
        mallProvinceAdapter = new MallProvinceAdapter(getContext(), provinceList);
        mallProvinceAdapter.setOnClickLisener(this);
        lv_province.setAdapter(mallProvinceAdapter);
        this.provinceList = provinceList;
    }

    @Override
    public void callbackCitySuccess(List<City> cityList) {

    }

    @Override
    public void callbackCountySuccess(List<County> countyList) {

    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void onClickProvince(View view, int i) {
        Province province = provinceList.get(i);
        provinceIDCallBackCallBack.provinceIDCallBack(province.getRegion_id(), province.getRegion_name());
    }

    public void setData(int data) {

    }


    public interface ProvinceIDCallBackCallBack {
        void provinceIDCallBack(int id, String name);
    }
}
