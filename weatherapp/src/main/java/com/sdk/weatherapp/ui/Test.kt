package com.sdk.weatherapp.ui

import com.sdk.weatherapp.data.db.ForecastDbHelper

class Test {

    companion object{
        val instance by lazy { Test() }
    }

    private val suscriptions : List<Suscription> by lazy { ArrayList<Suscription>() }

    private fun  toSubscribe(){
        
    }

    private fun unSubscribe(){
        suscriptions.forEach {
            it?.unSubscribe()
        }
    }

}