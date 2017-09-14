package com.lidegou.lideshangmeng.mobile.fragment.SearchCommodity;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Classify;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/12/7.
 */

public class SearchCommodityAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Classify.Products> list = new ArrayList<>();
    private boolean isExid = true;

    public SearchCommodityAdapter(Context context, boolean exid) {
        inflater = LayoutInflater.from(context);
        this.isExid = exid;
    }

    public void setData(List<Classify.Products> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Classify.Products getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MallClassifySearchGoodsHolder holder;
        if (isExid) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_search_goods_recyclerview, null);
                holder = new MallClassifySearchGoodsHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (MallClassifySearchGoodsHolder) convertView.getTag();
            }
        } else {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_search_goods_recyclerview2, null);
                holder = new MallClassifySearchGoodsHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (MallClassifySearchGoodsHolder) convertView.getTag();
            }
        }
        Classify.Products data = getItem(position);
        String name = stringFilter(ToDBC(data.getGoods_name()));
        holder.tvTitle.setText(name);
        holder.tvPrice.setText(data.getOrg_price());
        MyImageLoader.getInstance().loaderImageMy(data.getGoods_img(), holder.ivImg);
        if (data.getIs_promote().equals("0")) {
            holder.ivNew.setVisibility(View.GONE);
        } else {
            holder.ivNew.setVisibility(View.VISIBLE);
        }

        holder.tv_goods_count.setText("库存:" + data.getGoods_number() + "");
        holder.tv_goods_number.setText("销量:" + data.getSales_volume() + "");
        holder.tv_goods_orgprice.setText(data.getMarket_price() + "");
        holder.tv_goods_orgprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

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


    class MallClassifySearchGoodsHolder extends RecyclerView.ViewHolder {

        ImageView ivImg;
        TextView tvTitle;
        TextView tvPrice;
        ImageView ivNew;

        TextView tv_goods_count;
        TextView tv_goods_number;
        TextView tv_goods_orgprice;


        public MallClassifySearchGoodsHolder(View itemView) {
            super(itemView);
            ivImg = (ImageView) itemView.findViewById(R.id.iv_img);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_goods_title);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_goods_price);
            ivNew = (ImageView) itemView.findViewById(R.id.iv_new);

            tv_goods_count = (TextView) itemView.findViewById(R.id.tv_goods_count);
            tv_goods_number = (TextView) itemView.findViewById(R.id.tv_goods_number);
            tv_goods_orgprice = (TextView) itemView.findViewById(R.id.tv_goods_orgprice);

        }
    }

}
