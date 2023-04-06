package com.example.androidbase.presentation.ui.flims

import androidx.fragment.app.viewModels
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentFlimsBinding
import com.example.androidbase.entities.remote.ResultX
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.configure
import com.example.androidbase.presentation.extensions.myOnScrolled
import com.example.androidbase.presentation.extensions.observeApiResult
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

    override fun setUpUi() = with(binding) {
        configure(binding.toolbarLayout, title = getString(R.string.flims))
        viewModel.getFilms(currentPage.toString())
        binding.recycler.adapter = filmsAdapter
        recycler.myOnScrolled {
            if (!canCallToTheNextPage) {
                return@myOnScrolled
            }
            currentPage?.let {
                currentPage = currentPage!! + 1
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

    private fun getCurrentPage(pageInUrl: String?): Int? {
        if (pageInUrl == null) {
            return null
        }
        return pageInUrl.split("=")[1].toInt()
    }

}