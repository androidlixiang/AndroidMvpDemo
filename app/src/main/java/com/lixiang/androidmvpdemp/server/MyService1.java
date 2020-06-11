package com.lixiang.androidmvpdemp.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.common.util.LogUtil;
import com.guomin.aidltest.ILiXiangAidlInterface;

/**
 * Copyright (C),  国民健康科技有限公司
 *
 * @ProjectName: AidlTest
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2020/6/3 15:49
 */
public class MyService1 extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ILiXiangAidlInterface.Stub() {
            @Override
            public void getInfo(String name, String passWord) throws RemoteException {

                LogUtil.logTest(name + "--------" + passWord);

            }

            @Override
            public String getStudent() throws RemoteException {
                return "李祥";
            }
        };
    }

}
