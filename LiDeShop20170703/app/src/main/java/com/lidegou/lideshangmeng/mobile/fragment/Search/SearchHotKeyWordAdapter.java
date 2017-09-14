package com.lidegou.lideshangmeng.mobile.fragment.Search;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;

import java.util.List;

public class SearchHotKeyWordAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mdata;
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public SearchHotKeyWordAdapter(List<String> data, Context context) {
        this.mContext = context;
        this.mdata = data;
    }

    public void refresh(List<String> data) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext,
                    R.layout.search_hotkeyword_gv_item, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView
                    .findViewById(R.id.search_hotkeyword_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(mdata.get(position).toString());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.callbackClickListener(mdata.get(position).toString());;
                }
            }
        });
        return convertView;
    }

    private static class ViewHolder {
        TextView textView;

    }

    public interface OnClickListener {
        void callbackClickListener(String keyword);
    }
}
