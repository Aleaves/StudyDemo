package com.sdk.simpleeyes.ui.splash

import com.sdk.simpleeyes.R
import com.sdk.simpleeyes.ui.base.BaseAppCompatFragment

class SloganFragment : BaseAppCompatFragment() {

    companion object {
        fun newInstance(): SloganFragment = SloganFragment()
    }

    override fun getContentViewLayoutId(): Int = R.layout.fragment_slogan
}