package com.lixiang.androidmvpdemp.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.common.base.BaseActivity
import com.common.util.LogUtil
import com.common.util.ToastUtil
import com.lixiang.androidmvpdemp.R
import com.lixiang.androidmvpdemp.utils.CalendarRemindUtlis
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_rili.*
import java.util.*


/**
 * Copyright (C),  国民健康科技有限公司

 * @ProjectName: AndroidMvpDemo
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2020/6/9 9:47
 */
@Route(path = "/activity/RiliActivity")
class RiliActivity : BaseActivity() {

    override fun getLayoutResId() = R.layout.activity_rili

    override fun initView() {

        bt_one.setOnClickListener {
            //            insertCalendar()
            var c = CalendarRemindUtlis.checkAndAddCalendarAccount(mContext)
            if (c != -1) {

                val aa = CalendarRemindUtlis.addCalendarEvent(mContext, "lixiangderili", (Calendar.getInstance().timeInMillis + 1000 * 30), "1,3,7")
                if (aa) {
                    ToastUtil.toast("插入成功")
                } else {
                    ToastUtil.toast("插入失败")
                }

            }

        }
        bt_event.setOnClickListener {
            CalendarRemindUtlis.deleteCalendarEvent(mContext, "1591682333360")

        }
        val observable = Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) {
                emitter.onNext("1")
                emitter.onNext("1")
                emitter.onNext("1")
                emitter.onComplete()

            }
        })

        var observer = object : Observer<String> {
            override fun onComplete() {
                LogUtil.logTest("onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                LogUtil.logTest("onSubscribe")
            }

            override fun onNext(t: String) {
                LogUtil.logTest("onNext")
            }

            override fun onError(e: Throwable) {
                LogUtil.logTest("onError")
            }
        }
        observable.subscribe(observer)
    }


}