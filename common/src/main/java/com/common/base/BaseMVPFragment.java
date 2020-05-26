package com.common.base;

import android.content.Context;
import android.os.Bundle;

/**
 * @创建者:李祥
 * @创建日期： 2019/7/23 10:38
 * @类说明：MVP模式的BaseMVPFragment,使用MVP基础这个activity
 */

public abstract class BaseMVPFragment<P extends IPresenter> extends BaseFragment  {

    protected Context mContext;
    protected P mPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }


    protected abstract P createPresenter();

    protected abstract int getLayoutResId();


    protected abstract void initData();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
