package com.common.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @创建者:李祥
 * @创建日期： 2019/7/23 10:38
 * @类说明：Header拦截器
 */
public class HeaderInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();
//                builder.addHeader("X-APP-Agent", "corp_zx_app")
//                        .addHeader("X-OS", "Android")
////                        .addHeader("X-APP-ID", "20181018000061")
//                        .addHeader("X-APP-ID", "20181130000009")
//                        .addHeader("X-DEVICE-TYPE", "USERNAME")
//                        .addHeader("appId", "281")
//                        .addHeader("businessType", "610001");
//
//                String token = "";
//                if (!TextUtils.isEmpty(token)) {
//                    builder.addHeader("Access-Token", token);
//                }
        Request request = builder.build();
        return chain.proceed(request);

    }
}
