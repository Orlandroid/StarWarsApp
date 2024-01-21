package com.orlando.androidbase.presentation.ui.planets

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.orlando.androidbase.entities.remote.ResultPlanet
import com.orlando.androidbase.presentation.extensions.click
import com.orlando.androidbase.presentation.extensions.loadUrl
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getPlanetsImages
import com.orlando.androidbase.databinding.ItemPlanetBinding


class PlanetsAdapter(private val clickOnPlanet: (ResultPlanet) -> Unit) :
    PagingDataAdapter<ResultPlanet, PlanetsAdapter.ViewHolder>(PlanetComparator) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPlanetBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(private val binding: ItemPlanetBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(planet: ResultPlanet) = with(binding) {
            binding.root.click {
                clickOnPlanet(planet)
            }
            tvName.text = planet.name
            tvPopulation.text = planet.population
            tvClimate.text = planet.climate
            tvTerrain.text = planet.terrain
            image.loadUrl(getImageFromJson(planet.name, getPlanetsImages()))
        }
    }


    object PlanetComparator : DiffUtil.ItemCallback<ResultPlanet>() {
        override fun areItemsTheSame(oldItem: ResultPlanet, newItem: ResultPlanet): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ResultPlanet, newItem: ResultPlanet): Boolean {
            return oldItem == newItem
        }
    }


}