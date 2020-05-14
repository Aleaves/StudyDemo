package com.sdk.simpleeyes.ui.feed

import com.sdk.simpleeyes.R
import com.sdk.simpleeyes.ui.base.BaseAppCompatFragment

class FeedFragment : BaseAppCompatFragment() {

    companion object {
        @JvmStatic
        fun newInstance(): FeedFragment = FeedFragment()
    }

    override fun getContentViewLayoutId(): Int = R.layout.fragment_feed
}