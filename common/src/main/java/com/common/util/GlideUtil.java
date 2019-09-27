package com.common.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class GlideUtil {
//
//    //圆形图片
//    public static void LoadCircleImage(Context context, String url, ImageView imageView) {
//        Glide.with(context).load(url)
//                .centerCrop()
//                .placeholder(R.drawable.mine)
//                .error(R.drawable.mine)
//                .transform(new CircleTransform(context))
//                .into(imageView);
//    }

//    //圆形图片设置占位图
//    public static void LoadCircleImage(Context context, String url, ImageView imageView, int placeholderResourceId, int errorResourceId) {
//        Glide.with(context).load(url)
//                .centerCrop()
//                .placeholder(placeholderResourceId)
//                .error(errorResourceId)
//                .transform(new CircleTransform(context))
//                .into(imageView);
//    }

//    //一般图片
//    public static void LoadImage(Context context, String url, ImageView imageView) {
//        Glide.with(context).load(url)
//                .placeholder(R.drawable.tab_pic)
//                .error(R.drawable.tab_pic)
//                .into(imageView);
//    }

    //一般图片设置占位图
    public static void LoadImage(Context context, String url, ImageView imageView, int placeholderResourceId, int errorResourceId) {
        Glide.with(context).load(url)
                .placeholder(placeholderResourceId)
                .error(errorResourceId)
                .into(imageView);
    }

    //圆角图片
    public static void LoadCornersImage(Context context, String url, ImageView imageView, int num) {
        RoundedCorners roundedCorners = new RoundedCorners(num);
        Glide.with(context).load(url)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(roundedCorners))
                .into(imageView);
    }



}
