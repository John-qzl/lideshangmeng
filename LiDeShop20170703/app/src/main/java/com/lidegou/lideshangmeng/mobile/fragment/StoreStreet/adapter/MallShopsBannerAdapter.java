package com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;
import com.lidegou.lideshangmeng.mobile.util.dialog.BigPicDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Y on 2016/8/10.
 */

public class MallShopsBannerAdapter extends PagerAdapter {

    private Context context;
    private List<String> bannerList;

    private OnBannerClickListener onBannerClickListener;

    public MallShopsBannerAdapter(Context context, List<String> bannerList) {
        this.context = context;
        this.bannerList = bannerList;
    }

    public void setOnBannerClickListener(OnBannerClickListener onBannerClickListener) {
        this.onBannerClickListener = onBannerClickListener;
    }

    @Override
    public int getCount() {
        return bannerList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        String banner = bannerList.get(position);
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(container.getLayoutParams());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setTag(banner);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> list = new ArrayList<String>();
                list.addAll(bannerList);
                BigPicDialog dialog = new BigPicDialog(context);
                dialog.show(list, true);
            }
        });


        MyImageLoader.getInstance().loaderImage(banner, imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public interface OnBannerClickListener {

        void itemClickBanner(View view, int position);

    }

}
