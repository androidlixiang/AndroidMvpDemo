package com.xing.commonbase.constants;

import android.os.Environment;

import com.xing.commonbase.BuildConfig;


public class Constants {
    /**
     * 文件目录
     */
    public static final String FILE_CATALOG = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + BuildConfig.APPLICATION_ID;

}
