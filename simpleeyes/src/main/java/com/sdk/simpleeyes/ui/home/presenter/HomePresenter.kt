package com.sdk.simpleeyes.ui.home.presenter

import android.view.View
import com.sdk.simpleeyes.ui.base.presenter.BasePresenter
import com.sdk.simpleeyes.ui.home.model.HomeModel
import com.sdk.simpleeyes.ui.home.view.HomeView
import com.uber.autodispose.autoDispose

class HomePresenter : BasePresenter<HomeView>() {

    private var mHomeModel: HomeModel = HomeModel()
    private var mNextPageUrl: String? = null

    fun loadCategoryData() {
        mView?.showLoading()
        mHomeModel.loadCategoryInfo().autoDispose(mScopeProvider)
                .subscribe({
                    mView?.showContent()
                    mNextPageUrl = it.nextPageUrl
                    mView?.loadDataSuccess(it)
                }, {
                    mView?.showNetError(View.OnClickListener {
                        loadCategoryData()
                    })
                })
    }

    fun refreshCategoryData(){
        mHomeModel.refreshCategoryInfo().autoDispose(mScopeProvider)
                .subscribe ({
                    mView?.showContent()
                    mNextPageUrl = it.nextPageUrl
                    mView?.refreshDataSuccess(it)
                },{
                    mView?.showNetError(View.OnClickListener {
                        refreshCategoryData()
                    })
                })
    }

    fun loadMoreCategoryData(){
        if (mNextPageUrl != null) {
            mHomeModel.loadMoreAndyInfo(mNextPageUrl).autoDispose(mScopeProvider).subscribe({
                mView?.showContent()
                if (it.nextPageUrl == null) {
                    mView?.showNoMore()
                } else {
                    mNextPageUrl = it.nextPageUrl
                    mView?.loadMoreSuccess(it)
                }
            }, {
                mView?.showNetError(View.OnClickListener {
                    loadMoreCategoryData()
                })
            })
        }
    }

}