package com.lixiang.androidmvpdemp;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lixiang.androidmvpdemp.login.LogPresent;
import com.lixiang.androidmvpdemp.login.LoginCon;
import com.lixiang.androidmvpdemp.login.UserBean;
import com.xing.commonbase.base.BaseMVPActivity;

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
                ARouter.getInstance()
                        .build("/activity/second")
                        .navigation();
//                startActivity(new Intent(mContext,SecondActivity.class));
                break;
            case R.id.button2:
                mPresenter.login1(name, paw);
                break;
        }
    }

}
