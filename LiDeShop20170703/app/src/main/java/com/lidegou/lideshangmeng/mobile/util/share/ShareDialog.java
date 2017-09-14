package com.lidegou.lideshangmeng.mobile.util.share;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.jiongbull.jlog.JLog;
import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.pay.wechat.Util;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.util.dialog.BaseDialog;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.utils.Utility;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;


/**
 * 分享选择
 *
 * @author Y
 * @version 0.1
 * @date 2016年8月22日09:32:49
 */

public class ShareDialog extends BaseDialog {
    BaseActivity activity;
    private String title;
    private String imgUrl;
    private String url;

    public ShareDialog(Context context) {
        super(context);
        this.activity = (BaseActivity) context;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_share;
    }

    @Override
    protected void init() {
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        setCanceledOnTouchOutside(true);

        findViewById(R.id.guanbi).setOnClickListener(this);
        findViewById(R.id.ll_we_chat).setOnClickListener(this);
        findViewById(R.id.ll_we_chart_friend).setOnClickListener(this);
        //findViewById(R.id.ll_we_chart_collection).setOnClickListener(this);
        findViewById(R.id.ll_qq).setOnClickListener(this);
        findViewById(R.id.ll_weibo).setOnClickListener(this);
    }

    public void show(String title, String imgUrl, String url) {
        super.show();
        if (title == null) {
            title = "利德商盟";
        }
        if (url == null) {
            url = "";
        }
        this.title = title;
        this.imgUrl = imgUrl;
        this.url = url;
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_we_chat:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        weChatShare(0);
                    }
                }).start();
                break;
            case R.id.ll_we_chart_friend:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        weChatShare(1);
                    }
                }).start();
                break;
          /*  case R.id.ll_we_chart_collection:
                weChatShare(2);
                break;*/
            case R.id.ll_qq:
                qqShare();
                break;
            case R.id.ll_weibo:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        weiBo();
                    }
                }).start();
                break;
            case R.id.guanbi:
                dismiss();
                break;
        }
    }

    /**
     * 微信分享111
     *
     * @param make
     */
    private void weChatShare(int make) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;
        msg.description = "利德商盟";
        Bitmap thumb = WxSharePic.GetLocalOrNetBitmap(imgUrl);
        if (thumb != null) {
            thumb = Bitmap.createScaledBitmap(thumb, 120, 120, true);//压缩Bitmap
            msg.thumbData = Util.bmpToByteArray(thumb, true);
        }
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.message = msg;
        req.scene = make;
        getApp().getWXAPI().sendReq(req);
        Log.i(TAG, "getApp().getWXAPI().sendReq(req):" + getApp().getWXAPI().sendReq(req));
    }

    /**
     * QQ分享
     */

    private void qqShare() {
        Bundle bundle = new Bundle();
        //分享的标题。注：PARAM_TITLE、PARAM_IMAGE_URL、PARAM_	SUMMARY不能全为空，最少必须有一个是有值的。
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        //这条分享消息被好友点击后的跳转URL。
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);
        ///分享的消息摘要，最长50个字
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, "利德商盟");

        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imgUrl);

        getApp().getTencent().shareToQQ(getActivity(), bundle, iUiListener);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, iUiListener);
    }

    private IUiListener iUiListener = new IUiListener() {
        @Override
        public void onComplete(Object o) {
            Log.e(TAG, String.valueOf(o));
        }

        @Override
        public void onError(UiError uiError) {
            Log.e(TAG, uiError.errorCode + "");
            Log.e(TAG, uiError.errorMessage + "");
            Log.e(TAG, uiError.errorDetail + "");
        }

        @Override
        public void onCancel() {

        }
    };


    /**
     * 微博分享
     */
    private void weiBo() {
        WebpageObject mediaObject = new WebpageObject();
        mediaObject.identify = Utility.generateGUID();
        mediaObject.title = title;
        mediaObject.description = "利德商盟";
        Bitmap thumb = WxSharePic.GetLocalOrNetBitmap(imgUrl);
        if (thumb != null) {
            thumb = Bitmap.createScaledBitmap(thumb, 120, 120, true);//压缩Bitmap
            // 设置 Bitmap 类型的图片到视频对象里         设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
            mediaObject.setThumbImage(thumb);
        }

        mediaObject.actionUrl = url;
        mediaObject.defaultText = "Webpage 默认文案";

        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        weiboMessage.mediaObject = mediaObject;

        SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.multiMessage = weiboMessage;

        AuthInfo authInfo = new AuthInfo(getActivity(), Config.WB.APP_KEY, Config.WB.REDIRECT_URL, Config.WB.SCOPE);
        Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(getApp());
        String token = "";
        if (accessToken != null) {
            token = accessToken.getToken();
        }
        getApp().getWeiboShareAPI().sendRequest(getActivity(), request, authInfo, token, new WeiboAuthListener() {
            @Override
            public void onComplete(Bundle bundle) {
                Oauth2AccessToken newToken = Oauth2AccessToken.parseAccessToken(bundle);
                AccessTokenKeeper.writeAccessToken(getApp(), newToken);
            }

            @Override
            public void onWeiboException(WeiboException e) {
                JLog.e(e.getMessage());
            }

            @Override
            public void onCancel() {

            }
        });
    }
}
