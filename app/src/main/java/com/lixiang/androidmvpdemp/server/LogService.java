package com.lixiang.androidmvpdemp.server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.lixiang.androidmvpdemp.ILoginAidlInterface;

/**
 * Copyright (C),  国民健康科技有限公司
 *
 * @ProjectName: 脉诊
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2019/12/18 16:53
 */
public class LogService extends Service {

    Binder binder = new ILoginAidlInterface.Stub() {
        @Override
        public void getInfo(String name, String passWord) throws RemoteException {

        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
