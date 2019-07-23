package com.xing.commonbase.http;


/**
 * @创建者:李祥
 * @创建日期： 2019/7/23 10:38
 * @类说明：自定义网络异常
 */

public class ApiException extends RuntimeException {

    private int errcode;
    private String errmsg;

    public ApiException(int code, String msg) {
        this.errcode = code;
        this.errmsg = msg;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}