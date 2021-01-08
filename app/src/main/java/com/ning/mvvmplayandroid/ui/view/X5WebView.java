package com.ning.mvvmplayandroid.ui.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import fule.com.playandroidkotlin.base.App;

/**
 * @作者: njb
 * @时间: 2019/11/8 10:32
 * @描述: WebView基类
 */
public class X5WebView extends WebView {
	private static final String TAG = "XWebView";
	private WebSettings webSettings;

	private WebViewClient client = new WebViewClient() {
		/**
		 * 防止加载网页时调起系统浏览器
		 */
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;

		}

		@Override
		public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
			super.onPageStarted(webView, s, bitmap);
			webSettings.setBlockNetworkImage(true);
			Log.d(TAG, " onPageStart " + s + "------------");
		}

		@Override
		public void onPageFinished(WebView webView, String s) {
			super.onPageFinished(webView, s);
			X5WebView.this.loadUrl("javascript:MyApp.resize(document.body.getBoundingClientRect().height)");
			Log.d(TAG," onPageFinished " + s + "----------");
			webSettings.setBlockNetworkImage(false);
			if (!webSettings.getLoadsImagesAutomatically()) {
				//设置wenView加载图片资源
				webSettings.setBlockNetworkImage(false);
				webSettings.setLoadsImagesAutomatically(true);
			}
		}
	};

	public X5WebView(Context arg0, AttributeSet arg1) {
		super(arg0, arg1);
		this.setWebViewClient(client);
		initWebViewSettings();
		this.getView().setClickable(true);
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void initWebViewSettings() {
		webSettings = this.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
		webSettings.setAllowFileAccess(true);
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
		webSettings.setSupportZoom(true);
		webSettings.setBuiltInZoomControls(true);
		webSettings.setUseWideViewPort(true);
		webSettings.setSupportMultipleWindows(true);
		webSettings.setAppCacheEnabled(true);
		//开启数据库形式存储
		webSettings.setDatabaseEnabled(true);
		//开启Dom形式存储
		webSettings.setDomStorageEnabled(true);
		webSettings.setGeolocationEnabled(true);
		webSettings.setAppCacheMaxSize(Long.MAX_VALUE);
		webSettings.setPluginState(WebSettings.PluginState.ON_DEMAND);
		webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		//缓存数据的存储地址
		String appCacheDir = getContext().getDir("cache", Context.MODE_PRIVATE).getPath();
		webSettings.setAppCachePath(appCacheDir);
		//开启缓存功能
		webSettings.setAppCacheEnabled(true);
		//缓存模式
		webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		webSettings.setAllowFileAccess(true);

		Mobile mobile = new Mobile();
		addJavascriptInterface(mobile, "mobile");

		this.addJavascriptInterface(this, "MyApp");
        this.getView().setOverScrollMode(View.OVER_SCROLL_ALWAYS);
    }



	public X5WebView(Context arg0) {
		super(arg0);
	}

	private class Mobile {
		@JavascriptInterface
		public void onGetWebContentHeight() {
			//重新调整webview高度
			X5WebView.this.post(() -> {
				X5WebView.this.measure(0, 0);
				int measuredHeight = X5WebView.this.getMeasuredHeight();
				ViewGroup.LayoutParams layoutParams = X5WebView.this.getLayoutParams();
				layoutParams.height = measuredHeight;
				X5WebView.this.setLayoutParams(layoutParams);
			});

		}
	}

	@JavascriptInterface
	public void resize(final float height) {
		((Activity) getContext()).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				X5WebView.this.setLayoutParams(new LinearLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels, (int) (height * getResources().getDisplayMetrics().density)));
			}
		});
	}

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        try {
            super.onConfigurationChanged(newConfig);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 向webView发出信息
    private void enableX5FullscreenFunc() {

        if (this.getX5WebViewExtension() != null) {
			Toast.makeText(App.Companion.getContext(), "开启X5全屏播放模式", Toast.LENGTH_LONG).show();
            Bundle data = new Bundle();

            data.putBoolean("standardFullScreen", false);// true表示标准全屏，false表示X5全屏；不设置默认false，

            data.putBoolean("supportLiteWnd", false);// false：关闭小窗；true：开启小窗；不设置默认true，

            data.putInt("DefaultVideoScreen", 2);// 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1

            this.getX5WebViewExtension().invokeMiscMethod("setVideoParams",
                    data);
        }
    }

    private void disableX5FullscreenFunc() {
        if (this.getX5WebViewExtension() != null) {
            Toast.makeText(App.Companion.getContext(), "恢复webkit初始状态", Toast.LENGTH_LONG).show();
            Bundle data = new Bundle();

            data.putBoolean("standardFullScreen", true);// true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，

            data.putBoolean("supportLiteWnd", false);// false：关闭小窗；true：开启小窗；不设置默认true，

            data.putInt("DefaultVideoScreen", 2);// 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1

            this.getX5WebViewExtension().invokeMiscMethod("setVideoParams",
                    data);
        }
    }

    private void enableLiteWndFunc() {
        if (this.getX5WebViewExtension() != null) {
            Toast.makeText(App.Companion.getContext(), "开启小窗模式", Toast.LENGTH_LONG).show();
            Bundle data = new Bundle();

            data.putBoolean("standardFullScreen", false);// true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，

            data.putBoolean("supportLiteWnd", true);// false：关闭小窗；true：开启小窗；不设置默认true，

            data.putInt("DefaultVideoScreen", 2);// 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1

            this.getX5WebViewExtension().invokeMiscMethod("setVideoParams",
                    data);
        }
    }

    private void enablePageVideoFunc() {
        if (this.getX5WebViewExtension() != null) {
            Toast.makeText(App.Companion.getContext(), "页面内全屏播放模式", Toast.LENGTH_LONG).show();
            Bundle data = new Bundle();

            data.putBoolean("standardFullScreen", false);// true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，

            data.putBoolean("supportLiteWnd", false);// false：关闭小窗；true：开启小窗；不设置默认true，

            data.putInt("DefaultVideoScreen", 1);// 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1

            this.getX5WebViewExtension().invokeMiscMethod("setVideoParams",
                    data);
        }
    }


}
