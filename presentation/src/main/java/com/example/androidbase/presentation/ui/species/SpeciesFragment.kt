package com.example.androidbase.presentation.ui.species

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentSpeciesBinding
import com.example.androidbase.entities.remote.ResultSpecie
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.myOnScrolled
import com.example.androidbase.presentation.extensions.observeApiResult
import com.example.androidbase.presentation.extensions.toJson
import com.example.androidbase.presentation.ui.MainActivity
import com.example.androidbase.presentation.util.getCurrentPage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpeciesFragment : BaseFragment<FragmentSpeciesBinding>(R.layout.fragment_species) {

    private val viewModel: SpeciesViewModel by viewModels()
    private val speciesAdapter = SpeciesAdapter { clickOnSpecie(it) }
    private var currentPage: Int? = 1
    private var canCallToTheNextPage = true
    private var speciesList: ArrayList<ResultSpecie> = arrayListOf()
    private var isFirstTimeOnTheView: Boolean = true

    private fun clickOnSpecie(specie: ResultSpecie) {
        findNavController().navigate(
            SpeciesFragmentDirections.actionSpeciesFragmentToSpeciesDetailFragment(
                specie.toJson()
            )
        )
    }

    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true,
        toolbarTitle = getString(R.string.species)
    )

    override fun setUpUi() = with(binding) {
        if (isFirstTimeOnTheView) {
            isFirstTimeOnTheView = false
            viewModel.getSpecies(currentPage.toString())
        }
        recycler.adapter = speciesAdapter
        recycler.myOnScrolled {
            if (!canCallToTheNextPage) {
                return@myOnScrolled
            }
            currentPage?.let {
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

}