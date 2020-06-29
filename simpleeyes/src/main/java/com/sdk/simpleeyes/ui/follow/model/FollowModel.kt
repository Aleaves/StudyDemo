package com.sdk.simpleeyes.ui.follow.model

import com.sdk.simpleeyes.entity.AndyInfo
import com.sdk.simpleeyes.net.Api
import com.sdk.simpleeyes.rx.RxThreadHelper
import com.sdk.simpleeyes.rx.error.globalHandleError
import com.sdk.simpleeyes.ui.base.model.BaseModel
import io.reactivex.Observable

class FollowModel : BaseModel{

    /**
     * 获取关注首页信息
     */
    fun getFollowInfo(): Observable<AndyInfo> =
            Api.getDefault()
                    .getFollowInfo()
                    .compose(globalHandleError())
                    .compose(RxThreadHelper.switchObservableThread())

    /**
     * 获取全部作者
     */
    fun getAllAuthor(): Observable<AndyInfo> =
            Api.getDefault()
                    .getAllAuthor()
                    .compose(globalHandleError())
                    .compose(RxThreadHelper.switchObservableThread())

}