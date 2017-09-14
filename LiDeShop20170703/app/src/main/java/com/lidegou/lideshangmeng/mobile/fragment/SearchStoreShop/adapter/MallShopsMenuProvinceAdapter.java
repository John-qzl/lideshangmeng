package com.lidegou.lideshangmeng.mobile.fragment.SearchStoreShop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Province;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */

public class MallShopsMenuProvinceAdapter extends BaseAdapter{

    private Context context;
    private List<Province> provinceList;
    private OnClickLisener onClickLisener;
    public MallShopsMenuProvinceAdapter(Context context, List<Province> provinceList) {
        this.context = context;
        this.provinceList = provinceList;
    }

    public void setOnClickLisener(OnClickLisener onClickLisener) {
        this.onClickLisener = onClickLisener;
    }

    @Override
    public int getCount() {
        return provinceList.size();
    }

    @Override
    public Object getItem(int i) {
        return provinceList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        MallShopsMenuProvinceAdapter.ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_province_text, null);
            holder = new MallShopsMenuProvinceAdapter.ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (MallShopsMenuProvinceAdapter.ViewHolder) view.getTag();
        }

        Province province = provinceList.get(i);
        holder.tvProvince.setText(province.getRegion_name().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickLisener!=null){
                    onClickLisener.onClickProvince(v,i);
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
        void onClickProvince(View view, int i);
    }

}
