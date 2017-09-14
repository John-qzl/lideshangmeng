package com.lidegou.lideshangmeng.mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsInFourEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Y on 2017/2/6.
 */

public class MerchantsInFourGridViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<MerchantsInFourEntity.Cats_Son> list = new ArrayList<>();

    public MerchantsInFourGridViewAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<MerchantsInFourEntity.Cats_Son> list) {
        this.list.clear();
        this.list.addAll(list);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public MerchantsInFourEntity.Cats_Son getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_four_gridview, null);
        }
        ImageView iv_select = (ImageView) convertView.findViewById(R.id.iv_select);
        if (getItem(position).isSelect()) {
            iv_select.setBackgroundResource(R.drawable.merchant_select);
        } else {
            iv_select.setBackgroundResource(R.drawable.merchant_unselect);
        }
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        tv_name.setText(getItem(position).getCat_name());
        return convertView;
    }
}
