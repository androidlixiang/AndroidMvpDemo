package com.xing.commonbase.base;

/**
 * @创建者:李祥
 * @创建日期： 2019/7/23 10:38
 * @类说明：BasePresenter的需要实现的接口
 */
public interface IPresenter<V extends IView> {

    /**
     * 绑定 View ，一般在初始化时调用
     *
     * @param view
     */
    void attachView(V view);

    /**
     * 解除绑定 View,一般在 onDestroy 中调用
     */
    void detachView();

    /**
     * 是否绑定了View,一般在发起网络请求之前调用
     *
     * @return
     */
    boolean isViewAttached();

    V getView();
}
