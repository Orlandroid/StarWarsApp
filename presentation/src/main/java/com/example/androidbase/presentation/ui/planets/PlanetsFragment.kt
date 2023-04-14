package com.example.androidbase.presentation.ui.planets

import androidx.fragment.app.viewModels
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentPlanetsBinding
import com.example.androidbase.entities.remote.ResultPlanet
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.myOnScrolled
import com.example.androidbase.presentation.extensions.observeApiResult
import com.example.androidbase.presentation.ui.MainActivity
import com.example.androidbase.presentation.util.getCurrentPage
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PlanetsFragment : BaseFragment<FragmentPlanetsBinding>(R.layout.fragment_planets) {

    private val viewModel: PlanetsViewModel by viewModels()
    private val planetsAdapter = PlanetsAdapter { clickOnPeople(it) }
    private var currentPage: Int? = 1
    private var canCallToTheNextPage = true
    private var planetsList: ArrayList<ResultPlanet> = arrayListOf()

    private fun clickOnPeople(clickOnPeople: ResultPlanet) {

    }

    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true,
        toolbarTitle = getString(R.string.planets)
    )


    override fun setUpUi() = with(binding) {
        viewModel.getPlanets(currentPage.toString())
        binding.recycler.adapter = planetsAdapter
        recycler.myOnScrolled {
            if (!canCallToTheNextPage) {
                return@myOnScrolled
            }
            currentPage?.let {
                canCallToTheNextPage = false
                viewModel.getPlanets(page = currentPage.toString())
            }
        }
    }

    override fun observerViewModel() {
        super.observerViewModel()
        observeApiResult(viewModel.planetsResponse) {
            planetsList.addAll(it.results)
            planetsAdapter.setData(planetsList)
            canCallToTheNextPage = true
            currentPage = getCurrentPage(it.next)
        }
    }
}