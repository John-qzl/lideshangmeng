package com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.City;
import com.lidegou.lideshangmeng.mobile.data.entity.County;
import com.lidegou.lideshangmeng.mobile.data.entity.Province;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.MallStoreStreetContract;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.MallStoreStreetPresenter;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.adapter.MallShopsMenuAreaAdapter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.util.dialog.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class FragmentFour extends BaseFragment implements MallStoreStreetContract.City, MallShopsMenuAreaAdapter.OnClickLisener {

    private ListView lvArea;
    private Button btnConfirm;
    private LoadingDialog loadingDialog;
    private MallStoreStreetContract.Presenter presenter;
    private MallShopsMenuAreaAdapter mallShopsMenuAreaAdapter;
    int region_idcity;
    private List<County> countylist = new ArrayList<>();
    private AreaIDCallBackCallBack areaIDCallBackCallBack;
    private TextView title;

    public FragmentFour(AreaIDCallBackCallBack areaIDCallBackCallBack) {
        this.areaIDCallBackCallBack = areaIDCallBackCallBack;
    }

    public FragmentFour() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_menu_four;
    }

    @Override
    protected void init() {
        loadingDialog = new LoadingDialog(getContext());
        title = (TextView) getRootView().findViewById(R.id.title);
        lvArea = (ListView) getRootView().findViewById(R.id.lv_area);

        getRootView().findViewById(R.id.btn_confirm).setOnClickListener(this);
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
            case R.id.btn_confirm:
                areaIDCallBackCallBack.areaConfirm();
                break;
            case R.id.btn_cancel:
                areaIDCallBackCallBack.areaCancel();
                break;
        }
    }

    @Override
    protected void lazyLoad() {

    }


    @Override
    public Integer region_id() {
        return null;
    }

    @Override
    public Integer region_idcity() {
        return region_idcity;
    }

    @Override
    public void callbackProvinceSuccess(List<Province> provinceList) {

    }

    @Override
    public void callbackCitySuccess(List<City> cityList) {

    }

    @Override
    public void callbackCountySuccess(List<County> countyList) {
        mallShopsMenuAreaAdapter = new MallShopsMenuAreaAdapter(getContext(), countyList);
        lvArea.setAdapter(mallShopsMenuAreaAdapter);
        mallShopsMenuAreaAdapter.setOnClickLisener(this);
        this.countylist = countyList;
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    public void setData(int data) {
        region_idcity = data;
        presenter.County();
    }

    @Override
    public void onClickArea(View view, int i) {
        County county = countylist.get(i);
        areaIDCallBackCallBack.areaIDCallBack(county.getRegion_id(), county.getRegion_name());
    }

    public void setName(String province, String name) {
        title.setText("所在地区: " + province + " " + name);
    }

    public interface AreaIDCallBackCallBack {
        void areaIDCallBack(int id, String name);

        void areaConfirm();

        void areaCancel();
    }
}
