package com.example.androidbase.presentation.ui.character_detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentCharacterDetailBinding
import com.example.androidbase.entities.remote.ResultPeople
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.configure
import com.example.androidbase.presentation.extensions.loadUrl
import com.example.androidbase.presentation.extensions.observeApiResult
import com.example.androidbase.presentation.util.getImageFromJson
import com.example.androidbase.presentation.util.utilimages.data.getPeopleImages
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding>(R.layout.fragment_character_detail) {

    private val args: CharacterDetailFragmentArgs by navArgs()
    private var peopleItemWithImage: ResultPeople? = null
    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun setUpUi() {
        configure(binding.toolbarLayout, title = getString(R.string.detalle))
        val gson = Gson()
        peopleItemWithImage = gson.fromJson(args.character, ResultPeople::class.java)
        peopleItemWithImage?.let {
            bind(it)
        }
    }

    private fun bind(people: ResultPeople) = with(binding) {
        characterImage.loadUrl(getImageFromJson(people.name, getPeopleImages()))
        tvHeight.text = people.height
        tvHairColor.text = people.hair_color
        tvHomeworld.text = getHomeWorkId(people.homeworld)
        tvCharacterName.text = people.name
        tvGender.text = people.gender
        tvMass.text = people.mass
        tvSkinColor.text = people.skin_color
    }

    private fun getHomeWorkId(homeWork: String): String {
        return homeWork.takeLast(2).replace("/", "")
    }

    override fun observerViewModel() {
        super.observerViewModel()
        observeApiResult(viewModel.peopleDetailResponse) {

        }
    }

}