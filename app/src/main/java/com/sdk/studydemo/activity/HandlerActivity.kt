package com.sdk.studydemo.activity

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.sdk.studydemo.R

class HandlerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)
    }

    private val mHandler : Handler = object : Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            sendEmptyMessageDelayed(1,1000)
        }
    }



}