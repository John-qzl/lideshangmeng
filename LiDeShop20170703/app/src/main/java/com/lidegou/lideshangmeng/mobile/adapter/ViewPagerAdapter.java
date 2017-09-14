package com.lidegou.lideshangmeng.mobile.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;

import java.util.List;

/**
 * Created by Y on 2017/2/5.
 */


public class ViewPagerAdapter extends PagerAdapter {
    private List<String> list;
    private Context context;
    private Dialog dialog;

    public ViewPagerAdapter(Context context, List<String> list, Dialog dialog) {
        this.context = context;
        this.list = list;
        this.dialog = dialog;
    }


    @Override
    public int getCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_banner, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_banner_img);
        MyImageLoader.getInstance().loadImageReturnBitMap(imageView, list.get(position), 0);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}