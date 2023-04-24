package com.example.androidbase.presentation.ui.species

import androidx.navigation.fragment.navArgs
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentSpeciesDetailBinding
import com.example.androidbase.entities.remote.ResultSpecie
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.fromJson
import com.example.androidbase.presentation.extensions.loadUrl
import com.example.androidbase.presentation.ui.MainActivity
import com.example.androidbase.presentation.util.getImageFromJson
import com.example.androidbase.presentation.util.utilimages.data.getSpeciesImages


class SpeciesDetailFragment :
    BaseFragment<FragmentSpeciesDetailBinding>(R.layout.fragment_species_detail) {


    private val args: SpeciesDetailFragmentArgs by navArgs()

    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true,
        toolbarTitle = "Detail"
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