package com.example.androidbase.presentation.ui.people


import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentPeopleBinding
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.click
import com.example.androidbase.presentation.extensions.observeApiResult
import com.example.domain.entities.remote.PeopleResponseItem
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PeopleFragment : BaseFragment<FragmentPeopleBinding>(R.layout.fragment_people) {


    private val viewModel: PeopleViewModel by viewModels()
    private val userAdapter = PeopleAdapter { clickOnCharacter(it) }

    override fun setUpUi() = with(binding) {
        toolbarLayout.toolbarBack.click {
            findNavController().popBackStack()
        }
        toolbarLayout.toolbarTitle.text = getString(R.string.people)
        viewModel.getAllPeople()
        recycler.adapter = userAdapter
    }

    override fun observerViewModel() {
        super.observerViewModel()
        observeApiResult(viewModel.allPeopleResponse) {
            userAdapter.setData(it)
        }
    }

    private fun clickOnCharacter(people: PeopleResponseItem) {
        val gson = Gson()
        val peopleInJson = gson.toJson(people)
        findNavController().navigate(
            PeopleFragmentDirections.actionUsersFragmentToCharacterDetailFragment(
                peopleInJson
            )
        )
    }

}