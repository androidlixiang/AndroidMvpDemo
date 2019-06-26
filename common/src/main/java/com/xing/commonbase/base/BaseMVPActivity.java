package com.xing.commonbase.base;

import com.xing.commonbase.mvp.IPresenter;
import com.xing.commonbase.mvp.IView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMVPActivity<P extends IPresenter> extends BaseActivity implements IView {
    protected P mPresenter;
    private Unbinder unbinder;

    @Override
    protected void initData() {
        super.initData();
        mPresenter = createPresenter();
        // presenter 绑定 view
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        unbinder = ButterKnife.bind(this);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public boolean isDestroyData() {
        return isFinishing();
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
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
