package com.lixiang.androidmvpdemp.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.widget.Toast;

import com.common.base.BaseActivity;
import com.lixiang.androidmvpdemp.R;
import com.lixiang.androidmvpdemp.widget.AirHockeyRenderer;

import butterknife.BindView;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/8/1 17:07
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/8/1 17:07
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class OpenGlDemoActivity extends BaseActivity {
    @BindView(R.id.mGLSurfaceView)
    GLSurfaceView glSurfaceView;
    boolean rendererSet;
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_opengl;
    }

    @Override
    protected void initView() {

        ActivityManager activityManager =
                (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo configurationInfo = activityManager
                .getDeviceConfigurationInfo();

        final boolean supportsEs2 =
                configurationInfo.reqGlEsVersion >= 0x20000
                        || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
                        && (Build.FINGERPRINT.startsWith("generic")
                        || Build.FINGERPRINT.startsWith("unknown")
                        || Build.MODEL.contains("google_sdk")
                        || Build.MODEL.contains("Emulator")
                        || Build.MODEL.contains("Android SDK built for x86")));


        if (supportsEs2) {
            glSurfaceView.setEGLContextClientVersion(2);
            glSurfaceView.setRenderer(new AirHockeyRenderer(this));
            rendererSet = true;
        } else {
            Toast.makeText(this, "This device does not support OpenGL ES 2.0.",
                    Toast.LENGTH_LONG).show();
            return;
        }

//
//        if (supportsEs2) {
//            // Request an OpenGL ES 2.0 compatible context.
//            glSurfaceView.setEGLContextClientVersion(2);
//
//            // Assign our renderer.
//            glSurfaceView.setRenderer(new FirstOpenGLProjectRenderer());
//            rendererSet = true;
//        } else {
//            Toast.makeText(this, "This device does not support OpenGL ES 2.0.",
//                    Toast.LENGTH_LONG).show();
//            return;
//        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (rendererSet) {
            glSurfaceView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (rendererSet) {
            glSurfaceView.onResume();
        }
    }

}
