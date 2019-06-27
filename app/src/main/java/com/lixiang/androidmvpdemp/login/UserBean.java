package com.lixiang.androidmvpdemp.login;

import java.io.Serializable;

public class UserBean implements Serializable {


    /**
     * user_id : 258057
     * wx_nickname : 恒
     * wx_headimg : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLRzIRxEdiceuQnu7TWdgNhYFp8Aicx10ANc6QlrALM9y4OTfJIDJu7Rr62WMnSFeD6r30rVExIMsZQ/132
     * userToken : d3fafc46ace8565272c81011f05447c3_258057
     * phone : 15637939253
     * grade : 1
     * grade_name : 董事
     */

    private String user_id;
    private String wx_nickname;
    private String wx_headimg;
    private String userToken;
    private String phone;
    private String grade;
    private String grade_name;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getWx_nickname() {
        return wx_nickname;
    }

    public void setWx_nickname(String wx_nickname) {
        this.wx_nickname = wx_nickname;
    }

    public String getWx_headimg() {
        return wx_headimg;
    }

    public void setWx_headimg(String wx_headimg) {
        this.wx_headimg = wx_headimg;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade_name() {
        return grade_name;
    }

    public void setGrade_name(String grade_name) {
        this.grade_name = grade_name;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "user_id='" + user_id + '\'' +
                ", wx_nickname='" + wx_nickname + '\'' +
                ", wx_headimg='" + wx_headimg + '\'' +
                ", userToken='" + userToken + '\'' +
                ", phone='" + phone + '\'' +
                ", grade='" + grade + '\'' +
                ", grade_name='" + grade_name + '\'' +
                '}';
    }
}
