package com.lixiang.androidmvpdemp.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * Copyright (C),  国民健康科技有限公司
 *
 * @ProjectName: AndroidMvpDemo
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2020/6/3 17:30
 */
public class MyProvider extends ContentProvider {

    private String mAuthority = "com.lixiang.androidmvpdemp.myprovider";
    private UriMatcher mUriMatcher;

    @Override
    public boolean onCreate() {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(mAuthority, "string", 1);
        mUriMatcher.addURI(mAuthority, "boolean", 2);
        mUriMatcher.addURI(mAuthority, "int", 3);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        if (selectionArgs == null) return null;

        String key = selectionArgs[0];
        String defValue = selectionArgs[1];
        MatrixCursor cursor = new MatrixCursor(new String[]{"name"});
        Bundle bundle = new Bundle();
        switch (key) {
            case "1":
                bundle.putString("v", "张三");
                break;
            case "2":
                bundle.putString("v", "李四");

                break;

        }
        cursor.setExtras(bundle);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
