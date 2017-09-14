package com.lidegou.lideshangmeng.mobile.data.dao;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.entity.Classify;
import com.lidegou.lideshangmeng.mobile.data.entity.Comments;
import com.lidegou.lideshangmeng.mobile.data.entity.Commodity;
import com.lidegou.lideshangmeng.mobile.data.entity.PPEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.Search;
import com.lidegou.lideshangmeng.mobile.data.entity.ShoppingCart;
import com.lidegou.lideshangmeng.mobile.data.entity.ShopsStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/19.
 */

public interface ICommodityDao {

    void selectCollectionCommodity(String uid, String page, ResponseCallback<Commodity.Data.CollectionCommodity> responseCallback);//收藏的商品

    void clearCollectionCommodity(String uid, String rec_id, ResponseCallback<String> responseCallback);//删除收藏的商品

    void selectAttentionCommodity(String uid, String page, ResponseCallback<ShopsStore.Attention> responseCallback);//关注的商品

    void clearAttentionCommodity(String uid, String rec_id, ResponseCallback<Integer> responseCallback);//删除关注的商品

    void ClassSubProductsCommodity(String page, String brand, String price_min, String price_max, String filter_attr, String sort, String order, String keyword, String isself, String size, String id, String hasgoods, String promotion, ResponseCallback<List<Classify.Products>> responseCallback);//二级分类商品

    void SearchHotKeyWord(ResponseCallback<Search.HotKeyWord> responseCallback);//搜索热门关键词

    void SearchStore(String page, String brand, String price_min, String price_max, String filter_attr, String sort, String order, String keyword, String isself, String size, String id, String hasgoods, String promotion, String typeSelete, ResponseCallback<List<ShopsStore>> responseCallback);//搜索

    void getShopsClassify(String uid, ResponseCallback<List<ShopsStore.ShopsStoreClassify>> responseCallback);//店铺分类

    void getShopsStore(String uid, String keyword, String page, String id, String province_id, String city_id, String district_id, String longitude, String latitude, String type, ResponseCallback<List<ShopsStore>> responseCallback);//店铺列表

    void getTabs(ResponseCallback<List<ShopsStore.Tabs>> responseCallback);//店铺上的头部

    void FocusShops(String uid, String shopid, ResponseCallback<String> responseCallback);//关注店铺

    void selectStoreDetails(String uid, String shop_uid, ResponseCallback<ShopsStore.ShopsStoreDetails> responseCallback);//店铺详情

    void selectCommodityDetails(String uid, String id, ResponseCallback<Commodity.Data.CommodityDetails> responseCallback);//商品详情

    void addToCat(String uid, String warehouse_id, String area_id, String quick, String spec, String goods_id, String number, ResponseCallback<String> responseCallback);//添加购物车

    /**
     * 购物车列表
     */
    void catList(String uid, ResponseCallback<ShoppingCart> responseCallback);

    /**
     * 删除购物车
     */
    void deleteCar(String uid, String id, ResponseCallback<String> responseCallback);

    /**
     * 批量删除购物车
     */
    void batchDeleteCar(String uid, String id, ResponseCallback<String> responseCallback);

    /**
     * 评价商品
     */
    void addEvaluation(String uid, String comment_rank, String content, String order_id, String goods_id, String pic, String img_type, ResponseCallback<String> responseCallback);

    /**
     * 收藏商品
     */
    void addCollection(String uid, String id, ResponseCallback<String> responseCallback);

    /**
     * 商品评价列表
     */
    void Evaluation(String uid, String id, String page, String rank, ResponseCallback<Comments> responseCallback);

    /**
     * 修改购物车商品数量
     */
    void updateGoodNumber(String uid, String id, String number, String arr, ResponseCallback<String> resultCallback);

    /**
     * 客服列表
     */
    void qqList(String uid, ResponseCallback<String> resultCallback);

    /**
     * 是否有收货地址
     */
    void isAddress(String uid, ResponseCallback<Boolean> resultCallback);


    void BrandsList(ResponseCallback<ArrayList<PPEntity>> responseCallback);
}
