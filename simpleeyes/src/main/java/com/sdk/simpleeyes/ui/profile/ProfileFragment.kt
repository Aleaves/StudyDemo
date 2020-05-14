package com.sdk.simpleeyes.ui.profile

import com.sdk.simpleeyes.R
import com.sdk.simpleeyes.ui.base.BaseAppCompatFragment

class ProfileFragment : BaseAppCompatFragment() {

    companion object {
        @JvmStatic
        fun newInstance(): ProfileFragment = ProfileFragment()
    }

    override fun getContentViewLayoutId(): Int = R.layout.fragment_profile
}