package com.swensonhe.weatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.swensonhe.common.entities.Location
import com.swensonhe.weatherapp.databinding.ItemSearchResultBinding
import com.swensonhe.weatherapp.model.Coordinates

class SearchResultAdapter(private val onItemClicked: OnItemClicked<Coordinates>) :
    ListAdapter<Location, SearchResultViewHolder>(AsyncDifferConfig.Builder(COMPARATOR).build()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder =
        SearchResultViewHolder(
            ItemSearchResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClicked
        )

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<Location>() {
            override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean =
                oldItem.tzId == newItem.tzId

            override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean =
                oldItem == newItem
        }
    }

}

class SearchResultViewHolder(
    private val binding: ItemSearchResultBinding,
    private val onItemClicked: OnItemClicked<Coordinates>
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Location) = with(binding) {
        itemSearchResultCityLabel.text = item.name
        if (item.region.isNotEmpty()) {
            itemSearchResultCityRegion.text = root.context.getString(
                R.string.place_holder_search_result_region,
                item.region
            )
        }
        root.setOnClickListener {
            onItemClicked(
                Coordinates(
                    lat = item.lat,
                    lon = item.lon
                )
            )
        }
    }
}