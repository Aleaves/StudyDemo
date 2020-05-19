package com.sdk.simpleeyes.ui.feed

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.sdk.simpleeyes.R
import com.sdk.simpleeyes.entity.TabInfo
import com.sdk.simpleeyes.ui.base.BaseAppCompatFragment
import com.sdk.simpleeyes.ui.base.BaseFragment
import com.sdk.simpleeyes.ui.base.BaseFragmentItemAdapter
import com.sdk.simpleeyes.ui.feed.presenter.FeedPresenter
import com.sdk.simpleeyes.ui.feed.view.FeedView
import com.sdk.simpleeyes.ui.search.SearchHotActivity
import com.sdk.simpleeyes.utils.bindView
import com.sdk.simpleeyes.utils.readyGo
import com.sdk.simpleeyes.utils.setHeightAndPadding
import com.sdk.simpleeyes.widget.font.CustomFontTextView
import com.sdk.simpleeyes.widget.tab.ShortTabLayout

class FeedFragment : BaseFragment<FeedView,FeedPresenter>(),FeedView {

    private val mViewPager : ViewPager by bindView(R.id.view_pager)
    private val mTvAllCategory: CustomFontTextView by bindView(R.id.tv_all_category)
    private val mIvSearch: ImageView by bindView(R.id.iv_search)
    private val mTabLayout: ShortTabLayout by bindView(R.id.tab_layout)
    private val mDiscoverTitle : FrameLayout by bindView(R.id.discover_title_fl)

    companion object {
        @JvmStatic
        fun newInstance(): FeedFragment = FeedFragment()
    }

    override fun getContentViewLayoutId(): Int = R.layout.fragment_feed

    override fun initView(savedInstanceState: Bundle?) {
        _mActivity.setHeightAndPadding(mDiscoverTitle)
        mPresenter.getDiscoveryTab()
        mIvSearch.setOnClickListener {
            readyGo<SearchHotActivity>()
        }
        mTvAllCategory.setOnClickListener {
            readyGo<AllCategoryActivity>()
        }
    }

    override fun loadTabSuccess(tabInfo: TabInfo) {
        mViewPager.adapter = BaseFragmentItemAdapter(childFragmentManager,initFragments(tabInfo),initTitles(tabInfo))
        mViewPager.offscreenPageLimit   = tabInfo.tabList.size
        mTabLayout.setupWithViewPager(mViewPager)
    }

    private fun initFragments(tabInfo: TabInfo): MutableList<Fragment>{
        val fragments = mutableListOf<Fragment>()
        for (i in tabInfo.tabList.indices){
            fragments.add(TagDetailInfoFragment.newInstance(tabInfo.tabList[i].apiUrl))
        }
        return fragments
    }

    private fun initTitles(tabInfo: TabInfo): MutableList<String>{
        val titles = mutableListOf<String>()
        for (i in tabInfo.tabList.indices){
            titles.add(tabInfo.tabList[i].name)
        }
        return titles
    }
}