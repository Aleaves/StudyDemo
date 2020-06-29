package com.sdk.simpleeyes.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sdk.simpleeyes.ui.base.presenter.BasePresenter
import com.sdk.simpleeyes.utils.getGenericInstance

abstract class BaseFragment<V, T : BasePresenter<V>> : BaseAppCompatFragment(), BaseView {

    protected lateinit var mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = getGenericInstance(this,1)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mPresenter.attachView(this as V,this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun showLoading() {
        mMultipleStateView.showLoading()
    }

    override fun showNetError(onClickListener: View.OnClickListener) {
        mMultipleStateView.showNetError(onClickListener)
    }

    override fun showEmpty(onClickListener: View.OnClickListener) {
        mMultipleStateView.showEmpty(onClickListener)
    }

    override fun showContent() {
        mMultipleStateView.showContent()
    }

}