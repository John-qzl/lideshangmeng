package com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.tixian;

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
 * Created by Administrator on 2017/2/3.
 */

public class WithdrawalAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<MoneyManage.Accountraply.BankBean> bankBeanArrayList;

    public WithdrawalAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<MoneyManage.Accountraply.BankBean> bankBeanArrayList) {
        this.bankBeanArrayList = bankBeanArrayList;
    }

    @Override
    public int getCount() {
        return bankBeanArrayList.size();
    }

    public MoneyManage.Accountraply.BankBean getItem(int position) {
        return bankBeanArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_with_drawal, null);
        }
        TextView content = (TextView) convertView.findViewById(R.id.content);
        content.setText(getItem(position).getBank_name() + "  " + getItem(position).getBank_card());
        return convertView;
    }
}
