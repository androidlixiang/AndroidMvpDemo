package com.common.base;

/**
 * @创建者:李祥
 * @创建日期： 2019/7/23 10:38
 * @类说明：MVP模式的BaseMVPActivity,使用MVP基础这个activity
 */

public abstract class BaseMVPActivity<P extends IPresenter> extends BaseActivity {
    protected P mPresenter;

    @Override
    protected void initData() {
        super.initData();
        mPresenter = createPresenter();
        // presenter 绑定 view
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }


    protected abstract int getLayoutResId();

    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Activity 销毁时取消所有的订阅
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
