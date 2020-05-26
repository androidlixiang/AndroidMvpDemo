package com.common.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.common.widget.LoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @创建者:李祥
 * @创建日期： 2019/7/23 10:38
 * @类说明：Fragment的基础类,所有的都需要基础这个类，MVP模式和懒加载的需要基础BaseLazyFragment
 * @see BaseMVPActivity
 */


public abstract class BaseFragment extends Fragment implements IView {
    protected Context mContext;
    private Unbinder unbinder;
    private LoadingDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 基类中注册 eventbus
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container,
                                   Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutResId(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView(rootView);
        initData();
        return rootView;
    }

    protected abstract int getLayoutResId();

    protected void initView(View rootView) {


    }

    ;

    protected abstract void initData();

    //EventBus默认的实现
    @Subscribe
    public void onEventMainThread(String str) {

    }


    @Override
    public void showLoading() {
        dialog = new LoadingDialog(mContext, "正在加载中");
        dialog.show();
    }

    @Override
    public void hideLoading() {
        if (dialog != null) {
            dialog.close();
        }
    }


    @Override
    public boolean isDestroyData() {
        return !isAdded();
    }

    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 取消注册
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        unbinder.unbind();
    }
}
