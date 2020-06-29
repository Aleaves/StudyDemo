package com.sdk.simpleeyes.ui.base.model

import com.sdk.simpleeyes.entity.AndyInfo
import com.sdk.simpleeyes.entity.JenniferInfo
import com.sdk.simpleeyes.net.Api
import com.sdk.simpleeyes.rx.RxThreadHelper
import com.sdk.simpleeyes.rx.error.globalHandleError
import io.reactivex.Observable

interface BaseModel {

    /**
     * 加载更多信息，针对于AndyInfo数据类型
     */
    fun loadMoreAndyInfo(nextPageUrl: String?): Observable<AndyInfo> = Api.getDefault()
            .getMoreAndyInfo(nextPageUrl)
            .compose(globalHandleError())
            .compose(RxThreadHelper.switchObservableThread())

    /**
     * 加载更多信息，针对于JenniferInfo数据类型
     */
    fun loadMoreJenniferInfo(nextPageUrl: String?): Observable<JenniferInfo> = Api.getDefault()
            .getMoreJenniferInfo(nextPageUrl)
            .compose(globalHandleError())
            .compose(RxThreadHelper.switchObservableThread())

    /**
     * 根据url,获取数据
     */
    fun getDataInfoFromUrl(url: String?): Observable<AndyInfo> = Api.getDefault()
            .getDataInfoFromUrl(url)
            .compose(globalHandleError())
            .compose(RxThreadHelper.switchObservableThread())

}