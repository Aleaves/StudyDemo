package com.sdk.simpleeyes.ui.follow

import com.sdk.simpleeyes.R
import com.sdk.simpleeyes.ui.base.BaseAppCompatFragment

class FollowFragment : BaseAppCompatFragment() {

    companion object {
        @JvmStatic
        fun newInstance(): FollowFragment = FollowFragment()
    }

    override fun getContentViewLayoutId(): Int = R.layout.fragment_follow

}