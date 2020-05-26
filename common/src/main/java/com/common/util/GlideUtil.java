package com.common.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.common.listener.Bitmaplistener;

public class GlideUtil {


    //加载图片
    public static void load(Context activity, String url, ImageView imageView) {
        Glide.with(activity)
                .load(url)
//                .centerCrop()
//                    .placeholder(R.drawable.)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

    }

    /**
     * 加载圆角图片
     *
     * @param activity
     * @param url
     * @param imageView
     */
    public static void loadCircular(Activity activity, String url, ImageView imageView) {
        Glide.with(activity)
                .load(url)
                .centerCrop()
                .transform(new GlideCircleTransform())
                .into(imageView);


    }

    public static void asBitmap(Context context, String url, Bitmaplistener bitmaplistener) {
        Glide.with(context)
                .asBitmap()
                .load(url)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        LogUtil.logTest((Looper.getMainLooper() == Looper.myLooper()) + "<-------");
                        bitmaplistener.getBitmap(resource);
                    }
                });

    }


}
