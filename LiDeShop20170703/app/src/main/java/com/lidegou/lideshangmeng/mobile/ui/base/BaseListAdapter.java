package com.lidegou.lideshangmeng.mobile.ui.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Y on 2016/8/12.
 */

public abstract class BaseListAdapter<T, VH extends BaseListAdapter.ViewHolder> extends BaseAdapter {

    private Context context;
    private List<T> dataList;

    public BaseListAdapter(Context context) {
        this.context = context;
        dataList = new ArrayList<>();
    }

    public void setDataList(List<T> dataList) {
        if (dataList == null || dataList.size() <= 0) {
            this.dataList.clear();
        } else {
            if (this.dataList != null || this.dataList.size() > 0) {
                this.dataList.clear();
            }
            this.dataList.addAll(dataList);
        }
        notifyDataSetChanged();
    }

    public void setDataList2(List<T> dataList) {
        if (dataList == null || dataList.size() <= 0) {
            this.dataList.clear();
        } else {
            if (this.dataList != null || this.dataList.size() > 0) {
                this.dataList.clear();
            }
            this.dataList.addAll(dataList);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        VH holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(getLayoutId(), null);
            holder = getViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (VH) view.getTag();
        }
        getView(viewGroup, view, holder, dataList.get(i), i);
        return view;
    }

    protected abstract VH getViewHolder(View view);

    protected abstract int getLayoutId();

    protected abstract void getView(ViewGroup viewGroup, View view, VH holder, T data, int position);

    public class ViewHolder {

        public View itemView;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
        }

    }

}
