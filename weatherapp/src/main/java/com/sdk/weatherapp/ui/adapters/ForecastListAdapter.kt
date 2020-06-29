package com.sdk.weatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sdk.weatherapp.R
import com.sdk.weatherapp.data.server.Forecast
import com.sdk.weatherapp.extensions.ctx
import com.sdk.weatherapp.extensions.toDateString
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_forecast.view.*

class ForecastListAdapter(private val weekForecast: List<Forecast>, private val itemClick: (Forecast) -> Unit) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int = weekForecast.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    class ViewHolder(override val containerView: View, private val itemClick: (Forecast) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindForecast(forecast: Forecast) {

            with(forecast) {
                //Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.dateText.text = (dt * 1000).toDateString()
                itemView.descriptionText.text = speed.toString()
                itemView.maxTemperature.text = "${humidity}º"
                itemView.minTemperature.text = "${pressure}º"
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }

}