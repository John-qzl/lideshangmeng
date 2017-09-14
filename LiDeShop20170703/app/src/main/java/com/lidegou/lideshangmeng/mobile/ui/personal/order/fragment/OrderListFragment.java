package com.lidegou.lideshangmeng.mobile.ui.personal.order.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.allinpay.appayassistex.APPayAssistEx;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Order;
import com.lidegou.lideshangmeng.mobile.data.entity.OtherPayEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.ShopOrderEntity;
import com.lidegou.lideshangmeng.mobile.pay.tonly.PaaCreator;
import com.lidegou.lideshangmeng.mobile.pay.treasure.PayTreasure;
import com.lidegou.lideshangmeng.mobile.pay.wechat.PayWeChat;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.ui.personal.order.OrderListAdapter;
import com.lidegou.lideshangmeng.mobile.ui.personal.order.OrderListContract;
import com.lidegou.lideshangmeng.mobile.ui.personal.order.OrderListPresenter;
import com.lidegou.lideshangmeng.mobile.ui.personal.order.details.OrderDetailsActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.order.fragment.orderevlustion.OrderEvaluationActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.order.success.PaySuccessActivity;
import com.lidegou.lideshangmeng.mobile.util.dialog.LoadingDialog;
import com.lidegou.lideshangmeng.mobile.util.dialog.PromptDialog;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class OrderListFragment extends BaseFragment implements OrderListContract.View, OrderListAdapter.OnOrdersDetailListener, PullToRefreshBase.OnRefreshListener2<ListView> {
    private PullToRefreshListView lv;
    private OrderListAdapter orderListAdapter;
    private OrderListPresenter presenter;
    private boolean isClear = false;
    private int page = 1;
    private Order order;
    //提示框
    private PromptDialog promptDialog;
    private LoadingDialog loadingDialog;
    private TextView zanwu;


    private List<Order.DataBean> dataBeanList = new ArrayList<>();
    private List<ShopOrderEntity> entities = new ArrayList<>();

    private String pay_order_id;
    private String pay_order_sn;
    private String payment_id;

    private String state;

    public OrderListFragment() {
    }

    public OrderListFragment(String state) {
        this.state = state;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tab_chat;
    }

    @Override
    protected void init() {
        lv = (PullToRefreshListView) getRootView().findViewById(R.id.commodity_order_recycler_view);
        lv.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        lv.setOnRefreshListener(this);
        orderListAdapter = new OrderListAdapter(getActivity(), dataBeanList);
        orderListAdapter.setOnOrdersDetailListener(this);
        lv.setAdapter(orderListAdapter);

        zanwu = (TextView) getRootView().findViewById(R.id.zanwu);
        if (promptDialog == null) {
            promptDialog = new PromptDialog(getContext());
        }
        loadingDialog = new LoadingDialog(getContext());

        if (presenter == null) {
            presenter = new OrderListPresenter(this);
        }
        presenter.start();

    }

    @Override
    protected void viewClick(View view) {

    }

    @Override
    protected void lazyLoad() {
        if (App.getApp().isPayOrderSuccess() || App.getApp().isTakeOrderSuccess()) {
            if (presenter == null) {
                presenter = new OrderListPresenter(this);
            }
            page = 1;
            isClear = true;
            presenter.start();
            if (App.getApp().isPayOrderSuccess()) {
                App.getApp().setPayOrderSuccess(false);
            }
            if (App.getApp().isTakeOrderSuccess()) {
                App.getApp().setTakeOrderSuccess(false);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (App.getApp().isPayOrderSuccess() || App.getApp().isTakeOrderSuccess()) {
            if (presenter == null) {
                presenter = new OrderListPresenter(this);
            }
            page = 1;
            isClear = true;
            presenter.start();
            if (App.getApp().isPayOrderSuccess()) {
                App.getApp().setPayOrderSuccess(false);
            }
            if (App.getApp().isTakeOrderSuccess()) {
                App.getApp().setTakeOrderSuccess(false);
            }
        }
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }


    @Override
    public void callbackOrderList(Order order) {
        this.order = order;
        if (isClear) {
            dataBeanList.clear();
            entities.clear();
            isClear = false;
        }

        if (order != null && order.getData().size() != 0) {
            page++;
            for (Order.DataBean dataBean : order.getData()) {
                for (Order.DataBean.OrderGoodsBean orderGoodsBean : dataBean.getOrder_goods()) {
                    entities.add(new ShopOrderEntity(dataBean.getOrder_id(), orderGoodsBean));
                }
            }
            this.dataBeanList.addAll(order.getData());
            orderListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public String status() {
        return state;
    }

    @Override
    public String page() {
        return page + "";
    }

    @Override
    public void btnClickSuccess(String msg) {
        isClear = true;
        page = 1;
        presenter.myorderList();
        showToast(msg);
    }

    @Override
    public String pay_order_id() {
        return pay_order_id;
    }

    @Override
    public String payment_id() {
        return payment_id;
    }

    @Override
    public void callbackOrderPaySuccess(OtherPayEntity entity) {
        if (payment_id.equals("9")) {
            Intent intent = new Intent(getActivity(), PaySuccessActivity.class);
            intent.putExtra(PaySuccessActivity.ORDERID, pay_order_id);
            intent.putExtra(PaySuccessActivity.ORDERSN, pay_order_sn);
            startActivity(intent);
            App.getApp().setPayOrderSuccess(true);
            onResume();
        }
        if (payment_id.equals("12")) {
            OtherPayEntity.PaaCreatorEntity paaCreatorEntity = entity.getPaaCreatorEntity();
            if (paaCreatorEntity != null) {
                JSONObject payData = PaaCreator.randomPaa(paaCreatorEntity);
                APPayAssistEx.startPay(getActivity(), payData.toString(), APPayAssistEx.MODE_PRODUCT);
            }
        }
        if (payment_id.equals("13")) {
            OtherPayEntity.WeChatEntity weChatEntity = entity.getWeChatEntity();
            if (weChatEntity != null) {
                PayWeChat payWeChat = new PayWeChat(getActivity(), App.getApp(), new PayHandler());
                payWeChat.payBack(weChatEntity);
            }
        }
        if (payment_id.equals("14")) {
            OtherPayEntity.TreasureEntity treasureEntity = entity.getTreasureEntity();
            if (treasureEntity != null) {
                PayTreasure payTreasure = new PayTreasure(getActivity(), new PayHandler());
                payTreasure.payBack(treasureEntity.getPayInfo());
            }
        }
    }

    @Override
    public void onPullDownToRefresh(final PullToRefreshBase<ListView> refreshView) {
        isClear = true;
        page = 1;
        presenter.myorderList();
        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshView.onRefreshComplete();
            }
        }, 500);
    }

    @Override
    public void onPullUpToRefresh(final PullToRefreshBase<ListView> refreshView) {
        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshView.onRefreshComplete();
            }
        }, 500);
        if (order == null || order.getTotalPage() >= page) {
            presenter.myorderList();
        } else {
            showToast("没有更多数据");
        }
    }


    @Override
    public void btnClick(final int type, final Order.DataBean needBean) {
        if (type == 1) {
            promptDialog.setMsg("是否确定" + needBean.getHandler());
            promptDialog.setOnPromptClickListener(new PromptDialog.OnPromptClickListener() {
                @Override
                public void promptConfirmClick(View view) {
                    presenter.btn_click(needBean.getBtn_url(), needBean.getOrder_id());
                    promptDialog.dismiss();
                }

                @Override
                public void promptCancelClick(View view) {
                    promptDialog.dismiss();
                }
            });
            promptDialog.show();
        } else if (type == 2) {
            showToast("正在进行支付,请稍等");
            pay_order_id = needBean.getOrder_id();
            pay_order_sn = needBean.getOrder_sn();
            payment_id = needBean.getPay_id();
            presenter.orderPay();
        } else if (type == 3) {
            startActivity(new Intent(getActivity(), OrderEvaluationActivity.class));
        }
    }


    @Override
    public void selectOrderDetail(Order.DataBean needBean) {
        Intent intent = new Intent(getContext(), OrderDetailsActivity.class);
        intent.putExtra(OrderDetailsActivity.ORDERID, needBean.getOrder_id());
        intent.putExtra(OrderDetailsActivity.STATE, needBean.getHandler());
        intent.putExtra(OrderDetailsActivity.URL, needBean.getBtn_url());
        intent.putExtra(OrderDetailsActivity.TYPE, 1);
        startActivity(intent);
    }

    public class PayHandler extends Handler {
        @Override
        public void handleMessage(final Message msg) {
            String code = msg.getData().getString(Config.Pay.PAY_STAUTS);
            if (code.equals(Config.Pay.PAY_SUCCESS)) {
                Intent intent = new Intent(getActivity(), PaySuccessActivity.class);
                intent.putExtra(PaySuccessActivity.ORDERID, pay_order_id);
                intent.putExtra(PaySuccessActivity.ORDERSN, pay_order_sn);
                startActivity(intent);
                App.getApp().setPayOrderSuccess(true);
            } else {
                showToast("支付失败");
            }
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                    showToast("支付成功！");
                    Intent intent = new Intent(getActivity(), PaySuccessActivity.class);
                    intent.putExtra(PaySuccessActivity.ORDERID, pay_order_id);
                    intent.putExtra(PaySuccessActivity.ORDERSN, pay_order_sn);
                    startActivity(intent);
                    App.getApp().setPayOrderSuccess(true);
                } else {
                    showToast("支付失败！");
                }
            }
        }
    }
}
