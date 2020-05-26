package com.common.interceptor;

import android.text.TextUtils;

import com.common.util.LogUtil;
import com.common.util.UserManagerTool;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @创建者:李祥
 * @创建日期： 2019/7/23 10:38
 * @类说明：Header拦截器
 */
public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();
        if (UserManagerTool.isLogin()) {
            builder.addHeader("token", UserManagerTool.getToken());
            LogUtil.logTest("token---->"+UserManagerTool.getToken());
        }
        Request request = builder.build();
        Request.Builder requestBuilder = request.newBuilder();
        Response response = chain.proceed(requestBuilder.build());
        //这是解码需要的key
        String token = response.header("token");
        if (!TextUtils.isEmpty(token)) {
          UserManagerTool.setToken(token);
        }
        return response;

    }
}
