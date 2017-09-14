//
// Created by Y on 2016/8/11.
//

#include <jni.h>
#include <iostream>


extern "C" {

std::string getServerUrl() {
    return "http://123.56.8.164:8080/chuangke/";
}

/**
 * 登录
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getLogin(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "userLogin";
    return env->NewStringUTF(url.data());
}

/**
 * 注册
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getRegister(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "register";
    return env->NewStringUTF(url.data());
}

/**
 * 注册验证码
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getRegisterSmsCode(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "registerGetCode";
    return env->NewStringUTF(url.data());
}

/**
 * 忘记密码
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getForgot(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "setPassword";
    return env->NewStringUTF(url.data());
}

/**
 * 忘记密码验证码
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getForgotSmsCode(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "getCode";
    return env->NewStringUTF(url.data());
}

/**
 * 添加地址
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_addAddress(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "addUserAddress";
    return env->NewStringUTF(url.data());
}

/**
 * 删除地址
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_deleteAddress(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "dlAddress";
    return env->NewStringUTF(url.data());
}

/**
 * 获取默认地址
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getDefaultAddress(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "defaultAddress";
    return env->NewStringUTF(url.data());
}

/**
 * 地址列表
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getAddressList(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "getAllAdress";
    return env->NewStringUTF(url.data());
}

/**
 * 更新地址信息
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_updateAddress(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "upOldAddress";
    return env->NewStringUTF(url.data());
}

/**
 * 更新默认地址
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_updateDefaultAddress(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "upUserAddress";
    return env->NewStringUTF(url.data());
}

/**
 * 按id查询地址
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getaddressid(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "selectByAdressID";
    return env->NewStringUTF(url.data());
}

/**
 * 修改昵称
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_updateNickname(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "updateName";
    return env->NewStringUTF(url.data());
}

/**
 * 修改用户头像
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_updateStoreImage(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "modify";
    return env->NewStringUTF(url.data());
}

/**
 * 首页轮播图
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getBanner(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "getWelcomeImage";
    return env->NewStringUTF(url.data());
}

/**
 * 广告图片
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getAdvertising(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "getvipAdvertising";
    return env->NewStringUTF(url.data());
}

/**
 * 今日推荐商品
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getTodayRecom(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "selectRecommImage";
    return env->NewStringUTF(url.data());
}

/**
 * 获取单个分类横幅
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getSingleClassifyBanner(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "getadwareImage";
    return env->NewStringUTF(url.data());
}

/**
 * 特殊商品
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getSpecialCommodity(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "selectSpecialComm";
    return env->NewStringUTF(url.data());
}

/**
 * 子分类
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getSubClassify(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "selectChar";
    return env->NewStringUTF(url.data());
}

/**
 * 首页分类商品
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getHomeClassifyCommodity(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "getCommoditys";
    return env->NewStringUTF(url.data());
}

/**
 * 分类商品
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getClassifyCommodity(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "selectCharname";
    return env->NewStringUTF(url.data());
}

/**
 * 分区商品
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getCommodityZone(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "selectZoneComm";
    return env->NewStringUTF(url.data());
}

/**
 * 旗舰店商品
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getFlagshipStoreCommodity(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "selectstoreimg";
    return env->NewStringUTF(url.data());
}

/**
 * Name 查询商品详情
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getNameCommodityDetails(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "findCommoditys";
    return env->NewStringUTF(url.data());
}

/**
 * Id 查询商品详情
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getIdCommodityDetails(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "findCommoditys";
    return env->NewStringUTF(url.data());
}

/**
 * Code 查询商品详情
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getCodeCommodityDetails(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "findbySpecialstoreID";
    return env->NewStringUTF(url.data());
}

/**
 * 获取商品轮播图
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getCommodityBanner(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "selectCommLunbo";
    return env->NewStringUTF(url.data());
}

/**
 * 获取商品介绍图片
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getCommoditytIntroduceImg(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "selectCommsummary";
    return env->NewStringUTF(url.data());
}


/**
 * 添加商品评论
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_addCommodityComments(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "getCommodityReview";
    return env->NewStringUTF(url.data());
}

/**
 * 查看商品评论
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_selectCommodityComments(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "getEvaluation";
    return env->NewStringUTF(url.data());
}

/**
 * 判断是否收藏
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_judgeCommodityCollection(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "seleConcern";
    return env->NewStringUTF(url.data());
}

/**
 * 添加收藏商品
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_addCommodityCollection(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "saveNewConcern";
    return env->NewStringUTF(url.data());
}

/**
 * 删除收藏商品
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_deleteCommodityCollection(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "deleMyConcern";
    return env->NewStringUTF(url.data());
}

/**
 * 查看收藏商品
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_selectCommodityCollection(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "seleMyConcerns";
    return env->NewStringUTF(url.data());
}

/**
 * 添加商品浏览记录
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_addCommodityBrowsingHistory(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "addbrowse";
    return env->NewStringUTF(url.data());
}

/**
 * 查看商品浏览记录
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_selectCommodityBrowsingHistory(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "getbrowse";
    return env->NewStringUTF(url.data());
}

/**
 * 添加购物车
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_addShoppingCart(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "addCommodityCart";
    return env->NewStringUTF(url.data());
}

/**
 * 获取购物车列表
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getShoppingCart(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "findByuserid";
    return env->NewStringUTF(url.data());
}

/**
 * 修改购物车商品
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_updateShoppingCart(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "modifyCommodityCart";
    return env->NewStringUTF(url.data());
}

/**
 * 删除购物车商品
 */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_deleteShoppingCart(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "delCommodityCart";
    return env->NewStringUTF(url.data());
}

/**
  * 查询 0/未支付 1/已支付 2/已完成订单
  */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getOrderListid(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "getOrderUserid";
    return env->NewStringUTF(url.data());
}

/**
  * 直接下单
  */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_directpaymentOrder(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "directpaymentOrder";
    return env->NewStringUTF(url.data());
}

/**
  * 查询单一订单
  */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_getSingeOrderid(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "getOrderid";
    return env->NewStringUTF(url.data());
}

/**
  * 购物车下订单
  */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_shoppingCartCommitOrder(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "commit";
    return env->NewStringUTF(url.data());
}

/**
  * 取消订单
  */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_cancleOrder(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "cancleOrderInfo";
    return env->NewStringUTF(url.data());
}

/**
  * 确认收货
  */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_confirmOrder(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "confirmCommodity";
    return env->NewStringUTF(url.data());
}


/**
   * 提醒发货
   */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_remindOrder(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "remindFaHuo";
    return env->NewStringUTF(url.data());
}

/**
  *  支付完成
  */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_alipayPayment(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "AliPayBuyed";
    return env->NewStringUTF(url.data());
}

/**
  *  手机钱币支付完成
  */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_phoneMoneyPayment(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "Phonemoney";
    return env->NewStringUTF(url.data());
}

/**
  *  红包支付完成
  */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_RedbagMoneyPayment(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "redenveMoney";
    return env->NewStringUTF(url.data());
}


/**
  * 返利
  */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_rebateprice(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "getRebatepriceUserid";
    return env->NewStringUTF(url.data());
}

/**
  * 用户反馈
  */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_userfeedback(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "feedbackSth";
    return env->NewStringUTF(url.data());
}

/**
  * 消息
  */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_jpushMessage(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "selectMessage";
    return env->NewStringUTF(url.data());
}

/**
  * 根据id查询用户信息
  */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_userinfoid(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "selectUserID";
    return env->NewStringUTF(url.data());
}

/**
  * 查询订单记录
  */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_selectOrderRecord(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "selectOrderRecord";
    return env->NewStringUTF(url.data());
}

/**
  * 查询返现记录
  */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_selectRebateRecord(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "selectRebateRecord";
    return env->NewStringUTF(url.data());
}

/**
  * 查询我的分享
  */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_selectMyshare(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "selectMyshare";
    return env->NewStringUTF(url.data());
}

/**
  * 领取红包
  */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_receivePrice(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "myshare";
    return env->NewStringUTF(url.data());
}

/**
  * 删除我的收藏
  */
jstring
Java_com_wnlc_community_o2o_data_HttpInterface_deleteCollection(JNIEnv *env, jobject) {
    std::string url = getServerUrl() + "deleColl";
    return env->NewStringUTF(url.data());
}

}