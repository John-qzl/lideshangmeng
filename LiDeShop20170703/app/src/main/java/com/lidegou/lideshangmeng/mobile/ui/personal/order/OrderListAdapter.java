package com.lidegou.lideshangmeng.mobile.ui.personal.order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Order;
import com.lidegou.lideshangmeng.mobile.event.OnRecyclerItemClickListener;
import com.lidegou.lideshangmeng.mobile.ui.personal.order.listdetails.OrderLisContentAdapter;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;

import java.util.ArrayList;
import java.util.List;

import static com.lidegou.lideshangmeng.mobile.R.id.tv_all;

/**
 * Created by Administrator on 2016/8/26.
 */

public class OrderListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private OnOrdersDetailListener onOrdersDetailListener;
    private OnRecyclerItemClickListener onRecyclerItemClickListener;
    private List<Order.DataBean> shopOrderEntities;

    public OrderListAdapter(Context context, List<Order.DataBean> shopOrderEntities) {
        inflater = LayoutInflater.from(context);
        this.shopOrderEntities = shopOrderEntities;
    }

    public void setOnOrdersDetailListener(OnOrdersDetailListener onOrdersDetailListener) {
        this.onOrdersDetailListener = onOrdersDetailListener;

    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    @Override
    public int getCount() {
        return shopOrderEntities.size();
    }

    @Override
    public Order.DataBean getItem(int position) {
        return shopOrderEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyOrderListHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_orderlist, null);
            holder = new MyOrderListHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyOrderListHolder) convertView.getTag();
        }
        final Order.DataBean data = getItem(position);

        setOrderdata(holder, data.getOrder_id(), data);

        ArrayList<Order.DataBean.OrderGoodsBean> orderList = (ArrayList<Order.DataBean.OrderGoodsBean>) data.getOrder_goods();

        if (orderList.size() >= 3) {
            holder.linImg.setVisibility(View.VISIBLE);
            holder.ivImg1.setVisibility(View.VISIBLE);
            holder.ivImg2.setVisibility(View.VISIBLE);
            holder.ivImg3.setVisibility(View.VISIBLE);
            MyImageLoader.getInstance().loaderImage(orderList.get(0).getGoods_thumb(), holder.ivImg1);
            MyImageLoader.getInstance().loaderImage(orderList.get(1).getGoods_thumb(), holder.ivImg2);
            MyImageLoader.getInstance().loaderImage(orderList.get(2).getGoods_thumb(), holder.ivImg3);
        } else if (orderList.size() == 2) {
            holder.linImg.setVisibility(View.VISIBLE);
            holder.ivImg1.setVisibility(View.VISIBLE);
            holder.ivImg2.setVisibility(View.VISIBLE);
            MyImageLoader.getInstance().loaderImage(orderList.get(0).getGoods_thumb(), holder.ivImg1);
            MyImageLoader.getInstance().loaderImage(orderList.get(1).getGoods_thumb(), holder.ivImg2);
            holder.ivImg3.setVisibility(View.INVISIBLE);
        } else if (orderList.size() == 1) {
            holder.linImg.setVisibility(View.VISIBLE);
            holder.ivImg1.setVisibility(View.VISIBLE);
            MyImageLoader.getInstance().loaderImage(orderList.get(0).getGoods_thumb(), holder.ivImg1);
            holder.ivImg2.setVisibility(View.INVISIBLE);
            holder.ivImg3.setVisibility(View.INVISIBLE);
        } else {
            holder.linImg.setVisibility(View.INVISIBLE);
        }
        holder.tvAll.setText("共" + orderList.size() + "款");

        return convertView;
    }

    private void setOrderdata(MyOrderListHolder holder, String orderId, Order.DataBean needBean) {
        holder.tvShopName.setText(needBean.getUser_name());
        holder.tvType.setText(needBean.getOrder_status());
        holder.tvAllprice.setText(needBean.getTotal_fee());
        holder.tvNumber.setText(needBean.getOrder_sn());
        holder.tvGoodTime.setText(needBean.getOrder_time());
        setButton(holder, needBean);
    }

    private void setButton(final MyOrderListHolder holder, final Order.DataBean needBean) {
        holder.btn_order.setVisibility(View.VISIBLE);
        holder.tv_order.setVisibility(View.GONE);
        final String data = needBean.getHandler();
        if (data.contains("确认收货") || data.contains("取消订单")) {
            holder.btn_order.setText(data);
            setButtonListener(holder, needBean, 1);
        } else if (data.contains("付款")) {
            holder.btn_order.setText(data);
            setButtonListener(holder, needBean, 2);
        } else if (data.contains("评价")) {
            holder.btn_order.setText(data);
            setButtonListener(holder, needBean, 3);
        } else {
            holder.btn_order.setVisibility(View.GONE);
            holder.tv_order.setVisibility(View.VISIBLE);
            holder.tv_order.setText(data);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOrdersDetailListener.selectOrderDetail(needBean);
            }
        });


        holder.rv_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onOrdersDetailListener.selectOrderDetail(needBean);
            }
        });
    }

    public void setButtonListener(final MyOrderListHolder holder, final Order.DataBean needBean, final int type) {
        holder.btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOrdersDetailListener.btnClick(type, needBean);
            }
        });
    }

    class MyOrderListHolder extends RecyclerView.ViewHolder {
        TextView tvShopName;
        TextView tvType;
        TextView tvAllprice;
        TextView tvNumber;
        TextView tvGoodTime;
        ListView rv_view;
        Button btn_order;
        TextView tv_order;

        LinearLayout linImg;
        ImageView ivImg1;
        ImageView ivImg2;
        ImageView ivImg3;
        TextView tvAll;


        private OrderLisContentAdapter orderLisContentAdapter;

        public MyOrderListHolder(View itemView) {
            super(itemView);
            tvShopName = (TextView) itemView.findViewById(R.id.order_item_name);
            tvType = (TextView) itemView.findViewById(R.id.tv_wait_payment);
            tvAllprice = (TextView) itemView.findViewById(R.id.tv_allprice);
            btn_order = (Button) itemView.findViewById(R.id.btn_order);
            tv_order = (TextView) itemView.findViewById(R.id.tv_order);
            linImg = (LinearLayout) itemView.findViewById(R.id.lin_img);
            ivImg1 = (ImageView) itemView.findViewById(R.id.iv_img1);
            ivImg2 = (ImageView) itemView.findViewById(R.id.iv_img2);
            ivImg3 = (ImageView) itemView.findViewById(R.id.iv_img3);
            tvAll = (TextView) itemView.findViewById(tv_all);


            rv_view = (ListView) itemView.findViewById(R.id.list_order_content);
            tvNumber = (TextView) itemView.findViewById(R.id.tv_number);
            tvGoodTime = (TextView) itemView.findViewById(R.id.tv_good_time);
            orderLisContentAdapter = new OrderLisContentAdapter(itemView.getContext());


        }


        public void setOrderList(List<Order.DataBean.OrderGoodsBean> orderGoodsBeanList) {
            orderLisContentAdapter.setDataList(orderGoodsBeanList);
            rv_view.setAdapter(orderLisContentAdapter);
        }
    }

    public interface OnOrdersDetailListener {
        void btnClick(int type, Order.DataBean needBean);

        void selectOrderDetail(Order.DataBean needBean);
    }
}
