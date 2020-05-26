package com.common.base;


import com.common.http.ApiException;
import com.common.http.ExceptionHandler;
import com.common.util.CommonConstants;

import io.reactivex.observers.DisposableObserver;

/**
 * @创建者:李祥
 * @创建日期： 2019/7/23 10:38
 * @类说明：RXjava的基础类，统一数据，错误等的处理
 */

public abstract class BaseObserver<T> extends DisposableObserver<BaseResponse<T>> {
    private IView baseView;
    private boolean isShowLoading;


    public BaseObserver(IView baseView) {
        this(baseView, true);
    }

    public BaseObserver(IView baseView, boolean isShowLoading) {
        this.baseView = baseView;
        this.isShowLoading = isShowLoading;
    }

    public BaseObserver() {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (baseView != null) {
            if (isShowLoading) {
                baseView.showLoading();
            }
        }
    }

    @Override
    public void onNext(BaseResponse<T> baseResponse) {
        hideLoad();
        if (baseView != null && baseView.isDestroyData()) {
            return;
        }

        int errcode = baseResponse.getCode();
        String errmsg = baseResponse.getMessage();
        if (errcode == CommonConstants.HTTPSUCCEEDCODE) {
            T data = baseResponse.getData();
            try {
                onSuccess(data);
            } catch (Exception e) {
                e.printStackTrace();
//                上传处理异常
            }
        } else {
            onError(new ApiException(errcode, errmsg));
        }
    }

    //隐藏加载框
    private void hideLoad() {
        if (baseView != null && isShowLoading) {
            isShowLoading = false;
            baseView.hideLoading();
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
     * (2) 服务端返回了数据，但 errcode!=10000,即服务端返回的data为空，如 密码错误,App登录超时,token失效
     */
    @Override
    public void onError(Throwable e) {
        hideLoad();
        ExceptionHandler.handleException(e);
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        hideLoad();
    }
}
