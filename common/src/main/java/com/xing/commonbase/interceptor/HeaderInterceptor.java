package com.xing.commonbase.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

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
