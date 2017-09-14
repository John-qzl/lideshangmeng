package com.lidegou.lideshangmeng.mobile.fragment.Class;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Classify;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */

public class MallClassifyAdaper extends BaseAdapter {
    private Context context;
    private List<Classify> classifyList;
    int mSelect = 0;   //选中项

    public MallClassifyAdaper(Context context, List<Classify> classifyList) {
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

    public void changeSelected(int positon) { //刷新方法
        if (positon != mSelect) {
            mSelect = positon;
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_mall_classify, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if (mSelect == i) {
            holder.classifyName.setTextColor(Color.parseColor("#FF0000"));  //其他项背景;  //选中项背景
        } else {
            holder.classifyName.setTextColor(Color.parseColor("#000000"));  //其他项背景
        }
        Classify classify = classifyList.get(i);
        holder.classifyName.setText(classify.getName());
        return view;
    }

    class ViewHolder {

        View itemView;
        TextView classifyName;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
            classifyName = (TextView) itemView.findViewById(R.id.classifyName);
        }

    }


}
