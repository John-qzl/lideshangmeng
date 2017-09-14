package com.lidegou.lideshangmeng.mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.MyLike;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */

public class MallHomeMyLikeAdapter extends BaseAdapter {

    private Context context;
    private List<MyLike> myLikeList;
    private int inSampleSize;

    public MallHomeMyLikeAdapter(Context context, List<MyLike> myLikeList) {
        this.context = context;
        this.myLikeList = myLikeList;
    }

    @Override
    public int getCount() {
        return myLikeList.size();
    }

    @Override
    public Object getItem(int i) {
        return myLikeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        MallHomeMyLikeAdapter.ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_home_mylike_grid, null);
            holder = new MallHomeMyLikeAdapter.ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (MallHomeMyLikeAdapter.ViewHolder) view.getTag();
        }

        MyLike goods = myLikeList.get(i);
        if (goods.getGoods_name().length() > 20) {
            holder.tvTitle.setText(goods.getGoods_name().substring(0, 20) + "...");
        } else {
            holder.tvTitle.setText(goods.getGoods_name() + "");
        }
        holder.tvPrice.setText(goods.getOrg_price() + "");
        MyImageLoader.getInstance().loaderImage(goods.getGoods_img(), holder.ivImg);
        return view;
    }


    class ViewHolder {

        View itemView;
        ImageView ivImg;
        TextView tvTitle;
        TextView tvPrice;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
            ivImg = (ImageView) itemView.findViewById(R.id.iv_img);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_mylike_title);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_mylike_price);
        }

    }

}
