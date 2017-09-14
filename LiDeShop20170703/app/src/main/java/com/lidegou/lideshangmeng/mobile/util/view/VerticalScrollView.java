package com.lidegou.lideshangmeng.mobile.util.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.event.OnScrollListener;


/**
 * Created by Y on 2016/8/10.
 */

public class VerticalScrollView extends ScrollView implements View.OnClickListener{

    private int downX;
    private int downY;
    private int mTouchSlop;

    private View contentView;
    private OnBorderListener onBorderListener;
    private OnScrollListener onScrollListener;

    private ImageView goTopBtn;


    public VerticalScrollView(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public VerticalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public VerticalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public void setOnScrollListener(OnScrollListener onScrollListener,ImageView goTopBtn) {
        this.onScrollListener = onScrollListener;
        this.goTopBtn = goTopBtn;
        this.goTopBtn.setOnClickListener(this);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        doOnBorderListener();

        if (onScrollListener != null) {
            onScrollListener.onScrollListenner(t);
        }
        /**
         * 滑动距离超过500px,出现向上按钮,可以做为自定义属性
         */
        if (t >= 800)
        {
            goTopBtn.setVisibility(View.VISIBLE);
        }
        else
        {
            goTopBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) e.getRawX();
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_fragmenthome_goTop)
        {
            this.smoothScrollTo(0, 0);
        }
    }


    /**
     * OnBorderListener, Called when scroll to top or bottom
     *
     * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-5-22
     */
    public interface OnBorderListener {

        /**
         * Called when scroll to bottom
         */
        void onBottom();

        /**
         * Called when scroll to top
         */
        void onTop();
    }

    private void doOnBorderListener() {
        if (contentView != null && contentView.getMeasuredHeight() <= getScrollY() + getHeight()) {
            if (onBorderListener != null) {
                onBorderListener.onBottom();
            }
        } else if (getScrollY() == 0) {
            if (onBorderListener != null) {
                onBorderListener.onTop();
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}