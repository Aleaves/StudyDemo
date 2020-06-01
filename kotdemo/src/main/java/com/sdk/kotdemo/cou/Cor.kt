package com.sdk.kotdemo.cou

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class Cor : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    var job = Job()

    fun testCor() {
        launch {
            //delay(3000)
            println("delay 3000")
        }
        println("no delay")
    }

}