package com.lidegou.lideshangmeng.mobile.data.dao;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.entity.Classify;

import java.util.List;

/**
 * Created by Administrator on 2016/12/16.
 */

public interface IClassifyDao {

    void getClassifyList(ResponseCallback<List<Classify>> responseCallback);

}
