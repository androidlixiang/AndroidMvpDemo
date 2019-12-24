package com.lixiang.androidmvpdemp.activity

import android.webkit.WebView
import com.common.base.BaseActivity
import com.lixiang.androidmvpdemp.R

class TestActivity : BaseActivity() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_test
    }

    override fun initView() {

        var webView: WebView = findViewById(R.id.webview)
        webView.loadUrl("https://www.baidu.com/")
    }
}