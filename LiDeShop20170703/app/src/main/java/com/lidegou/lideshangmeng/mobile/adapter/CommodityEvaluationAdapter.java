package com.lidegou.lideshangmeng.mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Comments;
import com.lidegou.lideshangmeng.mobile.event.OnRecyclerItemClickListener;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;
import com.lidegou.lideshangmeng.mobile.util.dialog.BigPicDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/14.
 */

public class CommodityEvaluationAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private OnRecyclerItemClickListener onRecyclerItemClickListener;
    private List<Comments.DataBean> dataBeans;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }


    public CommodityEvaluationAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<Comments.DataBean> dataBeans) {
        this.dataBeans = dataBeans;
    }

    @Override
    public int getCount() {
        return dataBeans.size();
    }

    @Override
    public Comments.DataBean getItem(int position) {
        return dataBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_commodity_evaluation, null);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onRecyclerItemClickListener != null) {
                    onRecyclerItemClickListener.recyclerItemClick(view, position);
                }
            }
        });
        final Comments.DataBean data = getItem(position);
        ImageView ivImg = (ImageView) convertView.findViewById(R.id.iv_img);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_time = (TextView) convertView.findViewById(R.id.tv_time);
        TextView tv_content = (TextView) convertView.findViewById(R.id.tv_content);
        RatingBar rat_xing = (RatingBar) convertView.findViewById(R.id.rat_xing);
        LinearLayout ll_evaluation_image = (LinearLayout) convertView.findViewById(R.id.ll_evaluation_image);
        ImageView iv_evaluation_image1 = (ImageView) convertView.findViewById(R.id.iv_evaluation_image1);
        ImageView iv_evaluation_image2 = (ImageView) convertView.findViewById(R.id.iv_evaluation_image2);
        ImageView iv_evaluation_image3 = (ImageView) convertView.findViewById(R.id.iv_evaluation_image3);

        int progress = Integer.parseInt(data.getRank());
        rat_xing.setProgress(2 * progress);
        tv_time.setText(data.getAdd_time());
        tv_name.setText(data.getUsername());
        tv_content.setText(data.getContent());
        setImage(ll_evaluation_image, iv_evaluation_image1, iv_evaluation_image2, iv_evaluation_image3, data);
        ll_evaluation_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigPicDialog dialog = new BigPicDialog(context);
                dialog.show(data.getThumb(), false);
            }
        });

        return convertView;
    }


    private void setImage(LinearLayout ll_evaluation_image, ImageView iv_evaluation_image1, ImageView iv_evaluation_image2, ImageView iv_evaluation_image3, Comments.DataBean data) {
        ArrayList<String> images = (ArrayList<String>) data.getThumb();
        if (images.size() == 0) {
            ll_evaluation_image.setVisibility(View.GONE);
        } else if (images.size() == 1) {
            if (images.get(0).equals("0")) {
                ll_evaluation_image.setVisibility(View.GONE);
            } else {
                ll_evaluation_image.setVisibility(View.VISIBLE);
                MyImageLoader.getInstance().loaderImage(images.get(0), iv_evaluation_image1);
            }
        } else if (images.size() == 2) {
            ll_evaluation_image.setVisibility(View.VISIBLE);
            MyImageLoader.getInstance().loaderImage(images.get(0), iv_evaluation_image1);
            MyImageLoader.getInstance().loaderImage(images.get(1), iv_evaluation_image2);
        } else if (images.size() == 3) {
            ll_evaluation_image.setVisibility(View.VISIBLE);
            MyImageLoader.getInstance().loaderImage(images.get(0), iv_evaluation_image1);
            MyImageLoader.getInstance().loaderImage(images.get(1), iv_evaluation_image2);
            MyImageLoader.getInstance().loaderImage(images.get(2), iv_evaluation_image3);
        }
    }


}
