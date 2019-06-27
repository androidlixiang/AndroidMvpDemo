package com.xing.commonbase.interceptor;

import com.xing.commonbase.util.LogUtil;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;

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
        LogUtil.logi(TAG, "\n");
        LogUtil.logi(TAG, "----------Start----------------");
        LogUtil.logi(TAG, "| " + request.toString());
        String method = request.method();
        if ("POST".equals(method)) {
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                }
                sb.delete(sb.length() - 1, sb.length());
                LogUtil.logi(TAG, "| RequestParams:{" + sb.toString() + "}");
            }
        }
        LogUtil.logi(TAG, "| Response:" + content);
        LogUtil.logi(TAG, "----------End:" + duration + "毫秒----------");
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }
}
