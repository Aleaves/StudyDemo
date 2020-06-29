package com.sdk.simpleeyes.ui.feed.presenter

import android.view.View
import com.sdk.simpleeyes.entity.AndyInfo
import com.sdk.simpleeyes.ui.base.presenter.LoadMorePresenter
import com.sdk.simpleeyes.ui.feed.model.FeedModel
import com.sdk.simpleeyes.ui.feed.view.TagDetailInfoView
import com.uber.autodispose.autoDispose

class TagDetailInfoPresenter : LoadMorePresenter<AndyInfo,FeedModel,TagDetailInfoView>() {

    override var mBaseModel : FeedModel = FeedModel()

    fun getDetailInfo(url : String){
        mBaseModel.getDataInfoFromUrl(url).autoDispose(mScopeProvider)
                .subscribe({
                    mView?.showContent()
                    mView?.showGetTabInfoSuccess(it)
                    mNextPageUrl = it.nextPageUrl
                    if (mNextPageUrl == null) mView?.showNoMore()
                },{
                    mView?.showNetError(View.OnClickListener { getDetailInfo(url) })
                })
    }

}