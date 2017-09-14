package com.lidegou.lideshangmeng.mobile.data.dao;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.entity.AccountDetails;
import com.lidegou.lideshangmeng.mobile.data.entity.BankCard;
import com.lidegou.lideshangmeng.mobile.data.entity.Footprint;
import com.lidegou.lideshangmeng.mobile.data.entity.IntegralEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsIn;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsInFiveEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsInFourEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.MessageOrderntity;
import com.lidegou.lideshangmeng.mobile.data.entity.MessageSysEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.MoneyManage;
import com.lidegou.lideshangmeng.mobile.data.entity.OtherPayEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.PaySinleEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.RecommendEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.SetEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.Single;
import com.lidegou.lideshangmeng.mobile.data.entity.UserInfo;
import com.lidegou.lideshangmeng.mobile.fragment.My.paymoney.PaymoneyActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.chongzhi.PayType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/17.
 */

public interface IMyDao {
    void getUserInfo(String uid, ResponseCallback<List<UserInfo>> responseCallback);//个人中心

    void getUserName(String uid, final ResponseCallback<ArrayList<String>> responseCallback);//通过id获取姓名

    void getFootprint(String uid, ResponseCallback<List<Footprint>> responseCallback);//足迹

    void clearFootprint(String uid, ResponseCallback<String> responseCallback);//清空足迹

    void getMoneyManage(String uid, ResponseCallback<MoneyManage> responseCallback);//资金管理

    void getMoneyManage2(String uid, ResponseCallback<MoneyManage> responseCallback);//资金管理

    void getBayType(String uid, String pr_uid, ResponseCallback<String> responseCallback, PaymoneyActivity activity); //获取支付方式

    void gettoPay(String usemoney, String jine, String type, String uid, String shop_uid, ResponseCallback<String> responseCallback, PaymoneyActivity activity);

    void getAccountDetails(String uid, String page, ResponseCallback<AccountDetails> responseCallback);//账户明细

    void addCard(String uid, String bank_code, String bank_card, String bank_user_name, String bank_region, ResponseCallback<Integer> responseCallback);//添加银行卡

    void getCardlist(String uid, ResponseCallback<List<BankCard>> responseCallback);//银行卡列表

    void deleteCardlist(String uid, String Id, ResponseCallback<Integer> responseCallback);//删除银行卡

    void addCardInfo(String uid, ResponseCallback<BankCard.AddCardInfo> responseCallback);//多个银行

    void payType(ResponseCallback<ArrayList<PayType>> responseCallback);

    void rechargeMoney(String uid, String payment_id, String amount, String user_note, String process_type, String type, ResponseCallback<OtherPayEntity> responseCallback);//充值页面

    void rechargePayMoney(String uid, String id, String payment, ResponseCallback<OtherPayEntity> responseCallback);//充值页面

    void accounTraply(String uid, ResponseCallback<MoneyManage.Accountraply> responseCallback);//提现页面

    void submitAccounTraply(String uid, String amount, String user_note, String bank_number, String real_name, ResponseCallback<Integer> responseCallback);//申请提现

    void applyRecord(String uid, String page, ResponseCallback<MoneyManage.ApplyRecord> responseCallback);//申请记录

    void applyRecordDetail(String uid, String id, ResponseCallback<MoneyManage.ApplyRecord.DataBean> responseCallback);//申请记录详情

    void integral(String uid, String page, ResponseCallback<IntegralEntity> responseCallback);//积分记录

    void recommend(String uid, String page, ResponseCallback<RecommendEntity> responseCallback);//推荐会员

    void bonusTurn(String uid, ResponseCallback<String> responseCallback);

    void getNumber(String uid, ResponseCallback<String> responseCallback);

    void getSingleCeilingMoney(String uid, ResponseCallback<Single.Data.CeilingMoney> responseCallback);//做单上限金额

    void getSingleMcheckuser(String uid, String mobile, ResponseCallback<String> responseCallback);//做单检测用户

    void AddSingle(String uid, String mobile, String jine, String goodsname, String goodsnum, String goodsinfo, String qingdanbianhao, String is_me, String zuodan_img, String picType, ResponseCallback<String> responseCallback);//做单提交

    void SingleList(String uid, String page, String status, ResponseCallback<Single> responseCallback);//做单列表

    void PaySingle(String uid, String id, ResponseCallback<PaySinleEntity> responseCallback);//做单支付

    void pay(String uid, String type, String id, ResponseCallback<OtherPayEntity> responseCallback);

    /**
     * 客户留言
     */
    void addmessage(String uid, String msg_title, String device_type, ResponseCallback<String> responseCallback);

    /**
     * 客户列表
     */
    void messageList(String uid, String page, ResponseCallback responseCallback);


    /**
     * 店铺入驻查询申请状态
     */
    void MerchantsInCheck(String uid, ResponseCallback<MerchantsIn.MerchantsInCheck> responseCallback);


    /**
     * 店铺入驻协议页面
     */
    void MerchantsInOne(String uid, ResponseCallback<MerchantsIn.MerchantsInProtocol> responseCallback);

    /**
     * 店铺入驻协议页面提交
     */
    void MerchantsInOnedone(String uid, ResponseCallback<String> responseCallback);


    /**
     * 联系方式页面
     */
    void MerchantsInTwo(String uid, ResponseCallback<MerchantsIn.MerchantsInContact> responseCallback);

    /**
     * 联系方式页面提交
     */
    void MerchantsInTwodone(String uid, String contactXinbie, String contactPhone, String contactName, ResponseCallback<String> responseCallback);

    /**
     * 公司信息页面
     */
    void MerchantsInThree(String uid, ResponseCallback<MerchantsIn.MerchantsInCompany> responseCallback);

    /**
     * 公司信息页面提交
     */
    void MerchantsInThreedone(String uid, String companyName, String business_license_id, String legal_person, String busines_scope, String license_fileImg, String company_adress, String company_contactTel, String province, String city, String district, String longitude, String latitude, ResponseCallback<String> responseCallback);


    /**
     * 公司信息页面
     */
    void MerchantsInFour(String uid, ResponseCallback<MerchantsInFourEntity> responseCallback);

    /**
     * 公司信息页面
     */
    void MerchantsInFourdone(String uid, String shop_categoryMain, String cat_id, String shop_class_keyWords, String rz_shopName, ResponseCallback<String> responseCallback);

    /**
     * 公司信息页面
     */
    void MerchantsInFive(String uid, ResponseCallback<MerchantsInFiveEntity> responseCallback);

    /**
     * 是否有银行卡
     */
    void isHasCard(String uid, ResponseCallback<Boolean> responseCallback);

    /**
     * 取消提现
     */
    void cancelRecord(String uid, String id, ResponseCallback<Boolean> responseCallback);

    /**
     * 我的系统信息
     */
    void SysMsg(String uid, String page, ResponseCallback<MessageSysEntity> responseCallback);


    /**
     * 系统信息详情
     */
    void SysMsgDes(String uid, String id, ResponseCallback<MessageSysEntity.Data> responseCallback);

    /**
     * 订单信息
     */
    void OrderMsg(String uid, String page, ResponseCallback<MessageOrderntity> responseCallback);


    /**
     * 订单信息详情
     */
    void OrderMsgDes(String uid, String id, ResponseCallback<MessageOrderntity.Data> responseCallback);

    void set(String url, ResponseCallback<SetEntity> responseCallback);

    void IsSeller(String uid, ResponseCallback<String> responseCallback);
}
