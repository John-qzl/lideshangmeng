package com.lidegou.lideshangmeng.mobile.data;

/**
 * Created by Y on 2016/8/10.
 */

public interface ResponseCallback<T> {

    void onSuccess(T data);

    void onFailure(int code, String msg);


}
