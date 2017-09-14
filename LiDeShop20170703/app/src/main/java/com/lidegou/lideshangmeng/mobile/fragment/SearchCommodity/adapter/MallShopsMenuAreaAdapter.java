package com.lidegou.lideshangmeng.mobile.fragment.SearchCommodity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.County;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */

public class MallShopsMenuAreaAdapter extends BaseAdapter{

    private Context context;
    private List<County> countyList;
    private OnClickLisener onClickLisener;
    public MallShopsMenuAreaAdapter(Context context, List<County> countyList) {
        this.context = context;
        this.countyList = countyList;
    }

    public void setOnClickLisener(OnClickLisener onClickLisener) {
        this.onClickLisener = onClickLisener;
    }

    @Override
    public int getCount() {
        return countyList.size();
    }

    @Override
    public Object getItem(int i) {
        return countyList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        MallShopsMenuAreaAdapter.ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_province_text, null);
            holder = new MallShopsMenuAreaAdapter.ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (MallShopsMenuAreaAdapter.ViewHolder) view.getTag();
        }

        County county = countyList.get(i);
        holder.tvProvince.setText(county.getRegion_name().toString());
        holder.tvProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickLisener!=null){
                    onClickLisener.onClickArea(v,i);
                }
            }
        });
        return view;
    }

    class ViewHolder {

        View itemView;
        TextView tvProvince;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
            tvProvince = (TextView) itemView.findViewById(R.id.tv_province);
        }

    }

    public interface OnClickLisener{
        void onClickArea(View view, int i);
    }

}
