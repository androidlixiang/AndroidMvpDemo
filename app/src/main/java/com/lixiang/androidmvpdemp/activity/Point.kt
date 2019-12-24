package com.lixiang.androidmvpdemp.activity


/**
 * Copyright (C),  国民健康科技有限公司

 * @ProjectName: 脉诊
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2019/12/23 17:20
 */
class Point constructor(x: Int = 1, var y: Int = 3) {

    companion object{
         var  a="qqq"
        fun  getad(){


        }

    }

     var localX = x + 1

    var localY = y + 1

    constructor(c: Int, d: Int, e: Int) : this() {

        this.localX = c

    }

    init {
        println("initializer blocks , x value is: $x , y value is: $y")
        println("initializer blocks , localX value is: $localX , localY value is: $localY")
    }

    fun  getStrong():String{

        return  y.toString()
    }


}