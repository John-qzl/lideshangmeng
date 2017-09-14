package com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.recommend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.RecommendEntity;

import java.util.List;

/**
 * Created by Y on 2017/5/17.
 */

public class RecommendAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<RecommendEntity.DataBean> list;

    public RecommendAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<RecommendEntity.DataBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public RecommendEntity.DataBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_apply_record, null);
        }
        RecommendEntity.DataBean data = getItem(position);
        TextView tvType = (TextView) convertView.findViewById(R.id.tv_type);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tv_time);
       TextView tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
        TextView tvStatus = (TextView) convertView.findViewById(R.id.tv_status);
        tvStatus.setTextColor(tvPrice.getTextColors().getDefaultColor());
        tvType.setText(data.getXm() + "");
        tvPrice.setText(data.getShopname()+"");
    /*   + "");*/
        tvTime.setText(data.getUser_name() + "");
        tvStatus.setText(data.getStatus() + "");
        return convertView;
    }
}
