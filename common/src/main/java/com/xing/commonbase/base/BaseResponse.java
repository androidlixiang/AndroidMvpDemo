package com.xing.commonbase.base;

import java.io.Serializable;

/**
 * 网络请求返回的数据，按格式统一包装成 BaseResponse 类
 * Created by Administrator on 2018/9/15.
 */

public class BaseResponse<T> implements Serializable {
    private int code;//后台给的状态码
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
