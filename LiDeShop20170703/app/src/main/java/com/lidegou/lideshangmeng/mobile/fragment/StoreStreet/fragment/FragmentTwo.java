package com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ListView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.City;
import com.lidegou.lideshangmeng.mobile.data.entity.County;
import com.lidegou.lideshangmeng.mobile.data.entity.Province;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.MallStoreStreetContract;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.MallStoreStreetPresenter;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.adapter.MallShopsMenuProvinceAdapter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.util.dialog.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class FragmentTwo extends BaseFragment implements MallStoreStreetContract.City, MallShopsMenuProvinceAdapter.OnClickLisener {
    private ListView lv_province;
    private MallStoreStreetContract.Presenter presenter;
    private LoadingDialog loadingDialog;
    private MallShopsMenuProvinceAdapter mallShopsMenuProvinceAdapter;
    private ProvinceIDCallBackCallBack provinceIDCallBackCallBack;
    private List<Province> provinceList = new ArrayList<>();

    public FragmentTwo(ProvinceIDCallBackCallBack provinceIDCallBackCallBack) {
        this.provinceIDCallBackCallBack = provinceIDCallBackCallBack;
    }

    public FragmentTwo() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_menu_two;
    }

    @Override
    protected void init() {
        loadingDialog = new LoadingDialog(getContext());
        lv_province = (ListView) getRootView().findViewById(R.id.lv_province);

        getRootView().findViewById(R.id.btn_cancel).setOnClickListener(this);
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
        switch (view.getId()) {
            case R.id.btn_cancel:
                provinceIDCallBackCallBack.provinceCancel();
                break;
        }
    }

    @Override
    protected void lazyLoad() {
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
        mallShopsMenuProvinceAdapter = new MallShopsMenuProvinceAdapter(getContext(), provinceList);
        mallShopsMenuProvinceAdapter.setOnClickLisener(this);
        lv_province.setAdapter(mallShopsMenuProvinceAdapter);
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

        void provinceCancel();
    }
}
