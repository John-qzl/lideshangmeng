package com.lidegou.lideshangmeng.mobile.ui.personal.dosingle.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Single;
import com.lidegou.lideshangmeng.mobile.ui.personal.dosingle.paySingle.SinlePayActivity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MineDoSingleApater extends BaseAdapter {

    private List<Single.Data> singleList;
    private Context context;

    public MineDoSingleApater(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return singleList.size();
    }

    @Override
    public Object getItem(int position) {
        return singleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setDataList(List<Single.Data> dataList) {
        this.singleList = dataList;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_list_single, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final Single.Data single = singleList.get(position);
        holder.tvTitle.setText(single.getGoodsname());
        holder.tvTime.setText("做单时间：" + single.getAdddate());
        holder.tvName.setText("商品数量：" + single.getGoodsnum() + " 买家：" + single.getBuy_name());
        holder.tvStatus.setText(single.getOrder_status());
        holder.tvNumber.setText("清单编号：" + single.getQingdanbianhao());
        holder.tvPrice.setText(single.getJine());
        holder.tvState.setText("审核状态：" + single.getIs_auditing());
        if (single.getRemark().equals("")) {
            holder.lineFeeback.setVisibility(View.GONE);
        } else {
            holder.lineFeeback.setVisibility(View.VISIBLE);
            holder.tvFeedback.setText(single.getRemark());
        }
        if (single.getIs_pay().equals("0")) {
            holder.btnPay.setVisibility(View.VISIBLE);
            holder.btnPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SinlePayActivity.class);
                    intent.putExtra("id", single.getId());
                    context.startActivity(intent);
                }
            });
        } else {
            holder.btnPay.setVisibility(View.GONE);
        }
        return view;
    }

    class ViewHolder {
        View itemView;
        ImageView ivImg;
        TextView tvTitle;
        TextView tvTime;
        TextView tvName;
        TextView tvState;
        LinearLayout lineFeeback;
        TextView tvFeedback;
        TextView tvStatus;
        TextView tvNumber;
        TextView tvPrice;
        Button btnPay;

        public ViewHolder(View itemView) {
            this.itemView = itemView;

            ivImg = (ImageView) itemView.findViewById(R.id.mess_img);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_single_title);
            tvTime = (TextView) itemView.findViewById(R.id.tv_single_time);
            tvName = (TextView) itemView.findViewById(R.id.tv_single_name);
            tvState = (TextView) itemView.findViewById(R.id.tv_single_state);
            lineFeeback = (LinearLayout) itemView.findViewById(R.id.line_single_feedback);
            tvFeedback = (TextView) itemView.findViewById(R.id.tv_single_feedback);
            tvStatus = (TextView) itemView.findViewById(R.id.tv_single_status);
            tvNumber = (TextView) itemView.findViewById(R.id.tv_single_number);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_single_price);
            btnPay = (Button) itemView.findViewById(R.id.btn_pay);
        }
    }
}