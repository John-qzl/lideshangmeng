package com.lidegou.lideshangmeng.mobile.ui.personal.order.success;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.order.details.OrderDetailsActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PaySuccessActivity extends BaseActivity {
    public static String ORDERID = "order_id";
    public static String ORDERSN = "order_sn";

    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.tv_success_orderId)
    TextView tvSuccessOrderId;
    @InjectView(R.id.tv_select_order)
    TextView tvSelectOrder;

    private String order_id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_success;
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);
        App.getApp().setPayOrderSuccess(true);
        linBack.setOnClickListener(this);
        tvSelectOrder.setOnClickListener(this);
        order_id = getIntent().getStringExtra(ORDERID);
        String order_sn = getIntent().getStringExtra(ORDERSN);
        tvSuccessOrderId.setText(order_sn);
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.tv_select_order:
                Intent intent = new Intent(this, OrderDetailsActivity.class);
                intent.putExtra(OrderDetailsActivity.ORDERID, order_id);
                intent.putExtra(OrderDetailsActivity.TYPE, 1);
                intent.putExtra(OrderDetailsActivity.STATE, "已支付");
                startActivity(intent);
                finish();
                break;
        }
    }
}
