package com.lixiang.androidmvpdemp.login.kt

import com.common.base.IView


/**
 * Copyright (C),  国民健康科技有限公司

 * @ProjectName: 脉诊
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2019/12/24 10:57
 */
interface KtLoginCon {

    interface View : IView {
        fun loginSuccess(userBean: KtUserBeanData)
    }

    interface Presenter {
        fun login(phone: String, paw: String)
    }
}