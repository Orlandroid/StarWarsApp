package com.example.androidbase.presentation.ui.starships

import androidx.fragment.app.viewModels
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentStarshipsBinding
import com.example.androidbase.entities.remote.ResultPeople
import com.example.androidbase.entities.remote.ResultStarship
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.myOnScrolled
import com.example.androidbase.presentation.extensions.observeApiResult

class StarshipsFragment : BaseFragment<FragmentStarshipsBinding>(R.layout.fragment_starships) {

    private val viewModel: StarshipViewModel by viewModels()
    private val starshipsAdapter = StarshipsAdapter { clickOnPeople(it) }
    private var currentPage: Int? = 1
    private var canCallToTheNextPage = true
    private var starshipList: ArrayList<ResultStarship> = arrayListOf()

    private fun clickOnPeople(clickOnPeople: ResultStarship) {

    }

    override fun setUpUi() = with(binding) {
        viewModel.getStarships(currentPage.toString())
        binding.recycler.adapter = starshipsAdapter
        recycler.myOnScrolled {
            if (!canCallToTheNextPage) {
                return@myOnScrolled
            }
            currentPage?.let {
                currentPage = currentPage!! + 1
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

    private fun getCurrentPage(pageInUrl: String?): Int? {
        if (pageInUrl == null) {
            return null
        }
        return pageInUrl.split("=")[1].toInt()
    }

}