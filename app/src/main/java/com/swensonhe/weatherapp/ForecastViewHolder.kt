package com.swensonhe.weatherapp

import androidx.recyclerview.widget.RecyclerView
import com.swensonhe.weatherapp.databinding.ItemForecastBinding
import com.swensonhe.weatherapp.model.ForecastItem

/**
 * Normally it's better to have a `BaseViewHolder` class and inherit our viewholders from it,
 * but for the sake of this assignment, I'm using the [RecyclerView.ViewHolder] directly.
 * */
class ForecastViewHolder(
    private val binding: ItemForecastBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ForecastItem) = with(binding) {
        when(item.weatherType){
            else -> {}
        }
        itemForecastTempLabel.text = root.context.getString(
            R.string.forecast_temp_placeholder,
            item.minTemperature,
            item.maxTemperature
        )
        itemForecastDayLabel.text = item.day
    }
}








