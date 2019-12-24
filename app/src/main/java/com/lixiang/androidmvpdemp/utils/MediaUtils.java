package com.lixiang.androidmvpdemp.utils;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;

import java.util.HashMap;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 脉诊
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/12/6 17:00
 */
public class MediaUtils {


    /**
     * 获取视频的第一帧图片
     */
    public static void getImageForVideo(String videoPath, OnLoadVideoImageListener listener) {
        LoadVideoImageTask task = new LoadVideoImageTask(listener);
        task.execute(videoPath);
    }

    public static class LoadVideoImageTask extends AsyncTask<String, Integer, Bitmap> {
        private OnLoadVideoImageListener listener;

        public LoadVideoImageTask(OnLoadVideoImageListener listener) {
            this.listener = listener;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            String path = params[0];
            //获取网络视频第一帧图片
            mmr.setDataSource(path, new HashMap());
            Bitmap bitmap = mmr.getFrameAtTime();
            mmr.release();
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap file) {
            super.onPostExecute(file);
            if (listener != null) {
                listener.onLoadImage(file);
            }
        }
    }

    public interface OnLoadVideoImageListener {
        void onLoadImage(Bitmap file);
    }
}
