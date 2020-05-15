package com.sdk.simpleeyes.ui.base

import android.view.View

interface BaseView {

    /**
     * 显示正在加载
     */
    fun showLoading()

    /**
     * 显示内容
     */
    fun showContent()

    /**
     * 显示网络异常
     */
    fun showNetError(onClickListener: View.OnClickListener)

    /**
     * 显示空界面
     */
    fun showEmpty(onClickListener: View.OnClickListener)

}