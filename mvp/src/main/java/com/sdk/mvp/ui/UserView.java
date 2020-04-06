package com.sdk.mvp.ui;

import com.sdk.mvp.base.IBaseView;
import com.sdk.mvp.entity.UserInfo;

public interface UserView extends IBaseView {
    void onLoginSuccess(UserInfo userInfo);
    void onLoginFailure();
}
