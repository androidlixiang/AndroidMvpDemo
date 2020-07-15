package com.lixiang.androidmvpdemp.utils;

import androidx.fragment.app.Fragment;

import com.common.util.LogUtil;

import java.lang.reflect.Method;

/**
 * Copyright (C),  国民健康科技有限公司
 *
 * @ProjectName: AndroidMvpDemo
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2020/6/12 15:32
 */
public class ClassLoaderLi {

    public static Fragment getFragment(String s) {

        try {
//            Class<?> aClass = Class.forName("com.guomin.niubi.fragment.KtMineFragment");
            Class<?> aClass = Class.forName("com.guomin.niubi.fragment.MineFragment");

            for (Method method : aClass.getDeclaredMethods()) {
                LogUtil.logTest(method.getName().toString());
            }
            Method getInsytance = aClass.getMethod("getInsytance",String.class);
            Object invoke = getInsytance.invoke(null, s);
            return (Fragment) invoke;
//            return (Fragment) aClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;

    }

}
