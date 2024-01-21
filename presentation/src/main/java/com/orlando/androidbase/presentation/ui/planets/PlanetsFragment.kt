package com.orlando.androidbase.presentation.ui.planets

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.orlando.androidbase.entities.remote.ResultPlanet
import com.orlando.androidbase.presentation.base.BaseFragment
import com.orlando.androidbase.presentation.extensions.getError
import com.orlando.androidbase.presentation.extensions.gone
import com.orlando.androidbase.presentation.extensions.showError
import com.orlando.androidbase.presentation.extensions.showErrorApi
import com.orlando.androidbase.presentation.extensions.toJson
import com.orlando.androidbase.presentation.extensions.visible
import com.orlando.androidbase.presentation.ui.MainActivity
import com.orlando.androidbase.R
import com.orlando.androidbase.databinding.FragmentPlanetsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PlanetsFragment : BaseFragment<FragmentPlanetsBinding>(R.layout.fragment_planets) {

    private val viewModel: PlanetsViewModel by viewModels()
    private val planetsAdapter = PlanetsAdapter { clickOnPlanet(it) }

    private fun clickOnPlanet(planet: ResultPlanet) {
        findNavController().navigate(
            PlanetsFragmentDirections.actionPlanetsFragmentToPlanetDetailFragment(
                planet.toJson()
            )
        )
    }

    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true, toolbarTitle = getString(R.string.planets)
    )

    override fun setUpUi() = with(binding) {
        recycler.adapter = planetsAdapter
        getCharacters()
        listenerAdapter()
    }


    private fun getCharacters() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getPlanetsPagingSource.collectLatest { characters ->
                    planetsAdapter.submitData(lifecycle, characters)
                }
            }
        }
    }

    private fun listenerAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                planetsAdapter.addLoadStateListener { loadState ->
                    if (loadState.source.append is LoadState.Loading || loadState.source.refresh is LoadState.Loading) {
                        binding.progressBar.visible()
                    } else {
                        binding.progressBar.gone()
                    }
                    val errorState = loadState.getError()
                    errorState?.showError {
                        showErrorApi()
                    }
                }
            }
        }
    }
}