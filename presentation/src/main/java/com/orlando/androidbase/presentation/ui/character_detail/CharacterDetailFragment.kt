package com.orlando.androidbase.presentation.ui.character_detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.orlando.androidbase.entities.remote.ResultPeople
import com.orlando.androidbase.presentation.base.BaseFragment
import com.orlando.androidbase.presentation.extensions.loadUrl
import com.orlando.androidbase.presentation.extensions.observeApiResult
import com.orlando.androidbase.presentation.ui.MainActivity
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getPeopleImages
import com.google.gson.Gson
import com.orlando.androidbase.R
import com.orlando.androidbase.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding>(R.layout.fragment_character_detail) {

    private val args: CharacterDetailFragmentArgs by navArgs()
    private var peopleItemWithImage: ResultPeople? = null
    private val viewModel: CharacterDetailViewModel by viewModels()


    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true,
        toolbarTitle = getString(R.string.detalle)
    )

    override fun setUpUi() {
        val gson = Gson()
        peopleItemWithImage = gson.fromJson(args.character, ResultPeople::class.java)
        peopleItemWithImage?.let {
            bind(it)
        }
    }

    private fun bind(people: ResultPeople) = with(binding) {
        imagePeople.loadUrl(getImageFromJson(people.name, getPeopleImages()))
        tvHeigth.text = people.height
        tvHairColor.text = people.hair_color
        tvHomeworkid.text = getHomeWorkId(people.homeworld)
        tvName.text = people.name
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