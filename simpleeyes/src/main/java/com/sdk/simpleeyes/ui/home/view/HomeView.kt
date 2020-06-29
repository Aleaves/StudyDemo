package com.sdk.simpleeyes.ui.home.view

import com.sdk.simpleeyes.entity.AndyInfo
import com.sdk.simpleeyes.ui.base.BaseView

interface HomeView : BaseView {
    /**
     * 加载信息成功
     */
    fun loadDataSuccess(andyInfo: AndyInfo)

    /**
     * 加载信息成功
     */
    fun refreshDataSuccess(andyInfo: AndyInfo)

    /**
     * 加载更多成功
     */
    fun loadMoreSuccess(andyInfo: AndyInfo)

    /**
     * 没有更多
     */
    fun showNoMore()
}