package com.sdk.mvp.base;

import com.sdk.mvp.entity.BaseEntity;

public interface IBasePresenter<V extends IBaseView> {

    void attachView(V v);

    void detachView();

    V getView();

    boolean isAttachView();

    void loadDataSuccess(BaseEntity baseEntity);

    void loadDataError(String errorMsg);

}
