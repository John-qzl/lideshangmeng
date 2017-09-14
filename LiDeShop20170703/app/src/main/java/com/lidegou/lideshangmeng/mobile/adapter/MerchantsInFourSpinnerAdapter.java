package com.lidegou.lideshangmeng.mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsInFourEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Y on 2017/2/6.
 */

public class MerchantsInFourSpinnerAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<MerchantsInFourEntity.CatsData> list = new ArrayList<>();

    public MerchantsInFourSpinnerAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<MerchantsInFourEntity.CatsData> list) {
        this.list.clear();
        this.list.addAll(list);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public MerchantsInFourEntity.CatsData getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_four_spinner, null);
        }
        TextView content = (TextView) convertView.findViewById(R.id.content);
        content.setText(getItem(position).getCat_name());
        return convertView;
    }
}
