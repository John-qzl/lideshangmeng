package com.lidegou.lideshangmeng.mobile.ui.personal.moneymanage.accountdetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.AccountDetails;

import java.util.List;

/**
 * Created by Administrator on 2016/12/16.
 */

public class AccountDetailsApapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<AccountDetails.DataBean> datas;

    public AccountDetailsApapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<AccountDetails.DataBean> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public AccountDetails.DataBean getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_account_details, null);
        }
        AccountDetails.DataBean dataBean = getItem(position);
        TextView tvType = (TextView) convertView.findViewById(R.id.tv_type);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tv_time);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
        tvType.setText(dataBean.getChange_desc());
        tvTime.setText(dataBean.getChange_time());
        tvPrice.setText(dataBean.getAmount());
        return convertView;
    }
}
