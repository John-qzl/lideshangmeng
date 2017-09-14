package com.lidegou.lideshangmeng.mobile.ui.personal.order.details;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allinpay.appayassistex.APPayAssistEx;
import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Order;
import com.lidegou.lideshangmeng.mobile.data.entity.OrderSubmit;
import com.lidegou.lideshangmeng.mobile.data.entity.OtherPayEntity;
import com.lidegou.lideshangmeng.mobile.event.OnRecyclerItemClickListener;
import com.lidegou.lideshangmeng.mobile.fragment.Commodity.CommodityDetailsActivity;
import com.lidegou.lideshangmeng.mobile.pay.tonly.PaaCreator;
import com.lidegou.lideshangmeng.mobile.pay.treasure.PayTreasure;
import com.lidegou.lideshangmeng.mobile.pay.wechat.PayWeChat;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.login.LoginActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.mydata.address.AddressListActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.order.fragment.orderevlustion.OrderEvaluationActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.order.success.PaySuccessActivity;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;
import com.lidegou.lideshangmeng.mobile.util.dialog.LoadingDialog;
import com.lidegou.lideshangmeng.mobile.util.dialog.PayDialog;
import com.lidegou.lideshangmeng.mobile.util.dialog.PromptDialog;
import com.lidegou.lideshangmeng.mobile.util.dialog.select.SelectDialog;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 订单详情
 *
 * @author Y
 * @version 0.1
 * @date 2016年8月22日09:11:02
 */

public class OrderDetailsActivity extends BaseActivity implements OrderDetailsContract.View, OnRecyclerItemClickListener {
    public static final String TAG = OrderDetailsActivity.class.getSimpleName();
    public static final String ORDERID = "orderid";//下单后订单详情传递的参数
    public static final String STATE = "state";//下单后订单详情传递的参数
    public static final String URL = "url";//下单后订单详情传递的参数


    public static final String IDS = "ids";//购物车未下单传递的参数
    public static final String TYPE = "type";//用于区分两种订单详情

    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.tv_true)
    TextView tvTrue;
    @InjectView(R.id.address_name)
    TextView addressName;
    @InjectView(R.id.address_phone)
    TextView addressPhone;
    @InjectView(R.id.address_dizhi)
    TextView addressDizhi;
    @InjectView(R.id.confirm_addre)
    LinearLayout confirmAddre;
    @InjectView(R.id.iv_order_details)
    RecyclerView ivOrderDetails;
    @InjectView(R.id.lin_postscript)
    LinearLayout linPostscript;
    @InjectView(R.id.ed_postscript)
    EditText edPostscript;
    @InjectView(R.id.gouwuxiangqing_gouwu_btn_zhifu)
    Button gouwuxiangqingGouwuBtnZhifu;
    @InjectView(R.id.tv_number)
    TextView tvNumber;
    @InjectView(R.id.lin_good_time)
    LinearLayout linGoodTime;
    @InjectView(R.id.tv_good_time)
    TextView tvGoodTime;
    @InjectView(R.id.tv_shipping_name)
    TextView tvShippingName;
    @InjectView(R.id.tv_shipping_fee)
    TextView tvShippingFee;
    @InjectView(R.id.tv_pay_name)
    TextView tvPayName;
    @InjectView(R.id.tv_pay_fee)
    TextView tvPayFee;
    @InjectView(R.id.tv_inv_payee)
    TextView tvInvPayee;
    @InjectView(R.id.tv_goods_amount)
    TextView tvGoodsAmount;
    @InjectView(R.id.tv_youhui)
    TextView tvYouhui;
    @InjectView(R.id.tv_order_amount)
    TextView tvOrderAmount;
    @InjectView(R.id.tv_shop_name)
    TextView tvShopName;
    @InjectView(R.id.tv_inv_content)
    TextView tvInvContent;
    @InjectView(R.id.ll_pay)
    LinearLayout llPay;
    @InjectView(R.id.tv_status)
    TextView tvStatus;
    @InjectView(R.id.ll_confirm)
    LinearLayout llConfirm;
    @InjectView(R.id.TextView)
    TextView TextView;
    @InjectView(R.id.display_image_1)
    ImageView displayImage1;
    @InjectView(R.id.display_image_2)
    ImageView displayImage2;
    @InjectView(R.id.display_image_3)
    ImageView displayImage3;
    @InjectView(R.id.display_image_text)
    TextView displayImageText;
    @InjectView(R.id.display_image)
    LinearLayout displayImage;
    @InjectView(R.id.re_shipping)
    RelativeLayout reShipping;
    @InjectView(R.id.re_pay)
    RelativeLayout repay;
    @InjectView(R.id.re_youhui)
    RelativeLayout reYouhui;

    private String ids;
    private String state;
    private String url;
    private int type;

    private PayDialog payDialog;
    private PromptDialog promptDialog;
    public static PayHandler payHandler;
    private String status;
    private LoadingDialog loadingDialog;
    private OrderDetailsContract.Presenter presenter;
    private OrderDetailsAdapter orderDetailsAdapter;
    private OrderDetailsAdapter2 orderDetailsAdapter2;

    private OrderSubmit orderSubmit;

    private Order.OrderDetail orderDetail;


    private SelectDialog shippingDialog;
    private SelectDialog payTypeDialog;
    private String shipping_id;
    private String pay_type_id = "";
    private String done_order_id;

    private String pay_order_id;
    private String pay_order_sn;

    private boolean isSuccess = false;
    private boolean isOrderDone = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_details;
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }

        if (pay_order_id != null && type == 2 && pay_type_id.equals("12") && !isSuccess) {
            Intent intent = new Intent(OrderDetailsActivity.this, OrderDetailsActivity.class);
            intent.putExtra(OrderDetailsActivity.ORDERID, pay_order_id);
            intent.putExtra(OrderDetailsActivity.TYPE, 1);
            intent.putExtra(OrderDetailsActivity.STATE, "付款");
            startActivity(intent);
            finish();
        }

        if (Config.User.STATUS) {
            if (type != 1) {
                presenter.isAddress();
            }
        } else {
            startActivityForResult(new Intent(getContext(), LoginActivity.class), LOGIN_REQUEST);
        }
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);
        if (presenter == null) {
            presenter = new OrderDetalisPresenter(this);
        }
        promptDialog = new PromptDialog(this);

        pay_order_id = getIntent().getStringExtra(ORDERID);
        ids = getIntent().getStringExtra(IDS);
        state = getIntent().getStringExtra(STATE);
        url = getIntent().getStringExtra(URL);
        type = getIntent().getIntExtra(TYPE, 0);
        if (type == 1) {
            presenter.myorderDetailsList();//查看订单详情
            tvTrue.setVisibility(View.GONE);
            linPostscript.setVisibility(View.GONE);
            gouwuxiangqingGouwuBtnZhifu.setVisibility(View.GONE);
            orderDetailsAdapter = new OrderDetailsAdapter();
            orderDetailsAdapter.setOnRecyclerItemClickListener(this);
            ivOrderDetails.setLayoutManager(new LinearLayoutManager(this));
            ivOrderDetails.setAdapter(orderDetailsAdapter);
            if (state != null) {
                tvStatus.setText(state);
                tvStatus.setTextColor(Color.WHITE);
                tvStatus.setBackgroundColor(Color.RED);
                setBottom();
            } else {
                tvStatus.setVisibility(View.GONE);
            }
        } else {
            findViewById(R.id.line_order_id).setVisibility(View.GONE);
            repay.setOnClickListener(this);
            reShipping.setOnClickListener(this);
            displayImage.setVisibility(View.VISIBLE);
            ivOrderDetails.setVisibility(View.GONE);
            gouwuxiangqingGouwuBtnZhifu.setVisibility(View.VISIBLE);
            orderDetailsAdapter2 = new OrderDetailsAdapter2();
            orderDetailsAdapter2.setOnRecyclerItemClickListener(this);
            ivOrderDetails.setLayoutManager(new LinearLayoutManager(this));
            confirmAddre.setOnClickListener(this);
            ivOrderDetails.setAdapter(orderDetailsAdapter2);
        }

        orderDetail = new Order.OrderDetail();
        loadingDialog = LoadingDialog.getInstance(this);
        payHandler = new PayHandler();
        linBack.setOnClickListener(this);
        gouwuxiangqingGouwuBtnZhifu.setOnClickListener(this);
        displayImage.setOnClickListener(this);

    }

    private void setBottom() {
        if (state.contains("确认收货") || state.contains("取消订单")) {
            findViewById(R.id.tv_status).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    promptDialog.setMsg("是否确定" + state);
                    promptDialog.setOnPromptClickListener(new PromptDialog.OnPromptClickListener() {
                        @Override
                        public void promptConfirmClick(View view) {
                            presenter.btn_click(url, pay_order_id);
                            promptDialog.dismiss();
                        }

                        @Override
                        public void promptCancelClick(View view) {
                            promptDialog.dismiss();
                        }
                    });
                    promptDialog.show();
                }
            });
        } else if (state.contains("付款")) {
            findViewById(R.id.tv_status).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadingDialog.show("支付中...");
                    presenter.orderPay();
                }
            });
        } else if (state.contains("评价")) {
            findViewById(R.id.tv_status).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadingDialog.show("支付中...");
                    startActivity(new Intent(OrderDetailsActivity.this, OrderEvaluationActivity.class));
                }
            });
        }
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.gouwuxiangqing_gouwu_btn_zhifu:
                if (edPostscript.getText().toString().length() > 50) {
                    showToast("备注字数大于50字,请处理后再次提交");
                    return;
                }
                if (pay_type_id == null) {
                    showToast("请选择支付方式");
                    return;
                }
                if (shipping_id == null) {
                    showToast("请选择配送方式");
                    return;
                }
                gouwuxiangqingGouwuBtnZhifu.setClickable(false);
                presenter.orderDone();
                loadingDialog.show("支付中...");
                break;
            case R.id.display_image:
                displayImage.setVisibility(View.GONE);
                ivOrderDetails.setVisibility(View.VISIBLE);
                break;
            case R.id.confirm_addre:
                Intent intent = new Intent(this, AddressListActivity.class);
                intent.putExtra("isFinish", true);
                startActivity(intent);
                break;
            case R.id.re_shipping:
                if (orderSubmit == null || orderSubmit.getShopping() == null) {
                    return;
                }
                ArrayList<String> shippingList = new ArrayList<>();
                for (OrderSubmit.ShippingBean shippingBean : orderSubmit.getShopping()) {
                    if (shippingBean.getShopping_fee().equals("免运费")) {
                        shippingList.add(shippingBean.getShopping_name() + " " + shippingBean.getShopping_fee());
                    } else {
                        shippingList.add(shippingBean.getShopping_name() + " 手续费:" + shippingBean.getShopping_fee() + "元");
                    }
                }
                shippingDialog = new SelectDialog(this);
                shippingDialog.show("选择配送方式", shippingList, shippingClick);
                break;
            case R.id.re_pay:
                if (orderSubmit == null || orderSubmit.getPayment_list() == null) {
                    return;
                }
                ArrayList<String> payTypeList = new ArrayList<>();
                for (OrderSubmit.PaymentBean paymentBean : orderSubmit.getPayment_list()) {
                    payTypeList.add(paymentBean.getPay_name() + " 手续费:" + paymentBean.getPay_fee() + "元");
                }
                payTypeDialog = new SelectDialog(this);
                payTypeDialog.show("选择支付方式", payTypeList, payTypeClick);
                break;
        }
    }

    @Override
    public void recyclerItemClick(View view, int position) {
        if (orderDetailsAdapter != null) {
            Order.OrderDetail.GoodsListBean goodsListBean = orderDetailsAdapter.getDataList().get(position);
            Intent intent = new Intent(this, CommodityDetailsActivity.class);
            intent.putExtra("goodid", goodsListBean.getGoods_id());
            startActivity(intent);
        } else if (orderDetailsAdapter2 != null) {
            OrderSubmit.ListBean.GoodsListBean goodsListBean = orderDetailsAdapter2.getDataList().get(position);
            Intent intent = new Intent(this, CommodityDetailsActivity.class);
            intent.putExtra("goodid", goodsListBean.getGoods_id());
            startActivity(intent);
        }
    }


    @Override
    public void isAddressSuccess(boolean isAddress) {
        if (!isAddress) {
            finish();
            showToast("请添加地址后重新下单");
        } else {
            //下单前确认订单详情
            presenter.orderSubmit();
        }
    }

    @Override
    public String cartvalue() {
        return ids;
    }

    @Override
    public void callbackOrderDetailsList(Order.OrderDetail orderDetail) {
        ArrayList<Order.OrderDetail.GoodsListBean> beans = new ArrayList<>();
        for (Order.OrderDetail.GoodsListBean goodsListBean : orderDetail.getGoods_list()) {
            beans.add(goodsListBean);
        }
        orderDetailsAdapter.setDataList(beans);
        orderDetailsAdapter.notifyDataSetChanged();
        setOrder(orderDetail);
    }


    @Override
    public void callbackOrderSubmitSuccess(OrderSubmit orderSubmit) {//下单前确认订单
        this.orderSubmit = orderSubmit;
        ArrayList<OrderSubmit.ListBean.GoodsListBean> beans = new ArrayList<>();
        if (orderSubmit.getList() == null) {
            return;
        }
        for (OrderSubmit.ListBean.GoodsListBean goodsListBean : orderSubmit.getList().get(0).getGoods_list()) {
            beans.add(goodsListBean);
        }

        OrderSubmit.ListBean listBean = orderSubmit.getList().get(0);
        if (listBean.getGoods_list().size() == 0) {

        } else if (listBean.getGoods_list().size() == 1) {
            MyImageLoader.getInstance().loadImageReturnBitMap(
                    displayImage1, listBean.getGoods_list().get(0).getGoods_thumb(), 0);
        } else if (listBean.getGoods_list().size() == 2) {
            MyImageLoader.getInstance().loadImageReturnBitMap(
                    displayImage1, listBean.getGoods_list().get(0).getGoods_thumb(), 0);
            MyImageLoader.getInstance().loadImageReturnBitMap(
                    displayImage2, listBean.getGoods_list().get(1).getGoods_thumb(), 0);
        } else {
            MyImageLoader.getInstance().loadImageReturnBitMap(
                    displayImage1, listBean.getGoods_list().get(0).getGoods_thumb(), 0);
            MyImageLoader.getInstance().loadImageReturnBitMap(
                    displayImage2, listBean.getGoods_list().get(1).getGoods_thumb(), 0);
            MyImageLoader.getInstance().loadImageReturnBitMap(
                    displayImage3, listBean.getGoods_list().get(2).getGoods_thumb(), 0);
        }

        displayImageText.setText(" 共 " + listBean.getGoods_list().size() + " 件 ");
        orderDetailsAdapter2.setDataList(beans);
        orderDetailsAdapter2.notifyDataSetChanged();
        setOrder2(orderSubmit);
        this.done_order_id = orderSubmit.getOrder().getPay_id() + "";
    }

    @Override
    public String done_order_id() {
        return done_order_id;
    }

    @Override
    public String pay_order_id() {
        return pay_order_id;
    }

    @Override
    public void callbackOrderDoneSuccess(ArrayList<String> data) {
        App.setPayOrderSuccess(true);
        if (pay_type_id.equals("9")) {
            showToast("支付成功");
            Intent intent = new Intent(OrderDetailsActivity.this, PaySuccessActivity.class);
            intent.putExtra(PaySuccessActivity.ORDERID, data.get(0));
            intent.putExtra(PaySuccessActivity.ORDERSN, data.get(1));
            startActivity(intent);
            finish();
        } else {
            this.pay_order_id = data.get(0);
            this.pay_order_sn = data.get(1);
            presenter.orderPay();
        }
        isOrderDone = true;
    }

    @Override
    public String shipping_id() {
        return shipping_id;
    }

    @Override
    public String payment_id() {
        return pay_type_id;
    }


    @Override
    public String postscript() {
        return edPostscript.getText().toString();
    }


    @Override
    public void callbackOrderPaySuccess(OtherPayEntity entity) {
        if (pay_type_id.equals("12")) {
            OtherPayEntity.PaaCreatorEntity paaCreatorEntity = entity.getPaaCreatorEntity();
            if (paaCreatorEntity != null) {
                JSONObject payData = PaaCreator.randomPaa(paaCreatorEntity);
                APPayAssistEx.startPay(this, payData.toString(), APPayAssistEx.MODE_PRODUCT);
            }
        }
        if (pay_type_id.equals("13")) {
            OtherPayEntity.WeChatEntity weChatEntity = entity.getWeChatEntity();
            if (weChatEntity != null) {
                PayWeChat payWeChat = new PayWeChat(this, App.getApp(), new PayHandler());
                payWeChat.payBack(weChatEntity);
            }
        }
        if (pay_type_id.equals("14")) {
            OtherPayEntity.TreasureEntity treasureEntity = entity.getTreasureEntity();
            if (treasureEntity != null) {
                PayTreasure payTreasure = new PayTreasure(this, new PayHandler());
                payTreasure.payBack(treasureEntity.getPayInfo());
            }
        }
    }

    @Override
    public void btnClickSuccess(String data) {
        App.getApp().setTakeOrderSuccess(true);
        showToast(data);
        finish();
    }

    @Override
    public void showError(String msg) {
        if (!gouwuxiangqingGouwuBtnZhifu.isClickable()) {
            gouwuxiangqingGouwuBtnZhifu.setClickable(true);
        }
        showToast(msg);
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }


    public void setOrder(Order.OrderDetail orderDetail) {
        if (orderDetail.getOrder() != null) {
            pay_type_id = orderDetail.getOrder().getPay_id();
            addressName.setText(orderDetail.getOrder().getConsignee() + "");
            addressPhone.setText(orderDetail.getOrder().getMobile() + "");
            addressDizhi.setText(orderDetail.getOrder().getDetail_address() + "");
            tvShopName.setText(orderDetail.getOrder().getShop_name() + "");
            tvNumber.setText(orderDetail.getOrder().getOrder_sn() + "");
            tvGoodTime.setText(orderDetail.getOrder().getAdd_time() + "");
            tvShippingName.setText(orderDetail.getOrder().getShipping_name() + "");
            tvPayName.setText(orderDetail.getOrder().getPay_name() + "");
            tvPayFee.setText(orderDetail.getOrder().getPay_fee() + "");
            tvInvPayee.setText(orderDetail.getOrder().getInv_payee() + "");
            tvInvContent.setText(orderDetail.getOrder().getInv_content() + "");
            tvGoodsAmount.setText("￥" + orderDetail.getOrder().getGoods_amount() + "");
            tvYouhui.setText("—￥" + orderDetail.getOrder().getCard_fee());
            tvOrderAmount.setText(orderDetail.getOrder().getOrder_amount() + "");
            if (orderDetail.getOrder().getStatus().equals("已确认") && state == null) {
                llConfirm.setVisibility(View.VISIBLE);
                tvStatus.setText(orderDetail.getOrder().getStatus() + "");
            }
        }
    }

    public void setOrder2(OrderSubmit orderSubmit) {
        if (orderSubmit != null) {
            addressName.setText(orderSubmit.getConsignee().getConsignee() + "");
            addressPhone.setText(orderSubmit.getConsignee().getMobile() + "");
            String regin = orderSubmit.getConsignee().getRegion();
            if (regin.contains("&nbsp;")) {
                regin = regin.replace("&nbsp;", " ");
            }
            addressDizhi.setText(regin + "");
            tvShopName.setText(orderSubmit.getList().get(0).getRu_name() + "");
            tvNumber.setText(orderSubmit.getList().get(0).getGoods_count() + "");
            linGoodTime.setVisibility(View.GONE);
            if (orderSubmit.getShopping().size() > 0) {
                setShippingId(0);
            }
            if (orderSubmit.getPayment_list().size() > 0) {
                setPayTypeId(0);
            }
//            tvInvContent.setText(orderSubmit.getTotal().getOrder().getInv_content() + "");
            tvGoodsAmount.setText("￥" + orderSubmit.getTotal().getGoods_price() + "");
            reYouhui.setVisibility(View.GONE);
            tvOrderAmount.setText(orderSubmit.getTotal().getMarket_price_formated() + "");
        }
    }


    AdapterView.OnItemClickListener shippingClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            setShippingId(position);
            shippingDialog.dismiss();
        }
    };
    AdapterView.OnItemClickListener payTypeClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            setPayTypeId(position);
            payTypeDialog.dismiss();
        }
    };

    private void setShippingId(int position) {
        OrderSubmit.ShippingBean shipingBean = orderSubmit.getShopping().get(position);
        tvShippingName.setText(shipingBean.getShopping_name());
        if (shipingBean.getShopping_fee().contains("免运费")) {
            tvShippingFee.setText(" " + shipingBean.getShopping_fee());
        } else {
            tvShippingFee.setText(" 手续费:" + shipingBean.getShopping_fee() + "元");
        }
        shipping_id = shipingBean.getShopping_id();
    }

    private void setPayTypeId(int position) {
        OrderSubmit.PaymentBean payTypeBean = orderSubmit.getPayment_list().get(position);
        tvPayName.setText(payTypeBean.getPay_name());
        tvPayFee.setText(" 手续费:" + payTypeBean.getPay_fee() + "元");
        pay_type_id = payTypeBean.getPay_id();
    }


    public class PayHandler extends Handler {
        @Override
        public void handleMessage(final Message msg) {
            String code = msg.getData().getString(Config.Pay.PAY_STAUTS);
            if (code.equals(Config.Pay.PAY_SUCCESS)) {
                isSuccess = true;
                Intent intent = new Intent(OrderDetailsActivity.this, PaySuccessActivity.class);
                intent.putExtra(PaySuccessActivity.ORDERID, pay_order_id);
                intent.putExtra(PaySuccessActivity.ORDERSN, pay_order_sn);
                startActivity(intent);
                finish();
            } else {
                showToast("支付失败");
                if (!gouwuxiangqingGouwuBtnZhifu.isClickable()) {
                    gouwuxiangqingGouwuBtnZhifu.setClickable(true);
                }

                if (pay_order_id != null && type == 2) {
                    Intent intent = new Intent(OrderDetailsActivity.this, OrderDetailsActivity.class);
                    intent.putExtra(OrderDetailsActivity.ORDERID, pay_order_id);
                    intent.putExtra(OrderDetailsActivity.TYPE, 1);
                    intent.putExtra(OrderDetailsActivity.STATE, "付款");
                    startActivity(intent);
                    finish();
                }

            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (APPayAssistEx.REQUESTCODE == requestCode) {
            if (null != data) {
                String payRes = null;
                try {
                    JSONObject resultJson = new JSONObject(data.getExtras().getString("result"));
                    payRes = resultJson.getString(APPayAssistEx.KEY_PAY_RES);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (null != payRes && payRes.equals(APPayAssistEx.RES_SUCCESS)) {
                    isSuccess = true;
                    showToast("支付成功！");
                    Intent intent = new Intent(OrderDetailsActivity.this, PaySuccessActivity.class);
                    intent.putExtra(PaySuccessActivity.ORDERID, pay_order_id);
                    intent.putExtra(PaySuccessActivity.ORDERSN, pay_order_sn);
                    startActivity(intent);
                    finish();
                } else {
                    showToast("支付失败！");
                    if (!gouwuxiangqingGouwuBtnZhifu.isClickable()) {
                        gouwuxiangqingGouwuBtnZhifu.setClickable(true);
                    }

                    if (pay_order_id != null && type == 2) {
                        Intent intent = new Intent(OrderDetailsActivity.this, OrderDetailsActivity.class);
                        intent.putExtra(OrderDetailsActivity.ORDERID, pay_order_id);
                        intent.putExtra(OrderDetailsActivity.TYPE, 1);
                        intent.putExtra(OrderDetailsActivity.STATE, "付款");
                        startActivity(intent);
                        finish();
                    }
                }
            }
        }
    }
}
