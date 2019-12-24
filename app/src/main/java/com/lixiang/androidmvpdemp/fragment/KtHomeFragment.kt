package com.lixiang.androidmvpdemp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.common.base.BaseFragment
import com.lixiang.androidmvpdemp.R
import com.lixiang.androidmvpdemp.activity.KtLoginActivity


/**
 * Copyright (C),  国民健康科技有限公司

 * @ProjectName: 脉诊
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2019/12/24 9:43
 */
class KtHomeFragment : BaseFragment() {

    companion object {
        fun getInsytance(string: String): KtHomeFragment {
            var bundle = Bundle()
            bundle.putString("type", string)
            var ktHomeFragment = KtHomeFragment()
            ktHomeFragment.arguments = bundle
            return ktHomeFragment
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_kt_home
    }

    override fun initData() {


    }

    override fun initView(rootView: View?) {
        val textView = rootView?.findViewById<TextView>(R.id.textView)
        textView?.text = arguments?.getString("type")
        textView?.setOnClickListener {
            startActivity(Intent(mContext,KtLoginActivity::class.java))
        }

    }


}