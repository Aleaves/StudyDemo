package com.sdk.studydemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sdk.studydemo.activity.HandlerActivity
import com.sdk.studydemo.camera.CameraActivty

class AppMainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_main)
    }

    fun startCamera(view: View) {
        startActivity(Intent(this,CameraActivty::class.java))
    }

    fun startHandler(view: View) {
        startActivity(Intent(this,HandlerActivity::class.java))
    }

    fun startCrash(view: View) {

    }

}