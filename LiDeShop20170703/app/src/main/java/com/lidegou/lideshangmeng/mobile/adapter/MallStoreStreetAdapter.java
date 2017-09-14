package com.lidegou.lideshangmeng.mobile.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.ShopsStore;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */

public class MallStoreStreetAdapter extends BaseAdapter {
    private LayoutInflater inflate;
    private List<ShopsStore> shopsStoreList;

    public MallStoreStreetAdapter(Context context) {
        inflate = LayoutInflater.from(context);
    }

    public void setData(List<ShopsStore> shopsStoreList) {
        this.shopsStoreList = shopsStoreList;
    }

    @Override
    public int getCount() {
        return shopsStoreList.size();
    }

    @Override
    public ShopsStore getItem(int position) {
        return shopsStoreList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MallClassifySearchGoodsHolder holder = null;
        if (convertView == null) {
            convertView = inflate.inflate(R.layout.item_shopstore_recyclerview, null);
            holder = new MallClassifySearchGoodsHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MallClassifySearchGoodsHolder) convertView.getTag();
        }
        final ShopsStore data = shopsStoreList.get(position);

        holder.tvTitle.setText(data.getShop_name() + "");
        holder.tvDistance.setText(data.getGaze_number() + "人");
        holder.tvMi.setText(data.getKm() + "");
        holder.to_find_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gotoCallBack != null) {
                    gotoCallBack.go(data.getShop_name(), data.getLongitude(), data.getLatitude());
                }
            }
        });
        MyImageLoader.getInstance().loaderImage(
                data.getShop_logo(), holder.ivImg, 1
        );
        if (data.getGaze_status().equals("1")) {
            holder.ivAttention.setImageResource(R.drawable.attention_unselect);
        } else {
            holder.ivAttention.setImageResource(R.drawable.attention_select);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gotoCallBack != null) {
                    gotoCallBack.onItemClick(position);
                }
            }
        });
        holder.ivAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gotoCallBack != null) {
                    gotoCallBack.attention(data.getUser_id());
                }
            }
        });
        return convertView;
    }

    class MallClassifySearchGoodsHolder extends RecyclerView.ViewHolder {
        ImageView to_find_route;
        ImageView ivImg;
        TextView tvTitle;
        TextView tvMi;
        TextView tvDistance;
        ImageView ivAttention;


        public MallClassifySearchGoodsHolder(View itemView) {
            super(itemView);
            ivImg = (ImageView) itemView.findViewById(R.id.iv_img);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_shops_title);
            tvMi = (TextView) itemView.findViewById(R.id.tv_mi);
            tvDistance = (TextView) itemView.findViewById(R.id.tv_shops_distance);
            to_find_route = (ImageView) itemView.findViewById(R.id.to_find_route);
            ivAttention = (ImageView) itemView.findViewById(R.id.iv_attention);
        }
    }

    public interface IGotoCallBack {
        void go(String name, String longitude, String latitude);

        void attention(String shopUid);

        void onItemClick(int position);
    }

    IGotoCallBack gotoCallBack;

    public void setGotoCallBack(IGotoCallBack gotoCallBack) {
        this.gotoCallBack = gotoCallBack;
    }
}
