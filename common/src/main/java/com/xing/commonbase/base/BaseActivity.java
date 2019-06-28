package com.xing.commonbase.base;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xing.commonbase.receiver.NetworkChangeReceiver;
import com.xing.commonbase.util.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity implements IView {

    protected Context mContext;
    private NetworkChangeReceiver receiver;
    private Unbinder unbinder;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        fullScreen(this,true);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ARouter.getInstance().inject(this);
        mContext = this;
//        setStatusBarColor();
        // 基类中注册 eventbus
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        //注册ButterKnife
        unbinder = ButterKnife.bind(this);
        registerNetworkChangeReceiver();
        initView();
        initData();
    }


    protected abstract int getLayoutResId();

    protected abstract void initView();

    protected void initData() {

    }


    @Override
    public void showLoading() {
        if (!isShowLoading()) return;
        LogUtil.logTest("showLoading");

    }

    @Override
    public void hideLoading() {
        if (!isShowLoading()) return;
        LogUtil.logTest("hideLoading");
    }

    @Override
    public boolean isDestroyData() {
        return isFinishing();
    }

    /**
     * 注册网络监听广播
     */
    private void registerNetworkChangeReceiver() {
        receiver = new NetworkChangeReceiver(this);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver.onDestroy();
            receiver = null;
        }
        // 取消注册
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }


    //EventBus默认的实现
    @Subscribe
    public void onEventMainThread(String str) {

    }

    /**
     * @return 是否显示加载的loading
     */
    public boolean isShowLoading() {
        return true;
    }

    /**
     * 通过设置全屏，设置状态栏透明 字体为灰色
     *
     * @param activity
     * @param isGray
     */
    public void fullScreen(Activity activity, boolean isGray) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                int option;
                if (isGray) {
                    option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                } else {
                    option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                }

                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间

                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                attributes.flags |= flagTranslucentStatus;
                window.setAttributes(attributes);
            }
        }
    }

    /**
     * 通过设置全屏，设置状态栏透明
     *
     * @param activity
     */
    public void fullScreen(Activity activity) {
        fullScreen(activity, false);
    }


}
