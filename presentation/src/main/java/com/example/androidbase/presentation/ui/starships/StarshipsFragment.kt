package com.example.androidbase.presentation.ui.starships

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentStarshipsBinding
import com.example.androidbase.entities.remote.ResultPeople
import com.example.androidbase.entities.remote.ResultStarship
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.getError
import com.example.androidbase.presentation.extensions.gone
import com.example.androidbase.presentation.extensions.showError
import com.example.androidbase.presentation.extensions.showErrorApi
import com.example.androidbase.presentation.extensions.toJson
import com.example.androidbase.presentation.extensions.visible
import com.example.androidbase.presentation.ui.MainActivity
import com.example.androidbase.presentation.ui.people.PeopleFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StarshipsFragment : BaseFragment<FragmentStarshipsBinding>(R.layout.fragment_starships) {

    private val viewModel: StarshipViewModel by viewModels()
    private val starshipsAdapter = StarshipsAdapter { clickOnPeople(it) }

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
        recycler.adapter = starshipsAdapter
        getCharacters()
        listenerAdapter()
    }

    private fun clickOnPeople(result: ResultPeople) {
        findNavController().navigate(
            PeopleFragmentDirections.actionUsersFragmentToCharacterDetailFragment(
                result.toJson()
            )
        )
    }

    private fun getCharacters() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getStarshipsPagingSource.collectLatest { characters ->
                    starshipsAdapter.submitData(lifecycle, characters)
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