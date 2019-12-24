package com.lixiang.androidmvpdemp;


import com.common.base.BaseResponse;
import com.lixiang.androidmvpdemp.login.LogBean;
import com.lixiang.androidmvpdemp.login.UserBean;
import com.lixiang.androidmvpdemp.login.kt.KtUserBean;
import com.lixiang.androidmvpdemp.login.kt.KtUserBeanData;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("member/login")
    Observable<BaseResponse<UserBean>> login(@Body LogBean logBean);

    @POST("member/login")
    Observable<BaseResponse<UserBean>> login1(@Body HashMap map);

    @POST("member/login")
    Observable<BaseResponse<KtUserBeanData>> login2(@Body HashMap map);

    @POST("member/login")
    Observable<BaseResponse<KtUserBean>> login3(@Body HashMap map);
}
