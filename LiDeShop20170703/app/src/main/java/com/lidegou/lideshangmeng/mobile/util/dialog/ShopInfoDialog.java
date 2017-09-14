package com.lidegou.lideshangmeng.mobile.util.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Commodity;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/1/13.
 */

public class ShopInfoDialog extends BaseDialog {
    public static int TYPE_STYLE = 0;
    public static int TYPE_CAR = 1;
    public static int TYPE_BUY = 2;

    private Context context;
    private ShopInfoCallBack callBack;


    private int type;
    private int count = 1;

    private ImageView ivShop;
    private TextView tv_buy_count;

    public ShopInfoDialog(Context context, @NonNull ShopInfoCallBack callBack) {
        super(context);
        this.context = context;

        this.callBack = callBack;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_shoop_info;
    }

    @Override
    protected void init() {
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        findViewById(R.id.view).setOnClickListener(this);

    }

    public void show(@NonNull int type, Commodity.Data.CommodityDetails.GoodsBean goodsBean) {
        super.show();
        this.type = type;
        if (type == TYPE_STYLE) {
            findViewById(R.id.tv_add_car).setVisibility(View.GONE);
            findViewById(R.id.tv_buy).setVisibility(View.GONE);
        } else if (type == TYPE_CAR) {
            findViewById(R.id.tv_add_car).setVisibility(View.VISIBLE);
            findViewById(R.id.tv_buy).setVisibility(View.GONE);
        } else if (type == TYPE_BUY) {
            findViewById(R.id.tv_add_car).setVisibility(View.GONE);
            findViewById(R.id.tv_buy).setVisibility(View.VISIBLE);
        }
        findViewById(R.id.iv_remove_count).setOnClickListener(this);
        findViewById(R.id.iv_add_count).setOnClickListener(this);
        findViewById(R.id.tv_buy).setOnClickListener(this);
        findViewById(R.id.tv_add_car).setOnClickListener(this);
        findViewById(R.id.iv_close).setOnClickListener(this);

        tv_buy_count = (TextView) findViewById(R.id.tv_buy_count);
        GridView gridView = (GridView) findViewById(R.id.gv_shop_style);
        ivShop = (ImageView) findViewById(R.id.iv_shop);
        MyImageLoader.getInstance().loaderImage(goodsBean.getGoods_img(), ivShop);
        TextView tvShopName = (TextView) findViewById(R.id.tv_shop_name);
        TextView tvShopPrice = (TextView) findViewById(R.id.tv_shop_price);
        TextView tvShopNumber = (TextView) findViewById(R.id.tv_shop_number);
        tvShopName.setText(goodsBean.getGoods_name());
        DecimalFormat df = new DecimalFormat("######0.00");
        tvShopPrice.setText(df.format(Double.parseDouble(goodsBean.getGoods_price())));
        tvShopNumber.setText("库存： " + goodsBean.getGoods_number());
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.view:
                dismiss();
                break;
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.iv_remove_count:
                if (count > 1) {
                    count--;
                }
                tv_buy_count.setText(count + "");
                break;
            case R.id.iv_add_count:
                count++;
                tv_buy_count.setText(count + "");
                break;
            case R.id.tv_add_car:
                callBack.addCarCallBack(type, count);
                break;
            case R.id.tv_buy:
                callBack.buyCallBack(type, count);
                break;
        }
    }

    public interface ShopInfoCallBack {
        void addCarCallBack(int type, int count);

        void buyCallBack(int type, int count);
    }
}
