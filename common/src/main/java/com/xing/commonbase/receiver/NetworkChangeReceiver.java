package com.xing.commonbase.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.xing.commonbase.util.NetworkUtil;
import com.xing.commonbase.widget.NoNetworkTip;

/**
 * @创建者:李祥
 * @创建日期： 2019/7/23 10:38
 * @类说明：网络变化的广播
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    private Activity activity;
    private NoNetworkTip noNetworkTip;

    public NetworkChangeReceiver(Activity activity) {
        this.activity = activity;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceive(Context context, Intent intent) {
        if (noNetworkTip == null) {
            noNetworkTip = new NoNetworkTip(activity);
        }
        if (NetworkUtil.isNetworkAvailable(context)) {
            if (noNetworkTip != null && noNetworkTip.isShowing()) {
                noNetworkTip.dismiss();
            }
        } else {
            if (noNetworkTip != null && !noNetworkTip.isShowing()) {
                noNetworkTip.show();
            }
        }
    }

    public void onDestroy() {
        if (noNetworkTip != null && noNetworkTip.isShowing()) {
            noNetworkTip.dismiss();
        }
        activity = null;
        noNetworkTip = null;
    }
}
