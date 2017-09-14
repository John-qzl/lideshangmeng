package com.lidegou.lideshangmeng.mobile.fragment.home;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lidegou.lideshangmeng.mobile.data.entity.Banner;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;

import java.util.List;

/**
 * Created by Y on 2016/8/10.
 */

public class MallHomeBannerAdapter extends PagerAdapter {

    private Context context;
    private List<Banner> bannerList;

    private OnBannerClickListener onBannerClickListener;

    public MallHomeBannerAdapter(Context context, List<Banner> bannerList) {
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
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(container.getLayoutParams());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onBannerClickListener != null) {
                    onBannerClickListener.itemClickBanner(view, position);
                }
            }
        });

        Banner banner = bannerList.get(position);
        MyImageLoader.getInstance().loaderImage(
                banner.getSrc(),
                imageView
        );

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
