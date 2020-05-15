package com.sdk.simpleeyes.ui.home.model

import com.sdk.simpleeyes.entity.AndyInfo
import com.sdk.simpleeyes.net.Api
import com.sdk.simpleeyes.rx.RxThreadHelper
import com.sdk.simpleeyes.rx.error.globalHandleError
import com.sdk.simpleeyes.ui.base.model.BaseModel
import io.reactivex.Observable

class HomeModel: BaseModel{
    /**
     * 加载首页信息
     */
    fun loadCategoryInfo(): Observable<AndyInfo> =
            Api.getDefault()
                    .getHomeInfo()
                    .compose(globalHandleError())
                    .compose(RxThreadHelper.switchObservableThread())
}