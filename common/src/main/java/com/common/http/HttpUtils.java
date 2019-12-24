package com.common.http;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSONObject;
import com.common.base.BaseObserver;
import com.common.base.BaseResponse;
import com.common.bean.BaseBean;
import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: 单个网络请求
 * @Author: lixiang
 * @CreateDate: 2019/11/12 14:01
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/11/12 14:01
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class HttpUtils {

    public static final OkHttpClient client = RetrofitClient.getInstance().getOkHttpClient();
    public static final Gson mGson = new Gson();
    //类型 utf-8
    private static final MediaType mMediaType = MediaType.parse("application/json;charset=UTF-8");

    //post
    public static <T extends BaseBean> void postDefault(String httpUrl, HashMap mapUtils,
                                                        Class<T> mClass, @NonNull BaseObserver<T> listener) {
        //添加了一些固定的信息,userId和pageSize
        post(httpUrl, RequestBody.create(mMediaType, mGson.toJson(mapUtils)), mClass, listener);
    }


    private static <T extends BaseBean> void post(String httpUrl, RequestBody requestBody,
                                                  Class<T> mClass, @NonNull BaseObserver<T> listener) {
        httpAsyTask(httpUrl, new Request.Builder().post(requestBody), mClass, listener);
    }

    /**
     * 异步请求，可以在ui中更新界面
     */
    private static <T extends BaseBean> void httpAsyTask(final String httpUrl,
                                                         Request.Builder builder,
                                                         final Class<T> mClass, final BaseObserver<T> listener) {


        new AsyncTask<Request.Builder, Void, BaseResponse<T>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                if (listener != null) {
                    listener.onStart();
                }
            }

            @Override
            protected BaseResponse<T> doInBackground(Request.Builder... params) {
                params[0].tag(httpUrl).url(httpUrl);
                try {
                    Response response = client.newCall(params[0].build()).execute();
                    String body = response.body().string();
                    if (response.code() == 200) {
                        BaseResponse<T> baseResponse = JSONObject.parseObject(body, BaseResponse.class);
                        JSONObject object = JSONObject.parseObject(body);
                        T data = JSONObject.parseObject(object.getString("data"), mClass);
                        baseResponse.setData(data);
                        return baseResponse;
                    } else {
                        Exception exception = new Exception();
                        if (listener != null) {
                            listener.onError(exception);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.onError(e);
                    }
                }


                return null;
            }

            @Override
            protected void onPostExecute(BaseResponse<T> tBaseObserver) {
                super.onPostExecute(tBaseObserver);
                if (listener != null) {
                    listener.onNext(tBaseObserver);
                }
            }
        }.execute(builder);
    }


}
