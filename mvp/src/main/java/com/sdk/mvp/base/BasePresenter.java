package com.sdk.mvp.base;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    private WeakReference<V> vWeakReference;

    protected BasePresenter(@NonNull V v){
        attachView(v);
    }

    @Override
    public void attachView(V v) {
        vWeakReference = new WeakReference<>(v);
    }

    @Override
    public void detachView() {
        if (vWeakReference != null) {
            vWeakReference.clear();
            vWeakReference = null;
            System.gc();
        }
    }

    @Override
    public V getView() {
        if (vWeakReference != null) {
            return vWeakReference.get();
        }
        return null;
    }

    @Override
    public boolean isAttachView() {
        return getView() != null;
    }

}
