package com.txmpay.ewallet.ui.webview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.NestedScrollAgentWebView;
import com.txmpay.ewallet.R;
import com.txmpay.ewallet.base.BaseActivity;

import butterknife.BindView;

/**
 * created by czh on 2018-03-08
 * webview相关
 */
public class BaseWebviewActivity extends BaseActivity {

    protected AgentWeb mAgentWeb;

    @BindView(R.id.root_layout)
    CoordinatorLayout mRootLayout;

    @BindView(R.id.webView_container)
    LinearLayout mWebViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_base_webview;
    }

    @Override
    protected void initView() {
        setToolBarScrollEnable(setToolBarScroll());

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mWebViewContainer, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebView(new NestedScrollAgentWebView(this))
                .setWebChromeClient(creatWebChromeClient())
                .setWebViewClient(creatWebViewClient())
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go(loadUrl());
    }

    protected boolean setToolBarScroll(){
        return true;
    }

    protected WebChromeClient creatWebChromeClient(){
        WebChromeClient webChromeClient = new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
            }
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

                setBarTitle(title);
            }
        };
        return webChromeClient;
    }

    protected WebViewClient creatWebViewClient(){
        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
            }
        };
        return webViewClient;
    }

    public void setPaddingTop(int paddingTop){
        ViewGroup contentView = ((ViewGroup) this.findViewById(android.R.id.content));
        contentView.setPadding(0,paddingTop,0,0);
    }

    protected String loadUrl(){
        return "https://m.jd.com/";
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        if (mAgentWeb!=null){
            mAgentWeb.getWebLifeCycle().onPause();
        }
        super.onPause();

    }

    @Override
    protected void onResume() {
        if (mAgentWeb!=null){
            mAgentWeb.getWebLifeCycle().onResume();
        }
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAgentWeb!=null){
            mAgentWeb.getWebLifeCycle().onDestroy();
        }
    }
}
