package com.lidegou.lideshangmeng.mobile.data.dao;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.entity.Banner;

import java.util.List;

/**
 * Created by Y on 2016/8/10.
 */

public interface IBannerDao {

    void getBannderList(ResponseCallback<List<Banner>> responseCallback);

}
