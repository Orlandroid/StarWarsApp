package com.example.androidbase.presentation.ui.people

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentPeopleBinding
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.click
import com.example.androidbase.presentation.extensions.observeApiResult
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PeopleFragment : BaseFragment<FragmentPeopleBinding>(R.layout.fragment_people) {


    private val viewModel: PeopleViewModel by viewModels()
    private val userAdapter by lazy {
        PeopleAdapter()
    }

    override fun setUpUi() = with(binding) {
        toolbarLayout.toolbarBack.click {
            findNavController().popBackStack()
        }
        viewModel.getAllPeople()
        recycler.adapter = userAdapter
    }

    override fun observerViewModel() {
        super.observerViewModel()
        observeApiResult(viewModel.allPeopleResponse) {
            userAdapter.setData(it)
        }
    }

}