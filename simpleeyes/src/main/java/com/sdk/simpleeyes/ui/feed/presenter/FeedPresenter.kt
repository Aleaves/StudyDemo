package com.sdk.simpleeyes.ui.feed.presenter

import android.view.View
import com.sdk.simpleeyes.ui.base.presenter.BasePresenter
import com.sdk.simpleeyes.ui.feed.model.FeedModel
import com.sdk.simpleeyes.ui.feed.view.FeedView
import com.uber.autodispose.autoDispose

class FeedPresenter : BasePresenter<FeedView>() {

    private var mFeedModel : FeedModel = FeedModel()

    fun getDiscoveryTab(){
        mFeedModel.getDiscoveryTab().autoDispose(mScopeProvider)
                .subscribe({
                    mView?.showContent()
                    mView?.loadTabSuccess(it.tabInfo)
                },{
                    mView?.showNetError(View.OnClickListener {
                        getDiscoveryTab()
                    })
                })
    }

}