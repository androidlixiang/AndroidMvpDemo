package com.common.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.common.base.BaseApplication;

import java.util.Set;


/**
 *
 */
public class SPUtil {
    public static final SharedPreferences mSP = BaseApplication.getApplication().getSharedPreferences("data", Context.MODE_PRIVATE);
    public static final SharedPreferences.Editor mEdit=mSP.edit();

    //put东西
    public static void put(String key, boolean value) {
        mEdit.putBoolean(key, value).apply();
    }

    public static void put(String key, int value) {
        mEdit.putInt(key, value).apply();
    }

    public static void put(String key, long value) {
        mEdit.putLong(key, value).apply();
    }

    public static void put(String key, float value) {
        mEdit.putFloat(key, value).apply();
    }

    public static void put(String key, String value) {
        mEdit.putString(key, value).apply();
    }

    public static void put(String key, Set<String> value) {
        mEdit.putStringSet(key, value).apply();
    }


    //get东西
    public static boolean getBoolean(String key) {
        return mSP.getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return mSP.getBoolean(key, defaultValue);
    }

    public static int getInt(String key) {
        return mSP.getInt(key, 0);
    }

    public static int getInt(String key, int defaultValue) {
        return mSP.getInt(key, defaultValue);
    }

    public static long getLong(String key) {
        return mSP.getLong(key, 0L);
    }

    public static long getLong(String key, long defaultValue) {
        return mSP.getLong(key, defaultValue);
    }

    public static float getFloat(String key) {
        return mSP.getFloat(key, 0f);
    }

    public static float getFloat(String key, float defaultValue) {
        return mSP.getFloat(key, defaultValue);
    }

    public static String getString(String key) {
        return mSP.getString(key, null);
    }

    public static String getString(String key, String defaultValue) {
        return mSP.getString(key, defaultValue);
    }

    public static Set<String> getStringSet(String key) {
        return mSP.getStringSet(key, null);
    }

    public static Set<String> getStringSet(String key, Set<String> defaultValue) {
        return mSP.getStringSet(key, defaultValue);
    }
}
