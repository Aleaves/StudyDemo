package com.sdk.mvp.ui.Presenter;

import android.os.Build;

import androidx.annotation.NonNull;

import com.sdk.mvp.base.BaseModel;
import com.sdk.mvp.base.BasePresenter;
import com.sdk.mvp.entity.BaseEntity;
import com.sdk.mvp.entity.UserInfo;
import com.sdk.mvp.http.api.RestService;
import com.sdk.mvp.ui.UserView;
import com.sdk.mvp.utils.SignUtils;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserPresenter extends BasePresenter<UserView> {


    public UserPresenter(@NonNull UserView userView) {
        super(userView);
    }

    public void quickLogin(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sdkng.xyzs.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        RestService restService = retrofit.create(RestService.class);
        HashMap<String,String> map = new HashMap<>();
        String deviceId = "df"+System.currentTimeMillis();
        map.put("appid","100009");
        map.put("source","200000");
        map.put("equip",deviceId);
        map.put("device_id",deviceId);
        map.put("device_name", Build.MODEL);
        map.put("app_version","1.0.0");
        map.put("appver", "1.0.0");
        map.put("sdk_version", "1.3.0");
        map.put("os_version",Build.VERSION.SDK_INT+"");//系统sdk版本号
        map.put("devicetype","android");
        map.put("os",Build.VERSION.SDK_INT+"");
        map.put("sign", SignUtils.getSignParams(map));
        new BaseModel(this).post(restService.quickLogin(map));
    }

    @Override
    public void loadDataSuccess(BaseEntity baseEntity) {
        UserInfo userInfo = (UserInfo) baseEntity.getData();
        getView().onLoginSuccess(userInfo);
    }

    @Override
    public void loadDataError(String errorMsg) {

    }
}
