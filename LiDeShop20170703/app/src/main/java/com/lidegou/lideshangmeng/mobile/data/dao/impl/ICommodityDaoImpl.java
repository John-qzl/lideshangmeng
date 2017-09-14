package com.lidegou.lideshangmeng.mobile.data.dao.impl;

import android.util.Log;

import com.google.gson.Gson;
import com.jiongbull.jlog.JLog;
import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.data.HttpInterface;
import com.lidegou.lideshangmeng.mobile.data.HttpUtil;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.ICommodityDao;
import com.lidegou.lideshangmeng.mobile.data.entity.Classify;
import com.lidegou.lideshangmeng.mobile.data.entity.Comments;
import com.lidegou.lideshangmeng.mobile.data.entity.Commodity;
import com.lidegou.lideshangmeng.mobile.data.entity.PPEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.Place;
import com.lidegou.lideshangmeng.mobile.data.entity.Search;
import com.lidegou.lideshangmeng.mobile.data.entity.ShoppingCart;
import com.lidegou.lideshangmeng.mobile.data.entity.ShopsStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/19.
 */

public class ICommodityDaoImpl extends HttpUtil implements ICommodityDao {
    Map<String, String> map = new HashMap<>();

    @Override
    public void selectCollectionCommodity(String uid, String page, final ResponseCallback<Commodity.Data.CollectionCommodity> responseCallback) {
        map.put("uid", uid);
        map.put("page", page);
        POST(HttpInterface.selectCollectionCommodity(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    Commodity.Data.CollectionCommodity collectionCommodity = new Commodity.Data.CollectionCommodity();
                    List<Commodity.Data.CollectionCommodity.DataBean> dataBeanList = new ArrayList<Commodity.Data.CollectionCommodity.DataBean>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.optJSONObject(i);
                        if (object == null) {
                            break;
                        }
                        Commodity.Data.CollectionCommodity.DataBean dataBean = getObj(object, Commodity.Data.CollectionCommodity.DataBean.class);
                        dataBeanList.add(dataBean);
                    }
                    Integer totalPage = jsonObject.getInt("totalPage");
                    collectionCommodity.setTotalPage(totalPage);
                    collectionCommodity.setData(dataBeanList);
                    responseCallback.onSuccess(collectionCommodity);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void clearCollectionCommodity(String uid, final String rec_id, final ResponseCallback<String> responseCallback) {
        map.put("uid", uid);
        map.put("rec_id", rec_id);
        POST(HttpInterface.clearCollectionCommodity(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.optInt("code");
                    if (code == 100) {
                        String msg = jsonObject.optString("msg");
                        responseCallback.onSuccess(msg);
                    } else {
                        responseCallback.onFailure(code, jsonObject.optString("msg"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void selectAttentionCommodity(String uid, String page, final ResponseCallback<ShopsStore.Attention> responseCallback) {
        map.put("uid", uid);
        map.put("page", page);
        POST(HttpInterface.selectAttentionCommodity(), map, new Callback() {
            @Override
            public void callback(String result) {
                JLog.json(result);
                Gson gson = new Gson();
                ShopsStore.Attention attention = gson.fromJson(result, ShopsStore.Attention.class);
                responseCallback.onSuccess(attention);
            }
        }, responseCallback);
    }

    @Override
    public void clearAttentionCommodity(String uid, String shopid, final ResponseCallback<Integer> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("shop_uid", shopid);
        Log.i("ICommodityDaoImpl", "map:" + map);
        POST(HttpInterface.FocusShops(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.optInt("code");
                    if (code == 100) {
                        responseCallback.onSuccess(Integer.parseInt(jsonObject.optString("code")));
                    } else {
                        responseCallback.onFailure(code, jsonObject.optString("msg"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void ClassSubProductsCommodity(String page, String brand, String price_min, String price_max, String filter_attr, String sort, String order, String keyword, String isself, String size, String id, String hasgoods, String promotion, final ResponseCallback<List<Classify.Products>> responseCallback) {
        map.put("page", page);
        map.put("brand", brand);
        map.put("price_min", price_min);
        map.put("price_max", price_max);
        map.put("filter_attr", filter_attr);
        map.put("sort", sort);
        map.put("order", order);
        map.put("keyword", keyword);
        map.put("isself", isself);
        map.put("size", size);
        map.put("id", id);
        map.put("hasgoods", hasgoods);
        map.put("promotion", promotion);
        map.put("type_select", "2");
        Log.i("ICommodityDaoImpl", "map:" + map);
        POST(HttpInterface.getProducts(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject object = new JSONObject(result);
                    JSONArray jsonArray = object.optJSONArray("data");
                    List<Classify.Products> productsList = new ArrayList<Classify.Products>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.optJSONObject(i);
                        if (jsonObject == null) {
                            break;
                        }
                        List<Classify.Products.ZconmentsBean> zconmentsBeanList = new ArrayList<Classify.Products.ZconmentsBean>();
                        JSONObject json = jsonObject.getJSONObject("zconments");
                        Classify.Products.ZconmentsBean catID = getObj(json, Classify.Products.ZconmentsBean.class);
                        zconmentsBeanList.add(catID);
                        Classify.Products products = getObj(jsonObject, Classify.Products.class);
                        products.setZconmentsBeanList(zconmentsBeanList);
                        productsList.add(products);
                    }
                    responseCallback.onSuccess(productsList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void SearchHotKeyWord(final ResponseCallback<Search.HotKeyWord> responseCallback) {
        GET(HttpInterface.SearchHotKeyword(), new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("keyword");
                    Search.HotKeyWord hotKeyWord = new Search.HotKeyWord();
                    List<String> stringList = new ArrayList<String>();
                    if (jsonArray == null) {
                        return;
                    }
                    for (int i = 0; i < jsonArray.length(); i++) {
                        stringList.add(jsonArray.get(i).toString());
                    }
                    hotKeyWord.setKeyword(stringList);
                    responseCallback.onSuccess(hotKeyWord);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, responseCallback);
    }

    @Override
    public void SearchStore(String page, String brand, String price_min, String price_max, String filter_attr, String sort, String order, String keyword, String isself, String size, String id, String hasgoods, String promotion, String typeSelete, final ResponseCallback<List<ShopsStore>> responseCallback) {
        map.put("page", page);
        map.put("brand", brand);
        map.put("price_min", price_min);
        map.put("price_max", price_max);
        map.put("filter_attr", filter_attr);
        map.put("sort", sort);
        map.put("order", order);
        map.put("keyword", keyword);
        map.put("isself", isself);
        map.put("size", size);
        map.put("id", id);
        map.put("hasgoods", hasgoods);
        map.put("promotion", promotion);
        map.put("type_select", "1");
        Log.i("ICommodityDaoImpl", "map:" + map);
        POST(HttpInterface.getProducts(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject object = new JSONObject(result);

                    JSONArray jsonArray = object.optJSONArray("data");
                    List<ShopsStore> shopsStoreList = new ArrayList<ShopsStore>();
                    if (jsonArray == null) {
                        responseCallback.onSuccess(shopsStoreList);
                        return;
                    }
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.optJSONObject(i);
                        if (jsonObject == null) {
                            break;
                        }
                        ShopsStore shopsStore = getObj(jsonObject, ShopsStore.class);
                        shopsStoreList.add(shopsStore);
                    }
                    responseCallback.onSuccess(shopsStoreList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void getShopsClassify(String uid, final ResponseCallback<List<ShopsStore.ShopsStoreClassify>> responseCallback) {
        map.put("uid", uid);
        POST(HttpInterface.getShopsClassify(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    List<ShopsStore.ShopsStoreClassify> shopsStoreClassifyList = new ArrayList<ShopsStore.ShopsStoreClassify>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.optJSONObject(i);
                        if (jsonObject == null) {
                            break;
                        }
                        ShopsStore.ShopsStoreClassify shopsStoreClassify = getObj(jsonObject, ShopsStore.ShopsStoreClassify.class);
                        shopsStoreClassifyList.add(shopsStoreClassify);
                    }
                    responseCallback.onSuccess(shopsStoreClassifyList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void getShopsStore(String uid, String keyword, String page, String id, String province_id, String city_id, String district_id, String longitude, String latitude, String type, final ResponseCallback<List<ShopsStore>> responseCallback) {
        map.put("uid", uid);
        map.put("keyword", keyword);
        map.put("id", id);
        map.put("province_id", province_id);
        map.put("city_id", city_id);
        map.put("district_id", district_id);
        map.put("longitude", longitude);
        map.put("latitude", latitude);
        map.put("type", type);
        map.put("page", page);
        Log.i("IHomeDaoImpl", "map:" + map);
        POST(HttpInterface.getShopStoreList(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject object = new JSONObject(result);

                    JSONObject placeObj = object.optJSONObject("place");
                    Place place = getObj(placeObj, Place.class);
                    App.getApp().setPlace(place);

                    JSONArray jsonArray = object.optJSONArray("data");
                    List<ShopsStore> shopsStoreList = new ArrayList<ShopsStore>();
                    if (jsonArray == null) {
                        responseCallback.onSuccess(shopsStoreList);
                        return;
                    }
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.optJSONObject(i);
                        if (jsonObject == null) {
                            break;
                        }
                        ShopsStore shopsStore = getObj(jsonObject, ShopsStore.class);
                        int totalPage = object.getInt("totalPage");
                        shopsStore.setTotalPage(totalPage);
                        shopsStoreList.add(shopsStore);
                    }

                    responseCallback.onSuccess(shopsStoreList);
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void getTabs(final ResponseCallback<List<ShopsStore.Tabs>> responseCallback) {
        GET(HttpInterface.getTabs(), new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    List<ShopsStore.Tabs> tabsList = new ArrayList<ShopsStore.Tabs>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.optJSONObject(i);
                        if (jsonObject == null) {
                            break;
                        }
                        ShopsStore.Tabs tabs = getObj(jsonObject, ShopsStore.Tabs.class);
                        tabsList.add(tabs);
                    }
                    responseCallback.onSuccess(tabsList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, responseCallback);
    }

    @Override
    public void FocusShops(String uid, String shopid, final ResponseCallback<String> responseCallback) {
        map.put("uid", uid);
        map.put("shop_uid", shopid);
        Log.i("ICommodityDaoImpl", "map:" + map);
        POST(HttpInterface.FocusShops(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.optInt("code");
                    if (code == 100) {
                        String msg = jsonObject.optString("msg");
                        responseCallback.onSuccess(msg);
                    } else {
                        responseCallback.onFailure(code, jsonObject.optString("msg"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void selectStoreDetails(String uid, String shop_uid, final ResponseCallback<ShopsStore.ShopsStoreDetails> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("shop_uid", shop_uid);
        Log.i("map", map + "");
        POST(HttpInterface.getStoreDetails(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    ShopsStore.ShopsStoreDetails shopsStoreDetails = getObj(jsonObject, ShopsStore.ShopsStoreDetails.class);
                    responseCallback.onSuccess(shopsStoreDetails);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, responseCallback);
    }

    @Override
    public void selectCommodityDetails(String uid, String id, final ResponseCallback<Commodity.Data.CommodityDetails> responseCallback) {
        map.put("uid", uid);
        map.put("id", id);
        POST(HttpInterface.getCommodityDetails(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    Commodity.Data.CommodityDetails commodityDetails = getObj(jsonObject, Commodity.Data.CommodityDetails.class);
                    responseCallback.onSuccess(commodityDetails);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void addToCat(String uid, String warehouse_id, String area_id, String quick, String spec, String goods_id, String number, final ResponseCallback<String> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("warehouse_id", warehouse_id);
        map.put("area_id", area_id);
        map.put("quick", quick);
        map.put("spec", spec);
        map.put("goods_id", goods_id);
        map.put("number", number);
        Log.i("ICommodityDaoImpl", "map:" + map);
        POST(HttpInterface.getAddToCat(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.optInt("code");
                    if (code == 100) {
                        String msg = jsonObject.optString("msg");
                        responseCallback.onSuccess(msg);
                    } else {
                        responseCallback.onFailure(code, jsonObject.optString("msg"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void catList(String uid, final ResponseCallback<ShoppingCart> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        POST(HttpInterface.CarList(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    Log.i("ICommodityDaoImpl", "购物车");
                    JLog.json(result);
                    ShoppingCart shoppingCart = new ShoppingCart();
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    List<ShoppingCart.DataBean> dataBeanList = new ArrayList<ShoppingCart.DataBean>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject dataObject = jsonArray.optJSONObject(i);
                        ShoppingCart.DataBean dataBean = getObj(dataObject, ShoppingCart.DataBean.class);
                        dataBeanList.add(dataBean);
                    }
                    JSONObject cartShowObject = jsonObject.getJSONObject("cart_show");
                    ShoppingCart.CartShowBean cartShowBean = getObj(cartShowObject, ShoppingCart.CartShowBean.class);
                    JSONObject totalObject = jsonObject.getJSONObject("total");
                    ShoppingCart.TotalBean totalBean = getObj(totalObject, ShoppingCart.TotalBean.class);
                    JSONArray relationArray = jsonObject.getJSONArray("relation");
                    List<ShoppingCart.RelationBean> relationBeanList = new ArrayList<ShoppingCart.RelationBean>();
                    for (int i = 0; i < relationArray.length(); i++) {
                        JSONObject relationObject = relationArray.optJSONObject(i);
                        ShoppingCart.RelationBean relationBean = getObj(relationObject, ShoppingCart.RelationBean.class);
                        relationBeanList.add(relationBean);
                    }

                    shoppingCart.setRelation(relationBeanList);
                    shoppingCart.setTotal(totalBean);
                    shoppingCart.setCart_show(cartShowBean);
                    shoppingCart.setData(dataBeanList);
                    responseCallback.onSuccess(shoppingCart);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void deleteCar(String uid, String id, final ResponseCallback<String> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        POST(HttpInterface.DeleteCar(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.optInt("code");
                    if (code == 100) {
                        String msg = jsonObject.optString("msg");
                        responseCallback.onSuccess(msg);
                    } else {
                        responseCallback.onFailure(code, jsonObject.optString("msg"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, responseCallback);
    }

    @Override
    public void batchDeleteCar(String uid, String id, final ResponseCallback<String> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        Log.i("ICommodityDaoImpl", "map:" + map);
        POST(HttpInterface.BatchDeleteCar(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.optInt("code");
                    if (code == 100) {
                        String msg = jsonObject.optString("msg");
                        responseCallback.onSuccess(msg);
                    } else {
                        responseCallback.onFailure(code, jsonObject.optString("msg"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void addEvaluation(String uid, String comment_rank, String content, String order_id, String goods_id, String pic, String img_type, final ResponseCallback<String> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("comment_rank", comment_rank);
        map.put("content", content);
        map.put("order_id", order_id);
        map.put("goods_id", goods_id);
        map.put("pic", pic);
        map.put("img_type", img_type);
        Log.i("ICommodityDaoImpl", "map:" + map);
        POST(HttpInterface.addEvaluation(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.optInt("code");
                    if (code == 100) {
                        String msg = jsonObject.optString("msg");
                        responseCallback.onSuccess(msg);
                    } else {
                        responseCallback.onFailure(code, jsonObject.optString("msg"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void addCollection(String uid, String id, final ResponseCallback<String> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        Log.i("ICommodityDaoImpl", "map:" + map);
        POST(HttpInterface.addCollection(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.optInt("code");
                    if (code == 100) {
                        String msg = jsonObject.optString("msg");
                        responseCallback.onSuccess(msg);
                    } else {
                        responseCallback.onFailure(code, jsonObject.optString("msg"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }


    @Override
    public void Evaluation(String uid, String id, String page, String rank, final ResponseCallback<Comments> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        map.put("page", page);
        map.put("rank", rank);
        Log.i("ICommodityDaoImpl", "map:" + map);
        POST(HttpInterface.Evaluation(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject object = new JSONObject(result);
                    Comments comments = getObj(object, Comments.class);
                    if (comments != null) {
                        responseCallback.onSuccess(comments);
                    }
                } catch (JSONException e) {
                }
            }
        }, responseCallback);
    }

    @Override
    public void updateGoodNumber(String uid, String id, String number, String arr, final ResponseCallback<String> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        map.put("number", number);
        map.put("arr", arr);
        Log.i("ICommodityDaoImpl", "map:" + map);
        POST(HttpInterface.updateGoodNumber(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.optInt("code");
                    if (code == 100) {
                        String msg = jsonObject.optString("msg");
                        responseCallback.onSuccess(msg);
                    } else {
                        responseCallback.onFailure(code, jsonObject.optString("msg"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void qqList(String uid, final ResponseCallback<String> resultCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        POST(HttpInterface.qqList(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.optInt("code");
                    if (code == 100) {
                        String msg = jsonObject.optString("msg");
                        resultCallback.onSuccess(msg);
                    } else {
                        resultCallback.onFailure(code, jsonObject.optString("msg"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, resultCallback);
    }

    @Override
    public void isAddress(String uid, final ResponseCallback<Boolean> resultCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        POST(HttpInterface.isAddress(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.optInt("code");
                    if (code == 100) {
                        resultCallback.onSuccess(true);
                    } else {
                        resultCallback.onSuccess(false);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, resultCallback);
    }

    @Override
    public void BrandsList(final ResponseCallback<ArrayList<PPEntity>> responseCallback) {
        GET(HttpInterface.BrandsList(), new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONArray array = new JSONArray(result);
                    Gson gson = new Gson();
                    ArrayList<PPEntity> entities = new ArrayList<PPEntity>();
                    for (int i = 0; i < array.length(); i++) {
                        String data = array.getString(i);
                        PPEntity entity = gson.fromJson(data, PPEntity.class);
                        entities.add(entity);
                    }
                    responseCallback.onSuccess(entities);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

}
