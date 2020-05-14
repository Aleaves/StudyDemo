package com.sdk.simpleeyes.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager

fun Activity.immersive() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val window = getWindow()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        var visibility = window.decorView.systemUiVisibility
        visibility = visibility or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        visibility = visibility or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        window.decorView.systemUiVisibility = visibility
    } else {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }
}

fun Context.getStatusBarHeigh(): Int {
    val resId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resId > 0) {
        return resources.getDimensionPixelSize(resId)
    }
    return 0;
}

fun Context.setHeightAndPadding(view: View?) {
    view?.let {
        val layoutParams = it.layoutParams
        layoutParams.height += getStatusBarHeigh()
        it.setPadding(it.paddingLeft, it.paddingTop + getStatusBarHeigh(), it.paddingRight, it.paddingBottom)
    }

}