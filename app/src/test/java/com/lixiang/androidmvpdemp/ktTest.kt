package com.lixiang.androidmvpdemp


/**
 * Copyright (C),  国民健康科技有限公司

 * @ProjectName: 脉诊
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2019/12/23 15:00
 */
open class ktTest {


 open   fun test() {
        aaa();


    }

    private fun aaa() {
    }

    fun getLength(string: String): Int {

        if (string.isNullOrBlank()) {
            return 0
        } else {

            return string.length
        }
    }

    fun testWhen(a: Int) {

        when (a) {
            in 2..6 -> print(1)
            is Int -> print(2)
            2, 6 -> print(3)


        }

    }




}