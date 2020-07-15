package com.guomin.niubi.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.common.base.BaseFragment
import com.guomin.niubi.R


/**
 * Copyright (C),  国民健康科技有限公司

 * @ProjectName: 脉诊
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2019/12/24 9:43
 */
class KtMineFragment : BaseFragment() {

    companion object {
        fun getInsytance(string: String): KtMineFragment {
            var bundle = Bundle()
            bundle.putString("type", string)
            var ktHomeFragment = KtMineFragment()
            ktHomeFragment.arguments = bundle
            return ktHomeFragment
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_kt_mine
    }

    override fun initData() {
//        mPieView.start()

    }

    override fun initView(rootView: View?) {
        val textView = rootView?.findViewById<TextView>(R.id.textView)
        textView?.text = arguments?.getString("type")
        textView?.setOnClickListener {
        }

    }


}