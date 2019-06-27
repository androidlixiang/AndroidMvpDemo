package com.xing.commonbase.base;

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
