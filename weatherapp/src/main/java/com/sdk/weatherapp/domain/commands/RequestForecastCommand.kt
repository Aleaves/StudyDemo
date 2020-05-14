package com.sdk.weatherapp.domain.commands

import com.sdk.weatherapp.domain.datasource.ForecastProvider
import com.sdk.weatherapp.domain.model.ForecastList

class RequestForecastCommand(private val zipCode: Long, private val forecastProvider: ForecastProvider ): Command<ForecastList>{
    override suspend fun execute(): ForecastList {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}