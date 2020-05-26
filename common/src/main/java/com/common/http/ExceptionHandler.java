package com.common.http;

import android.content.Intent;
import android.net.ParseException;
import android.util.Log;

import com.common.base.BaseApplication;
import com.common.util.CommonConstants;
import com.common.util.ToastUtil;
import com.common.util.UserManagerTool;
import com.google.gson.JsonParseException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;

import retrofit2.HttpException;

/**
 * @创建者:李祥
 * @创建日期： 2019/7/23 10:38
 * @类说明：网络请求数据的异常处理
 */

public class ExceptionHandler {
    private static final String TAG = "ExceptionHandler";
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static void handleException(Throwable e) {
        String errmsg;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    errmsg = "网络错误";
                    break;
            }
            errmsg = errmsg + ":" + httpException.code();
        } else if (e instanceof ApiException) {
            ApiException exception = (ApiException) e;
            errmsg = exception.getErrmsg();
            int errcode = exception.getErrcode();
            // 根据业务逻辑处理异常信息，如：token失效，跳转至登录界面
            handleServerException(errcode);
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            errmsg = "解析错误";
        } else if (e instanceof ConnectException) {
            errmsg = "网络连接失败,请稍后重试";
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            e.printStackTrace();
            errmsg = "证书验证失败";
            Log.d(TAG, "handleException: " + e.getMessage());
        } else if (e instanceof ConnectTimeoutException) {
            errmsg = "网络连接超时";
        } else if (e instanceof java.net.SocketTimeoutException) {
            errmsg = "连接超时";
        } else {
            errmsg = "网络连接异常,请稍后重试";
        }
        ToastUtil.toast(errmsg);
    }

    /**
     * 根据业务逻辑处理异常信息
     */
    private static void handleServerException(int errcode) {
        switch (errcode) {
            case CommonConstants.HTTPRELOGINCODE:
                gotoLoginActivity();
                break;
            default:
                break;
        }
    }

    /**
     * 跳转到登录界面
     */
    private static void gotoLoginActivity() {

        Intent intent = new Intent();
        intent.setClassName(BaseApplication.getApplication(), "com.guomin.guomindoctor.mvp.ui.activity.CodeLoginActivity");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.getApplication().startActivity(intent);
        UserManagerTool.logOut();
//        Intent intent=new Intent(BaseApplication.getApplication(),Login/);

    }
}
