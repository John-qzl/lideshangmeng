package com.lidegou.lideshangmeng.mobile.ui.personal.order.listdetails;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Order;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseListAdapter;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;


/**
 * Created by Administrator on 2016/9/1.
 */

public class OrderLisContentAdapter extends BaseListAdapter<Order.DataBean.OrderGoodsBean, OrderLisContentAdapter.MyOrderListContentHolder> {

    public OrderLisContentAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_order_content;
    }

    @Override
    protected void getView(ViewGroup viewGroup, View view, MyOrderListContentHolder holder, Order.DataBean.OrderGoodsBean data, final int position) {
        MyImageLoader.getInstance().loadImageReturnBitMap(holder.iv_img, data.getGoods_thumb(), 0);
        holder.tvAll.setText("共" + data.getGoods_number() + "款");
    }

    @Override
    public MyOrderListContentHolder getViewHolder(View view) {
        return new MyOrderListContentHolder(view);
    }


    class MyOrderListContentHolder extends BaseListAdapter.ViewHolder {
        ImageView iv_img;
        TextView tvAll;

        public MyOrderListContentHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            tvAll = (TextView) itemView.findViewById(R.id.tv_all);
        }
    }

}
