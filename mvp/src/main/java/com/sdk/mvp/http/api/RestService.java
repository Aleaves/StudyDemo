package com.sdk.mvp.http.api;

import com.sdk.mvp.entity.BaseEntity;
import com.sdk.mvp.entity.UserInfo;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestService {

    @FormUrlEncoded
    @POST("/visitor/login")
    Observable<BaseEntity<UserInfo>> quickLogin(@FieldMap HashMap<String,String> map);

}
