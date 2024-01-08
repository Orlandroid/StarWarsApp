package com.example.androidbase.presentation.ui.flims

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentFlimsBinding
import com.example.androidbase.entities.remote.Film
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.getError
import com.example.androidbase.presentation.extensions.gone
import com.example.androidbase.presentation.extensions.showError
import com.example.androidbase.presentation.extensions.showErrorApi
import com.example.androidbase.presentation.extensions.toJson
import com.example.androidbase.presentation.extensions.visible
import com.example.androidbase.presentation.ui.MainActivity
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