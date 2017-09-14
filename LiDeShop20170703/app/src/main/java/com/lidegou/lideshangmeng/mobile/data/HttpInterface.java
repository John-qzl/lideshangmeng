package com.lidegou.lideshangmeng.mobile.data;

/**
 * Created by Y on 2016/8/10.
 */

public class HttpInterface {

    public static String URL =
    //"http://www.lidegou.com/apptest/index.php";
    "http://www.lidegou.com/app/index.php";
    public static String URL_IMEI = URL + "?r=user/profile/GetQr";

    private static String IP = URL;
    private static String nowPlace;
    private static boolean address;

    public static String getIP() {
        return IP;
    }

    /**
     * 登录
     *
     * @return
     */

    public static String getLogin() {
        return IP + "?r=user/login";
    }

    /**
     * 注册
     *
     * @return
     */
    public static String getRegister() {
        return IP + "?r=user/login/register";
    }

    /*
    *加载省
     */
    public static String getProvince() {
        return IP + "?r=user/login/province_list";
    }

    /*
    *加载市
     */
    public static String getCity() {
        return IP + "?r=user/login/getregion";
    }

    /*
    *加载区
     */
    public static String getCounty() {
        return IP + "?r=user/login/getregion";
    }

    /**
     * 注册验证码
     *
     * @return
     */
    public static String getRegisterSmsCode() {
        return IP + "?r=sms/index/send";
    }

    /**
     * 是否已经注册
     *
     * @return
     */
    public static String isRegister() {
        return IP + "?r=user/login/Checkregmobilename";
    }
    /**
     * 提交身份信息
     */
    public static String register_idcard(){
        return IP+"?r=user/index/IdEdit";
    }
    /**
     * 身份证验证状态
     */
    public static String isIdCard(){
        return IP+"?r=user/index/Idcheck";
    }

    /**
     * 忘记密码
     *
     * @return
     */
    public static String getUpdatePwdCode() {
        return IP + "?r=sms/index/send";
    }

    public static String getUid() {
        return IP + "?r=user/login/get_password_phone";
    }


    public static String getUpdatePwd() {
        return IP + "?r=user/login/forget_password";
    }

    /**
     * 修改密码
     *
     * @return
     */
    public static String getUpdatePass() {
        return IP + "?r=user/index/edit_password";
    }

    /**
     * 修改邮箱
     *
     * @return
     */
    public static String updateEmail() {
        return IP + "?r=user/profile/user_edit_email";
    }

    /**
     * 注销
     */
    public static String getloginout() {
        return IP + "?r=user/login/loginout";
    }

    /**
     * 获取个人信息
     */
    public static String getUserInfoList() {
        return IP + "?r=user/profile";
    }

    /**
     * 用户
     */
    public static String getUserInfo() {
        return IP + "?r=user";
    }

    ;

    /**
     * 轮播图
     */
    public static String getBanner() {
        return IP + "?r=site/index/auto_pic";
    }

    /**
     * 公告
     */
    public static String getAnnouncement() {
        return IP + "?r=site/index/new_articles";
    }

    /**
     * 猜你喜欢
     */
    public static String getMylike() {
        return IP + "?r=site/index/async_list";
    }

    /**
     * 分类
     */
    public static String getClassifyList() {
        return IP + "?r=category";
    }

    /**
     * 推荐商铺
     */
    public static String getShopStore() {
        return IP + "?r=site/index/new_store";
    }

    /**
     * 推荐商品
     */
    public static String getCommodity() {
        return IP + "?r=site/index/new_goods";
    }

    /**
     * 我的足迹
     */
    public static String getFootprint() {
        return IP + "?r=user/index/history";
    }

    /**
     * 清空我的足迹
     */
    public static String clearFootprint() {
        return IP + "?r=user/index/clear_history";
    }

    /**
     * 收藏商品
     */

    public static String selectCollectionCommodity() {
        return IP + "?r=user/index/collectionlist";
    }

    /**
     * 删除收藏商品
     */

    public static String clearCollectionCommodity() {
        return IP + "?r=user/index/delcollection";
    }

    /**
     * 关注店铺
     */

    public static String selectAttentionCommodity() {
        return IP + "?r=user/index/storelist";
    }

    /**
     * 删除收藏商品
     */

    public static String clearAttentionCommodity() {
        return IP + "?r=user/index/delstore";
    }

    /**
     * 资金管理
     */
    public static String getMoneyManage() {
        return IP + "?r=user/account";
    }
    /**
     * 资金管理
     */
    public static String getMoneyManage2() {
        return IP + "?r=user/zuodan/addinfo";
    }

    /**
     * 账户明细
     */
    public static String getAccountDetails() {
        return IP + "?r=user/account/detail";
    }

    /**
     * 子分类商品
     */
    public static String getProducts() {
        return IP + "?r=category/index/products";
    }

    /**
     * 添加银行卡
     */
    public static String AddCard() {
        return IP + "?r=user/account/addcard";
    }

    /**
     * 银行卡列表
     */
    public static String getCardlist() {
        return IP + "?r=user/account/cardlist";
    }

    /**
     * 删除银行卡
     */
    public static String deleteCardlist() {
        return IP + "?r=user/account/delcard";
    }

    /**
     * 多个银行
     */
    public static String addCardInfo() {
        return IP + "?r=user/account/addcardinfo";
    }

    /**
     * 提现页面
     */
    public static String accounTraply() {
        return IP + "?r=user/account/accountraply";
    }

    /**
     * 提现
     */
    public static String submitAccounTraply() {
        return IP + "?r=user/account/account";
    }

    /**
     * 申请记录
     */
    public static String ApplyRecord() {
        return IP + "?r=user/account/log";
    }

    /**
     * 申请记录详情
     */
    public static String ApplyRecordDetail() {
        return IP + "?r=user/account/accountdetail";
    }

    /**
     * 取消提现
     */
    public static String cancelRecord() {
        return IP + "?r=user/account/Cancel";
    }

    /**
     * 积分记录
     */
    public static String AccountBonus() {
        return IP + "?r=user/account/AccountBonus";
    }

    /**
     * 推荐会员
     */
    public static String AccountRecommend() {
        return IP + "?r=user/account/PartnerList";
    }

    /**
     * 转入钱包
     */
    public static String BonusTurn() {
        return IP + "?r=user/account/BonusTurn";
    }

    /**
     * 获取推荐人数量
     * @return
     */
    public static String getNumber(){
        return IP+"?r=user/account/index";
    }

    /**
     * 搜索热门关键词
     */
    public static String SearchHotKeyword() {
        return IP + "?r=category/index/hotkeyword";

    }

    /**
     * 搜索商品的品牌列表
     */
    public static String BrandsList() {
        return IP + "?r=category/index/BrandsList";
    }

    /**
     * 店铺分类
     */
    public static String getShopsClassify() {
        return IP + "?r=store/index/getcats";
    }

    /**
     * 店铺列表
     */
    public static String getShopStoreList() {
        return IP + "?r=store";
    }

    /**
     * 做单上限金额
     */
    public static String getCeilingMoney() {
        return IP + "?r=user/zuodan/addinfo";
    }

    /**
     * 做单检测用户
     */
    public static String getMcheckuser() {
        return IP + "?r=user/index/mcheckuser";
    }

    /**
     * 提交做单
     */
    public static String AddSingle() {
        return IP + "?r=user/zuodan/add";
    }


    /**
     * 做单列表
     */
    public static String getSingleList() {
        return IP + "?r=user/zuodan";
    }

    /**
     * 做单支付信息
     */
    public static String getPaySingle() {
        return IP + "?r=user/zuodan/payinfo";
    }

    /**
     * 做单支付
     */
    public static String PaySingle() {
        return IP + "?r=user/zuodan/pay";
    }

    /**
     * 店铺头部
     */
    public static String getTabs() {
        return IP + "?r=store/index/gettabs";
    }

    /**
     * 关注店铺
     */
    public static String FocusShops() {
        return IP + "?r=store/index/addcollect";
    }


    /**
     * 店铺详情
     */
    public static String getStoreDetails() {
        return IP + "?r=store/index/shop_info";
    }

    /**
     * 商品详情
     */
    public static String getCommodityDetails() {
        return IP + "?r=goods/Index";
    }


    /**
     * 添加购物车
     */
    public static String getAddToCat() {
        return IP + "?r=cart/index/add_to_cart";
    }

    /**
     * 收货地址
     */
    public static String addressList() {
        return IP + "?r=user/index/addresslist";
    }

    /**
     * 添加/修改 收货地址
     */
    public static String AddAddress() {
        return IP + "?r=user/index/addaddress";
    }

    /**
     * 修改收货地址页面
     */
    public static String UpdateAddressInfo() {
        return IP + "?r=user/index/addressinfo";
    }

    /**
     * 设置默认地址
     */
    public static String SetdefaultAddress() {
        return IP + "?r=user/index/ajax_make_address";
    }

    /**
     * 删除收货地址
     */
    public static String deleteAddress() {
        return IP + "?r=user/index/drop";
    }

    /**
     * 设置性别
     */
    public static String SetGender() {
        return IP + "?r=user/profile/editprofile";
    }


    /**
     * 设置头像
     */
    public static String updateLogo() {
        return IP + "?r=user/index/Update_userlogo";
    }

    /**
     * 验证是否登录
     */
    public static String CheckUsers() {
        return IP + "?r=user/login/CheckUsers";
    }

    /**
     * 购物车列表
     */
    public static String CarList() {
        return IP + "?r=cart/index";
    }

    /**
     * 删除购物车
     */
    public static String DeleteCar() {
        return IP + "?r=cart/index/delete_cart";
    }

    /**
     * 批量删除购物车
     */
    public static String BatchDeleteCar() {
        return IP + "?r=cart/index/drop_goods";
    }

    /**
     * 修改购物车商品数量
     */
    public static String updateGoodNumber() {
        return IP + "?r=cart/index/cart_goods_number";
    }

    /**
     * 订单列表
     */
    public static String OrderList() {
        return IP + "?r=user/order";
    }

    /**
     * 待评价列表
     */
    public static String waitComment() {
        return IP + "?r=user/index/comment_list";
    }

    /**
     * 查看订单详情
     */
    public static String OrderDetail() {
        return IP + "?r=user/order/detail";
    }

    /**
     * 下单前订单详情
     */
    public static String getOrderSubmit() {
        return IP + "?r=flow/index/";
    }

    /**
     * 评价商品
     */
    public static String addEvaluation() {
        return IP + "?r=user/index/addcomment";
    }

    /**
     * 评价列表
     */
    public static String Evaluation() {
        return IP + "?r=goods/Index/comment";
    }

    /**
     * 收藏商品
     */
    public static String addCollection() {
        return IP + "?r=goods/index/add_collection";
    }

    /**
     * 删除订单
     *
     * @return
     */
    public static String deleteOrderSubmit() {
        return IP + "?r=user/login/cancel";
    }


    /**
     * *确认订单
     *
     * @return
     */
    public static String confirmOrderSubmit() {
        return IP + "?r=user/order/AffirmReceived";
    }

    /**
     * 提交订单
     */
    public static String confirmOrderDone() {
        return IP + "?r=flow/index/done";
    }

    /**
     * 立即支付
     */
    public static String confirmOrderPay() {
        return IP + "?r=flow/index/pay";
    }


    /**
     * 客户留言
     */

    public static String addmessage() {
        return IP + "?r=user/index/addmessage";
    }

    /**
     * 客户留言列表
     */
    public static String messageList() {
        return IP + "?r=user/index/messagelist";
    }

    /**
     * 店铺入驻查询申请状态
     */
    public static String MerchantsInCheck() {
        return IP + "?r=store/settled/Check";
    }


    /**
     * 店铺入驻协议页面
     */
    public static String MerchantsInOne() {
        return IP + "?r=store/settled/one";
    }

    /**
     * 店铺入驻协议页面提交
     */
    public static String MerchantsInOneDone() {
        return IP + "?r=store/settled/onedone";
    }


    /**
     * 联系方式页面
     */
    public static String MerchantsInTwo() {
        return IP + "?r=store/settled/two";
    }

    /**
     * 联系方式页面提交
     */
    public static String MerchantsInTwoDone() {
        return IP + "?r=store/settled/twodone";
    }

    /**
     * 公司信息页面
     */
    public static String MerchantsInThree() {
        return IP + "?r=store/settled/three";
    }

    /**
     * 公司信息页面提交
     */
    public static String MerchantsInThreeDone() {
        return IP + "?r=store/settled/threedone";
    }

    /**
     * 公司信息页面
     */
    public static String MerchantsInFour() {
        return IP + "?r=store/settled/four";
    }

    /**
     * 公司信息页面提交
     */
    public static String MerchantsInFourDone() {
        return IP + "?r=store/settled/fourdone";
    }

    /**
     * 公司信息页面
     */
    public static String MerchantsInFive() {
        return IP + "?r=store/settled/five";
    }

    /**
     * 客服列表
     */
    public static String qqList() {
        return IP + "?r=user/index/messagelist";
    }

    public static String choosePlace() {
        return IP + "?r=user/index/ChoosePlace";
    }


    public static String payType() {
        return IP + "?r=user/account/deposit";
    }

    public static String rechargeMoney() {
        return IP + "?r=user/account/account";
    }

    public static String rechargePayMoney() {
        return IP + "?r=user/account/accountpay";
    }

    /**
     * 获取当前设置地区
     */
    public static String getNowPlace() {
        return IP + "?r=user/index/GetChoosePlace";
    }

    public static String isAddress() {
        return IP + "?r=flow/index/CheckConsignee";
    }

    public static String isHasCard() {
        return IP + "?r=user/account/CheckBank";
    }

    /**
     * 我的信息
     */
    public static String SysMsg() {
        return IP + "?r=user/account/SysMsg";
    }

    public static String SysMsgDes() {
        return IP + "?r=user/account/SysMsgDes";
    }

    public static String OrderMsg() {
        return IP + "?r=user/account/OrderMsg";
    }

    public static String OrderMsgDes() {
        return IP + "?r=user/account/OrderMsgDes";
    }

    /**
     * 我的设置
     */

    public static String AboutUs() {
        return IP + "?r=site/index/AboutUs";
    }

    public static String Contect() {
        return IP + "?r=site/index/Contect";
    }

    public static String Service() {
        return IP + "?r=site/index/Service";
    }

    public static String Disclaimer() {
        return IP + "?r=site/index/Disclaimer";
    }

    public static String AppIntroduction() {
        return IP + "?r=site/index/AppIntroduction";
    }

    /**
     * 是否入驻
     */
    public static String IsSeller() {
        return IP + "?r=user/index/IsSeller";
    }

    /**
     * 获取支付方式
     * @return
     */
    public static String getBayType(){
        return IP +"?r=user/zuodan/BuyerPay";
    }
    /**
     * 获取进行支付
     * @return
     */
    public static String getToBay(){
        return IP +"?r=user/zuodan/BuyerAdd";
    }


    public static String AppUpdate() {
        return IP + "?r=site/index/AppUpdate";
    }
}
