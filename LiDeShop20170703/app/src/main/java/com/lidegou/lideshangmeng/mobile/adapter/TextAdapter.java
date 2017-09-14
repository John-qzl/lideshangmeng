package com.lidegou.lideshangmeng.mobile.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Y on 2016/12/2.
 */
public class TextAdapter extends PagerAdapter {
    private List<TextView> data;
    Context context;

    public TextAdapter(List<TextView> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        //返回一个无穷大的值，
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {

        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //注意，这里什么也不做!!!

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TextView text = data.get(position % data.size());
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp = text.getParent();
        if (vp != null) {
            ViewGroup vg = (ViewGroup) vp;
            vg.removeView(text);
        }
        text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.i("TAG", "wqeriajnlkasdjf");
                        break;
                }
                return false;
            }
        });
        container.addView(data.get(position % data.size()));
        return data.get(position % data.size());
    }


}