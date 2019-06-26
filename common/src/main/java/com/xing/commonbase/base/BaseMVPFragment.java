package com.xing.commonbase.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.xing.commonbase.mvp.IPresenter;
import com.xing.commonbase.mvp.IView;

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
