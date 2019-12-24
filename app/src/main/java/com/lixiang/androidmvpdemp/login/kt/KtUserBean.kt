package com.lixiang.androidmvpdemp.login.kt

import com.common.bean.BaseBean


/**
 * Copyright (C),  国民健康科技有限公司

 * @ProjectName: 脉诊
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2019/12/24 11:39
 */
class KtUserBean : BaseBean() {

    val user_id: String? = null
    val wx_nickname: String? = null
    val wx_headimg: String? = null
    val userToken: String? = null
    val phone: String? = null
    val grade: String? = null
    val grade_name: String? = null
    override fun toString(): String {
        return "KtUserBean(user_id=$user_id, wx_nickname=$wx_nickname, wx_headimg=$wx_headimg, userToken=$userToken, phone=$phone, grade=$grade, grade_name=$grade_name)"
    }


}