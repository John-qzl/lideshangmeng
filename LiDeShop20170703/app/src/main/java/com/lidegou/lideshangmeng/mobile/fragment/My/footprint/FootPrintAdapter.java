package com.lidegou.lideshangmeng.mobile.fragment.My.footprint;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Footprint;
import com.lidegou.lideshangmeng.mobile.event.OnRecyclerItemClickListener;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;

import java.util.List;

/**
 * Created by Y on 2016/8/17.
 */

public class FootPrintAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private OnRecyclerItemClickListener onRecyclerItemClickListener;
    private List<Footprint> list;

    public FootPrintAdapter(Context context, List<Footprint> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Footprint getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        FootprintHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_foot_print, null);
            holder = new FootprintHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (FootprintHolder) convertView.getTag();
        }

        Footprint data = list.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRecyclerItemClickListener != null) {
                    onRecyclerItemClickListener.recyclerItemClick(v, position);
                }
            }
        });
        MyImageLoader.getInstance().loaderImage(data.getGoods_thumb(), holder.goods_thumb);
        holder.short_name.setText(data.getShort_name());
        holder.shop_price.setText(data.getShop_price());
        return convertView;
    }


    class FootprintHolder extends RecyclerView.ViewHolder {

        ImageView goods_thumb;
        TextView short_name;
        TextView shop_price;

        public FootprintHolder(View itemView) {
            super(itemView);

            goods_thumb = (ImageView) itemView.findViewById(R.id.goods_thumb);
            short_name = (TextView) itemView.findViewById(R.id.short_name);
            shop_price = (TextView) itemView.findViewById(R.id.shop_price);
        }
    }

}
