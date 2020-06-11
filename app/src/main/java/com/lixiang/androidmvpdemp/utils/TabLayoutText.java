package com.lixiang.androidmvpdemp.utils;


import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;

/**
 * Copyright (C),  国民健康科技有限公司
 *
 * @ProjectName: guomingVoiceSkill
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2020/6/5 9:37
 */
public class TabLayoutText {

    public static void setSelectedType(boolean isSelect, TabLayout.Tab tab) {
        try {
            Class<?> aClass = Class.forName("android.support.design.widget.TabLayout$TabView");
            Field textView = aClass.getDeclaredField("textView");
            textView.setAccessible(true);

           LinearLayout  view = tab.view;


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
