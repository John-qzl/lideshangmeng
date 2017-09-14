package com.lidegou.lideshangmeng.mobile.util.dialog.select;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;

import java.util.ArrayList;


/**
 * Created by Y on 2017/1/22.
 */

public class SelectDialogAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<String> list;

    public SelectDialogAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_dialog_select, null);
        }
        TextView dialog_select_text = (TextView) convertView.findViewById(R.id.dialog_select_text);
        dialog_select_text.setText(getItem(position));
        return convertView;
    }
}
