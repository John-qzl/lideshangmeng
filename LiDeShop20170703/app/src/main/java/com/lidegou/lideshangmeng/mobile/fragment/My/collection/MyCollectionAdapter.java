package com.lidegou.lideshangmeng.mobile.fragment.My.collection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Commodity;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Y on 2016/8/17.
 */

public class MyCollectionAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Commodity.Data.CollectionCommodity.DataBean> list = new ArrayList<>();
    private OnCollectionClickListener onCollectionClickListener;


    public MyCollectionAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setOnCollectionClickListener(OnCollectionClickListener onCollectionClickListener) {
        this.onCollectionClickListener = onCollectionClickListener;
    }


    public void setData(List<Commodity.Data.CollectionCommodity.DataBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Commodity.Data.CollectionCommodity.DataBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyCollectionHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_collection, null);
            holder = new MyCollectionHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyCollectionHolder) convertView.getTag();
        }
        Commodity.Data.CollectionCommodity.DataBean data = getItem(position);
        MyImageLoader.getInstance().loaderImage(
                data.getGoods_thumb(), holder.ivImg
        );
        String goodName = stringFilter(ToDBC(data.getGoods_name()));
        holder.tvName.setText(goodName);
        holder.tvInventory.setText("库存：" + data.getGoods_number());
        holder.tvPrice.setText(data.getShop_price() + "");
        holder.imDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCollectionClickListener != null) {
                    onCollectionClickListener.deleteCollectionCommodity(v, position);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCollectionClickListener != null) {
                    onCollectionClickListener.itemCommodityDetails(position);
                }
            }
        });
        return convertView;
    }

    /**
     * 半角转换为全角
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 去除特殊字符或将所有中文标号替换为英文标号
     *
     * @param str
     * @return
     */
    public static String stringFilter(String str) {
        str = str.replaceAll("【", "[").replaceAll("】", "]")
                .replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
        String regEx = "[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }


    class MyCollectionHolder extends RecyclerView.ViewHolder {

        ImageView ivImg;
        TextView tvName;
        TextView tvInventory;
        TextView tvPrice;
        ImageView imDelete;

        public MyCollectionHolder(View itemView) {
            super(itemView);
            ivImg = (ImageView) itemView.findViewById(R.id.iv_img);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvInventory = (TextView) itemView.findViewById(R.id.tv_inventory);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            imDelete = (ImageView) itemView.findViewById(R.id.im_delete);
        }
    }

    public interface OnCollectionClickListener {
        void deleteCollectionCommodity(View view, int position);

        void itemCommodityDetails(int position);
    }

}
