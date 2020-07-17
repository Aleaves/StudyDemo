package com.sdk.studydemo.activity

import okhttp3.Interceptor
import okhttp3.Response

class CacheInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val originResponse = chain.proceed(chain.request())

        //设置缓存时间为60秒，并移除了pragma消息头，移除它的原因是因为pragma也是控制缓存的一个消息头属性
        return originResponse.newBuilder()
                .header("Cache-Control","no-cache")
                .build()
    }
}