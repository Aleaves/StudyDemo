package com.sdk.simpleeyes.ui.base

interface LoadMoreView<T> : BaseView {
    /**
     * 加载更多信息成功
     */
    fun loadMoreSuccess(data: T)

    /**
     * 没有更多
     */
    fun showNoMore()
}