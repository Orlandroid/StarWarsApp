package com.example.androidbase.presentation.ui.starships

import androidx.navigation.fragment.navArgs
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentStarShipDetailBinding
import com.example.androidbase.entities.remote.ResultStarship
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.fromJson
import com.example.androidbase.presentation.extensions.loadUrl
import com.example.androidbase.presentation.ui.MainActivity
import com.example.androidbase.presentation.util.getImageFromJson
import com.example.androidbase.presentation.util.utilimages.data.getStartShipsImages


class StarShipDetailFragment :
    BaseFragment<FragmentStarShipDetailBinding>(R.layout.fragment_star_ship_detail) {

    private val args: StarShipDetailFragmentArgs by navArgs()

    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true,
        toolbarTitle = "Detail"
    )

    override fun setUpUi() {
        bind(args.startShip.fromJson())
    }

    private fun bind(planet: ResultStarship) = with(binding) {
        tvName.text = planet.name
        tvModel.text = planet.model
        tvManufacturer.text = planet.manufacturer
        tvMaxSpeed.text = planet.max_atmosphering_speed
        imageStartShip.loadUrl(getImageFromJson(planet.name, getStartShipsImages()))
    }

}