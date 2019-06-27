package com.xing.commonbase.interceptor;

import com.xing.commonbase.util.LogUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okio.Buffer;

/**
 * Created by lixiang on 2019/3/13
 */
public class LogInterceptor implements Interceptor {

    public static String TAG = "LogInterceptor";


    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        okhttp3.Response response = chain.proceed(chain.request());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        LogUtil.logi(TAG, "----------Start----------------");
        LogUtil.logi(TAG, "| " + request.toString());
        String method = request.method();
        if ("POST".equals(method)) {
            LogUtil.logi(TAG, "| RequestBody:" + bodyToString(request));
        }
        LogUtil.logi(TAG, "| Response:" + content);
        LogUtil.logi(TAG, "----------End:" + duration + "毫秒----------");
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }

    private String bodyToString(final Request request) {

        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            if (copy.body() == null) {
                return "get------------";
            }
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
