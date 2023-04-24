package com.example.androidbase.presentation.ui.starships

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentStarshipsBinding
import com.example.androidbase.entities.remote.ResultStarship
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.myOnScrolled
import com.example.androidbase.presentation.extensions.observeApiResult
import com.example.androidbase.presentation.extensions.toJson
import com.example.androidbase.presentation.ui.MainActivity
import com.example.androidbase.presentation.util.getCurrentPage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StarshipsFragment : BaseFragment<FragmentStarshipsBinding>(R.layout.fragment_starships) {

    private val viewModel: StarshipViewModel by viewModels()
    private val starshipsAdapter = StarshipsAdapter { clickOnPeople(it) }
    private var currentPage: Int? = 1
    private var canCallToTheNextPage = true
    private var starshipList: ArrayList<ResultStarship> = arrayListOf()
    private var isFirstTimeOnTheView: Boolean = true

    private fun clickOnPeople(starShip: ResultStarship) {
        findNavController().navigate(
            StarshipsFragmentDirections.actionStarshipsFragmentToStarShipDetailFragment(
                starShip.toJson()
            )
        )
    }

    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true,
        toolbarTitle = getString(R.string.startships)
    )

    override fun setUpUi() = with(binding) {
        if (isFirstTimeOnTheView) {
            isFirstTimeOnTheView = false
            viewModel.getStarships(currentPage.toString())
        }
        binding.recycler.adapter = starshipsAdapter
        recycler.myOnScrolled {
            if (!canCallToTheNextPage) {
                return@myOnScrolled
            }
            currentPage?.let {
                canCallToTheNextPage = false
                viewModel.getStarships(page = currentPage.toString())
            }
        }
    }

    override fun observerViewModel() {
        super.observerViewModel()
        observeApiResult(viewModel.starshipsResponse) {
            starshipList.addAll(it.results)
            starshipsAdapter.setData(starshipList)
            canCallToTheNextPage = true
            currentPage = getCurrentPage(it.next)
        }
    }
}