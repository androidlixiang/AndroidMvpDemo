package com.lixiang.androidmvpdemp.utils;

import java.lang.ref.WeakReference;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/11/13 17:55
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/11/13 17:55
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class MyRunable implements Runnable {

    public interface MyInterface {
        void doSomething();
    }
    private WeakReference<MyInterface> mInterface;

    public MyRunable(MyInterface mInterface) {
        this.mInterface = new WeakReference<>(mInterface);
    }

    @Override
    public void run() {
        mInterface.get().doSomething();
    }
}