package com.example.androidbase.presentation.ui.planets

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbase.databinding.ItemPlanetBinding
import com.example.androidbase.entities.remote.ResultPlanet
import com.example.androidbase.presentation.extensions.click
import com.example.androidbase.presentation.extensions.loadUrl
import com.example.androidbase.presentation.util.getImageFromJson
import com.example.androidbase.presentation.util.utilimages.data.getPlanetsImages


class PlanetsAdapter(private val clickOnPlanet: (ResultPlanet) -> Unit) :
    RecyclerView.Adapter<PlanetsAdapter.ViewHolder>() {

    private var listOfPlanets: List<ResultPlanet> = arrayListOf()

    fun setData(lista: List<ResultPlanet>) {
        listOfPlanets = lista
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemPlanetBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(planet: ResultPlanet, clickOnPlanet: (ResultPlanet) -> Unit) = with(binding) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPlanetBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfPlanets[position], clickOnPlanet)
    }

    override fun getItemCount() = listOfPlanets.size


}