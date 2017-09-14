package com.lidegou.lideshangmeng.mobile.fragment.ShopCar;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.ShoppingCart;
import com.lidegou.lideshangmeng.mobile.fragment.Commodity.CommodityDetailsActivity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseListAdapter;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;

import java.util.ArrayList;
import java.util.List;

import static com.lidegou.lideshangmeng.mobile.R.id.view1;

/**
 * Created by Y on 2016/8/13.
 */

public class MallShopCarShopAdapter extends BaseListAdapter<ShoppingCart.DataBean.GoodsListBean, MallShopCarShopAdapter.MallShopCarListContentHolder> {

    private GoodAdapterCallBack callBack;
    private int selectNumber = 0;

    private OnShopCarClickListener onShopCarClickListener;
    private MallShopCarFragment context;
    private List<ShoppingCart.DataBean.GoodsListBean> goodsListBeanList = new ArrayList<>();

    public MallShopCarShopAdapter(MallShopCarFragment context, GoodAdapterCallBack callBack) {
        super(context.getActivity());
        this.context = context;
        this.callBack = callBack;
    }

    public void setOnShopCarClickListener(OnShopCarClickListener onShopCarClickListener) {
        this.onShopCarClickListener = onShopCarClickListener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_shopcar_good;
    }


    @Override
    protected void getView(ViewGroup viewGroup, View view, final MallShopCarListContentHolder holder, final ShoppingCart.DataBean.GoodsListBean goodsBean, final int position) {
        MyImageLoader.getInstance().loaderImageMy(goodsBean.getGoods_thumb(), holder.ivImg);
        if (goodsBean.getGoods_name().length() > 25) {
            holder.tvGoodname.setText(goodsBean.getGoods_name().substring(0, 25) + "...");
        } else {
            holder.tvGoodname.setText(goodsBean.getGoods_name() + "");
        }
//        holder.tvNumber.setText("×"+data.getGoods_number());
        holder.tvPrice.setText(String.valueOf(Double.parseDouble(goodsBean.getGoods_price())));
        holder.tvBuyCount.setText(String.valueOf(goodsBean.getGoods_number() + ""));
        holder.ivRemoveCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int size = Integer.parseInt(holder.tvBuyCount.getText().toString().trim());
                    if (size > 1) {
                        size -= 1;
                        goodsBean.setGoods_number(size + "");
                        callBack.callToShopAdapterNumberChange(goodsBean.getRu_id(), goodsBean.getGoods_id(), size + "");
                    }
                } catch (NumberFormatException e) {
                    goodsBean.setGoods_number("1");
                    callBack.callToShopAdapterNumberChange(goodsBean.getRu_id(), goodsBean.getGoods_id(), "1");
                } finally {
//                    holder.tvBuyCount.setText(String.valueOf(goodsBean.getGoods_number()));
//                    DecimalFormat df = new DecimalFormat("######0.00");
//                    holder.tvPrice.setText(df.format(Double.parseDouble(goodsBean.getGoods_price()) * Double.parseDouble(goodsBean.getGoods_number())) + "");
                }
            }
        });
        if (goodsBean.isSelect()) {
            holder.ivItemSelect.setImageResource(R.drawable.shopping_cart_pressed);
        } else {
            holder.ivItemSelect.setImageResource(R.drawable.shopping_cart_normal);
        }
        holder.ivItemSelect.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View v) {
                                                       if (goodsBean.isSelect()) {
                                                           callBack.callToShopAdapterFalse(goodsBean.getRu_id(), goodsBean.getGoods_id());
                                                       } else {
                                                           callBack.callToShopAdapterTrue(goodsBean.getRu_id(), goodsBean.getGoods_id());
                                                       }
                                                   }
                                               }
        );

        holder.ivAddCount.setOnClickListener(new View.OnClickListener()

                                             {
                                                 @Override
                                                 public void onClick(View view) {
                                                     try {
                                                         int size = Integer.parseInt(holder.tvBuyCount.getText().toString().trim());
                                                         size += 1;
                                                         goodsBean.setGoods_number(size + "");
                                                         callBack.callToShopAdapterNumberChange(goodsBean.getRu_id(), goodsBean.getGoods_id(), size + "");
                                                     } catch (NumberFormatException e) {
                                                         goodsBean.setGoods_number("1");
                                                         callBack.callToShopAdapterNumberChange(goodsBean.getRu_id(), goodsBean.getGoods_id(), "1");
                                                     } finally {
//                                                         holder.tvBuyCount.setText(String.valueOf(goodsBean.getGoods_number()));
//                                                         DecimalFormat df = new DecimalFormat("######0.00");
//                                                         holder.tvPrice.setText(df.format(Double.parseDouble(goodsBean.getGoods_price()) * Double.parseDouble(goodsBean.getGoods_number())) + "");
                                                     }
                                                 }
                                             }

        );
        holder.ivDelete.setOnClickListener(new View.OnClickListener()

                                           {
                                               @Override
                                               public void onClick(View view) {
                                                   if (onShopCarClickListener != null) {
                                                       onShopCarClickListener.shopsCarListDelete(goodsBean.getRec_id());
                                                   }
                                               }
                                           }

        );
        holder.ivImg.setOnClickListener(new View.OnClickListener()

                                        {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(context.getActivity(), CommodityDetailsActivity.class);
                                                intent.putExtra("goodid", goodsBean.getGoods_id());
                                                context.startActivity(intent);
                                            }
                                        }

        );
    }

    @Override
    public MallShopCarListContentHolder getViewHolder(View view) {
        return new MallShopCarListContentHolder(view);
    }

    class MallShopCarListContentHolder extends BaseListAdapter.ViewHolder {
        ImageView ivImg;
        TextView tvGoodname;
        TextView tvPrice;
        TextView tvBuyCount;
        ImageView ivRemoveCount;
        ImageView ivAddCount;
        ImageView ivDelete;
        ImageView ivItemSelect;
        View view;

        LinearLayout llPrice;

        public MallShopCarListContentHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(view1);
            ivImg = (ImageView) itemView.findViewById(R.id.iv_img);
            tvGoodname = (TextView) itemView.findViewById(R.id.tv_goodname);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            tvBuyCount = (TextView) itemView.findViewById(R.id.tv_buy_count);
            ivRemoveCount = (ImageView) itemView.findViewById(R.id.iv_remove_count);
            ivAddCount = (ImageView) itemView.findViewById(R.id.iv_add_count);
            ivDelete = (ImageView) itemView.findViewById(R.id.ivDelete);
            ivItemSelect = (ImageView) itemView.findViewById(R.id.iv_item_select);

            llPrice = (LinearLayout) itemView.findViewById(R.id.ll_price);

        }
    }

    public interface OnShopCarClickListener {

        void shopsCarListDelete(String recId);

    }

    public interface GoodAdapterCallBack {
        void callToShopAdapterTrue(String shopId, String goodId);

        void callToShopAdapterFalse(String shopId, String goodId);

        void callToShopAdapterNumberChange(String shopId, String goodId, String number);

    }

}
