package com.sdk.simpleeyes.ui.home

import com.sdk.simpleeyes.R
import com.sdk.simpleeyes.ui.base.BaseAppCompatFragment

class HomeFragment : BaseAppCompatFragment() {

    companion object {
        @JvmStatic
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun getContentViewLayoutId(): Int = R.layout.fragment_home

    fun scrollToTop() {

    }

}