package com.lidegou.lideshangmeng.mobile.ui.personal.order.fragment.orderevlustion;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.OrderEvaluationEntity;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public class OrderEvluationAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private OrderEvluationListener evluationListener;
    private List<OrderEvaluationEntity.Data> allData;

    public OrderEvluationAdapter(Context context, List<OrderEvaluationEntity.Data> allData) {
        inflater = LayoutInflater.from(context);
        this.allData = allData;
    }

    public void setOnOrderEvluationListener(OrderEvluationListener evluationListener) {
        this.evluationListener = evluationListener;

    }

    @Override
    public int getCount() {
        return allData.size();
    }

    @Override
    public OrderEvaluationEntity.Data getItem(int position) {
        return allData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        OrderEvluationHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_wait_comment, null);
            holder = new OrderEvluationHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (OrderEvluationHolder) convertView.getTag();
        }
        final OrderEvaluationEntity.Data data = getItem(position);
        MyImageLoader.getInstance().loaderImage(data.getGoods_thumb(), holder.ivGoodImage);
        holder.tvGoodName.setText(data.getGoods_name());
        holder.btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evluationListener.btnClick(data.getOrder_id(), data.getGoods_id(), data.getGoods_name(), data.getGoods_thumb());
            }
        });
        return convertView;
    }


    class OrderEvluationHolder extends RecyclerView.ViewHolder {
        ImageView ivGoodImage;
        TextView tvGoodName;
        Button btnComment;

        public OrderEvluationHolder(View itemView) {
            super(itemView);
            ivGoodImage = (ImageView) itemView.findViewById(R.id.iv_good_image);
            tvGoodName = (TextView) itemView.findViewById(R.id.tv_good_name);
            btnComment = (Button) itemView.findViewById(R.id.btn_comment);
        }
    }

    public interface OrderEvluationListener {
        void btnClick(String order_id, String good_id, String good_name, String good_image);
    }
}
