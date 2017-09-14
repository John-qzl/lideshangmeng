package com.lidegou.lideshangmeng.mobile.util.popu.pay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.chongzhi.PayType;

import java.util.ArrayList;


/**
 * Created by Y on 2017/1/21.
 */

public class PayPopuWindowAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<PayType> datas = new ArrayList<>();

    public PayPopuWindowAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<PayType> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public PayType getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pay_popu, null);
        }
        TextView pay_popu_text = (TextView) convertView.findViewById(R.id.pay_popu_text);
        pay_popu_text.setText(getItem(position).getPay_name() + " 手续费" + getItem(position).getPay_fee() + "元");
        return convertView;
    }
}
