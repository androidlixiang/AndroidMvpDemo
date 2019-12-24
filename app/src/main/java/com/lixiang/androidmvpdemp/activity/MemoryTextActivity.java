package com.lixiang.androidmvpdemp.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.base.BaseMVPActivity;
import com.common.base.IPresenter;
import com.lixiang.androidmvpdemp.R;
import com.lixiang.androidmvpdemp.server.LogService;
import com.lixiang.androidmvpdemp.utils.MediaUtils;

import butterknife.BindView;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/11/13 11:41
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/11/13 11:41
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class MemoryTextActivity extends BaseMVPActivity {
    @BindView(R.id.tv_aa)
    TextView tvAa;
    @BindView(R.id.iv_greetings)
    ImageView ivGreetings;
    @BindView(R.id.iv_greetings1)
    ImageView ivGreetings1;
    private MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_meory;
    }

    @Override
    protected IPresenter createPresenter() {
        return null;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initView() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("mybroadcast");
        myBroadcastReceiver = new MyBroadcastReceiver();
//        registerReceiver(myBroadcastReceiver, intentFilter);
        ivGreetings.setImageDrawable(getDrawable(R.drawable.greetings));
//        ivGreetings1.setImageDrawable(getDrawable(R.drawable.listener));
        if (ivGreetings.getDrawable() instanceof AnimationDrawable) {
            ((AnimationDrawable) ivGreetings.getDrawable()).start();
        }
//        if (ivGreetings1.getDrawable() instanceof AnimationDrawable) {
//            ((AnimationDrawable) ivGreetings1.getDrawable()).start();
//        }
//

        MediaUtils.getImageForVideo("http://loudspeaker.nhf.cn/ljozPGz88U9sqB9rg806DyD32O2y", new MediaUtils.OnLoadVideoImageListener() {
            @Override
            public void onLoadImage(Bitmap file) {
                ivGreetings1.setImageBitmap(file);
            }
        });

        Intent intent = new Intent(this, LogService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }
    ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            tvAa.setText("哈哈哈我接收到了");

        }
    }


}
