package com.lidegou.lideshangmeng.mobile;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.jiongbull.jlog.JLog;
import com.lidegou.lideshangmeng.mobile.data.entity.Place;
import com.lidegou.lideshangmeng.mobile.data.entity.UserInfo;
import com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin.MerchantsInFourActivity;
import com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin.MerchantsInOneActivity;
import com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin.MerchantsInThreeActivity;
import com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin.MerchantsInTwoActivity;
import com.lidegou.lideshangmeng.mobile.fragment.Search.SearchAllActivity;
import com.lidegou.lideshangmeng.mobile.fragment.SearchCommodity.SearchCommodityActivity;
import com.lidegou.lideshangmeng.mobile.fragment.SearchStoreShop.SearchStoreShopActivity;
import com.lidegou.lideshangmeng.mobile.ui.MallHomeActivity;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;
import com.lidegou.lideshangmeng.mobile.util.NetUtil;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;
import com.uzmap.pkg.openapi.APICloud;
import com.zhy.http.okhttp.OkHttpUtils;

import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;


/**
 * 应用程序
 *
 * @author Y
 * @version 0.1
 * @date 2016年8月22日09:32:14
 */
public class App extends Application {
    private ImageLoaderConfiguration configuration;


    public static final String TAG = App.class.getSimpleName();

    private static App app;
    //用户信息
    private List<UserInfo> user;

    private String uid;

    private String show_pic;
    //Activity列表
    private List<AppCompatActivity> activityList;

    //微信 API
    private IWXAPI iwxapi;
    //QQ
    private Tencent tencent;
    //微博 API
    private IWeiboShareAPI iWeiboShareAPI;

    private static boolean payOrderSuccess = false;

    private static boolean paySingleALLSuccess = false;
    private static boolean paySingleYesSuccess = false;
    private static boolean paySingleWaitSuccess = false;
    private static boolean takeOrderSuccess= false;

    private boolean isLoginOut = false;
    private Place place;
    private int notiHomePage = -1;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;


        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        //百度地图
        SDKInitializer.initialize(this);
        MultiDex.install(this);


        //初始化Activity列表
        activityList = new ArrayList<>();

        //初始化Log
        JLog.init(this).setDebug(true);
        //初始化图片加载

        MyImageLoader.getInstance().init(this);

        //初始化网络请求
        initHttp();
        //初始化网络状态
        initNet();

        APICloud.initialize(this);//初始化APICloud，SDK中所有的API均需要初始化后方可调用执行
        //初始化APICloud网络请求框架，如果不需要则忽略，具体使用方式见MainActivity中的Case
//        APICloudHttpClient.createInstance(this);

        Log.e(TAG, "[App] ");
        //开启极光推送

        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush
//        JPushInterface.setAlias(this, "", null);

        x.Ext.init(this);

        /*************************************imageLoader初始化****************************************/
        String s = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "lidegou" + File.separator + "image";
        File cacheDir = new File(s);
        configuration = new ImageLoaderConfiguration.Builder(this)
                .threadPoolSize(4)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .diskCacheSize(200 * 1024 * 1024)
                .diskCacheFileCount(100)
                .diskCache(new UnlimitedDiskCache(cacheDir))
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .build();
        ImageLoader.getInstance().init(configuration);
    }

    public List<UserInfo> getUser() {
        return user;
    }

    public void setUser(List<UserInfo> user) {
        this.user = user;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getShow_pic() {
        return show_pic;
    }

    public void setShow_pic(String show_pic) {
        this.show_pic = show_pic;
    }

    public void addActivity(AppCompatActivity activity) {
        activityList.add(activity);
    }

    private void initHttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

    }

    /**
     * 初始化网络状态
     */
    private void initNet() {
        Config.Net.STATUS = NetUtil.isNetworkConnected(this);
        Config.Net.TYPE = NetUtil.getNetworkType(this);
    }

    @Override
    public void onTerminate() {
        if (activityList != null && activityList.size() > 0) {
            for (AppCompatActivity activity : activityList) {
                activity.finish();
            }
        }
        super.onTerminate();
    }

    public IWXAPI getWXAPI() {
        if (iwxapi == null) {
            iwxapi = WXAPIFactory.createWXAPI(this, Config.WX.APP_ID);
            iwxapi.registerApp(Config.WX.APP_ID);
        }
        return iwxapi;
    }

    public Tencent getTencent() {
        if (tencent == null) {
            tencent = Tencent.createInstance(Config.QQ.APP_ID, this);
        }
        return tencent;
    }

    public IWeiboShareAPI getWeiboShareAPI() {
        if (iWeiboShareAPI == null) {
            iWeiboShareAPI = WeiboShareSDK.createWeiboAPI(getApplicationContext(), Config.WB.APP_KEY);
            iWeiboShareAPI.registerApp();
        }
        return iWeiboShareAPI;
    }

    public static App getApp() {
        return app;
    }


    public void toHome() {
        for (AppCompatActivity appCompatActivity : activityList) {
            if (!(appCompatActivity instanceof MallHomeActivity)) {
                appCompatActivity.finish();
            } else {
                ((MallHomeActivity) appCompatActivity).toHome();
            }
        }
    }

    public void toMy() {
        for (AppCompatActivity appCompatActivity : activityList) {
            if (!(appCompatActivity instanceof MallHomeActivity)) {
                appCompatActivity.finish();
            } else {
                ((MallHomeActivity) appCompatActivity).toMy();
            }
        }
    }

    public void setShopsStoreFirst() {
        for (AppCompatActivity appCompatActivity : activityList) {
            if (appCompatActivity instanceof MallHomeActivity) {
                ((MallHomeActivity) appCompatActivity).setShopsStoreFirst();
            }
        }
    }


    public void closeMerchantsInOther() {
        for (AppCompatActivity appCompatActivity : activityList) {
            if (appCompatActivity instanceof MerchantsInOneActivity) {
                appCompatActivity.finish();
            }
            if (appCompatActivity instanceof MerchantsInTwoActivity) {
                appCompatActivity.finish();
            }
            if (appCompatActivity instanceof MerchantsInThreeActivity) {
                appCompatActivity.finish();
            }
            if (appCompatActivity instanceof MerchantsInFourActivity) {
                appCompatActivity.finish();
            }
        }
    }

    public void closeSearch() {
        for (AppCompatActivity appCompatActivity : activityList) {
            if (appCompatActivity instanceof SearchAllActivity) {
                appCompatActivity.finish();
            }
            if (appCompatActivity instanceof SearchCommodityActivity) {
                appCompatActivity.finish();
            }
            if (appCompatActivity instanceof SearchStoreShopActivity) {
                appCompatActivity.finish();
            }
        }
    }

    public static void setPayOrderSuccess(boolean payOrderSuccess) {
        App.payOrderSuccess = payOrderSuccess;
    }


    public static void setPaySingleALLSuccess(boolean paySingleALLSuccess) {
        App.paySingleALLSuccess = paySingleALLSuccess;
    }

    public static void setPaySingleWaitSuccess(boolean paySingleWaitSuccess) {
        App.paySingleWaitSuccess = paySingleWaitSuccess;
    }

    public static void setPaySingleYesSuccess(boolean paySingleYesSuccess) {
        App.paySingleYesSuccess = paySingleYesSuccess;
    }

    public static void setTakeOrderSuccess(boolean takeOrderSuccess) {
        App.takeOrderSuccess = takeOrderSuccess;
    }

    public static boolean isPayOrderSuccess() {
        return payOrderSuccess;
    }

    public static boolean isPaySingleALLSuccess() {
        return paySingleALLSuccess;
    }

    public static boolean isPaySingleWaitSuccess() {
        return paySingleWaitSuccess;
    }

    public static boolean isPaySingleYesSuccess() {
        return paySingleYesSuccess;
    }   public boolean isTakeOrderSuccess() {
        return takeOrderSuccess;
    }

    public void setLoginOut(boolean loginOut) {
        isLoginOut = loginOut;
    }

    public boolean isLoginOut() {
        return isLoginOut;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public void setNotiHomePage(int notiHomePage) {
        this.notiHomePage = notiHomePage;
    }

    public int getNotiHomePage() {
        return notiHomePage;
    }


}
