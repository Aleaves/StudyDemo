package com.sdk.simpleeyes.ui.splash

import android.os.Bundle
import com.sdk.simpleeyes.R
import com.sdk.simpleeyes.UserPreferences
import com.sdk.simpleeyes.ui.base.BaseAppCompatActivity

class LandingActivity : BaseAppCompatActivity() {

    override fun initView(savedInstanceState: Bundle?) {
        if (UserPreferences.getUserIsFirstLogin()) {
            loadRootFragment(R.id.fl_container, VideoLandingFragment.newInstance())
        } else {
            loadRootFragment(R.id.fl_container, LocalCommonLandingFragment.newInstance())
        }
    }

    override fun getContentViewLayoutId(): Int = R.layout.activity_landing

}