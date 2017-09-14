package com.lidegou.lideshangmeng.mobile.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Commodity;
import com.lidegou.lideshangmeng.mobile.event.OnRecyclerItemClickListener;
import com.lidegou.lideshangmeng.mobile.util.BaseRecyclerAdapter;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;

/**
 * Created by Administrator on 2016/12/7.
 */

public class MallClassifySearchGoodsAdapter2 extends BaseRecyclerAdapter<Commodity.Data, MallClassifySearchGoodsAdapter2.MallClassifySearchGoodsHolder> {


    private OnRecyclerItemClickListener onRecyclerItemClickListener;


    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }


    @Override
    public int getLayoutId() {
            return R.layout.item_search_goods_recyclerview2;
    }

    @Override
    public MallClassifySearchGoodsHolder getViewHolder(View view) {
        return new MallClassifySearchGoodsHolder(view);
    }

    @Override
    public void onBindViewHolder(MallClassifySearchGoodsHolder holder, Commodity.Data data, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onRecyclerItemClickListener != null) {
                    onRecyclerItemClickListener.recyclerItemClick(view, position);
                }
            }
        });
        holder.tvTitle.setText(data.getGoods_name()+"");
        holder.tvPrice.setText(data.getOrg_price()+"");
        MyImageLoader.getInstance().loadImageReturnBitMap(
                holder.ivImg,data.getGoods_img(),1
        );

    }

    class MallClassifySearchGoodsHolder extends RecyclerView.ViewHolder {

        ImageView ivImg;
        TextView tvTitle;
        TextView tvPrice;
        public MallClassifySearchGoodsHolder(View itemView) {
            super(itemView);
            ivImg = (ImageView) itemView.findViewById(R.id.iv_img);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_goods_title);
            tvPrice= (TextView) itemView.findViewById(R.id.tv_goods_price);
        }
    }

}
