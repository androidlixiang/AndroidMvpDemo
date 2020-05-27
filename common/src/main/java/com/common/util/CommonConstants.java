package com.common.util;

import android.os.Environment;

import com.common.base.BaseApplication;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @Description: 公用的常量类
 * @Author: lixiang
 * @CreateDate: 2019/11/8 16:42
 */
public class CommonConstants {

    //外部存储目录不需要权限
    public static final String EXTERNALFILESDIR = BaseApplication.getApplication().getExternalFilesDir(null).getAbsolutePath();
    //    图片存储位置
    public static final String DIRECTORY_PICTURES = BaseApplication.getApplication().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
    public static final int HTTPSUCCEEDCODE = 1000;
    public static final int HTTPRELOGINCODE = 6004;
    public static final int PAGESIZE = 10;

}
