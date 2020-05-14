package com.sdk.weatherapp.domain.datasource

import com.sdk.weatherapp.domain.model.Forecast
import com.sdk.weatherapp.domain.model.ForecastList

interface ForecastDataSource {

    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?

}