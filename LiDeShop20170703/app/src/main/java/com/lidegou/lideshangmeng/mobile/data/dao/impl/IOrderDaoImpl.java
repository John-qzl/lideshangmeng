package com.lidegou.lideshangmeng.mobile.data.dao.impl;

import android.util.Log;

import com.jiongbull.jlog.JLog;
import com.lidegou.lideshangmeng.mobile.data.HttpInterface;
import com.lidegou.lideshangmeng.mobile.data.HttpUtil;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IOrderDao;
import com.lidegou.lideshangmeng.mobile.data.entity.Order;
import com.lidegou.lideshangmeng.mobile.data.entity.OrderEvaluationEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.OrderSubmit;
import com.lidegou.lideshangmeng.mobile.data.entity.OtherPayEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/26.
 */

public class IOrderDaoImpl extends HttpUtil implements IOrderDao {


    @Override
    public void getOrderList(String uid, String status, String page, final ResponseCallback<Order> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("status", status);
        map.put("page", page);
        POST(HttpInterface.OrderList(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    Order order = getObj(jsonObject, Order.class);
                    responseCallback.onSuccess(order);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void waitComment(String uid, String page, final ResponseCallback<OrderEvaluationEntity> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("page", page);
        POST(HttpInterface.waitComment(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    OrderEvaluationEntity entity = new OrderEvaluationEntity();

                    JSONArray list = jsonObject.getJSONArray("list");
                    ArrayList<OrderEvaluationEntity.Data> datas = new ArrayList<OrderEvaluationEntity.Data>();
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject object = list.getJSONObject(i);
                        OrderEvaluationEntity.Data data = new OrderEvaluationEntity.Data();
                        String rec_id = object.getString("rec_id");
                        String order_id = object.getString("order_id");
                        String goods_id = object.getString("goods_id");
                        String goods_name = object.getString("goods_name");
                        String add_time = object.getString("add_time");
                        String goods_thumb = object.getString("goods_thumb");
                        String goods_product_tag = object.getString("goods_product_tag");
                        String ru_id = object.getString("ru_id");
                        data.setRec_id(rec_id);
                        data.setOrder_id(order_id);
                        data.setGoods_id(goods_id);
                        data.setGoods_name(goods_name);
                        data.setAdd_time(add_time);
                        data.setGoods_thumb(goods_thumb);
                        data.setGoods_product_tag(goods_product_tag);
                        data.setRec_id(ru_id);
                        datas.add(data);
                    }
                    entity.setData(datas);

                    int totalPage = jsonObject.getInt("totalPage");
                    entity.setTotalPage(totalPage);
                    responseCallback.onSuccess(entity);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }


    @Override
    public void getOrderDetail(String uid, String order_id, final ResponseCallback<Order.OrderDetail> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("order_id", order_id);
        POST(HttpInterface.OrderDetail(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject object = jsonObject.getJSONObject("order");
                    Order.OrderDetail orderDetail = new Order.OrderDetail();
                    Order.OrderDetail.OrderBean orderBean = getObj(object, Order.OrderDetail.OrderBean.class);
                    JSONArray jsonArray = jsonObject.getJSONArray("goods_list");
                    List<Order.OrderDetail.GoodsListBean> goodsListBeanList = new ArrayList<Order.OrderDetail.GoodsListBean>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                        Order.OrderDetail.GoodsListBean goodsListBean = getObj(jsonObject1, Order.OrderDetail.GoodsListBean.class);
                        goodsListBeanList.add(goodsListBean);
                    }
                    orderDetail.setGoods_list(goodsListBeanList);
                    orderDetail.setOrder(orderBean);
                    responseCallback.onSuccess(orderDetail);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void getOrderSubmit(String uid, String cart_value, final ResponseCallback<OrderSubmit> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("cart_value", cart_value);
        Log.i("IOrderDaoImpl", "map:" + map);
        POST(HttpInterface.getOrderSubmit(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    OrderSubmit orderSubmit = getObj(jsonObject, OrderSubmit.class);
                    responseCallback.onSuccess(orderSubmit);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void deleteOrderSubmit(String uid, String orderId, final ResponseCallback<String> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("order_id", orderId);
        Log.i("ICommodityDaoImpl", "map:" + map);
        POST(HttpInterface.deleteOrderSubmit(), map, new Callback() {
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
    public void confirmOrderSubmit(String uid, String orderId, final ResponseCallback<String> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("order_id", orderId);
        Log.i("ICommodityDaoImpl", "map:" + map);
        POST(HttpInterface.confirmOrderSubmit(), map, new Callback() {
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
    public void confirmOrderDone(String uid, String order_id, String shipping_id, String payment_id, String postscript, final ResponseCallback<ArrayList<String>> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("cart_value", order_id);
        map.put("shopping_id", shipping_id);
        map.put("payment", payment_id);
        map.put("postscript", postscript);
        Log.i("ICommodityDaoImpl", "map:" + map);
        POST(HttpInterface.confirmOrderDone(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getString("code").equals("100")) {
                        JSONObject order = jsonObject.getJSONObject("order");
                        if (order != null) {
                            ArrayList<String> list = new ArrayList<String>();
                            String order_id = order.getString("order_id");
                            String order_sn = order.getString("order_sn");
                            list.add(order_id);
                            list.add(order_sn);
                            responseCallback.onSuccess(list);
                        }
                    } else {
                        responseCallback.onFailure(201, jsonObject.getString("msg"));
                    }

                } catch (JSONException e) {
                    responseCallback.onFailure(101, "请求超时");
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }


    @Override
    public void confirmOrderPay(String uid, String order_id, final String payType, final ResponseCallback<OtherPayEntity> responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("order_id", order_id);
        Log.i("ICommodityDaoImpl", "map:" + map);
        POST(HttpInterface.confirmOrderPay(), map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JLog.json(result);
                    JSONObject jsonObject = new JSONObject(result);
                    OtherPayEntity entity = new OtherPayEntity();
                    if (payType.equals("12")) {
                        String orderAmount = jsonObject.getString("orderAmount");
                        String productName = jsonObject.getString("productName");
                        String ext1 = jsonObject.getString("ext1");
                        String merchantId = jsonObject.getString("merchantId");
                        String orderNo = jsonObject.getString("orderNo");
                        String receiveUrl = jsonObject.getString("receiveUrl");
                        OtherPayEntity.PaaCreatorEntity paaCreatorEntity = new OtherPayEntity.PaaCreatorEntity(orderAmount, productName, ext1, merchantId, orderNo, receiveUrl);
                        entity.setPaaCreatorEntity(paaCreatorEntity);
                        responseCallback.onSuccess(entity);
                    }
                    if (payType.equals("13")) {
                        String appid = jsonObject.getString("appid");
                        String noncestr = jsonObject.getString("noncestr");
                        String packageMsg = jsonObject.getString("package");
                        String partnerid = jsonObject.getString("partnerid");
                        String prepayid = jsonObject.getString("prepayid");
                        String timestamp = jsonObject.getString("timestamp");
                        String sign = jsonObject.getString("sign");
                        OtherPayEntity.WeChatEntity weChatEntity = new OtherPayEntity.WeChatEntity(appid, noncestr, packageMsg, partnerid, prepayid, timestamp, sign);
                        entity.setWeChatEntity(weChatEntity);
                        responseCallback.onSuccess(entity);
                    }
                    if (payType.equals("14")) {
                        int errorCode = jsonObject.getInt("errorCode");
                        String errorMsg = jsonObject.getString("errorMsg");
                        if (errorCode == 0) {
                            JSONObject responseData = jsonObject.getJSONObject("responseData");
                            String app_response = responseData.getString("app_response");
                            OtherPayEntity.TreasureEntity treasureEntity = new OtherPayEntity.TreasureEntity(app_response);
                            entity.setTreasureEntity(treasureEntity);
                            responseCallback.onSuccess(entity);
                        } else {
                            responseCallback.onFailure(101, errorMsg);
                        }
                    }

                } catch (JSONException e) {
                    responseCallback.onFailure(101, "请求超时");
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }

    @Override
    public void btnClick(String url, String uid, String order_id, final ResponseCallback<String> responseCallback) {
        if (url == null || uid == null || order_id == null) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("order_id", order_id);
        POST(url, map, new Callback() {
            @Override
            public void callback(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    int code = object.getInt("code");
                    if (code == 100) {
                        responseCallback.onSuccess(object.getString("msg"));
                    } else {
                        responseCallback.onFailure(code, object.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, responseCallback);
    }
}
