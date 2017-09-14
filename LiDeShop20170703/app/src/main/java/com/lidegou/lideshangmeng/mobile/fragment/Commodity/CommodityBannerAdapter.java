package com.lidegou.lideshangmeng.mobile.fragment.Commodity;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Commodity;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;
import com.lidegou.lideshangmeng.mobile.util.dialog.BigPicDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Y on 2016/8/15.
 */

public class CommodityBannerAdapter extends PagerAdapter {

    private Context context;
    private List<Commodity.Data.CommodityDetails.PicturesBean> bannerList;

    public CommodityBannerAdapter(Context context, List<Commodity.Data.CommodityDetails.PicturesBean> bannerList) {
        this.context = context;
        this.bannerList = bannerList;
    }

    @Override
    public int getCount() {
        return bannerList.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      /*  ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(container.getLayoutParams());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
*/
        // 初始化
        View view = LayoutInflater.from(context).inflate(R.layout.item_banner, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_banner_img);
        Commodity.Data.CommodityDetails.PicturesBean banner = bannerList.get(position);
        MyImageLoader.getInstance().loadImageReturnBitMap(imageView, banner.getImg_url(), 0);
        imageView.setTag(banner.getImg_url());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> list = new ArrayList<String>();
                for (Commodity.Data.CommodityDetails.PicturesBean picturesBean : bannerList) {
                    list.add(picturesBean.getImg_url());
                }
                BigPicDialog dialog = new BigPicDialog(context);
                dialog.show(list, true);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
