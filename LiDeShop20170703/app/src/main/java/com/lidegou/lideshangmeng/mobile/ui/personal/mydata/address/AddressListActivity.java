package com.lidegou.lideshangmeng.mobile.ui.personal.mydata.address;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Address;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.login.LoginActivity;
import com.lidegou.lideshangmeng.mobile.util.dialog.LoadingDialog;
import com.lidegou.lideshangmeng.mobile.util.dialog.PromptDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AddressListActivity extends BaseActivity implements AddressContract.View, AddressAdapter.OnAddressClickListener {

    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.rv_address)
    RecyclerView rvAddress;
    @InjectView(R.id.btn_add_address)
    Button btnAddAddress;

    private AddressContract.Presenter presenter;
    private AddressAdapter addressAdapter;
    private List<Address.ConsigneeListBean> consigneeListBeanList = new ArrayList<>();
    private String addressid;
    private LoadingDialog loadingDialog;
    private PromptDialog promptDialog;

    private Address address;
    private boolean isFinish;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    protected void init() {
        isFinish = getIntent().getBooleanExtra("isFinish", false);
        ButterKnife.inject(this);
        linBack.setOnClickListener(this);
        btnAddAddress.setOnClickListener(this);
        loadingDialog = new LoadingDialog(this);
        promptDialog = new PromptDialog(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Config.User.STATUS) {

        } else {
            startActivity(new Intent(AddressListActivity.this, LoginActivity.class));
            finish();
        }
        if (presenter == null) {
            presenter = new AddressPresenter(this);
        }
        presenter.start();
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.btn_add_address:
                startActivity(new Intent(AddressListActivity.this, AddressAddActivity.class));
                break;
        }
    }


    @Override
    public void callbackAddressListSuccess(Address address) {
        this.address = address;
        addressAdapter = new AddressAdapter(address);
        addressAdapter.setOnAddressClickListener(this);
        addressAdapter.setDataList(address.getConsignee_list());
        rvAddress.setLayoutManager(new LinearLayoutManager(this));
        rvAddress.setAdapter(addressAdapter);
        consigneeListBeanList = address.getConsignee_list();
        loadingDialog.dismiss();
    }

    @Override
    public void callbackSetdefaultAddressSuccess(String msg) {
        showToast(msg);
        presenter.addresslist();
        if (isFinish) {
            finish();
        }
    }

    @Override
    public void callbackDeleteAddressSuccess(String msg) {
        showToast(msg);
        presenter.addresslist();
    }

    @Override
    public String addressId() {
        return addressid;
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void addressDelete(View view, final int position) {
        promptDialog.setMsg("是否删除该收货地址");
        promptDialog.setOnPromptClickListener(new PromptDialog.OnPromptClickListener() {
            @Override
            public void promptConfirmClick(View view) {
                Address.ConsigneeListBean consigneeListBean = consigneeListBeanList.get(position);
                addressid = consigneeListBean.getAddress_id();
                presenter.deleteAddress();
            }

            @Override
            public void promptCancelClick(View view) {

            }
        });
        promptDialog.show();

    }

    @Override
    public void addressUpdate(View view, int position) {
        Address.ConsigneeListBean consigneeListBean = consigneeListBeanList.get(position);
        Intent intent = new Intent(AddressListActivity.this, AddressUpdateActivity.class);
        intent.putExtra("address_id", consigneeListBean.getAddress_id());
        startActivity(intent);
    }

    @Override
    public void addressDefault(String addressid, int position) {
        if (address.getAddress_id() != null || !addressid.equals(address.getAddress_id())) {
            this.addressid = addressid;
            presenter.setdefaultAddress();
            loadingDialog.show("");
        }
    }
}
