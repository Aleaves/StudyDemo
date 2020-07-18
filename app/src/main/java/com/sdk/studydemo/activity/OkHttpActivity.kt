package com.sdk.studydemo.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sdk.studydemo.R
import com.sdk.studydemo.utils.DanaConfig
import com.sdk.studydemo.utils.DanaEntryUtils
import com.sdk.studydemo.utils.MD5Utils
import com.sdk.studydemo.utils.xxtea.XXTEA
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.internal.cache.CacheInterceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.math.sign


class OkHttpActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)
    }

    private fun request() {
//        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> KyLog.i(message) })
//        //设置日志打印级别
//        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.i("===========", it)
        })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val cacheFile = File(externalCacheDir, "cache")
        val cacheSize = 10 * 1024 * 1024L
        val cache = Cache(cacheFile,cacheSize)

        var okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .build()

        var jsonArray: JSONArray = JSONArray()
        var jsonObject: JSONObject = JSONObject()
        jsonObject.put("project", DanaConfig.TOPIC)
        jsonObject.put("did", DanaConfig.DID)
        jsonObject.put("ouid", DanaConfig.OUID)
        jsonObject.put("timestamp", System.currentTimeMillis())
        jsonObject.put("eventid", DanaEntryUtils.getEventId("login", DanaConfig.DID))
        jsonObject.put("event", "login")
        var proJson: JSONObject = JSONObject()
        proJson.put("app_id", "313")
        proJson.put("ad_id", "1")
        proJson.put("plat", "")
        proJson.put("app_key", DanaConfig.APPKEY)
        proJson.put("channel", "")
        proJson.put("sdk_did", DanaConfig.DID)
        proJson.put("gameversion", "1.0.0")
        proJson.put("mac", "1")
        proJson.put("ssaid", "1")
        proJson.put("bundle_id", "com.sdk.studydemo")
        proJson.put("gamename", "1")
        proJson.put("terminal", "Android")
        proJson.put("lang", "cn")
        proJson.put("_area", "cn")
        proJson.put("_model", Build.MODEL)
        proJson.put("_brand", Build.BRAND)
        proJson.put("_nettype", "4G")
        proJson.put("_imsi", "1123")
        proJson.put("_res", "1080*1920")
        proJson.put("_osver", Build.VERSION.SDK_INT)
        proJson.put("_sdk", "Android")
        proJson.put("_sdkserver", "3.3.2")
        proJson.put("_appver", "1.0.0")
        proJson.put("roleid", "1233")
        proJson.put("rolename", "1233")
        proJson.put("_serverid", "1233")
        proJson.put("param1", "1233")
        proJson.put("param2", "1233")

        jsonObject.put("properties", proJson)
        jsonArray.put(jsonObject)

        Log.i("==========", jsonArray.toString())

        var json = jsonArray.toString()

        var isCompress = true

        if (isCompress) {
            json = DanaEntryUtils.encodeData(json)
        } else {
            json = XXTEA.encryptToBase64String(json, DanaConfig.TOKEN)
        }
        var entry = if (isCompress) "encryption/x-gzip" else "encryption/json"
        val type = entry.toMediaType()
        val body = RequestBody.create(type, json)
        var request = Request.Builder().url("http://kdclog.kingnetdc.com/dana").post(body)
                .header("User-Agent", "DanaAnalytics Android SDK")
                .header("Dry-Run", "true")
                .header("Authorization", DanaEntryUtils.getAuthorizationString(json))
                //.header("Content-Type", if (isCompress) "encryption/x-gzip" else "encryption/json")
                .header("Version", DanaConfig.INTERFACE_VERSION)
                .header("Topic", DanaConfig.TOPIC).build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("============1", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                Log.i("============2", response.body?.string())
                Log.i("============12", response.cacheResponse.toString());
                Log.i("============22", response.networkResponse.toString());
            }

        })
    }

    fun startRequest(view: View) {
        request()
    }

    fun startCache(view: View) {

        val file = File(externalCacheDir,"cache")
        val cacheSize = 10*1024*1024L
        val cache = Cache(file,cacheSize)

        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.i("===========", it)
        })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val cacehControl = CacheControl.Builder().onlyIfCached()

        val client = OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                //.addInterceptor(CacheInterceptor())
                .build()

        val request = Request.Builder().url("https://publicobject.com/helloworld.txt")
                //.cacheControl(cacehControl)
                .build()

        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                Log.i("============12", response.cacheResponse.toString());
                Log.i("============22", response.networkResponse.toString());
            }
        })
    }

    fun startInit(view: View) {

        val file = File(externalCacheDir,"cache")
        val cacheSize = 10*1024*1024L
        val cache = Cache(file,cacheSize)

        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.i("===========", it)
        })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .cookieJar(CookieJar.NO_COOKIES)
                //.addInterceptor(CacheInterceptor())
                .build()

        var map = mapOf<String,String>(
                "uuid" to "KY123456",
                "macaddr" to "mac123",
                "ad" to "1",
                "appid" to "20",
                "apk_name" to "123",
                "device_name" to Build.MODEL,
                "sdk_int" to "28",
                "miui_version" to "1",
                "manufacturer" to Build.MANUFACTURER,
                "sdk_version" to "3.3.2",
                "imsi" to "",
                "new_dana_data" to "1")

        var builder = FormBody.Builder()

        for (entry in map) {
            builder.add(entry.key,entry.value)
        }
        builder.add("sign",MD5Utils.getSafeSign(map,"y6Se+mmV@^Z+LqD-"))

        val cacehControl = CacheControl.Builder().maxAge(30,TimeUnit.SECONDS).build()
        val request = Request.Builder().post(builder.build()).url("https://adapi.mg3721.com/dana/useEncrypt")
                .cacheControl(cacehControl)
                .build()

        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                //Log.i("============2", response.body?.string())
                Log.i("============", "cacheResponse:"+response.cacheResponse.toString());
                Log.i("============", "networkResponse:"+response.networkResponse.toString());
            }
        })

    }


}