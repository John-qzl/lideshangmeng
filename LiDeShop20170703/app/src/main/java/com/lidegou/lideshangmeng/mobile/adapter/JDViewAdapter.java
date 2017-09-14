package com.lidegou.lideshangmeng.mobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Announcement;
import com.lidegou.lideshangmeng.mobile.util.view.JDAdverView;

import java.util.List;

/**
 * Created by Administrator on 2016/3/20.
 * 广告栏数据适配器
 */
public class JDViewAdapter {
    private List<Announcement> mDatas;
    private Context context;
    public JDViewAdapter(Context context,List<Announcement> mDatas) {
        this.mDatas = mDatas;
        this.context=context;
        if (mDatas == null || mDatas.isEmpty()) {
            throw new RuntimeException("nothing to show");
        }
    }



    /**
     * 获取数据的条数
     *
     * @return
     */
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    /**
     * 获取摸个数据
     *
     * @param position
     * @return
     */
    public Announcement getItem(int position) {
        return mDatas.get(position);
    }

    /**
     * 获取条目布局
     *
     * @param parent
     * @return
     */
    public View getView(JDAdverView parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_announcement, null);
    }

    /**
     * 条目数据适配
     *
     * @param view
     * @param data
     */
    public void setItem(final View view, final Announcement data) {
        for (int i = 0; i < mDatas.size(); i = i + 2) {
            TextView tv = (TextView) view.findViewById(R.id.title1);
            tv.setText(mDatas.get(i).getTitle().toString());
            TextView tag = (TextView) view.findViewById(R.id.tag);
            tag.setText(mDatas.get(i).getCat_name().toString());
            //你可以增加点击事件
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //比如打开url
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse("http://www.lidegou.com" + data.getUrl());
                    intent.setData(content_url);
                    context.startActivity(intent);
                }
            });
        }
    }
}
