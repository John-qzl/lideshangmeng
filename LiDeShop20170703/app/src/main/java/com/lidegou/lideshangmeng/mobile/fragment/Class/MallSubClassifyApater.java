package com.lidegou.lideshangmeng.mobile.fragment.Class;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Classify;
import com.lidegou.lideshangmeng.mobile.event.OnRecyclerItemClickListener;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseRecyclerAdapter;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;

/**
 * Created by Administrator on 2016/12/16.
 */

public class MallSubClassifyApater extends BaseRecyclerAdapter<Classify.CatID, MallSubClassifyApater.MallSubClassifyHolder> {
    private OnRecyclerItemClickListener onRecyclerItemClickListener;
    private Context context;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public MallSubClassifyApater(Context context) {
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_subclass;
    }

    @Override
    public MallSubClassifyHolder getViewHolder(View view) {
        return new MallSubClassifyHolder(view);
    }

    @Override
    public void onBindViewHolder(MallSubClassifyHolder holder, final Classify.CatID data, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onRecyclerItemClickListener != null) {
                    onRecyclerItemClickListener.recyclerItemClick(view, position);
                }
            }
        });
        if (App.getApp().getShow_pic().equals("1")) {
            holder.tvName.setVisibility(View.VISIBLE);
            holder.ivImg.setVisibility(View.VISIBLE);
            holder.btnName.setVisibility(View.GONE);
            holder.tvName.setText(data.getName());
            MyImageLoader.getInstance().loaderImage(data.getCat_img(), holder.ivImg);
        } else {
            holder.tvName.setVisibility(View.GONE);
            holder.ivImg.setVisibility(View.GONE);
            holder.btnName.setVisibility(View.VISIBLE);
            holder.btnName.setText(data.getName());
            holder.btnName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRecyclerItemClickListener.recyclerItemClick(view, position);
                }
            });
        }
    }

    class MallSubClassifyHolder extends RecyclerView.ViewHolder {
        ImageView ivImg;
        TextView tvName;
        Button btnName;

        public MallSubClassifyHolder(View itemView) {
            super(itemView);
            ivImg = (ImageView) itemView.findViewById(R.id.iv_img);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            btnName = (Button) itemView.findViewById(R.id.btn_name);
        }
    }
}
