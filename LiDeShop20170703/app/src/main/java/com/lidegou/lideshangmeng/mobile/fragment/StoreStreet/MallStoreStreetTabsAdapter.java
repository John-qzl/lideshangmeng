package com.lidegou.lideshangmeng.mobile.fragment.StoreStreet;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.ShopsStore;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */

public class MallStoreStreetTabsAdapter extends BaseAdapter {

    private Context context;
    private List<ShopsStore.Tabs> tabsList;
    private OnClickListener onClickListener;
    int mSelect = 0;   //选中项

    public MallStoreStreetTabsAdapter(Context context, List<ShopsStore.Tabs> tabsList) {
        this.context = context;
        this.tabsList = tabsList;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public int getCount() {
        return tabsList.size();
    }

    @Override
    public Object getItem(int i) {
        return tabsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void changeSelected(int positon) { //刷新方法
        if (positon != mSelect) {
            mSelect = positon;
            notifyDataSetChanged();
        }
    }


    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_store_tabs_gr, null);
            holder = new ViewHolder();
            holder.tvTitle = (TextView) convertView
                    .findViewById(R.id.search_rbn_index);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (mSelect == i) {
            holder.tvTitle.setTextColor(Color.parseColor("#FF4949"));  //选中项背景
        } else {
            holder.tvTitle.setTextColor(Color.parseColor("#000000"));  //其他项背景
        }
        ShopsStore.Tabs tabs = tabsList.get(i);
        holder.tvTitle.setText(tabs.getName()+"");
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.tabs(v, i);
                    changeSelected(i);
                }
            }
        });
        return convertView;
    }

    private static class ViewHolder {
        TextView tvTitle;
    }

    interface OnClickListener {
        void tabs(View view, int i);
    }

}
