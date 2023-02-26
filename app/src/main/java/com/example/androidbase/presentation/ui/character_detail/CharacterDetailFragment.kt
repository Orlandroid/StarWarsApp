package com.example.androidbase.presentation.ui.character_detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentCharacterDetailBinding
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.click
import com.example.androidbase.presentation.extensions.loadUrl
import com.example.domain.entities.remote.PeopleResponseItem
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding>(R.layout.fragment_character_detail) {

    private val args: CharacterDetailFragmentArgs by navArgs()
    private var peopleItemWithImage: PeopleResponseItem? = null


    override fun setUpUi() {
        binding.toolbarLayout.toolbarBack.click {
            findNavController().popBackStack()
        }
        val gson = Gson()
        peopleItemWithImage = gson.fromJson(args.characterWithImage, PeopleResponseItem::class.java)
        peopleItemWithImage?.let {
            bind(it)
        }
    }

    private fun bind(people: PeopleResponseItem) = with(binding) {
        characterImage.loadUrl(people.image)
        tvHeight.text = people.height.toString()
        tvHairColor.text = people.hairColor
        tvHomeworld.text = people.homeworld
        tvCharacterName.text = people.name
        tvGender.text = people.gender
        tvMass.text = people.mass.toString()
        tvSkinColor.text = people.skinColor
    }

}