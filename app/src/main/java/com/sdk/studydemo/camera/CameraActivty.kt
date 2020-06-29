package com.sdk.studydemo.camera

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sdk.studydemo.R
import kotlinx.android.synthetic.main.activity_camera_activty.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class CameraActivty : AppCompatActivity() {


    val TAG = "================"
    var photoUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_activty)
        initView()
        checkPermissionAndCamera()

        getExternalPhonePath()

        getInnerPath();

    }

    private fun getExternalPhonePath() {
        //判断是否有外置存储
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            //安卓10.0之后  就不能访问外部存储路径
            //如果targetSdkVersion小于29   requestLegacyExternalStorage  安卓10还是可以创建外部存储
            Log.i(TAG, "外部存储正常")
            var storagePath = Environment.getExternalStorageDirectory()
            Log.i(TAG, storagePath.absolutePath)  ///storage/emulated/0
            //Environment.DIRECTORY_MOVIES
            //Environment.DIRECTORY_DCIM  公共存储路径
            var publicStoragePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) ///storage/emulated/0/Movies
            Log.i(TAG, publicStoragePath.absolutePath)
        }

    }

    private fun getInnerPath() {
        // /data/user/0/com.sdk.studydemo/cache  在应用安装目录下
        Log.i(TAG, cacheDir.absolutePath)
        // /storage/emulated/0/Android/data/com.sdk.studydemo/cache
        // 在Android/data/包名/cache
        Log.i(TAG, externalCacheDir?.absolutePath)

        ///data/user/0/com.sdk.studydemo/files
        Log.i(TAG, filesDir.absolutePath)

        ///storage/emulated/0/Android/data/com.sdk.studydemo/files/Pictures
        Log.i(TAG, getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.absolutePath)
    }

    private fun initView() {
        take_photo.setOnClickListener {
            openCamera()
        }
    }

    private fun checkPermissionAndCamera() {
        var hasCameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        if (hasCameraPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 100)
        }
    }

    private fun openCamera() {
        var captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        //判断是否有相机
        if (captureIntent.resolveActivity(packageManager) != null) {
            var photoFile: File


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                photoUri = createImageUri()
            }else{
                photoFile = createImageFile()
            }

            Log.i(TAG,photoUri.toString())
            if(photoUri != null){
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri)
                captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                startActivityForResult(captureIntent,10)
            }


        }
    }

    private fun createImageFile() :File {
        var imageName = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        var storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if(storageDir?.exists()!!){
            storageDir.mkdir()
        }

        var tempFile = File(storageDir,imageName)
        return tempFile

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 10 && resultCode == Activity.RESULT_OK){
            iv_photo.setImageURI(photoUri)
        }
    }

    private fun createImageUri(): Uri? {
        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ContentValues())
    }

}
