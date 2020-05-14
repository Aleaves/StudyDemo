package com.sdk.weatherapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdk.weatherapp.R
import com.sdk.weatherapp.ui.adapters.RecyclerListAdapter
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerViewActivity : AppCompatActivity() {

    private val dataLists = listOf("1","2","3","4","5","6","7","8","9","10")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        val adapter = RecyclerListAdapter(dataLists){
            println("======$it")
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        dataLists.forEach {
            println("=========$it")
        }

    }
}