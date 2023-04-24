package com.example.androidbase.presentation.ui.planets

import androidx.navigation.fragment.navArgs
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentPlanetDetailBinding
import com.example.androidbase.entities.remote.ResultPlanet
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.fromJson
import com.example.androidbase.presentation.extensions.loadUrl
import com.example.androidbase.presentation.ui.MainActivity
import com.example.androidbase.presentation.util.getImageFromJson
import com.example.androidbase.presentation.util.utilimages.data.getPlanetsImages

class PlanetDetailFragment :
    BaseFragment<FragmentPlanetDetailBinding>(R.layout.fragment_planet_detail) {

    private val args: PlanetDetailFragmentArgs by navArgs()

    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true,
        toolbarTitle = "Detail"
    )


    override fun setUpUi() {
        bind(args.planet.fromJson())
    }

    private fun bind(planet: ResultPlanet) = with(binding) {
        tvName.text = planet.name
        tvClimate.text = planet.climate
        tvGravity.text = planet.gravity
        tvPopulation.text = planet.population
        imagePlanet.loadUrl(getImageFromJson(planet.name, getPlanetsImages()))
    }


}