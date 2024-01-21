package com.orlando.androidbase.presentation.ui.starships

import androidx.navigation.fragment.navArgs
import com.orlando.androidbase.entities.remote.ResultStarship
import com.orlando.androidbase.presentation.base.BaseFragment
import com.orlando.androidbase.presentation.extensions.fromJson
import com.orlando.androidbase.presentation.extensions.loadUrl
import com.orlando.androidbase.presentation.ui.MainActivity
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getStartShipsImages
import com.orlando.androidbase.R
import com.orlando.androidbase.databinding.FragmentStarShipDetailBinding


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