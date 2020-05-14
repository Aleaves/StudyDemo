package com.sdk.simpleeyes.manager

import android.app.Activity

class ActivityManager private constructor() {

    private val sActivityList = ArrayList<Activity>()

    companion object {
        fun getInstance(): ActivityManager {
            return BaseAppManagerHolder.instance
        }
    }

    private object BaseAppManagerHolder {
        val instance = ActivityManager()
    }

    /**
     * 当前activity的个数
     */
    val mActivitySize: Int get() = sActivityList.size

    /**
     * 栈顶Activity
     */
    val mForwardActivity: Activity? @Synchronized get() = if (mActivitySize > 0) sActivityList[mActivitySize - 1] else null


    /**
     * 移除activity
     */
    @Synchronized
    fun removeActivity(activity: Activity) {
        if (activity in sActivityList) {
            sActivityList.remove(activity)
        }
    }

    /**
     * 添加相应的activity
     */
    @Synchronized
    fun addActivity(activity: Activity) {
        sActivityList.add(activity)
    }

    /**
     * 清除栈内activity
     */
    fun clear() {
        var i = sActivityList.size - 1
        while (i > -1) {
            val activity = sActivityList[i]
            removeActivity(activity)
            activity.finish()
            i = sActivityList.size
            i--
        }
    }

    /**
     * 清除栈顶activity
     */
    @Synchronized
    fun clearToTop() {
        var i = sActivityList.size - 2
        while (i > -1) {
            val activity = sActivityList[i]
            removeActivity(activity)
            activity.finish()
            i = sActivityList.size - 1
            i--
        }
    }


    /**
     * 获取最上层的Activity
     */
    @Synchronized
    fun getTopActivity(): Activity? {
        return if (sActivityList.isNotEmpty()) {
            sActivityList[sActivityList.size - 1]
        } else {
            null
        }

    }


}