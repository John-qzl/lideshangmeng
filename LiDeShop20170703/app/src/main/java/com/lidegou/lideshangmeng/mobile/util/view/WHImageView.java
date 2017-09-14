package com.lidegou.lideshangmeng.mobile.util.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Y on 2017/2/13.
 */

public class WHImageView extends ImageView {
    public WHImageView(Context context) {
        super(context);
    }

    public WHImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WHImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(w, w);
    }
}
