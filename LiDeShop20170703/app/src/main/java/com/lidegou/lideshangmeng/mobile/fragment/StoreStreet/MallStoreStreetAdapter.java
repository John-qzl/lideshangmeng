package com.lidegou.lideshangmeng.mobile.fragment.StoreStreet;

import android.content.Context;
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
 * Created by Administrator on 2016/12/6.
 */

public class MallStoreStreetAdapter extends BaseAdapter {

    private Context context;
    private List<ShopsStore> shopsStoreList;

    public MallStoreStreetAdapter(Context context, List<ShopsStore> shopsStoreList) {
        this.context = context;
        this.shopsStoreList = shopsStoreList;
    }

    @Override
    public int getCount() {
        return shopsStoreList.size();
    }

    @Override
    public Object getItem(int i) {
        return shopsStoreList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        MallStoreStreetAdapter.ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_home_shopsstore_grid, null);
            holder = new MallStoreStreetAdapter.ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (MallStoreStreetAdapter.ViewHolder) view.getTag();
        }

        ShopsStore shopsStore = shopsStoreList.get(i);
        holder.tvTitle.setText(shopsStore.getShop_name() + "");
        //初始化图片加载
        MyImageLoader.getInstance().loaderImage(shopsStore.getShop_logo() + "", holder.ivImg, 1);

        return view;
    }

    class ViewHolder {

        View itemView;
        ImageView ivImg;
        TextView tvTitle;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
            ivImg = (ImageView) itemView.findViewById(R.id.iv_img);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_shops_title);
        }

    }

}
