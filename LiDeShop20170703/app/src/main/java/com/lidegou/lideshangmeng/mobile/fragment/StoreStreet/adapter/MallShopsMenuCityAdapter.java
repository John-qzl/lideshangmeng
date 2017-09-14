package com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.City;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */

public class MallShopsMenuCityAdapter extends BaseAdapter{

    private Context context;
    private List<City> cityList;
    private OnClickLisener onClickLisener;
    public MallShopsMenuCityAdapter(Context context, List<City> cityList) {
        this.context = context;
        this.cityList = cityList;
    }

    public void setOnClickLisener(OnClickLisener onClickLisener) {
        this.onClickLisener = onClickLisener;
    }

    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public Object getItem(int i) {
        return cityList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        MallShopsMenuCityAdapter.ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_province_text, null);
            holder = new MallShopsMenuCityAdapter.ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (MallShopsMenuCityAdapter.ViewHolder) view.getTag();
        }

        City city = cityList.get(i);
        holder.tvProvince.setText(city.getRegion_name().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickLisener!=null){
                    onClickLisener.onClickCity(v,i);
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
        void onClickCity(View view, int i);
    }

}
