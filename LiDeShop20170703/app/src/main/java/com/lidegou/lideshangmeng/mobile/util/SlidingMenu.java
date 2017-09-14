package com.lidegou.lideshangmeng.mobile.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class SlidingMenu extends HorizontalScrollView {
	
	//�Ƿ��һ�γ�ʼ������
	private boolean once;

	//��Ļ���
	private int mScreenWidth;

	//������ʾ��һ���������С��������
	private int mScrollWidth;

	//��һ������Ŀ��
	private int mExtraViewWidth = 400;

	public SlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		mScreenWidth = getResources().getDisplayMetrics().widthPixels;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		/**
		 * ��ʾ������һ�����
		 */
		if (!once) {
			LinearLayout wrapper = (LinearLayout) getChildAt(0);
			ViewGroup contentView = (ViewGroup) wrapper.getChildAt(0);
			ViewGroup extraView = (ViewGroup) wrapper.getChildAt(1);
			mScrollWidth = mExtraViewWidth / 2;
			contentView.getLayoutParams().width = mScreenWidth;
			extraView.getLayoutParams().width = mExtraViewWidth;
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			// ������һ������
			this.scrollTo(0, 0);
			once = true;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		// Upʱ�������жϣ������ʾ������ڲ˵����һ������ȫ��ʾ����������
		case MotionEvent.ACTION_UP:
			int scrollX = getScrollX();
			if (scrollX > mScrollWidth)
				this.smoothScrollTo(mScreenWidth - mExtraViewWidth, 0);
			else
				this.smoothScrollTo(0, 0);
			return true;
		}
		return super.onTouchEvent(ev);
	}

}