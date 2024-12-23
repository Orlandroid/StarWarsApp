package com.orlando.androidbase.presentation.features.flims

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.orlando.androidbase.entities.remote.Film
import com.orlando.androidbase.presentation.base.BaseFragment
import com.orlando.androidbase.presentation.extensions.getError
import com.orlando.androidbase.presentation.extensions.gone
import com.orlando.androidbase.presentation.extensions.showError
import com.orlando.androidbase.presentation.extensions.showErrorApi
import com.orlando.androidbase.presentation.extensions.toJson
import com.orlando.androidbase.presentation.extensions.visible
import com.orlando.androidbase.presentation.features.MainActivity
import com.orlando.androidbase.R
import com.orlando.androidbase.databinding.FragmentFlimsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FilmsFragment : BaseFragment<FragmentFlimsBinding>(R.layout.fragment_flims) {

    private val viewModel: FilmsViewModel by viewModels()
    private val filmsAdapter = FlimsAdapter { clickOnFilm(it) }

    private fun clickOnFilm(film: Film) {
        findNavController().navigate(
            FilmsFragmentDirections.actionFilmsFragmentToFilmDetailFragment(
                film.toJson()
            )
        )
    }

    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true,
        toolbarTitle = getString(R.string.flims)
    )

    override fun setUpUi() = with(binding) {
        binding.progressBar.visible()
        recycler.adapter = filmsAdapter
        getCharacters()
        listenerAdapter()
    }


    private fun getCharacters() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getFilmsPagingSource.collectLatest { characters ->
                    filmsAdapter.submitData(lifecycle, characters)
                }
            }
        }
    }

    private fun listenerAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                filmsAdapter.addLoadStateListener { loadState ->
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