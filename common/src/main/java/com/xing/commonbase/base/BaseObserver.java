package com.xing.commonbase.base;

import android.util.Log;

import com.xing.commonbase.http.ApiException;
import com.xing.commonbase.http.ExceptionHandler;
import com.xing.commonbase.mvp.IView;

import io.reactivex.observers.DisposableObserver;

public abstract class BaseObserver<T> extends DisposableObserver<BaseResponse<T>> {
    private IView baseView;


    public BaseObserver(IView baseView) {
        this.baseView = baseView;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (baseView != null) {
            baseView.showLoading();
        }
    }

    @Override
    public void onNext(BaseResponse<T> baseResponse) {
        if (baseView != null) {
            if (baseView.isDestroyData()) {
                return;
            }
            baseView.hideLoading();
        }
        int errcode = baseResponse.getCode();
        String errmsg = baseResponse.getMessage();
        if (errcode == 10000) {
            T data = baseResponse.getData();
            onSuccess(data);
        } else {
            onError(new ApiException(errcode, errmsg));
        }
    }

    /**
     * 回调正常数据
     *
     * @param data
     */
    protected abstract void onSuccess(T data);

    /**
     * 异常处理，包括两方面数据：
     * (1) 服务端没有没有返回数据，HttpException，如网络异常，连接超时;
     * (2) 服务端返回了数据，但 errcode!=0,即服务端返回的data为空，如 密码错误,App登陆超时,token失效
     */
    @Override
    public void onError(Throwable e) {
        ExceptionHandler.handleException(e);
        Log.w("BaseObserver", e);
    }

    @Override
    public void onComplete() {
        if (baseView != null) {
            baseView.hideLoading();
        }
    }
}
