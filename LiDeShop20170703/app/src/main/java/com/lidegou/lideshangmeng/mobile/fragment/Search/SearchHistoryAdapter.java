package com.lidegou.lideshangmeng.mobile.fragment.Search;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.HistoryShowBean;

import java.util.List;

public class SearchHistoryAdapter extends BaseAdapter {

    private Context mContext;
    private List<HistoryShowBean> mdata;

    public SearchHistoryAdapter(Context context) {
        this.mContext = context;

    }

    public void setData(List<HistoryShowBean> data) {
        this.mdata = data;
    }

    public void refresh(List<HistoryShowBean> data) {
        this.mdata = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext,
                    R.layout.search_history_lv_item, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView
                    .findViewById(R.id.search_history_tv);
            holder.imageView = (ImageView) convertView
                    .findViewById(R.id.search_history_iv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        HistoryShowBean bean = mdata.get(position);
        holder.textView.setText(bean.getJingdian());
        holder.imageView.setImageDrawable(bean.getImage());

        return convertView;
    }

    private static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

}
