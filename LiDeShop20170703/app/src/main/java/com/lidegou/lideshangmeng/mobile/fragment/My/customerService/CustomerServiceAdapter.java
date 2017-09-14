package com.lidegou.lideshangmeng.mobile.fragment.My.customerService;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.ServiceBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */

public class CustomerServiceAdapter extends BaseAdapter {
    private Context context;
    private List<ServiceBean.Data> classifyList;
    private String headImage = "";

    public CustomerServiceAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ServiceBean.Data> classifyList) {
        this.classifyList = classifyList;
    }

    @Override
    public int getCount() {
        return classifyList.size();
    }

    @Override
    public ServiceBean.Data getItem(int i) {
        return classifyList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_serivce_grid, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (headImage.equals("")) {
            holder.ivUserImg.setImageResource(R.drawable.touxiang1);
        } else {
            ImageLoader.getInstance().displayImage(headImage, holder.ivUserImg);
        }
        holder.tvUserClassify.setText(getItem(i).getMsg_content());
        holder.tvUserTime.setText(getItem(i).getMsg_time());


        if (getItem(i).getRe_msg_content() != null && !getItem(i).getRe_msg_content().equals("")) {
            holder.reService.setVisibility(View.VISIBLE);
            holder.tvServiceClassify.setText(getItem(i).getRe_msg_content());
            holder.tvServiceTime.setText(getItem(i).getRe_msg_time());
        } else {
            holder.reService.setVisibility(View.GONE);
        }
        return view;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    class ViewHolder {

        View itemView;
        RelativeLayout reService;
        TextView tvServiceTime;
        ImageView ivServiceImg;
        TextView tvServiceClassify;
        RelativeLayout reUser;
        TextView tvUserTime;
        ImageView ivUserImg;
        TextView tvUserClassify;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
            reService = (RelativeLayout) itemView.findViewById(R.id.re_service);
            tvServiceTime = (TextView) itemView.findViewById(R.id.tv_service_time);
            ivServiceImg = (ImageView) itemView.findViewById(R.id.iv_service_img);
            tvServiceClassify = (TextView) itemView.findViewById(R.id.tv_service_classify);
            reUser = (RelativeLayout) itemView.findViewById(R.id.re_user);
            tvUserTime = (TextView) itemView.findViewById(R.id.tv_user_time);
            ivUserImg = (ImageView) itemView.findViewById(R.id.iv_user_img);
            tvUserClassify = (TextView) itemView.findViewById(R.id.tv_user_classify);
        }

    }


}
