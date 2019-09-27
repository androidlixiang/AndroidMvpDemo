package com.common.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * @创建者:李祥
 * @创建日期： 2019/7/23 10:38
 * @类说明：MVP模式的BaseMVPFragment,使用MVP基础这个activity
 */

public abstract class BaseMVPFragment<P extends IPresenter> extends BaseFragment implements IView {

    protected Context mContext;
    protected P presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }


    protected abstract P createPresenter();

    protected abstract int getLayoutResId();

    protected abstract void initView(View rootView);

    protected abstract void initData();


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public boolean isDestroyData() {
        return !isAdded();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
    }
}
