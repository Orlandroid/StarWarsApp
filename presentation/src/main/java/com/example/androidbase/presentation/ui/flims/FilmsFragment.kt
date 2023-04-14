package com.example.androidbase.presentation.ui.flims

import androidx.fragment.app.viewModels
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentFlimsBinding
import com.example.androidbase.entities.remote.ResultX
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.myOnScrolled
import com.example.androidbase.presentation.extensions.observeApiResult
import com.example.androidbase.presentation.ui.MainActivity
import com.example.androidbase.presentation.util.getCurrentPage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilmsFragment : BaseFragment<FragmentFlimsBinding>(R.layout.fragment_flims) {

    private val viewModel: FilmsViewModel by viewModels()
    private val filmsAdapter = FlimsAdapter { clickOnPeople(it) }
    private var currentPage: Int? = 1
    private var canCallToTheNextPage = true
    private var filmsList: ArrayList<ResultX> = arrayListOf()

    private fun clickOnPeople(clickOnPeople: ResultX) {

    }

    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true,
        toolbarTitle = getString(R.string.flims)
    )

    override fun setUpUi() = with(binding) {
        viewModel.getFilms(currentPage.toString())
        binding.recycler.adapter = filmsAdapter
        recycler.myOnScrolled {
            if (!canCallToTheNextPage) {
                return@myOnScrolled
            }
            currentPage?.let {
                canCallToTheNextPage = false
                viewModel.getFilms(page = currentPage.toString())
            }
        }
    }

    override fun observerViewModel() {
        super.observerViewModel()
        observeApiResult(viewModel.filmsResponse) {
            filmsList.addAll(it.results)
            filmsAdapter.setData(filmsList)
            canCallToTheNextPage = true
            currentPage = getCurrentPage(it.next)
        }
    }

}