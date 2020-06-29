package com.sdk.mvvm.viewmodel;

import android.util.Log;
import android.view.View;

import com.sdk.mvvm.model.UserInfo;

public class LoginViewModel {
    public UserInfo userInfo;

    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("=========","onclick");
        }
    };
}
