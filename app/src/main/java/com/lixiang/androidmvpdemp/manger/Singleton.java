package com.lixiang.androidmvpdemp.manger;

/**
 * Copyright (C),  国民健康科技有限公司
 *
 * @ProjectName: 脉诊
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2019/12/20 13:57
 */
public class Singleton {

    public static class LazySingleton {
        private static final Singleton sin = new Singleton();
    }

    private Singleton() {
    }

    public static final Singleton getInstance() {
        return LazySingleton.sin;
    }
}
