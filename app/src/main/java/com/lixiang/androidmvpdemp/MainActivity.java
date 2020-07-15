package com.lixiang.androidmvpdemp;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.IBinder;
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

import com.alibaba.android.arouter.facade.annotation.Route;
import com.common.base.BaseMVPActivity;
import com.common.util.LogUtil;
import com.google.gson.Gson;
import com.lixiang.androidmvpdemp.activity.KtHomeActivity;
import com.lixiang.androidmvpdemp.bean.IPBean;
import com.lixiang.androidmvpdemp.login.LogPresent;
import com.lixiang.androidmvpdemp.login.LoginCon;
import com.lixiang.androidmvpdemp.login.UserBean;
import com.lixiang.androidmvpdemp.server.MyService1;
import com.lixiang.androidmvpdemp.utils.Base64Utils;
import com.lixiang.androidmvpdemp.widget.FocusImageView;
import com.lixiang.androidmvpdemp.widget.TextProgress;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = "/main/activity/MainActivity")
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

        String a="fqr"+"/";
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
        Intent intent = new Intent(mContext, MyService1.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.logTest("onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.logTest("onServiceDisconnected");

        }
    };

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
//                        requestIgnoreBatteryOptimizations();
                    }
                }

//                Intent innerIntent = new Intent(Intent.ACTION_PICK); // "android.intent.action.GET_CONTENT"innerIntent.setType("image/*");Intent wrapperIntent = Intent.createChooser(innerIntent, "选择二维码图片");this.startActivityForResult(wrapperIntent, 1);

//                SensorsStatisticsHelper.addStatistics("aa");

                break;
            case R.id.button2:
//                ARouter.getInstance().build("/niubi/Permission10Activity").navigation();
//                ARouter.getInstance().build("/activity/RiliActivity").navigation();
//                CalendarSelectDialog calendarSelectDialog=new  CalendarSelectDialog();
//                calendarSelectDialog.show(getSupportFragmentManager(), "");
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.aaa);
                String s = Base64Utils.bitmapToBase64(bitmap);
                Bitmap bitmap1 = Base64Utils.base64ToBitmap(s);
                LogUtil.logTest(s+bitmap1.toString());
                break;
        }


    }


    public void getMobileIP() {
        new Thread() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                try {
                    URL url = new URL("http://ip.chinaz.com/getip.aspx");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    String result = readInStream(in);
                    LogUtil.logTest(result);
                    if (!result.isEmpty()) {
                        IPBean ip = new Gson().fromJson(result, IPBean.class);
                    }
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    urlConnection.disconnect();
                }
            }
        }.start();

    }


    private String readInStream(InputStream in) {
        Scanner scanner = new Scanner(in).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }

    /**
     * 获取当前ip地址
     *
     * @param context
     * @return
     */
    public static String getLocalIpAddress(Context context) {
        try {

            WifiManager wifiManager = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int i = wifiInfo.getIpAddress();
            return int2ip(i);
        } catch (Exception ex) {
            return " 获取IP出错鸟!!!!请保证是WIFI,或者请重新打开网络!\n" + ex.getMessage();
        }
        // return null;
    }

    /**
     * 将ip的整数形式转换成ip形式
     *
     * @param ipInt
     * @return
     */
    public static String int2ip(int ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }

    private void initCalendar() {
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(CalendarContract.ACTION_EVENT_REMINDER);
//        intentFilter.addDataScheme("content");
//        intentFilter.addDataAuthority("com.android.calendar", null);
//        AlarmRemindReceiver mBroadcastReceiver
//        registerReceiver(mBroadcastReceiver, intentFilter);
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


    public static String GetNetIp() {
        String IP = "";
        try {
//            String address = "http://ip.taobao.com/service/getIpInfo2.php?ip=myip";
            String address = "http://ip.taobao.com/ipSearch";
            URL url = new URL(address);

            //  URLConnection htpurl=url.openConnection();

            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setUseCaches(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("user-agent",
                    "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.7 Safari/537.36"); //设置浏览器ua 保证不出现503

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = connection.getInputStream();

                // 将流转化为字符串
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(in));

                String tmpString = "";
                StringBuilder retJSON = new StringBuilder();
                while ((tmpString = reader.readLine()) != null) {
                    retJSON.append(tmpString + "\n");
                }

                JSONObject jsonObject = new JSONObject(retJSON.toString());
                String code = jsonObject.getString("code");

                LogUtil.logTest("提示：" +retJSON.toString());
                if (code.equals("0")) {
                    JSONObject data = jsonObject.getJSONObject("data");
                    IP = data.getString("ip") + "(" + data.getString("country")
                            + data.getString("area") + "区"
                            + data.getString("region") + data.getString("city")
                            + data.getString("isp") + ")";

                    Log.e("提示", "您的IP地址是：" + IP);
                } else {
                    IP = "";
                    Log.e("提示", "IP接口异常，无法获取IP地址！");
                }
            } else {
                IP = "";
                Log.e("提示", "网络连接异常，无法获取IP地址！");
            }
        } catch (Exception e) {
            IP = "";
            Log.e("提示", "获取IP地址时出现异常，异常信息是：" + e.toString());
        }
        return IP;
    }

}
