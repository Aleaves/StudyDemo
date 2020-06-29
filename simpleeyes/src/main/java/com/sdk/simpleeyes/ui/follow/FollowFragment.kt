package com.sdk.simpleeyes.ui.follow

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.jennifer.andy.simpleeyes.ui.base.adapter.BaseDataAdapter
import com.jennifer.andy.simpleeyes.widget.CustomLoadMoreView
import com.sdk.simpleeyes.R
import com.sdk.simpleeyes.entity.AndyInfo
import com.sdk.simpleeyes.ui.base.BaseAppCompatFragment
import com.sdk.simpleeyes.ui.base.BaseFragment
import com.sdk.simpleeyes.ui.follow.presenter.FollowPresenter
import com.sdk.simpleeyes.ui.follow.view.FollowView
import com.sdk.simpleeyes.ui.search.SearchHotActivity
import com.sdk.simpleeyes.utils.bindView
import com.sdk.simpleeyes.utils.readyGo
import com.sdk.simpleeyes.utils.setHeightAndPadding
import com.sdk.simpleeyes.widget.font.CustomFontTextView
import com.sdk.simpleeyes.widget.pull.refresh.PullToRefreshRecyclerView

class FollowFragment : BaseFragment<FollowView,FollowPresenter>(),FollowView {

    private val mFollowTitleFl : FrameLayout by bindView(R.id.follow_title_fl)
    private val mTvAllAuthor: CustomFontTextView by bindView(R.id.tv_all_author)
    private val mIvSearch: ImageView by bindView(R.id.iv_search)
    private val mRecyclerView: PullToRefreshRecyclerView by bindView(R.id.rv_follow_recycler)
    private var mAdapter: BaseDataAdapter? = null

    companion object {
        @JvmStatic
        fun newInstance(): FollowFragment = FollowFragment()
    }

    override fun getContentViewLayoutId(): Int = R.layout.fragment_follow

    override fun initView(savedInstanceState: Bundle?) {

        mPresenter.getFollowInfo()

        _mActivity.setHeightAndPadding(mFollowTitleFl)

        //跳转到搜索界面
        mIvSearch.setOnClickListener {
            readyGo<SearchHotActivity>()
        }

        //跳转到全部作者界面
        mTvAllAuthor.setOnClickListener {
            readyGo<AllAuthorActivity>()
        }

        mRecyclerView.refreshListener = {
            mPresenter.refresh()
        }

    }

    override fun loadFollowInfoSuccess(andyInfo: AndyInfo) {
        if (mAdapter == null) {
            mAdapter = BaseDataAdapter(andyInfo.itemList)
            mAdapter?.setLoadMoreView(CustomLoadMoreView())
            mAdapter?.setOnLoadMoreListener({ mPresenter.loadMoreInfo() }, mRecyclerView.rootView)

            mRecyclerView.setAdapterAndLayoutManager(mAdapter!!, LinearLayoutManager(context))
        } else {
            mAdapter?.setNewData(andyInfo.itemList)
        }
    }

    override fun refreshSuccess(andyInfo: AndyInfo) {
        loadFollowInfoSuccess(andyInfo)
        mRecyclerView.refreshComplete()
    }

    override fun loadMoreSuccess(data: AndyInfo) {
        mAdapter?.addData(data.itemList)
        mAdapter?.loadMoreComplete()
    }

    override fun showNoMore() {
        mAdapter?.loadMoreEnd()
    }
}