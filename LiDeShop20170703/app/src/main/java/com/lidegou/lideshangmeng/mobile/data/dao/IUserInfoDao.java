package com.lidegou.lideshangmeng.mobile.data.dao;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.entity.Address;
import com.lidegou.lideshangmeng.mobile.data.entity.City;
import com.lidegou.lideshangmeng.mobile.data.entity.County;
import com.lidegou.lideshangmeng.mobile.data.entity.Province;
import com.lidegou.lideshangmeng.mobile.data.entity.UserId;
import com.lidegou.lideshangmeng.mobile.data.entity.UserInfo;

import java.util.List;

/**
 * Created by Y on 2016/8/11.
 */

public interface IUserInfoDao {

    /**
     * 登录
     *
     * @param account
     * @param password
     * @param responseCallback
     */
    void login(String version, String account, String password, String mark, String remember, ResponseCallback<UserId> responseCallback);

    /**
     * 注册发送验证码
     *
     * @param telephone
     * @param responseCallback
     */
    void registerSmsCode(String telephone, ResponseCallback<Integer> responseCallback);

    /**
     * 注册用户
     *
     * @param nickname
     * @param telephone
     * @param code
     * @param password
     * @param responseCallback
     */
    void register(String version, String telephone, String code, String nickname, String password, String checkpassword, String referees, String province, String city, String county, String mark, ResponseCallback<UserId> responseCallback);


    /**
     * 加载省
     */
    void province(ResponseCallback<List<Province>> responseCallback);

    /**
     * 加载市
     */
    void city(int region_id, ResponseCallback<List<City>> responseCallback);

    /**
     * 加载区
     */
    void county(int region_id, ResponseCallback<List<County>> responseCallback);

    /**
     * 忘记密码发送验证码
     *
     * @param telephone
     * @param responseCallback
     */
    void updateSmsCode(String telephone, ResponseCallback<Integer> responseCallback);

    void getUid(String telephone, String smsCode, ResponseCallback<Integer> responseCallback);

    void updatePwd(String password, String uid, String smsCode, ResponseCallback<String> responseCallback);


    /**
     * 修改密码
     *
     * @param responseCallback
     */
    void updatePassword(String uid, String old_password, String new_password1, String new_password, ResponseCallback<Integer> responseCallback);

    /**
     * 修改邮箱
     *
     * @param responseCallback
     */
    void updateEmail(String uid, String email, ResponseCallback<Integer> responseCallback);

    /**
     * 修改用户头像
     * @param telephone
     * @param picName
     * @param picText
     * @param responseCallback
     */
//    void updateUserImage(String telephone, String picName, String picText, ResponseCallback<Integer> responseCallback);

    /**
     * 注销
     */
    void loginout(String uid, ResponseCallback<Integer> responseCallback);

    /**
     * 获取个人信息
     */
    void getUserInfo(String uid, ResponseCallback<UserInfo.UserInfoBean> responseCallback);


    /**
     * 收货地址
     */
    void addresslist(String uid, String shopid, ResponseCallback<Address> responseCallback);

    /**
     * 添加收货地址
     */
    void Addaddress(String uid, String province_region_id, String city_region_id, String district_region_id, String consignee, String mobile, String address, ResponseCallback<String> responseCallback);

    /**
     * 修改收货地址
     */
    void AddressUpdateInfo(String uid, String address_id, ResponseCallback<Address.AddressUpdateInfo> responseCallback);

    /**
     * 修改收货地址提交
     */
    void UpdateAddress(String uid, String address_id, String province_region_id, String city_region_id, String district_region_id, String consignee, String mobile, String address, ResponseCallback<String> responseCallback);

    /**
     * 设置默认地址
     */
    void setdefaultAddress(String uid, String address_id, ResponseCallback<String> responseCallback);

    /**
     * 删除地址
     */
    void deleteAddress(String uid, String address_id, ResponseCallback<String> responseCallback);

    /**
     * 设置性别
     */
    void setGender(String uid, String sex, ResponseCallback<String> responseCallback);

    /**
     * 修改头像
     */
    void updateLogo(String uid, String user_picture, ResponseCallback responseCallback);

    /**
     * 验证是否登录
     */
    void CheckUsers(String uid, ResponseCallback<Integer> responseCallback);

    void IsRegister(String telephone, ResponseCallback<Boolean> registerPresenter);
    /**
     * 身份证认证
     */
    void RegisterIdCard(String uid,String ID_card,String ID_name ,ResponseCallback activity);
    void isRegisterIdCard(String uid,final ResponseCallback activity);
}
