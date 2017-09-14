package com.lidegou.lideshangmeng.mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.PPEntity;

import java.util.ArrayList;

/**
 * Created by Y on 2017/2/18.
 */

public class PPAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<PPEntity> entities = new ArrayList<>();
    private String selectId = "";

    public PPAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<PPEntity> entities) {
        this.entities = entities;
    }

    public void setSelectId(String selectId) {
        this.selectId = selectId;
    }

    @Override
    public int getCount() {
        return entities.size();
    }

    @Override
    public PPEntity getItem(int position) {
        return entities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PPHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_pp_list, null);
            holder = new PPHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (PPHolder) convertView.getTag();
        }
        PPEntity entity = getItem(position);
        holder.tvName.setText(entity.getName());
        if (selectId.equals(entity.getId())) {
            holder.ivSelect.setVisibility(View.VISIBLE);
        } else {
            holder.ivSelect.setVisibility(View.GONE);
        }
        return convertView;
    }

    class PPHolder {

        View itemView;
        TextView tvName;
        ImageView ivSelect;

        public PPHolder(View itemView) {
            this.itemView = itemView;
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            ivSelect = (ImageView) itemView.findViewById(R.id.iv_select);
        }

    }
}
