package com.ning.mvvmplayandroid.ui.activity

import android.view.KeyEvent
import android.view.View
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.ui.view.CompletedListener
import fule.com.playandroidkotlin.base.BaseActivity
import fule.com.playandroidkotlin.ui.util.AppConstant
import kotlinx.android.synthetic.main.activity_webview.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 *@作者: njb
 *@时间: 2020/1/14 17:28
 *@描述: 网页界面
 */
class WebViewActivity : BaseActivity(), CompletedListener {
    private var shareId: Int = 0
    private var url: String? = null
    private var shareTitle: String? = null

    private val TAG = "X5WebView"



    override val layoutId: Int
        get() = R.layout.activity_webview

    override fun initView() {
        intent.extras.let {
            url = it!!.getString(AppConstant.WEBVIEW_URL_KEY)
            shareId = it.getInt(AppConstant.WEBVIEW_ID_KEY)
            shareTitle = it.getString(AppConstant.WEBVIEW_TITLE_KEY)
        }
        initToolBar()
        webView.loadUrl(url)
    }

    private fun initToolBar() {
        toolbar.run {
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            iv_search.visibility = View.GONE
        }
    }

    override fun addListener() {
    }

    override fun onCompleted() {
        closeLoadingDialog()
    }

    // mWebView点击返回键返回上一个页面
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}