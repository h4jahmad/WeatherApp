package com.swensonhe.weatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.swensonhe.weatherapp.databinding.ItemForecastBinding
import com.swensonhe.weatherapp.domain.usecase.datetime.ForecastDayType
import com.swensonhe.weatherapp.model.UiForecastDay

class ForecastAdapter : ListAdapter<UiForecastDay, ForecastViewHolder>(
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
        val COMPARATOR = object : DiffUtil.ItemCallback<UiForecastDay>() {
            override fun areItemsTheSame(oldItem: UiForecastDay, newItem: UiForecastDay): Boolean =
                oldItem.date == newItem.date

            override fun areContentsTheSame(
                oldItem: UiForecastDay,
                newItem: UiForecastDay
            ): Boolean =
                oldItem == newItem
        }
    }
}

/**
 * Normally it's better to have a `BaseViewHolder` class and inherit our viewholders from it,
 * but for the sake of this assignment, I'm using the [RecyclerView.ViewHolder] directly.
 * */
class ForecastViewHolder(
    private val binding: ItemForecastBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: UiForecastDay) = with(binding) {
        itemForecastIcon.load(item.condition.icon)
        itemForecastTempLabel.text = root.context.getString(
            R.string.forecast_temp_placeholder,
            item.mintempF,
            item.maxtempF
        )
        with(itemForecastDayLabel) {
            when (val label = item.dayLabel) {
                is ForecastDayType.Name -> text = label.name
                ForecastDayType.Today -> setText(R.string.today)
                ForecastDayType.Tomorrow -> setText(R.string.tomorrow)
            }
        }

    }
}