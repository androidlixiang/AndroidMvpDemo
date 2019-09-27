package com.common.base;

import java.io.Serializable;

/**
 * @创建者:李祥
 * @创建日期： 2019/7/23 10:38
 * @类说明：网络请求返回的数据，按格式统一包装成类
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
