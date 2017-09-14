package com.lidegou.lideshangmeng.mobile.fragment.home.web;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;

public class WebViewActivity extends BaseActivity {
    private WebView webView;
    private String url;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void init() {
        url = getIntent().getStringExtra("url");
        webView = (WebView) findViewById(R.id.webView);
        if (url != null) {
            WebSettings ws = webView.getSettings();
            ws.setJavaScriptEnabled(true); //这里如果本地html引入了javascript那么需要把这个设置为true
            ws.setAllowFileAccess(true);
            ws.setSaveFormData(false);
            ws.setBuiltInZoomControls(true);
            ws.setSupportZoom(true);
            ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            ws.setDefaultTextEncodingName("UTF-8"); //设置文本编码
            ws.setAppCacheEnabled(true);
            ws.setCacheMode(WebSettings.LOAD_DEFAULT);//设置缓存模式
            ws.setUseWideViewPort(true);// 这个很关键
            ws.setLoadWithOverviewMode(true);
            ws.setDomStorageEnabled(true);

            webView.loadUrl(url);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    // TODO Auto-generated method stub
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }
            });

        }
        findViewById(R.id.lin_back).setOnClickListener(this);
    }

    @Override
    protected void viewClick(View view) {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}