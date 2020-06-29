package com.sdk.weatherapp.data.db

import com.sdk.weatherapp.domain.datasource.ForecastDataSource
import com.sdk.weatherapp.domain.model.ForecastList
import com.sdk.weatherapp.extensions.byId
import com.sdk.weatherapp.extensions.parseList
import com.sdk.weatherapp.extensions.parseOpt
import org.jetbrains.anko.db.select

class ForecastDb(private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance, private val dataMapper: DbDataMapper = DbDataMapper()) : ForecastDataSource {


    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList {
                    DayForecast(HashMap(it))
                }
        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt {
                    CityForecast(HashMap(it), dailyForecast)
                }

        city?.let { dataMapper.convertToDomain(it) }
    }

    override fun requestDayForecast(id: Long) = forecastDbHelper.use {
        val forecast = select(DayForecastTable.NAME).byId(id).parseOpt { DayForecast(HashMap(it)) }

        forecast?.let { dataMapper.convertDayToDomain(it) }
    }
}