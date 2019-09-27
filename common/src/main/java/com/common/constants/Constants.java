package com.common.constants;

import android.os.Environment;

import com.xing.commonbase.BuildConfig;

/**
 * @创建者:李祥
 * @创建日期： 2019/7/23 10:38
 * @类说明：存放一些常量
 */

public class Constants {
    /**
     * 文件目录
     */
    public static final String FILE_CATALOG = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + BuildConfig.APPLICATION_ID;

}
