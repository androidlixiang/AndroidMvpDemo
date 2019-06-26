package com.xing.commonbase.mvp;

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

}
