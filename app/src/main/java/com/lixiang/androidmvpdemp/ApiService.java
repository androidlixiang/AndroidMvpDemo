package com.lixiang.androidmvpdemp;


import com.lixiang.androidmvpdemp.login.LogBean;
import com.lixiang.androidmvpdemp.login.UserBean;
import com.xing.commonbase.base.BaseResponse;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("member/login")
    Observable<BaseResponse<UserBean>> login(@Body LogBean logBean);

    @POST("member/login")
    Observable<BaseResponse<UserBean>> login1(@Body HashMap map);

}
