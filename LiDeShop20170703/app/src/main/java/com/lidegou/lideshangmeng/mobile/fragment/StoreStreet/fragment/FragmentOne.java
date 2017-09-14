package com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.ShopsStore;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.MallStoreStreetCatsAdapter;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.MallStoreStreetContract;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.MallStoreStreetPresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.util.dialog.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class FragmentOne extends BaseFragment implements MallStoreStreetContract.ShopsClassify, MallStoreStreetCatsAdapter.OnClickListener {
    private ListView commodity_order_recycler_view;
    private List<ShopsStore.ShopsStoreClassify> allShopsStoreClassifyList = new ArrayList<>();
    private LoadingDialog loadingDialog;
    private String shopid = "0";
    private GridView gv_getcats;
    private MallStoreStreetCatsAdapter mallStoreStreetCatsAdapter;
    private MallStoreStreetContract.Presenter presenter;
    private FragmentOneCallBack callBack;
    private ProvinceCallBackCallBack provinceCallBack;
    private TextView tvCity;
    private ImageView ivClear;

    public FragmentOne(FragmentOneCallBack callBack, ProvinceCallBackCallBack provinceCallBack) {
        this.callBack = callBack;
        this.provinceCallBack = provinceCallBack;
    }

    public FragmentOne() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_menu_one;
    }

    @Override
    protected void init() {
        loadingDialog = new LoadingDialog(getContext());
        gv_getcats = (GridView) getRootView().findViewById(R.id.gv_getcats);
        tvCity = (TextView) getRootView().findViewById(R.id.tv_city);
        ivClear = (ImageView) getRootView().findViewById(R.id.iv_clear);
        getRootView().findViewById(R.id.lin_delivery).setOnClickListener(this);
        ivClear.setOnClickListener(this);

        presenter = new MallStoreStreetPresenter(this);
        presenter.getShopsClass();
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_delivery:
                provinceCallBack.provinceCallBack(1);
                break;
            case R.id.iv_clear:
                if (callBack != null) {
                    callBack.clearAddress();
                    setAddress("全国", "", "");
                }
                break;
        }
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void callbackShopsClassSuccess(List<ShopsStore.ShopsStoreClassify> shopsStoreClassifyList) {
        List<ShopsStore.ShopsStoreClassify> allShopsStoreClassifyList = new ArrayList<>();
        ShopsStore.ShopsStoreClassify classify = new ShopsStore.ShopsStoreClassify();
        classify.setId("0");
        classify.setCat_alias_name("全部");
        allShopsStoreClassifyList.add(classify);
        allShopsStoreClassifyList.addAll(shopsStoreClassifyList);
        mallStoreStreetCatsAdapter = new MallStoreStreetCatsAdapter(allShopsStoreClassifyList, getContext());
        mallStoreStreetCatsAdapter.setOnClickListener(this);
        gv_getcats.setAdapter(mallStoreStreetCatsAdapter);
        this.allShopsStoreClassifyList = allShopsStoreClassifyList;
    }


    @Override
    public void ShopsStoreClassify(View view, int position) {
        ShopsStore.ShopsStoreClassify shopsStoreClassify = allShopsStoreClassifyList.get(position);
        shopid = shopsStoreClassify.getId();
        if (shopid != null) {
            callBack.oneCallBack(shopid);
        }
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    public void setAddress(String province, String city, String area) {
        if (province != null && city != null && area != null) {
            if (province.equals("")) {
                tvCity.setText("");
            } else if (city.equals("")) {
                tvCity.setText(province);
            } else if (area.equals("")) {
                tvCity.setText(province + "-" + city);
            } else {
                tvCity.setText(province + "-" + city + "-" + area);
            }
        }
    }

    public void setAddress(String address) {
        if (!address.equals("请选择地址")) {
            tvCity.setText(address);
        }
    }


    public interface FragmentOneCallBack {
        void oneCallBack(String s);

        void clearAddress();
    }

    public interface ProvinceCallBackCallBack {
        void provinceCallBack(int data);
    }
}
