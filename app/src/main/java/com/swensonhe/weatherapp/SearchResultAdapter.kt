package com.swensonhe.weatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.swensonhe.common.entities.Location
import com.swensonhe.weatherapp.databinding.ItemSearchResultBinding

class SearchResultAdapter(private val onItemClicked: OnItemClicked<Location>) :
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
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean =
                oldItem == newItem
        }
    }

}

class SearchResultViewHolder(
    private val binding: ItemSearchResultBinding,
    private val onItemClicked: OnItemClicked<Location>
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Location) = with(binding) {
        itemSearchResultCityLabel.text = item.name
        itemSearchResultCityRegion.text = item.region
        root.setOnClickListener { onItemClicked(item) }
    }
}