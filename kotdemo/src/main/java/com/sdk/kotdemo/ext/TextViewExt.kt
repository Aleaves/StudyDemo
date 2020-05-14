package com.sdk.kotdemo.ext

import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

fun TextView.setName(name:String){
    this.text = name
}

fun FloatingActionButton.setColor(){

}

fun String.lastChar() :Char{
    return this.get(this.length - 1)
}
