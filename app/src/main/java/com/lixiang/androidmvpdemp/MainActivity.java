package com.lixiang.androidmvpdemp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.common.base.BaseMVPActivity;
import com.common.util.LogUtil;
import com.lixiang.androidmvpdemp.activity.KtHomeActivity;
import com.lixiang.androidmvpdemp.login.LogPresent;
import com.lixiang.androidmvpdemp.login.LoginCon;
import com.lixiang.androidmvpdemp.login.UserBean;
import com.lixiang.androidmvpdemp.widget.FocusImageView;
import com.lixiang.androidmvpdemp.widget.TextProgress;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseMVPActivity<LogPresent> implements LoginCon.View {


    private static final int AUDIO_FILE_REQUEST_CODE = 111;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pasww)
    EditText etPasww;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.mImageView)
    ImageView mImageView;
    @BindView(R.id.mFrameLayout)
    FrameLayout mFrameLayout;
    @BindView(R.id.mProgressBar)
    TextProgress mProgressBar;
    @BindView(R.id.iv_animation)
    FocusImageView ivAnimation;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected LogPresent createPresenter() {
        return new LogPresent();
    }

    @Override
    protected void initView() {
        checkPermission();
//        startActivity(new Intent(this, LineActivity.class));
//        ivAnimation.setFocusableInTouchMode(true);
//        Glide.with(this).load(R.mipmap.mohu).into(mImageView);
//        Glide.with(this).asGif().load(R.mipmap.robot_head).into(mImageView);
//        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(1, 0);
//        valueAnimator.set
//        mProgressBar.setProgress(50);

//        ViewGroup.LayoutParams layoutParams = mImageView.getLayoutParams();
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.mohu);
//        layoutParams.height = bitmap.getHeight() * DensityUtils.getScreenWidth() / bitmap.getWidth();
//        layoutParams.width = DensityUtils.getScreenWidth();
//        Blurry.with(this)
//                .radius(15)
//                .sampling(8)
////                .color(Color.argb(66, 255, 255, 0))
//                .async()
//                .from(BitmapFactory.decodeResource(getResources(), R.mipmap.mohu)).into(mImageView);

        ivAnimation.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

//        getIntent().get

        tvContent.setText(null + "www");
    }


    @Override
    public void loginSuccess(UserBean userBean) {
        tvContent.setText(userBean.toString());
    }


    @OnClick({R.id.button1, R.id.button2})
    public void onViewClicked(View view) {
        tvContent.setText("请请求网络");

//        tvContent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//
//            }
//        });

        String name = etName.getText().toString();
        String paw = etPasww.getText().toString();
        switch (view.getId()) {
            case R.id.button1:
//                mPresenter.login(new LogBean(name, paw));
//                ARouter.getInstance().build("/activity/second").navigation();
//                ARouter.getInstance()
//                        .build("/activity/second")
//                        .navigation();
//                String path = Constants.FILE_CATALOG + "/lixiang";
//                File dir1 = new File(path);
//                if (!dir1.exists()) {
//                    boolean mkdirs = dir1.mkdirs();
//                    LogUtil.logTest(mkdirs + "");
//                }
//                ListBottomSheetDialogFragment fragment=new ListBottomSheetDialogFragment();
//                fragment.setTopOffset(DensityUtils.dp2px(100));
//                fragment.show(getSupportFragmentManager(), "1");
                startActivity(new Intent(mContext, KtHomeActivity.class));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!isIgnoringBatteryOptimizations()) {
                        requestIgnoreBatteryOptimizations();
                    }
                }

//                Intent innerIntent = new Intent(Intent.ACTION_PICK); // "android.intent.action.GET_CONTENT"innerIntent.setType("image/*");Intent wrapperIntent = Intent.createChooser(innerIntent, "选择二维码图片");this.startActivityForResult(wrapperIntent, 1);

//                SensorsStatisticsHelper.addStatistics("aa");

                break;
            case R.id.button2:

                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                        LogUtil.logTest("1当前是否在主线程---》" + (Looper.myLooper() == Looper.getMainLooper()));
                        emitter.onNext("1");
                        emitter.onNext("2");
                        emitter.onNext("3");
                        emitter.onNext("4");
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<String>() {
                            // 第二步：初始化Observer
                            private String i = "3";
                            private Disposable mDisposable;

                            @Override
                            public void onSubscribe(Disposable d) {
                                mDisposable = d;
                                LogUtil.logTest("onSubscribe");

                            }

                            @Override
                            public void onNext(String s) {
                                LogUtil.logTest("2当前是否在主线程---》" + (Looper.myLooper() == Looper.getMainLooper()));
                                LogUtil.logTest("onNext");
                                LogUtil.logTest("onNext--->" + s);
                                if (s.equals(i)) {
                                    mDisposable.dispose();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                LogUtil.logTest("onError");
                            }

                            @Override
                            public void onComplete() {
                                LogUtil.logTest("onComplete");
                            }
                        });
                break;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (AUDIO_FILE_REQUEST_CODE == requestCode) {
            if (data != null) {
                Log.e("data:", data.getDataString());
                Log.e("data:", data.getData().getPath());
                Cursor cursor = getApplicationContext().getContentResolver().query(
                        data.getData(), new String[]{
                                MediaStore.Audio.Media.DISPLAY_NAME,
                                MediaStore.Audio.Media.SIZE,
                                MediaStore.Audio.Media.DURATION,
                                MediaStore.Audio.Media.DATA}, null, null, null);
                if (null != cursor) {
//                        cursor1.getString(0);//获取音频的名字
//                        cursor1.getLong(1);//获取音频的大小
//                        cursor1.getLong(2);//获取音频的时长

                    while (cursor.moveToNext()) {
                        LogUtil.logTest(cursor.getString(cursor
                                .getColumnIndex("_display_name")));
                        LogUtil.logTest(cursor.getString(cursor
                                .getColumnIndex("duration")));
                        cursor.getString(cursor
                                .getColumnIndex("_size"));
                        cursor.getString(cursor
                                .getColumnIndex("_data"));
                    }


//                    cursor.moveToFirst();
//                    final String path = cursor.getString(3);//获取音频的地址，顺序是和上面的对应的
////
//                    Log.e("音频路径：", path + "");
//                    if (path != null) {
//
//                    }
                }
                cursor.close();
            }

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isIgnoringBatteryOptimizations() {
        boolean isIgnoring = false;
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if (powerManager != null) {
            isIgnoring = powerManager.isIgnoringBatteryOptimizations(getPackageName());
        }
        return isIgnoring;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestIgnoreBatteryOptimizations() {
        try {
            Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void checkPermission() {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        }
    }

}
