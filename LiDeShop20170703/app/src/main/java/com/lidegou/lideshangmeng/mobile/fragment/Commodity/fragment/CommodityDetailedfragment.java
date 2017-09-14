package com.lidegou.lideshangmeng.mobile.fragment.Commodity.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;

/**
 * 商品参数介绍
 */
@SuppressLint("ValidFragment")
public class CommodityDetailedfragment extends BaseFragment {

    WebView webView;
    LinearLayout lin_back;
    private OnBackClickListener onBackClickListener;

    public CommodityDetailedfragment(OnBackClickListener onBackClickListener) {
        this.onBackClickListener = onBackClickListener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_commodity_detailed;
    }

    @Override
    protected void init() {
        webView = (WebView) getRootView().findViewById(R.id.tv_goods_desc);
        lin_back = (LinearLayout) getRootView().findViewById(R.id.lin_back);
        lin_back.setOnClickListener(this);
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                onBackClickListener.callback();
                break;
        }
    }

    @Override
    protected void lazyLoad() {

    }

    public void setDesc(String desc) {
        if (desc == null) {
        } else {
            //能够的调用JavaScript代码
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

            String targeturl="http://www.lidegou.com/";
            webView.loadDataWithBaseURL(targeturl,"<html><body>"+desc+"</body></html>","text/html", "UTF-8",null);
        }
    }


    public interface OnBackClickListener {
        void callback();
    }
}
