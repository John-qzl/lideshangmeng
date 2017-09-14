package com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.applyRecord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.MoneyManage;

import java.util.List;

/**
 * Created by Administrator on 2016/12/16.
 */

public class ApplyRecordApapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<MoneyManage.ApplyRecord.DataBean> list;

    public ApplyRecordApapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<MoneyManage.ApplyRecord.DataBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public MoneyManage.ApplyRecord.DataBean getItem(int position) {
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
        MoneyManage.ApplyRecord.DataBean data = getItem(position);
        TextView tvType = (TextView) convertView.findViewById(R.id.tv_type);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tv_time);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
        TextView tvStatus = (TextView) convertView.findViewById(R.id.tv_status);
        tvType.setText(data.getType());
        tvPrice.setText(data.getAmount());
        tvTime.setText(data.getAdd_time());
        tvStatus.setText(data.getPay_status());
        return convertView;
    }

}
