package com.sdk.weatherapp.domain.datasource

class ForecastProvider(private val sources : List<ForecastDataSource>) {

    companion object{
        const val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES by lazy {
            //listOf()
        }
    }

}