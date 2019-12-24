package com.common.json;

import com.common.http.HttpUtils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * @创建者:李祥
 * @创建日期： 2019/7/23 10:38
 * @类说明：请求的数据处理
 */
class GsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json;charset=UTF-8");

    @Override
    public RequestBody convert(T value) throws IOException {
        return RequestBody.create(MEDIA_TYPE, HttpUtils.mGson.toJson(value));
    }
}
