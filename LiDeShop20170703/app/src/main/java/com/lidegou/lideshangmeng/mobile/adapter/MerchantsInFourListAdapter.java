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

public class MerchantsInFourListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<MerchantsInFourEntity.Cats_Son> select_cats_sons = new ArrayList<>();
    private MerchantsInFourListAdapterCallBack callBack;

    public MerchantsInFourListAdapter(Context context, MerchantsInFourListAdapterCallBack callBack) {
        inflater = LayoutInflater.from(context);
        this.callBack = callBack;
    }

    public void setData(List<MerchantsInFourEntity.Cats_Son> select_cats_sons) {
        this.select_cats_sons = select_cats_sons;
    }

    @Override
    public int getCount() {
        return select_cats_sons.size();
    }

    @Override
    public MerchantsInFourEntity.Cats_Son getItem(int position) {
        return select_cats_sons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_four_list, null);
        }
        TextView tv_number = (TextView) convertView.findViewById(R.id.tv_number);
        TextView tv_one_name = (TextView) convertView.findViewById(R.id.tv_one_name);
        TextView tv_two_name = (TextView) convertView.findViewById(R.id.tv_two_name);
        TextView tv_delete = (TextView) convertView.findViewById(R.id.tv_delete);
        tv_number.setText(position + 1 + "");
        tv_one_name.setText(getItem(position).getParent_name());
        tv_two_name.setText(getItem(position).getCat_name());
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.deleteCallBack(getItem(position));
            }
        });

        return convertView;
    }

    public interface MerchantsInFourListAdapterCallBack {
        void deleteCallBack(MerchantsInFourEntity.Cats_Son cats_son);
    }
}
