package com.sdk.studydemo.camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sdk.studydemo.R
import kotlinx.android.synthetic.main.activity_camera_activty.*

class CameraActivty : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_activty)
        initView()
        checkPermissionAndCamera()
    }

    private fun initView() {
        take_photo.setOnClickListener {

        }
    }

    private fun checkPermissionAndCamera(){
        var hasCameraPermission = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)
        if(hasCameraPermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),100)
        }
    }

    private fun openCamera(){
        var captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

    }

}
