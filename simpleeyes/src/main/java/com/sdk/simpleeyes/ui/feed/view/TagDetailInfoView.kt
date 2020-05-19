package com.sdk.simpleeyes.ui.feed.view

import com.sdk.simpleeyes.entity.AndyInfo
import com.sdk.simpleeyes.ui.base.LoadMoreView

interface TagDetailInfoView : LoadMoreView<AndyInfo> {
    fun showGetTabInfoSuccess(andyInfo: AndyInfo)
}