package com.example.androidbase.presentation.ui.people

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentPeopleBinding
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.click
import com.example.androidbase.presentation.extensions.myOnScrolled
import com.example.androidbase.presentation.extensions.observeApiResult
import com.example.domain.entities.remote.ResultPeople
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PeopleFragment : BaseFragment<FragmentPeopleBinding>(R.layout.fragment_people) {


    private val viewModel: PeopleViewModel by viewModels()
    private val userAdapter by lazy {
        PeopleAdapter()
    }
    private var currentPage: Int? = 1
    private var canCallToTheNextPage = true
    private var peopleList: ArrayList<ResultPeople> = arrayListOf()

    override fun setUpUi() = with(binding) {
        toolbarLayout.toolbarBack.click {
            findNavController().popBackStack()
        }
        viewModel.getPeople(currentPage.toString())
        recycler.adapter = userAdapter
        recycler.myOnScrolled {
            if (!canCallToTheNextPage) {
                return@myOnScrolled
            }
            currentPage?.let {
                currentPage = currentPage!! + 1
                canCallToTheNextPage = false
                viewModel.getPeople(page = currentPage.toString())
            }
        }
    }

    override fun observerViewModel() {
        super.observerViewModel()
        observeApiResult(viewModel.peopleResponse) {
            peopleList.addAll(it.results)
            userAdapter.setData(peopleList)
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