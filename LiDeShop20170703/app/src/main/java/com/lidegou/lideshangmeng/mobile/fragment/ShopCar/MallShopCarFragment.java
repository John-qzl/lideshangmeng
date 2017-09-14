package com.lidegou.lideshangmeng.mobile.fragment.ShopCar;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.ShoppingCart;
import com.lidegou.lideshangmeng.mobile.event.OnRecyclerItemClickListener;
import com.lidegou.lideshangmeng.mobile.fragment.ShopCar.MallShopCarAdapter.OnShoppingCartListener;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.ui.personal.mydata.address.AddressListActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.order.details.OrderDetailsActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Y on 2016/12/1.
 */
public class MallShopCarFragment extends BaseFragment implements MallShopCarContract.View, OnRecyclerItemClickListener, OnShoppingCartListener, MallShopCarShopAdapter.OnShopCarClickListener {
    public static String TYPE_HOME = "home";
    public static String TYPE_OTHER = "other";

    //商品列表
    private RecyclerView rvShoppingCart;
    private MallShopCarAdapter shoppingCartAdapter;
    //选择全部
    private ImageView ivSelectAll;
    //总价
    private TextView tvTotalMoney;
    //确认订单
    private Button btnConfirmOrder;

    private MallShopCarContract.Presenter presenter;
    private List<ShoppingCart.DataBean> dataBeanList = new ArrayList<>();
    private String rec_id;

    private View backs;
    private boolean isBack = false;
    private LinearLayout lin_shopcar_bottom;
    private LinearLayout ll_settlement;
    private LinearLayout ll_delete;
    private Button btn_delete;

    private boolean isAllSelct = false;
    private String number;
    private String type;

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mall_shopcar;
    }

    protected void init() {
        rvShoppingCart = (RecyclerView) getRootView().findViewById(R.id.rv_shopping_cart);
        rvShoppingCart.setLayoutManager(new LinearLayoutManager(getContext()));
        shoppingCartAdapter = new MallShopCarAdapter(this, this);
        shoppingCartAdapter.setOnRecyclerItemClickListener(this);
        shoppingCartAdapter.setOnShoppingCartListener(this);
        rvShoppingCart.setAdapter(shoppingCartAdapter);
        ivSelectAll = (ImageView) getRootView().findViewById(R.id.iv_select_all);
        ivSelectAll.setOnClickListener(this);
        tvTotalMoney = (TextView) getRootView().findViewById(R.id.tv_total_money);
        btnConfirmOrder = (Button) getRootView().findViewById(R.id.btn_confirm_order);
        btnConfirmOrder.setOnClickListener(this);

        lin_shopcar_bottom = (LinearLayout) getRootView().findViewById(R.id.lin_shopcar_bottom);
        ll_settlement = (LinearLayout) getRootView().findViewById(R.id.ll_settlement);
        ll_delete = (LinearLayout) getRootView().findViewById(R.id.ll_delete);
        btn_delete = (Button) getRootView().findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);

        shoppingCartAdapter.setDataList(dataBeanList);
        rvShoppingCart.setAdapter(shoppingCartAdapter);

        getRootView().findViewById(R.id.tv_editer).setOnClickListener(this);
        getRootView().findViewById(R.id.btn_backs).setOnClickListener(this);

        if (isBack) {
            backs = getRootView().findViewById(R.id.backs);
            backs.setOnClickListener(this);
            backs.setVisibility(View.VISIBLE);
        }
        if (presenter == null) {
            presenter = new MallShopCarPresenter(this);
        }
        presenter.start();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (App.getApp().isPayOrderSuccess() && presenter != null) {
            presenter.start();
            App.getApp().setPayOrderSuccess(false);
        }
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.backs:
                getActivity().finish();
                break;
            case R.id.iv_select_all:
                if (isAllSelct) {
                    isAllSelct = false;
                    ivSelectAll.setImageResource(R.drawable.shopping_cart_normal);
                } else {
                    isAllSelct = true;
                    ivSelectAll.setImageResource(R.drawable.shopping_cart_pressed);
                }
                for (ShoppingCart.DataBean dataBean : dataBeanList) {
                    dataBean.setSelect(isAllSelct);
                    for (ShoppingCart.DataBean.GoodsListBean goodsListBean : dataBean.getGoods_list()) {
                        goodsListBean.setSelect(isAllSelct);
                    }
                }
                shoppingCartAdapter.setDataList(dataBeanList);
                shoppingCartAdapter.notifyDataSetChanged();
                setSum();
                break;
            case R.id.btn_confirm_order:
                if (getSelectRecids().equals("")) {
                    showToast("请选择结算商品");
                    return;
                }
                btnConfirmOrder.setClickable(false);
                presenter.isAddress();
                break;
            case R.id.btn_delete:
                presenter.batchDeleteShopCar();
                break;
            case R.id.tv_editer:
                ll_settlement.setVisibility(View.GONE);
                ll_delete.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_backs:
                ll_settlement.setVisibility(View.VISIBLE);
                ll_delete.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void lazyLoad() {
        Log.i("shopcar", "lazyLoad");
        if (presenter == null) {
            presenter = new MallShopCarPresenter(this);
        }
        presenter.start();
    }

    @Override
    public void shoppingCartSelectTrue() {
        isAllSelct = true;
        ivSelectAll.setImageResource(R.drawable.shopping_cart_pressed);
        setSum();
    }

    @Override
    public void shoppingCartSelectFalse() {
        isAllSelct = false;
        ivSelectAll.setImageResource(R.drawable.shopping_cart_normal);
        setSum();
    }

    @Override
    public void shoppingCartSelectChange() {
        setSum();
    }

    @Override
    public void shoppingCartSelectChange(String rec_id, String number) {
        this.number = number;
        this.rec_id = rec_id;
        presenter.updateGoodNumebr();
    }

    @Override
    public void recyclerItemClick(View view, int position) {

    }

    @Override
    public void callbackCarListSuccess(ShoppingCart shoppingCart) {//查询购物车
        dataBeanList.clear();
        if (shoppingCart != null) {
            dataBeanList.addAll(shoppingCart.getData());
        }
        isAllSelct = true;
        ivSelectAll.setImageResource(R.drawable.shopping_cart_pressed);
        for (ShoppingCart.DataBean dataBean : dataBeanList) {
            dataBean.setSelect(isAllSelct);
            for (ShoppingCart.DataBean.GoodsListBean goodsListBean : dataBean.getGoods_list()) {
                goodsListBean.setSelect(isAllSelct);
            }
        }
        shoppingCartAdapter.setDataList(dataBeanList);
        shoppingCartAdapter.notifyDataSetChanged();
        setSum();
        setNone();
    }

    private void setNone() {
        if (dataBeanList.size() <= 0) {
            lin_shopcar_bottom.setVisibility(View.GONE);
            getRootView().findViewById(R.id.shopcar_none).setVisibility(View.VISIBLE);
            if (type.equals(TYPE_HOME)) {
                getRootView().findViewById(R.id.shopcar_to_home).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        App.getApp().toHome();
                    }
                });
            } else if (type.equals(TYPE_OTHER)) {
                getRootView().findViewById(R.id.shopcar_to_home).setVisibility(View.GONE);
            }
        } else {
            lin_shopcar_bottom.setVisibility(View.VISIBLE);
            getRootView().findViewById(R.id.shopcar_none).setVisibility(View.GONE);
        }
    }

    @Override
    public void callbackDeleteShopCarListSuccess(String msg) {//批量删除成功
//        showToast(msg);
        presenter.carList();
        tvTotalMoney.setText("0.0");
        ivSelectAll.setImageResource(R.drawable.shopping_cart_normal);

    }

    @Override
    public String rec_id() {
        return rec_id;
    }

    @Override
    public String batchID() {
        return getSelectRecids();
    }

    @Override
    public String number() {
        return number;
    }

    @Override
    public String arr() {
        return getAllRecids();
    }

    @Override
    public void callbackUpdateGoodNumebrSuccess(String msg) {
        setSum();
    }

    @Override
    public void isAddressSuccess(boolean isAddress) {
        if (isAddress) {
            Intent intent = new Intent(getContext(), OrderDetailsActivity.class);
            intent.putExtra(OrderDetailsActivity.IDS, getSelectRecids());
            intent.putExtra(OrderDetailsActivity.TYPE, 2);
            startActivity(intent);
            btnConfirmOrder.setClickable(true);
        } else {
            showToast("请优先设置收货地址");
            Intent addressIntent = new Intent(getActivity(), AddressListActivity.class);
            startActivity(addressIntent);
            btnConfirmOrder.setClickable(true);
        }
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
        btnConfirmOrder.setClickable(true);
    }

    @Override
    public void shopsCarListDelete(String recId) {//单个删除商品
        rec_id = recId;
        presenter.deleteShopCar();
    }

    public void setSum() {
        double sum = 0;
        for (ShoppingCart.DataBean dataBean : shoppingCartAdapter.getDataList()) {
            for (ShoppingCart.DataBean.GoodsListBean goodsListBean : dataBean.getGoods_list()) {
                if (goodsListBean.isSelect()) {
                    sum += Double.parseDouble(goodsListBean.getGoods_number()) * Double.parseDouble(goodsListBean.getGoods_price());
                }
            }
        }
        DecimalFormat df = new DecimalFormat("######0.00");
        tvTotalMoney.setText(df.format(sum));
    }

    private String getSelectRecids() {//批量删除id数组
        StringBuilder builder = new StringBuilder();
        for (ShoppingCart.DataBean dataBean : shoppingCartAdapter.getDataList()) {
            for (ShoppingCart.DataBean.GoodsListBean goodsListBean : dataBean.getGoods_list()) {
                if (goodsListBean.isSelect()) {
                    builder.append(goodsListBean.getRec_id());
                    builder.append(",");
                }
            }
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    private String getAllRecids() {//批量修改商品数量id数组
        StringBuilder builder = new StringBuilder();
        for (ShoppingCart.DataBean dataBean : shoppingCartAdapter.getDataList()) {
            for (ShoppingCart.DataBean.GoodsListBean goodsListBean : dataBean.getGoods_list()) {
                builder.append(goodsListBean.getRec_id());
                builder.append(",");
            }
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }


    public void showBack() {
        isBack = true;
    }
}