package com.common.base;

import java.io.Serializable;

/**
 * @创建者:李祥
 * @创建日期： 2019/7/23 10:38
 * @类说明：网络请求返回的数据，按格式统一包装成类
 */
public class BaseResponse<T> implements Serializable {


    private String message;
    private int code;
    private T data;
    private Object extendOne;
    private Object extendThree;
    private Object extendTwo;
    private boolean success;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getExtendOne() {
        return extendOne;
    }

    public void setExtendOne(Object extendOne) {
        this.extendOne = extendOne;
    }

    public Object getExtendThree() {
        return extendThree;
    }

    public void setExtendThree(Object extendThree) {
        this.extendThree = extendThree;
    }

    public Object getExtendTwo() {
        return extendTwo;
    }

    public void setExtendTwo(Object extendTwo) {
        this.extendTwo = extendTwo;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
