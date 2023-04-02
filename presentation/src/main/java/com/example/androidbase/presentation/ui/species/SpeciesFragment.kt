package com.example.androidbase.presentation.ui.species

import androidx.fragment.app.viewModels
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentSpeciesBinding
import com.example.androidbase.entities.remote.ResultPeople
import com.example.androidbase.entities.remote.ResultSpecie
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.myOnScrolled
import com.example.androidbase.presentation.extensions.observeApiResult

class SpeciesFragment : BaseFragment<FragmentSpeciesBinding>(R.layout.fragment_species) {

    private val viewModel: SpeciesViewModel by viewModels()
    private val speciesAdapter = SpeciesAdapter { clickOnPeople(it) }
    private var currentPage: Int? = 1
    private var canCallToTheNextPage = true
    private var speciesList: ArrayList<ResultSpecie> = arrayListOf()

    private fun clickOnPeople(clickOnPeople: ResultSpecie) {

    }

    override fun setUpUi() = with(binding) {
        viewModel.getSpecies(currentPage.toString())
        binding.recycler.adapter = speciesAdapter
        recycler.myOnScrolled {
            if (!canCallToTheNextPage) {
                return@myOnScrolled
            }
            currentPage?.let {
                currentPage = currentPage!! + 1
                canCallToTheNextPage = false
                viewModel.getSpecies(page = currentPage.toString())
            }
        }
    }

    override fun observerViewModel() {
        super.observerViewModel()
        observeApiResult(viewModel.speciesResponse) {
            speciesList.addAll(it.results)
            speciesAdapter.setData(speciesList)
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