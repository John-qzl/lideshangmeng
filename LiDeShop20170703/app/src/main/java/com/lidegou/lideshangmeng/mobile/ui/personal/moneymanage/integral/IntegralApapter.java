package com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.integral;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.IntegralEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/16.
 */

public class IntegralApapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<IntegralEntity.Data> list;

    public IntegralApapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<IntegralEntity.Data> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public IntegralEntity.Data getItem(int position) {
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
        IntegralEntity.Data data = getItem(position);
        TextView tvType = (TextView) convertView.findViewById(R.id.tv_type);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tv_time);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
        TextView tvStatus = (TextView) convertView.findViewById(R.id.tv_status);
        tvType.setText(data.getType());
        tvPrice.setText(data.getSend_money());
        tvTime.setText(data.getTime());
        tvStatus.setText(data.getStatus());
        return convertView;
    }

}
