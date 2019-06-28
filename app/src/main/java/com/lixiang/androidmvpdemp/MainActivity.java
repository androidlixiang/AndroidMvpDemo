package com.lixiang.androidmvpdemp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lixiang.androidmvpdemp.login.LogPresent;
import com.lixiang.androidmvpdemp.login.LoginCon;
import com.lixiang.androidmvpdemp.login.UserBean;
import com.xing.commonbase.base.BaseMVPActivity;
import com.xing.commonbase.constants.Constants;
import com.xing.commonbase.util.LogUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseMVPActivity<LogPresent> implements LoginCon.View {


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
    }


    @Override
    public void loginSuccess(UserBean userBean) {
        tvContent.setText(userBean.toString());

        TestDialog testDialog = new TestDialog();
        testDialog.show(getSupportFragmentManager(), "");
    }


    @OnClick({R.id.button1, R.id.button2})
    public void onViewClicked(View view) {
        tvContent.setText("请请求网络");


        String name = etName.getText().toString();
        String paw = etPasww.getText().toString();
        switch (view.getId()) {
            case R.id.button1:
//                mPresenter.login(new LogBean(name, paw));
//                ARouter.getInstance().build("/activity/second").navigation();
//                ARouter.getInstance()
//                        .build("/activity/second")
//                        .navigation();
                String path = Constants.FILE_CATALOG + "/lixiang";
                File dir1 = new File(path);
                if (!dir1.exists()) {
                    boolean mkdirs = dir1.mkdirs();
                    LogUtil.logTest(mkdirs + "");
                }

//                startActivity(new Intent(mContext,SecondActivity.class));
                break;
            case R.id.button2:
                mPresenter.login1(name, paw);
                break;
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
