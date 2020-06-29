package com.sdk.kotdemo.cou

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sdk.kotdemo.R
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CoruActivity : AppCompatActivity() ,CoroutineScope{

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private var job = Job()

    private lateinit var name : String

    private val age by lazy {
        1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var j = launch {
            //delay(3000)
            println("=====delay 3000")
            tv_name.text = "123434"
        }
        println("====no delay")

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}