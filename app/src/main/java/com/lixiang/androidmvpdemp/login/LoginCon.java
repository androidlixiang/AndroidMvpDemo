package com.lixiang.androidmvpdemp.login;

import com.xing.commonbase.base.IView;

public interface LoginCon {

    interface View extends IView {
        void loginSuccess(UserBean userBean);
    }
    interface Presenter{
        void login(LogBean bean);
        void login1(String phone,String paw);
    }

}
