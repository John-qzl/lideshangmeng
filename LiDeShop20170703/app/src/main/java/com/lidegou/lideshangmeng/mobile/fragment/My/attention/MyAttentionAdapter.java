package com.lidegou.lideshangmeng.mobile.fragment.My.attention;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.ShopsStore;
import com.lidegou.lideshangmeng.mobile.event.OnRecyclerItemClickListener;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;

import java.util.List;


/**
 * Created by Y on 2016/8/17.
 */

public class MyAttentionAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private OnAttentionListener onAttentionListener;
    private OnRecyclerItemClickListener onRecyclerItemClickListener;
    private List<ShopsStore.Attention.StoreListBean> list;

    public MyAttentionAdapter(Context context, List<ShopsStore.Attention.StoreListBean> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public void setOnAttentionListener(OnAttentionListener onAttentionListener) {
        this.onAttentionListener = onAttentionListener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ShopsStore.Attention.StoreListBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_attention, null);
        }
        ShopsStore.Attention.StoreListBean data = getItem(position);
        ImageView ivImg = (ImageView) convertView.findViewById(R.id.iv_img);
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tvInventory = (TextView) convertView.findViewById(R.id.tv_inventory);
        ImageView ivDelete = (ImageView) convertView.findViewById(R.id.iv_delete);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onRecyclerItemClickListener != null) {
                    onAttentionListener.itemMyAttention(position);
                }
            }
        });

        MyImageLoader.getInstance().loadImageReturnBitMap(
                ivImg, data.getShop_logo(), 0
        );
        tvName.setText(data.getStore_name() + "");
        tvInventory.setText(data.getCount_store() + "人关注");
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAttentionListener != null) {
                    onAttentionListener.attentionListDelete(v, position);
                }
            }
        });
        return convertView;
    }

    public interface OnAttentionListener {
        void attentionListDelete(View view, int position);

        void itemMyAttention(int position);
    }
}
