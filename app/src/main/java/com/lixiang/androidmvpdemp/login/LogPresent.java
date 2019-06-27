package com.lixiang.androidmvpdemp.login;

import com.lixiang.androidmvpdemp.ApiService;
import com.xing.commonbase.base.BaseObserver;
import com.xing.commonbase.base.BasePresenter;

import java.util.HashMap;

public class LogPresent extends BasePresenter<LoginCon.View> implements LoginCon.Presenter {
    @Override
    public void login(LogBean logBean) {

        addSubscribe(create(ApiService.class).login(logBean), new BaseObserver<UserBean>(getView()) {
            @Override
            protected void onSuccess(UserBean data) {
                if (isViewAttached()) {
                    getView().loginSuccess(data);
                }
            }
        });
    }

    @Override
    public void login1(String phone, String paw) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("password", paw);

        addSubscribe(create(ApiService.class).login1(map), new BaseObserver<UserBean>(getView()) {
            @Override
            protected void onSuccess(UserBean data) {
                if (isViewAttached()) {
                    getView().loginSuccess(data);
                }
            }
        });
    }
}
