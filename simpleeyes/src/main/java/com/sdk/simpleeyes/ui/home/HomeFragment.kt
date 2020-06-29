package com.sdk.simpleeyes.ui.home

import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.jennifer.andy.simpleeyes.ui.base.adapter.BaseDataAdapter
import com.jennifer.andy.simpleeyes.widget.CustomLoadMoreView
import com.sdk.simpleeyes.R
import com.sdk.simpleeyes.entity.AndyInfo
import com.sdk.simpleeyes.ui.base.BaseFragment
import com.sdk.simpleeyes.ui.home.presenter.HomePresenter
import com.sdk.simpleeyes.ui.home.view.HomeView
import com.sdk.simpleeyes.utils.bindView
import com.sdk.simpleeyes.utils.getScreenHeight
import com.sdk.simpleeyes.utils.getScreenWidth
import com.sdk.simpleeyes.widget.pull.head.HomePageHeaderView
import com.sdk.simpleeyes.widget.pull.zoom.PullToZoomBase
import com.sdk.simpleeyes.widget.pull.zoom.PullToZoomRecyclerView

class HomeFragment : BaseFragment<HomeView, HomePresenter>(), HomeView {

    private val mPullToZoomRecycler: PullToZoomRecyclerView by bindView(R.id.rv_home_recycler)
    private lateinit var mHomePageHeaderView: HomePageHeaderView
    private var mCateGoryAdapter: BaseDataAdapter? = null

    companion object {
        @JvmStatic
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun getContentViewLayoutId(): Int = R.layout.fragment_home

    override fun initView(savedInstanceState: Bundle?) {
        mPullToZoomRecycler.setOnPullZoomListener(object : PullToZoomBase.OnPullZoomListener{
            override fun onPullZooming(scrollValue: Int) {
                mHomePageHeaderView.showRefreshCover(scrollValue)
            }

            override fun onPullZoomEnd() {
                if(mHomePageHeaderView.judgeCanRefresh()){
                    mPresenter.refreshCategoryData()
                }else{
                    mHomePageHeaderView.hideRefreshCover()
                }
            }
        })

        mPresenter.loadCategoryData()
    }

    override fun loadDataSuccess(andyInfo: AndyInfo) {
        if (mCateGoryAdapter == null) {
            setHeaderInfo(andyInfo)
            setAdapterAndListener(andyInfo)
        }else {
            mCateGoryAdapter?.setNewData(andyInfo.itemList)
        }
    }

    private fun setHeaderInfo(andyInfo: AndyInfo){
        mHomePageHeaderView = HomePageHeaderView(context!!)
        val lp  = ViewGroup.LayoutParams(context!!.getScreenWidth(),context!!.getScreenHeight()/2)
        mPullToZoomRecycler.setHeaderViewLayoutParams(LinearLayout.LayoutParams(lp))
        mHomePageHeaderView.setHeaderInfo(andyInfo.topIssue,andyInfo.topIssue.data.itemList,this)
        mPullToZoomRecycler.setHeaderView(mHomePageHeaderView)
    }

    private fun setAdapterAndListener(andyInfo: AndyInfo){
        val recyclerView = mPullToZoomRecycler.getPullRootView()
        recyclerView.setItemViewCacheSize(10)
        mCateGoryAdapter = BaseDataAdapter(andyInfo.itemList)
        mCateGoryAdapter?.setOnLoadMoreListener({
            mPresenter.loadMoreCategoryData()
        },recyclerView)
        mCateGoryAdapter?.setLoadMoreView(CustomLoadMoreView())
        mPullToZoomRecycler.setAdapterAndLayoutManager(mCateGoryAdapter!!, LinearLayoutManager(_mActivity))
    }

    override fun refreshDataSuccess(andyInfo: AndyInfo) {
        mCateGoryAdapter?.removeAllFooterView()
        mCateGoryAdapter?.setNewData(andyInfo.itemList)
        mHomePageHeaderView.hideRefreshCover()
    }

    override fun loadMoreSuccess(andyInfo: AndyInfo) {
        mCateGoryAdapter?.addData(andyInfo.itemList)
        mCateGoryAdapter?.loadMoreComplete()
    }

    override fun showNoMore() {
        mCateGoryAdapter?.loadMoreEnd()
    }

    fun scrollToTop() {
        mPullToZoomRecycler.scrollToTop()
    }

}