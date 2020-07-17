package com.sdk.studydemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sdk.studydemo.activity.HandlerActivity
import com.sdk.studydemo.activity.OkHttpActivity
import com.sdk.studydemo.camera.CameraActivty
import kotlinx.coroutines.*

class AppMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_main)
        Log.e("========", "主线程id：${mainLooper.thread.id}")
        //test()
        var job = GlobalScope.launch(Dispatchers.IO) {
            delay(6000)
            Log.e("=======", "协程执行结束 -- 线程id：${Thread.currentThread().id}")
        }
        Log.e("========", "协程执行结束")
    }

    fun startCamera(view: View) {
        startActivity(Intent(this, CameraActivty::class.java))
    }

    fun startHandler(view: View) {
        startActivity(Intent(this, HandlerActivity::class.java))
    }

    fun startCrash(view: View) {
        startActivity(Intent(this, OkHttpActivity::class.java))
    }

    private fun test() = runBlocking {
        repeat(8) {
            Log.e("========", "协程执行$it 线程id：${Thread.currentThread().id}")
            delay(1000)
        }
    }

}