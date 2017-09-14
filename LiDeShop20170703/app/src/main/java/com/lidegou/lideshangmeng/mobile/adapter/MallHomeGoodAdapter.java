package com.lidegou.lideshangmeng.mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Commodity;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;

import java.util.List;

import static com.lidegou.lideshangmeng.mobile.R.id.iv_new;

/**
 * Created by Administrator on 2016/12/6.
 */

public class MallHomeGoodAdapter extends BaseAdapter {

    private Context context;
    private List<Commodity.Data> goodsList;

    public MallHomeGoodAdapter(Context context, List<Commodity.Data> goodsList) {
        this.context = context;
        this.goodsList = goodsList;
    }

    @Override
    public int getCount() {
        return goodsList.size();
    }

    @Override
    public Object getItem(int i) {
        return goodsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MallHomeGoodAdapter.ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_home_goods_grid, null);
            holder = new MallHomeGoodAdapter.ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (MallHomeGoodAdapter.ViewHolder) view.getTag();
        }

        Commodity.Data goods = goodsList.get(i);
        if (goods.getGoods_name().length() > 20) {
            holder.tvTitle.setText(goods.getGoods_name().substring(0, 20) + "...");
        } else {
            holder.tvTitle.setText(goods.getGoods_name() + "");
        }
        if (goods.getIs_new().equals("0")) {
            holder.ivNew.setVisibility(View.VISIBLE);
        } else {
            holder.ivNew.setVisibility(View.GONE);
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
        ImageView ivNew;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
            ivImg = (ImageView) itemView.findViewById(R.id.iv_img);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_goods_title);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_goods_price);
            ivNew = (ImageView) itemView.findViewById(iv_new);
        }

    }

}
