package com.sdk.simpleeyes.ui.feed.view

import com.sdk.simpleeyes.entity.TabInfo
import com.sdk.simpleeyes.ui.base.BaseView

interface FeedView : BaseView{
    fun loadTabSuccess(tabInfo: TabInfo)
}