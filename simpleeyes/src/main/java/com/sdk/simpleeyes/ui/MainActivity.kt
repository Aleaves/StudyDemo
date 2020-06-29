package com.sdk.simpleeyes.ui

import android.os.Bundle
import com.sdk.simpleeyes.R
import com.sdk.simpleeyes.ui.base.BaseAppCompatActivity
import com.sdk.simpleeyes.ui.feed.FeedFragment
import com.sdk.simpleeyes.ui.follow.FollowFragment
import com.sdk.simpleeyes.ui.home.HomeFragment
import com.sdk.simpleeyes.ui.profile.ProfileFragment
import com.sdk.simpleeyes.utils.bindView
import com.sdk.simpleeyes.widget.BottomBar
import com.sdk.simpleeyes.widget.BottomItem
import me.yokeyword.fragmentation.SupportFragment

class MainActivity : BaseAppCompatActivity() {

    private val mFragments = arrayOfNulls<SupportFragment>(4)

    private val mBottomNavigation by bindView<BottomBar>(R.id.bottom_navigation_bar)

    companion object {
        private const val FIRST = 0
        private const val SECOND = 1
        private const val THIRD = 2
        private const val FOURTH = 3
    }

    override fun initView(savedInstanceState: Bundle?) {
        mFragments[FIRST] = HomeFragment.newInstance()
        mFragments[SECOND] = FeedFragment.newInstance()
        mFragments[THIRD] = FollowFragment.newInstance()
        mFragments[FOURTH] = ProfileFragment.newInstance()
        loadMultipleRootFragment(R.id.fl_container, FIRST, *mFragments)

        initBottomNavigation()

    }

    private fun initBottomNavigation() {
        var home = BottomItem(R.drawable.ic_tab_strip_icon_feed_selected, getString(R.string.home))
        home.setUnSelectedDrawable(R.drawable.ic_tab_strip_icon_feed)
        val discover = BottomItem(R.drawable.ic_tab_strip_icon_category_selected, getString(R.string.discover))
        discover.setUnSelectedDrawable(R.drawable.ic_tab_strip_icon_category)
        val focus = BottomItem(R.drawable.ic_tab_strip_icon_follow_selected, getString(R.string.focus))
        focus.setUnSelectedDrawable(R.drawable.ic_tab_strip_icon_follow)
        val mine = BottomItem(R.drawable.ic_tab_strip_icon_profile_selected, getString(R.string.mine))
        mine.setUnSelectedDrawable(R.drawable.ic_tab_strip_icon_profile)

        with(mBottomNavigation){
            addItem(home)
            addItem(discover)
            addItem(focus)
            addItem(mine)
            initialise()
            setOnTabSelectedListener(object : BottomBar.TabSelectedListener{

                override fun onTabSelected(position: Int, prePosition: Int) {
                    showHideFragment(mFragments[position])
                }

                override fun onTabUnselected(position: Int) {

                }

                override fun onTabReselected(position: Int) {
                    if (position == FIRST) {
                        val categoryFragment = mFragments[FIRST] as HomeFragment
                        categoryFragment.scrollToTop()
                    }
                }

            })
        }
    }

    override fun getContentViewLayoutId(): Int = R.layout.activity_main
}
