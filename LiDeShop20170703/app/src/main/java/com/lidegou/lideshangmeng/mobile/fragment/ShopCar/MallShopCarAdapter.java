package com.lidegou.lideshangmeng.mobile.fragment.ShopCar;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.ShoppingCart;
import com.lidegou.lideshangmeng.mobile.event.OnRecyclerItemClickListener;
import com.lidegou.lideshangmeng.mobile.util.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Y on 2016/8/13.
 */

public class MallShopCarAdapter extends BaseRecyclerAdapter<ShoppingCart.DataBean, MallShopCarAdapter.ShoppingCartHolder> {
    private MallShopCarShopAdapter mallShopCarShopAdapter;
    private boolean isSelectAll;
    private OnRecyclerItemClickListener onRecyclerItemClickListener;
    private MallShopCarFragment context;
    private OnShoppingCartListener onShoppingCartListener;
    private int selectNumber = 0;

    private int editor = 0;
    private MallShopCarShopAdapter.OnShopCarClickListener listener;

    public MallShopCarAdapter(MallShopCarFragment context, MallShopCarShopAdapter.OnShopCarClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public void setOnShoppingCartListener(OnShoppingCartListener onShoppingCartListener) {
        this.onShoppingCartListener = onShoppingCartListener;
    }

    @Override
    public void setDataList(List<ShoppingCart.DataBean> dataList) {
        super.setDataList(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_shopping_cart;
    }

    @Override
    public ShoppingCartHolder getViewHolder(View view) {
        return new ShoppingCartHolder(view);
    }

    @Override
    public void onBindViewHolder(final ShoppingCartHolder holder, final ShoppingCart.DataBean data, final int position) {

        mallShopCarShopAdapter = new MallShopCarShopAdapter(context, callBack);
        mallShopCarShopAdapter.setOnShopCarClickListener(listener);
        mallShopCarShopAdapter.setDataList(data.getGoods_list());
        holder.lv.setAdapter(mallShopCarShopAdapter);

        if (data.isSelect()) {
            holder.ivSelect.setImageResource(R.drawable.shopping_cart_pressed);
        } else {
            holder.ivSelect.setImageResource(R.drawable.shopping_cart_normal);
        }

        holder.ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data.isSelect()) {
                    data.setSelect(false);
                } else {
                    data.setSelect(true);
                }
                for (ShoppingCart.DataBean.GoodsListBean goodsListBean : data.getGoods_list()) {
                    goodsListBean.setSelect(data.isSelect());
                }
                if (isAllSelect()) {
                    onShoppingCartListener.shoppingCartSelectTrue();
                } else {
                    onShoppingCartListener.shoppingCartSelectFalse();
                }
                notifyDataSetChanged();
            }
        });

//        holder.ivImg.setImageBitmap(ImageLoader.getInstance().getRoundedCornerBitmap(ImageLoader.getInstance().returnBitMap(data.getStore_image()),10));
        holder.tvName.setText(data.getRu_name() + "");
    }

    class ShoppingCartHolder extends RecyclerView.ViewHolder {

        ImageView ivSelect;
        TextView tvName;
        ListView lv;
        TextView tvEditor;


        public ShoppingCartHolder(View itemView) {
            super(itemView);
            ivSelect = (ImageView) itemView.findViewById(R.id.iv_select);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            lv = (ListView) itemView.findViewById(R.id.list_shopcar_content);
            tvEditor = (TextView) itemView.findViewById(R.id.tvEditor);
        }
    }

    MallShopCarShopAdapter.GoodAdapterCallBack callBack = new MallShopCarShopAdapter.GoodAdapterCallBack() {
        @Override
        public void callToShopAdapterTrue(String shopId, String goodId) {
            int shopPosition = 0;
            ArrayList<ShoppingCart.DataBean> datas = (ArrayList<ShoppingCart.DataBean>) getDataList();
            for (int i = 0; i < datas.size(); i++) {
                if (String.valueOf(datas.get(i).getRu_id()).equals(shopId)) {
                    shopPosition = i;
                    for (int j = 0; j < datas.get(i).getGoods_list().size(); j++) {
                        if (datas.get(i).getGoods_list().get(j).getGoods_id().equals(goodId)) {
                            datas.get(i).getGoods_list().get(j).setSelect(true);
                        }
                    }
                }
            }

            if (isDataSelect(datas.get(shopPosition))) {
                datas.get(shopPosition).setSelect(true);
            }
            if (isAllSelect()) {
                onShoppingCartListener.shoppingCartSelectTrue();
            }
            notifyDataSetChanged();
            onShoppingCartListener.shoppingCartSelectChange();
        }

        @Override
        public void callToShopAdapterFalse(String shopId, String goodId) {
            int shopPosition = 0;
            ArrayList<ShoppingCart.DataBean> datas = (ArrayList<ShoppingCart.DataBean>) getDataList();
            for (int i = 0; i < datas.size(); i++) {
                if (String.valueOf(datas.get(i).getRu_id()).equals(shopId)) {
                    shopPosition = i;
                    for (int j = 0; j < datas.get(i).getGoods_list().size(); j++) {
                        if (datas.get(i).getGoods_list().get(j).getGoods_id().equals(goodId)) {
                            datas.get(i).getGoods_list().get(j).setSelect(false);
                        }
                    }
                }
            }
            datas.get(shopPosition).setSelect(false);
            onShoppingCartListener.shoppingCartSelectFalse();
            notifyDataSetChanged();
            onShoppingCartListener.shoppingCartSelectChange();
        }

        @Override
        public void callToShopAdapterNumberChange(String shopId, String goodId, String number) {
            String recId = "";
            ArrayList<ShoppingCart.DataBean> datas = (ArrayList<ShoppingCart.DataBean>) getDataList();
            for (int i = 0; i < datas.size(); i++) {
                if (String.valueOf(datas.get(i).getRu_id()).equals(shopId)) {
                    for (int j = 0; j < datas.get(i).getGoods_list().size(); j++) {
                        if (datas.get(i).getGoods_list().get(j).getGoods_id().equals(goodId)) {
                            datas.get(i).getGoods_list().get(j).setGoods_number(number);
                            recId = datas.get(i).getGoods_list().get(j).getRec_id();
                        }
                    }
                }
            }
            notifyDataSetChanged();
            onShoppingCartListener.shoppingCartSelectChange(recId, number);
        }

    };

    private boolean isDataSelect(ShoppingCart.DataBean needData) {
        if (needData == null) {
            return false;
        }
        for (ShoppingCart.DataBean.GoodsListBean goodsListBean : needData.getGoods_list()) {
            if (!goodsListBean.isSelect()) {
                return false;
            }
        }
        return true;
    }

    private boolean isAllSelect() {
        for (ShoppingCart.DataBean dataBean : getDataList()) {
            if (!dataBean.isSelect()) {
                return false;
            }
        }
        return true;
    }


    public interface OnShoppingCartListener {
        void shoppingCartSelectTrue();

        void shoppingCartSelectFalse();

        void shoppingCartSelectChange();

        void shoppingCartSelectChange(String rec_id, String number);
    }

}
