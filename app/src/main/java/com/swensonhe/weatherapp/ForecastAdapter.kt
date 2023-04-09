package com.swensonhe.weatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.swensonhe.weatherapp.databinding.ItemForecastBinding
import com.swensonhe.weatherapp.model.ForecastItem

class ForecastAdapter : ListAdapter<ForecastItem, ForecastViewHolder>(
    AsyncDifferConfig.Builder(COMPARATOR).build()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder =
        ForecastViewHolder(
            ItemForecastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<ForecastItem>() {
            override fun areItemsTheSame(oldItem: ForecastItem, newItem: ForecastItem): Boolean =
                oldItem.day == newItem.day

            override fun areContentsTheSame(oldItem: ForecastItem, newItem: ForecastItem): Boolean =
                oldItem == newItem
        }
    }
}