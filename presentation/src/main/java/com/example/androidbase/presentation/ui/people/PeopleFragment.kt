package com.example.androidbase.presentation.ui.people


import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentPeopleBinding
import com.example.androidbase.entities.remote.ResultPeople
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.myOnScrolled
import com.example.androidbase.presentation.extensions.observeApiResult
import com.example.androidbase.presentation.ui.MainActivity
import com.example.androidbase.presentation.util.getCurrentPage
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PeopleFragment : BaseFragment<FragmentPeopleBinding>(R.layout.fragment_people) {


    private val viewModel: PeopleViewModel by viewModels()
    private val userAdapter = PeopleAdapter { clickOnPeople(it) }
    private var currentPage: Int? = 1
    private var canCallToTheNextPage = true
    private var peopleList: ArrayList<ResultPeople> = arrayListOf()
    private var isFirstTimeOnTheView: Boolean = true

    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true,
        toolbarTitle = getString(R.string.personajes)
    )

    override fun setUpUi() = with(binding) {
        if (isFirstTimeOnTheView) {
            isFirstTimeOnTheView = false
            viewModel.getPeople(currentPage.toString())
        }
        recycler.adapter = userAdapter
        recycler.myOnScrolled {
            if (!canCallToTheNextPage) {
                return@myOnScrolled
            }
            currentPage?.let {
                canCallToTheNextPage = false
                viewModel.getPeople(page = currentPage.toString())
            }
        }
    }

    override fun observerViewModel() {
        super.observerViewModel()
        observeApiResult(viewModel.peopleResponse, shouldCloseTheViewOnApiError = true) {
            peopleList.addAll(it.results)
            userAdapter.setData(peopleList)
            canCallToTheNextPage = true
            currentPage = getCurrentPage(it.next)
        }
    }

    private fun clickOnPeople(people: ResultPeople) {
        val gson = Gson()
        val peopleJson = gson.toJson(people)
        findNavController().navigate(
            PeopleFragmentDirections.actionUsersFragmentToCharacterDetailFragment(peopleJson)
        )
    }


}