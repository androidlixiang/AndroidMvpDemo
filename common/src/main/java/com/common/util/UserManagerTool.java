package com.common.util;

import android.text.TextUtils;

import com.common.bean.UserInfo;
import com.common.http.HttpUtils;

public class UserManagerTool {

    /**
     * 退出登录
     */
    public static void logOut() {
        SPUtil.put("token", "");
        SPUtil.put("userInfo", "");
    }

    /**
     * 是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        return !TextUtils.isEmpty(getToken());
    }

    public static String getToken() {
        return SPUtil.getString("token");
    }

    public static void setToken(String token) {
        SPUtil.put("token", token);
    }

    /**
     * 保存用户信息
     *
     * @param userInfo
     */
    public static void saveUserInfo(UserInfo userInfo) {
        if (userInfo == null) {
            SPUtil.put("userInfo", "");
            return;
        }
        String s = HttpUtils.mGson.toJson(userInfo);
        SPUtil.put("userInfo", s);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static UserInfo getUserInfo() {
        if (TextUtils.isEmpty(SPUtil.getString("userInfo"))) {
            return null;
        }
        return HttpUtils.mGson.fromJson(SPUtil.getString("userInfo"), UserInfo.class);
    }


}
