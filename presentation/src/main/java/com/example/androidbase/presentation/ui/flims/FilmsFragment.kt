package com.example.androidbase.presentation.ui.flims

import androidx.fragment.app.viewModels
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentFlimsBinding
import com.example.androidbase.entities.remote.ResultPeople
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.myOnScrolled

class FilmsFragment : BaseFragment<FragmentFlimsBinding>(R.layout.fragment_flims) {

    private val viewModel: FilmsViewModel by viewModels()
    private val filmsAdapter = FlimsAdapter { clickOnPeople(it) }
    private var currentPage: Int? = 1
    private var canCallToTheNextPage = true
    private var filmsList: ArrayList<ResultPeople> = arrayListOf()

    private fun clickOnPeople(clickOnPeople: ResultPeople) {

    }

    override fun setUpUi() = with(binding) {
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

}