package com.sdk.simpleeyes.ui.follow.view

import com.sdk.simpleeyes.entity.AndyInfo
import com.sdk.simpleeyes.ui.base.LoadMoreView

interface FollowView : LoadMoreView<AndyInfo>{

    fun loadFollowInfoSuccess(andyInfo: AndyInfo)

    fun refreshSuccess(andyInfo: AndyInfo)

}