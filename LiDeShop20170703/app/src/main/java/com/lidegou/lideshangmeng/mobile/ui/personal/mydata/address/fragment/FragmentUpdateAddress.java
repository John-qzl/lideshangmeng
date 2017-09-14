package com.lidegou.lideshangmeng.mobile.ui.personal.mydata.address.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Address;
import com.lidegou.lideshangmeng.mobile.data.entity.City;
import com.lidegou.lideshangmeng.mobile.data.entity.County;
import com.lidegou.lideshangmeng.mobile.data.entity.Province;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.ui.personal.mydata.address.AddressContract;
import com.lidegou.lideshangmeng.mobile.ui.personal.mydata.address.AddressPresenter;
import com.lidegou.lideshangmeng.mobile.util.dialog.LoadingDialog;

import java.util.List;

@SuppressLint("ValidFragment")
public class FragmentUpdateAddress extends BaseFragment implements AddressContract.AddAddress {

    private LoadingDialog loadingDialog;
    private ProvinceCallBackCallBack provinceCallBack;
    private TextView tvCity;
    ImageView loginuser01;
    EditText edGoodPeople;
    ImageView registername01;
    EditText edPhone;
    ImageView imReferees;
    EditText edAddress;
    Button btuConfirm;
    String address_id;
    private Integer strProvince, strCity, strCounty;


    private AddressContract.Presenter presenter;

    public FragmentUpdateAddress(ProvinceCallBackCallBack provinceCallBack, String address_id) {
        this.provinceCallBack = provinceCallBack;
        this.address_id = address_id;
    }

    public FragmentUpdateAddress() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_address_add_one;
    }

    @Override
    protected void init() {
        loadingDialog = new LoadingDialog(getContext());
        tvCity = (TextView) getRootView().findViewById(R.id.tv_city);
        loginuser01 = (ImageView) getRootView().findViewById(R.id.loginuser01);
        edGoodPeople = (EditText) getRootView().findViewById(R.id.ed_good_people);
        registername01 = (ImageView) getRootView().findViewById(R.id.registername01);
        edPhone = (EditText) getRootView().findViewById(R.id.ed_phone);
        imReferees = (ImageView) getRootView().findViewById(R.id.im_referees);
        edAddress = (EditText) getRootView().findViewById(R.id.ed_address);
        btuConfirm = (Button) getRootView().findViewById(R.id.btu_confirm);
        btuConfirm.setOnClickListener(this);
        tvCity.setOnClickListener(this);
        if (presenter == null) {
            presenter = new AddressPresenter(this);
        }
        presenter.updateInfo();
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_city:
                provinceCallBack.provinceCallBack(1);
                break;
            case R.id.btu_confirm:
                presenter.updateAddress();
                break;
        }
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public String address_id() {
        return address_id;
    }

    @Override
    public String province_region_id() {
        return strProvince + "";
    }

    @Override
    public String city_region_id() {
        return strCity + "";
    }

    @Override
    public String district_region_id() {
        return strCounty + "";
    }

    @Override
    public String consignee() {
        return edGoodPeople.getText().toString();
    }

    @Override
    public String mobile() {
        return edPhone.getText().toString();
    }

    @Override
    public String address() {
        return edAddress.getText().toString();
    }

    @Override
    public Integer region_id() {
        return 0;
    }

    @Override
    public Integer region_idcity() {
        return 0;
    }

    @Override
    public void callbackAddressSuccess(String data) {
        showToast(data);
        getActivity().finish();
    }

    @Override
    public void callbackProvinceSuccess(List<Province> provinceList) {
    }

    @Override
    public void callbackCitySuccess(List<City> cityList) {
    }

    @Override
    public void callbackCountySuccess(List<County> countyList) {
    }

    @Override
    public void callbackUpdateInfoSuccess(Address.AddressUpdateInfo addressUpdateInfo) {
        edGoodPeople.setText(addressUpdateInfo.getConsignee());
        edPhone.setText(addressUpdateInfo.getMobile());
        edAddress.setText(addressUpdateInfo.getAddress());

        strProvince = Integer.parseInt(addressUpdateInfo.getProvince_id());
        strCity = Integer.parseInt(addressUpdateInfo.getCity_id());
        strCounty = Integer.parseInt(addressUpdateInfo.getDistrict_id());
        tvCity.setText(addressUpdateInfo.getProvince() + addressUpdateInfo.getCity() + addressUpdateInfo.getDistrict() + "");
    }

    @Override
    public void callbackUpdateAddressSuccess(String msg) {
        showToast(msg);
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }


    public void setAddress(int provinceid, int cityid, int areaid, String province, String city, String area) {
        if (province != null && city != null && area != null) {
            tvCity.setText(province + city + area);
            this.strProvince = provinceid;
            this.strCity = cityid;
            this.strCounty = areaid;
        }
    }


    public interface ProvinceCallBackCallBack {
        void provinceCallBack(int data);
    }
}
