package com.sdk.simpleeyes.ui.base.presenter

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider

open class BasePresenter<V> : LifecycleObserver {

    protected var mView: V? = null
    protected lateinit var mScopeProvider: AndroidLifecycleScopeProvider

    fun attachView(view: V, lifecycleOwner: LifecycleOwner) {
        this.mView = view
        this.mScopeProvider = AndroidLifecycleScopeProvider.from(lifecycleOwner)
    }
}