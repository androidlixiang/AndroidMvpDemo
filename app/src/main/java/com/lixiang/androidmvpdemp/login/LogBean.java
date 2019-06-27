package com.lixiang.androidmvpdemp.login;

import java.io.Serializable;

public class LogBean implements Serializable {

    public LogBean(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    private  String phone;
    private  String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
