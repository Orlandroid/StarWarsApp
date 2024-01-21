package com.orlando.androidbase.presentation.ui.species

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.orlando.androidbase.entities.remote.ResultSpecie
import com.orlando.androidbase.presentation.base.BaseFragment
import com.orlando.androidbase.presentation.extensions.getError
import com.orlando.androidbase.presentation.extensions.gone
import com.orlando.androidbase.presentation.extensions.showError
import com.orlando.androidbase.presentation.extensions.showErrorApi
import com.orlando.androidbase.presentation.extensions.toJson
import com.orlando.androidbase.presentation.extensions.visible
import com.orlando.androidbase.presentation.ui.MainActivity
import com.orlando.androidbase.R
import com.orlando.androidbase.databinding.FragmentSpeciesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SpeciesFragment : BaseFragment<FragmentSpeciesBinding>(R.layout.fragment_species) {

    private val viewModel: SpeciesViewModel by viewModels()
    private val speciesAdapter = SpeciesAdapter { clickOnSpecie(it) }

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
        recycler.adapter = speciesAdapter
        getCharacters()
        listenerAdapter()
    }


    private fun getCharacters() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getSpeciesPagingSource.collectLatest { characters ->
                    speciesAdapter.submitData(lifecycle, characters)
                }
            }
        }
    }

    private fun listenerAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                speciesAdapter.addLoadStateListener { loadState ->
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