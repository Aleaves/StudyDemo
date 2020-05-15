package com.sdk.simpleeyes.utils

import com.sdk.simpleeyes.ui.base.model.BaseModel
import com.sdk.simpleeyes.ui.base.presenter.BasePresenter
import java.lang.reflect.ParameterizedType

fun <T> getGenericInstance(any: Any, index: Int): T {
    try {
        val type = any.javaClass.genericSuperclass as ParameterizedType
        val clazz = type.actualTypeArguments[index] as Class<T>
        val instance = clazz.newInstance()
        return if (instance is BasePresenter<*> || instance is BaseModel) {
            instance
        } else {
            throw IllegalStateException("if you use mvp user must support generic!!!")
        }
    } catch (e: Exception) {
        e.printStackTrace()
        throw IllegalStateException("translate fail!!")
    }
}