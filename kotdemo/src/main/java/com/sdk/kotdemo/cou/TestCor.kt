package com.sdk.kotdemo.cou

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun main() {
    var cor = Cor()
    cor.testCor()

    GlobalScope.launch(Dispatchers.IO) {

    }
}