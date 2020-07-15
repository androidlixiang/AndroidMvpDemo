package com.guomin.niubi.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.common.base.BaseActivity
import com.guomin.niubi.R
import kotlinx.android.synthetic.main.activity_niubi_home.*

/**
 * Copyright (C),  国民健康科技有限公司
 *
 * @ProjectName: AndroidMvpDemo
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2020/6/11 14:25
 */
@Route(path = "/niubi/homeActivity")
class HomeActivity : BaseActivity() {

    override fun getLayoutResId() = R.layout.activity_niubi_home

    override fun initView() {
        tv_home.setOnClickListener {
            ARouter.getInstance().build("/activity/RiliActivity").navigation();
        }
    }

}