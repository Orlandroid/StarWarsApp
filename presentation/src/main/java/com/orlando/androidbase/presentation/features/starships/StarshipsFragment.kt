package com.orlando.androidbase.presentation.features.starships

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.orlando.androidbase.R
import com.orlando.androidbase.databinding.FragmentStarshipsBinding
import com.orlando.androidbase.entities.remote.ResultStarship
import com.orlando.androidbase.presentation.base.BaseFragment
import com.orlando.androidbase.presentation.extensions.getError
import com.orlando.androidbase.presentation.extensions.gone
import com.orlando.androidbase.presentation.extensions.showError
import com.orlando.androidbase.presentation.extensions.showErrorApi
import com.orlando.androidbase.presentation.extensions.toJson
import com.orlando.androidbase.presentation.extensions.visible
import com.orlando.androidbase.presentation.features.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StarshipsFragment : BaseFragment<FragmentStarshipsBinding>(R.layout.fragment_starships) {

    private val viewModel: StarshipViewModel by viewModels()
    private val starshipsAdapter = StarshipsAdapter { clickOnStarship(it) }

    private fun clickOnStarship(starShip: ResultStarship) {
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
        binding.progressBar.visible()
        recycler.adapter = starshipsAdapter
        getCharacters()
        listenerAdapter()
    }

    private fun getCharacters() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getStarshipsPagingSource.collectLatest { characters ->
//                    starshipsAdapter.submitData(lifecycle, characters)
                }
            }
        }
    }

    private fun listenerAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                starshipsAdapter.addLoadStateListener { loadState ->
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