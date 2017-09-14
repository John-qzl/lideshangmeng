package com.lidegou.lideshangmeng.mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.HomeClassify;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */

public class MallHomeAdapter extends BaseAdapter {
    private Context context;
    private List<HomeClassify> classifyList;

    public MallHomeAdapter(Context context, List<HomeClassify> classifyList) {
        this.context = context;
        this.classifyList = classifyList;
    }

    @Override
    public int getCount() {
        return classifyList.size();
    }

    @Override
    public Object getItem(int i) {
        return classifyList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_home_classify_grid, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        HomeClassify classify = classifyList.get(i);
        holder.tvClassify.setText(classify.getClassify()+"");
        //初始化图片加载
        MyImageLoader.getInstance().init(context);;
        MyImageLoader.getInstance().loaderImage(
                classify.getIcon(),
                holder.ivImg
        );

        return view;
    }

    class ViewHolder {

        View itemView;
        ImageView ivImg;
        TextView tvClassify;

        public ViewHolder(View itemView) {
            this.itemView = itemView;

            ivImg = (ImageView) itemView.findViewById(R.id.iv_img);
            tvClassify = (TextView) itemView.findViewById(R.id.tv_classify);
        }

    }



}
