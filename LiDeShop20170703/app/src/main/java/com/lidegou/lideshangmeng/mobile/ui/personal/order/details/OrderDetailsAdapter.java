package com.lidegou.lideshangmeng.mobile.ui.personal.order.details;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Order;
import com.lidegou.lideshangmeng.mobile.event.OnRecyclerItemClickListener;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseRecyclerAdapter;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;

/**
 * Created by Administrator on 2016/8/27.
 */

public class OrderDetailsAdapter extends BaseRecyclerAdapter<Order.OrderDetail.GoodsListBean, OrderDetailsAdapter.OrderDetalisHolder> {

    private OnRecyclerItemClickListener onRecyclerItemClickListener;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_order_detalis;
    }

    @Override
    public OrderDetalisHolder getViewHolder(View view) {
        return new OrderDetalisHolder(view);
    }

    @Override
    public void onBindViewHolder(final OrderDetalisHolder holder, final Order.OrderDetail.GoodsListBean data, final int position) {
        holder.ivImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onRecyclerItemClickListener != null) {
                    onRecyclerItemClickListener.recyclerItemClick(view, position);
                }
            }
        });
        MyImageLoader.getInstance().loadImageReturnBitMap(
                holder.ivImg, data.getGoods_img(), 0

        );
        holder.tvName.setText(data.getGoods_name());
        holder.tvPrice.setText("¥ " + data.getGoods_amount());
        holder.tvNumber.setText("×" + data.getGoods_number());

    }

    class OrderDetalisHolder extends RecyclerView.ViewHolder {
        ImageView ivImg;
        TextView tvName;
        TextView tvPrice;
        TextView tvNumber;

        public OrderDetalisHolder(View itemView) {
            super(itemView);
            ivImg = (ImageView) itemView.findViewById(R.id.iv_img);
            tvName = (TextView) itemView.findViewById(R.id.tv_goods_name);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_goods_amount);
            tvNumber = (TextView) itemView.findViewById(R.id.tv_goods_number);

        }

    }

}
