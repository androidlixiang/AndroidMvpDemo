package com.common.base;

import android.content.Context;

/**
 * @创建者:李祥
 * @创建日期： 2019/7/23 10:38
 * @类说明：IView的接口
 */
public interface IView {

    /**
     * 显示 loading
     */
    void showLoading();

    /**
     * 隐藏 loading
     */
    void hideLoading();

    /**
     * 当前页面是否丢弃请求数据
     */
    boolean isDestroyData();

    /**
     * 拿到Context
     */
    Context getContext();

}
