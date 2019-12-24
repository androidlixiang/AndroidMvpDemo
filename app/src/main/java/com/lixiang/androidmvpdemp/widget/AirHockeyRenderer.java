package com.lixiang.androidmvpdemp.widget;

import android.app.Activity;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/8/1 17:11
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/8/1 17:11
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class AirHockeyRenderer implements GLSurfaceView.Renderer {

    public AirHockeyRenderer(Activity openGlDemoActivity) {
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(255, 0.0f, 0.0f, 1.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    }
}
