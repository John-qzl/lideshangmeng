package com.lidegou.lideshangmeng.mobile.data;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.ui.MallHomeActivity;
import com.lidegou.lideshangmeng.mobile.util.NetUtil;
import com.lidegou.lideshangmeng.mobile.util.Prefs;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;

/**
 * Created by Y on 2016/8/10.
 */

public class HttpUtil {

    public static final int NET_ERROR = -1;
    public static final int DATA_NULL_ERROR = -2;
    public static final int PARSER_ERROR = -3;

    private Gson gson;

    public HttpUtil() {
        gson = new Gson();
    }

    protected <T> T getObj(JSONObject jsonObj, Class<T> tClass) {
        return gson.fromJson(jsonObj.toString(), tClass);
    }

    protected void GET(String url, final Callback callback, final ResponseCallback responseCallback) {
        OkHttpUtils.get()
                .url(url)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                dealCallback(response, callback, responseCallback);
            }
        });
    }

    protected void POST(String url, Map<String, String> params, final Callback callback, final ResponseCallback responseCallback) {
        if (!NetUtil.isNetworkConnected(App.getApp())) {
            Toast.makeText(App.getApp(), "手机无网络，请检查网络后重启软件", Toast.LENGTH_SHORT).show();
            return;
        }
        String uid = params.get("uid");
        if (uid == null || uid.equals("") || url.contains("forget_password")) {
            post(url, params, callback, responseCallback);
        } else if (Config.User.STATUS) {
            PostCheckUsers(uid, url, params, callback, responseCallback);
        }
    }

    protected void PostCheckUsers(String uid, final String url, final Map<String, String> params, final Callback callback, final ResponseCallback responseCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        OkHttpUtils.post()
                .url(HttpInterface.CheckUsers())
                .params(map)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("code");
                    if (code == 100) {
                        Config.User.STATUS = true;
                        post(url, params, callback, responseCallback);
                    } else {
                        Config.User.STATUS = false;
                        App.getApp().toHome();
                        Toast.makeText(App.getApp(), "未登录，请重新登陆后进行操作", Toast.LENGTH_SHORT).show();
                        App.getApp().setUid(null);
                        JPushInterface.setAlias(App.getApp(), "", null);
                        Prefs.with(App.getApp()).writeString(Config.User.UID, null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void AutoLogin(final Map<String, String> map) {
        OkHttpUtils.post()
                .url(HttpInterface.CheckUsers())
                .params(map)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("code");
                    if (code == 100) {
                        String uid = map.get("uid");
                        JPushInterface.setAlias(App.getApp(), uid, null);
                        Config.User.STATUS = true;
                        App.getApp().setUid(uid);
                    } else {
                        App.getApp().setUid(null);
                        Prefs.with(App.getApp()).writeString(Config.User.UID, null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void AppUpdate(final MallHomeActivity activity) throws PackageManager.NameNotFoundException {
        PackageManager pm = activity.getPackageManager();
        final PackageInfo pi = pm.getPackageInfo(activity.getPackageName(),
              0);

        OkHttpUtils.get()
                .url(HttpInterface.AppUpdate())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject object = new JSONObject(response);
                    double newVersion = object.getDouble("version");
                    double version = Double.parseDouble(pi.versionName);
                    Log.i("whfyy","当前版本"+ pi.versionCode+" 最新版本:"+newVersion);
                    if (newVersion > version) {
                        String url = object.getString("url");
                        activity.AppUpdate(newVersion + "", url);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }


    private void post(String url, Map<String, String> params, final Callback callback, final ResponseCallback responseCallback) {
        OkHttpUtils.post()
                .url(url)
                .params(params)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                dealCallback(response, callback, responseCallback);
            }
        });
    }

    private synchronized void dealCallback(String response, Callback callback, ResponseCallback responseCallback) {

        if (response == null || response.length() <= 0) {
//            responseCallback.onFailure(DATA_NULL_ERROR, "Data is Null");
        }

        callback.callback(response);

    }

    public interface Callback {

        void callback(String result);

    }

}
