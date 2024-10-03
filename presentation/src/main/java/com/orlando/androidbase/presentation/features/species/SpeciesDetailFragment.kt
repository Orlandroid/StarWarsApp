package com.orlando.androidbase.presentation.features.species

import androidx.navigation.fragment.navArgs
import com.orlando.androidbase.entities.remote.ResultSpecie
import com.orlando.androidbase.presentation.base.BaseFragment
import com.orlando.androidbase.presentation.extensions.fromJson
import com.orlando.androidbase.presentation.extensions.loadUrl
import com.orlando.androidbase.presentation.features.MainActivity
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getSpeciesImages
import com.orlando.androidbase.R
import com.orlando.androidbase.databinding.FragmentSpeciesDetailBinding


class SpeciesDetailFragment :
    BaseFragment<FragmentSpeciesDetailBinding>(R.layout.fragment_species_detail) {


    private val args: SpeciesDetailFragmentArgs by navArgs()

    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true,
        toolbarTitle = getString(R.string.detail)
    )


    override fun setUpUi() {
        bind(args.specie.fromJson())
    }

    private fun bind(specie: ResultSpecie) = with(binding) {
        tvName.text = specie.name
        tvClasification.text = specie.classification
        tvSkinColor.text = specie.skin_colors
        tvAvarageLifespan.text = specie.average_lifespan
        imagePlanet.loadUrl(getImageFromJson(specie.name, getSpeciesImages()))
    }


}