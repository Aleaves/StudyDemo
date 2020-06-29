package com.sdk.simpleeyes.ui.follow.presenter

import android.view.View
import com.sdk.simpleeyes.entity.AndyInfo
import com.sdk.simpleeyes.ui.base.presenter.LoadMorePresenter
import com.sdk.simpleeyes.ui.follow.model.FollowModel
import com.sdk.simpleeyes.ui.follow.view.FollowView
import com.uber.autodispose.autoDispose

class FollowPresenter: LoadMorePresenter<AndyInfo,FollowModel,FollowView>() {

    override var mBaseModel: FollowModel = FollowModel()

    fun getFollowInfo() {
        mBaseModel.getFollowInfo().autoDispose(mScopeProvider).subscribe({
            mView?.showContent()
            mNextPageUrl = it.nextPageUrl
            mView?.loadFollowInfoSuccess(it)
        }, {
            mView?.showNetError(View.OnClickListener { getFollowInfo() })
        })
    }

    fun refresh() {
        mBaseModel.getFollowInfo().autoDispose(mScopeProvider).subscribe({
            mView?.showContent()
            mNextPageUrl = it.nextPageUrl
            mView?.refreshSuccess(it)
        }, {
            mView?.showNetError(View.OnClickListener {
                refresh()
            })
        })
    }

}