package com.lixiang.androidmvpdemp.activity

import android.view.View
import android.widget.Button
import android.widget.EditText
import com.common.base.BaseMVPActivity
import com.common.util.LogUtil
import com.common.util.ToastUtil
import com.lixiang.androidmvpdemp.R
import com.lixiang.androidmvpdemp.login.kt.KtLogPreSent
import com.lixiang.androidmvpdemp.login.kt.KtLoginCon
import com.lixiang.androidmvpdemp.login.kt.KtUserBeanData


/**
 * Copyright (C),  国民健康科技有限公司

 * @ProjectName: 脉诊
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2019/12/24 10:53
 */
class KtLoginActivity : BaseMVPActivity<KtLogPreSent>(), KtLoginCon.View {

    private var phone: EditText? = null
    private var paw: EditText? = null
    private var login: Button? = null

    override fun initView() {
        phone = findViewById(R.id.editText1)
        paw = findViewById(R.id.editText2)
        login = findViewById(R.id.button)
        login?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var phoneText = phone?.text.toString()
                var pawText = paw?.text.toString()

                if (phoneText.isBlank()) {
                    ToastUtil.toast("手机号不可以为空")
                    return
                }


                if (pawText.isBlank()) {
                    ToastUtil.toast("密码不可以为空")
                    return
                }
                mPresenter.login(phoneText, pawText)
            }
        })

    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_kt_login
    }

    override fun createPresenter(): KtLogPreSent {
        return KtLogPreSent()
    }

    override fun loginSuccess(userBean: KtUserBeanData) {


        LogUtil.logTest(userBean.toString())

    }


}