package com.sdk.weatherapp.ui.activity

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdk.weatherapp.R
import com.sdk.weatherapp.data.server.ForecastByZipCodeRequest
import com.sdk.weatherapp.extensions.DelegatesExt
import com.sdk.weatherapp.extensions.color
import com.sdk.weatherapp.ui.adapters.ForecastListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import org.jetbrains.anko.find
import kotlin.properties.Delegates

class MainActivity : CoroutineScopeActivity(), ToolbarManager {

    private val zipCode: Long by DelegatesExt.preference(this, SettingsActivity.ZIP_CODE, SettingsActivity.DEFAULT_ZIP)

    var progress: String by Delegates.observable("1") { property, oldValue, newValue ->
        println("$oldValue == $newValue")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initToolbar()
        forecastList.layoutManager = LinearLayoutManager(this)
        attachToScroll(forecastList)
        loadForecast()
        bt_progress.setOnClickListener {
            progress = "1222"
        }
        val map = HashMap<String,String>()
        val map1 = mutableMapOf<String,String>()
        map["name"] = ""
    }


    override val toolbar by lazy {
        find<Toolbar>(R.id.toolbar)
    }

    private fun loadForecast() {
        launch {
            val request = ForecastByZipCodeRequest(94043L)
            var result = request.execute()
            println("11111${result.toString()}")
            var adapter = ForecastListAdapter(result.list) {

            }
            println("============111")
            runOnUiThread {
                forecastList.adapter = adapter
            }
        }
    }
}
