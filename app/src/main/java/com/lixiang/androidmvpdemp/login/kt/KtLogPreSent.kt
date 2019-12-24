package com.lixiang.androidmvpdemp.login.kt

import com.common.base.BaseObserver
import com.common.base.BasePresenter
import com.lixiang.androidmvpdemp.ApiService
import java.util.*


/**
 * Copyright (C),  国民健康科技有限公司

 * @ProjectName: 脉诊
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2019/12/24 11:01
 */
class KtLogPreSent : BasePresenter<KtLoginCon.View>(), KtLoginCon.Presenter {
    override fun login(phone: String, paw: String) {
        var map = HashMap<String, String>();
        map["phone"] = phone
        map["password"] = paw
        addSubscribe(create(ApiService::class.java).login2(map), object : BaseObserver<KtUserBeanData>(view) {
            override fun onSuccess(data: KtUserBeanData?) {
                if (isViewAttached) {
                    data?.let {
                        view.loginSuccess(data)
                    }
                }

            }
        })

    }


}