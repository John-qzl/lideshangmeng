package com.lidegou.lideshangmeng.mobile.util.dialog;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.adapter.ViewPagerAdapter;
import com.lidegou.lideshangmeng.mobile.util.MyImageAsync;

import java.util.List;

/**
 * Created by Y on 2017/2/5.
 */

public class BigPicDialog extends BaseDialog {
    private Context context;

    public BigPicDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_big_pic;
    }

    @Override
    protected void init() {
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        setCanceledOnTouchOutside(true);

        findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    protected void viewClick(View view) {

    }

    public void show(final List<String> list, boolean isSave) {
        final TextView page = (TextView) findViewById(R.id.page);
        page.setText("1/" + list.size());

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                page.setText(position + 1 + "/" + list.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ViewPagerAdapter adapter = new ViewPagerAdapter(context, list, this);
        viewPager.setAdapter(adapter);
        if (!isSave) {
            findViewById(R.id.downPic).setVisibility(View.GONE);
        }
        findViewById(R.id.downPic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("保存图片到本地");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            MyImageAsync.downPic(list.get(viewPager.getCurrentItem()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        super.show();
    }
}
