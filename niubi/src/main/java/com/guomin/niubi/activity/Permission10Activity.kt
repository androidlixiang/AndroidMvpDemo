package com.guomin.niubi.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.common.base.BaseActivity
import com.common.util.FileService
import com.guomin.niubi.R
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_permiss.*
import java.util.*


/**
 * Copyright (C),  国民健康科技有限公司

 * @ProjectName: AndroidMvpDemo
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2020/6/23 10:38
 */
@Route(path = "/niubi/Permission10Activity")
class Permission10Activity : BaseActivity() {
    override fun initView() {
//        定位
        bt_dingwei.setOnClickListener {
            val rxPermissions = RxPermissions(this)
            rxPermissions.request("android.permission.ACCESS_COARSE_LOCATION").subscribe(object : Observer<Boolean> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Boolean) {

                }

                override fun onError(e: Throwable) {
                }


            })
        }
//        内部存储
        bt_1.setOnClickListener {
            FileService.writeFile("内部存储", "啦啦啦啦啦啦1", 0)
        }
//        外部存储
        bt_2.setOnClickListener {
            FileService.writeFile("外部存储", "啦啦啦啦啦啦1", 1)


        }
    }

    override fun getLayoutResId() = R.layout.activity_permiss


}