package com.sdk.weatherapp.extensions

import android.content.Context
import androidx.core.content.ContextCompat

fun Context.color(res: Int) = ContextCompat.getColor(this,res)