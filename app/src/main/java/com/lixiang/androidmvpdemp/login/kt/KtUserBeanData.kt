package com.lixiang.androidmvpdemp.login.kt

import com.common.bean.BaseBean


/**
 * Copyright (C),  国民健康科技有限公司

 * @ProjectName: 脉诊
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2019/12/24 11:28
 */
data class KtUserBeanData constructor(var user_id: String, var wx_nickname: String, var wx_headimg: String, var userToken: String, var phone: String) : BaseBean() {


}