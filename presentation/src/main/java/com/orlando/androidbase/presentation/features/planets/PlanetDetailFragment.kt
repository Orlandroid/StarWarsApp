package com.orlando.androidbase.presentation.features.planets

import androidx.navigation.fragment.navArgs
import com.orlando.androidbase.entities.remote.ResultPlanet
import com.orlando.androidbase.presentation.base.BaseFragment
import com.orlando.androidbase.presentation.extensions.fromJson
import com.orlando.androidbase.presentation.extensions.loadUrl
import com.orlando.androidbase.presentation.features.MainActivity
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getPlanetsImages
import com.orlando.androidbase.R
import com.orlando.androidbase.databinding.FragmentPlanetDetailBinding

class PlanetDetailFragment :
    BaseFragment<FragmentPlanetDetailBinding>(R.layout.fragment_planet_detail) {

    private val args: PlanetDetailFragmentArgs by navArgs()

    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true,
        toolbarTitle = getString(R.string.detail)
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