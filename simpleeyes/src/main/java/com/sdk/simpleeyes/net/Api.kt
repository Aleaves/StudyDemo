package com.sdk.simpleeyes.net

object Api {

    /**
     * 主域名
     */
    val BASE_URL: String get() = "https://baobab.kaiyanapp.com/"

    /**
     * 获取默认Service
     */
    fun getDefault() = RetrofitConfig.getDefaultService()

}