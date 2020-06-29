package com.sdk.simpleeyes.ui.feed

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jennifer.andy.simpleeyes.ui.base.adapter.BaseDataAdapter
import com.jennifer.andy.simpleeyes.widget.CustomLoadMoreView
import com.sdk.simpleeyes.R
import com.sdk.simpleeyes.entity.AndyInfo
import com.sdk.simpleeyes.net.Extras
import com.sdk.simpleeyes.ui.base.BaseAppCompatFragment
import com.sdk.simpleeyes.ui.base.BaseFragment
import com.sdk.simpleeyes.ui.feed.presenter.TagDetailInfoPresenter
import com.sdk.simpleeyes.ui.feed.view.TagDetailInfoView
import com.sdk.simpleeyes.utils.bindView

class TagDetailInfoFragment : BaseFragment<TagDetailInfoView,TagDetailInfoPresenter>(),TagDetailInfoView {

    private val mRecyclerView: RecyclerView by bindView(R.id.rv_recycler)
    private var mAdapter: BaseDataAdapter? = null

    private lateinit var mApiUrl: String

    companion object{
        @JvmStatic
        fun newInstance(apiUrl : String) : TagDetailInfoFragment{
            val categoryFragment = TagDetailInfoFragment()
            val bundle = Bundle()
            bundle.putString(Extras.API_URL,apiUrl)
            categoryFragment.arguments = bundle
            return categoryFragment
        }
    }

    override fun getContentViewLayoutId(): Int = R.layout.fragment_tag_detail_info

    override fun getBundleExtras(extras: Bundle) {
        mApiUrl = extras.getString(Extras.API_URL)!!
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        mPresenter.getDetailInfo(mApiUrl)
    }

    override fun showGetTabInfoSuccess(andyInfo: AndyInfo) {
        if (mAdapter == null) {
            mAdapter = BaseDataAdapter(andyInfo.itemList)
            mAdapter?.setLoadMoreView(CustomLoadMoreView())
            mAdapter?.setOnLoadMoreListener({ mPresenter.loadMoreInfo() }, mRecyclerView)
            mRecyclerView.adapter = mAdapter
            mRecyclerView.layoutManager = LinearLayoutManager(context)
        } else {
            mAdapter?.setNewData(andyInfo.itemList)
        }
    }

    override fun loadMoreSuccess(data: AndyInfo) {
        mAdapter?.addData(data.itemList)
        mAdapter?.loadMoreComplete()
    }

    override fun showNoMore() {
        mAdapter?.loadMoreEnd()
    }
}