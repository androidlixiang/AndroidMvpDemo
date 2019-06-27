package com.xing.commonbase.base;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.xing.commonbase.mvp.IView;
import com.xing.commonbase.receiver.NetworkChangeReceiver;
import com.xing.commonbase.util.StatusBarUtil;

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
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mContext = this;
        setStatusBarColor();
        // 基类中注册 eventbus
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//黑色
        }
        //注册ButterKnife
        unbinder = ButterKnife.bind(this);
        registerNetworkChangeReceiver();
        initView();
        initData();
    }

    public void setStatusBarColor() {
        StatusBarUtil.setColor(this, getResources().getColor(android.R.color.white), 0);
    }

    protected abstract int getLayoutResId();

    protected abstract void initView();

    protected void initData() {

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
        if (unbinder!=null){
            unbinder.unbind();
        }
    }

    public void setStatusBarTextColor(Window window, boolean lightStatusBar) {
        // 设置状态栏字体颜色 白色与深色
        View decor = window.getDecorView();
        int ui = decor.getSystemUiVisibility();
        ui |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (lightStatusBar) {
                ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
        }
        decor.setSystemUiVisibility(ui);
    }
    //EventBus默认的实现
    @Subscribe
    public void onEventMainThread(String str) {

    }

}
