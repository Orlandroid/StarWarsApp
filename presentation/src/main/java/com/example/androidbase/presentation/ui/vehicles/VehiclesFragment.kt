package com.example.androidbase.presentation.ui.vehicles

import androidx.fragment.app.viewModels
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentVehiclesBinding
import com.example.androidbase.entities.remote.ResultPeople
import com.example.androidbase.entities.remote.ResultVehicle
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.myOnScrolled
import com.example.androidbase.presentation.extensions.observeApiResult


class VehiclesFragment : BaseFragment<FragmentVehiclesBinding>(R.layout.fragment_vehicles) {

    private val viewModel: VehiclesViewModel by viewModels()
    private val vehiclesAdapter = VehiclesAdapter { clickOnPeople(it) }
    private var currentPage: Int? = 1
    private var canCallToTheNextPage = true
    private var vehiclesList: ArrayList<ResultVehicle> = arrayListOf()

    private fun clickOnPeople(clickOnPeople: ResultVehicle) {

    }

    override fun setUpUi() = with(binding) {
        viewModel.getVehicles(currentPage.toString())
        binding.recycler.adapter = vehiclesAdapter
        recycler.myOnScrolled {
            if (!canCallToTheNextPage) {
                return@myOnScrolled
            }
            currentPage?.let {
                currentPage = currentPage!! + 1
                canCallToTheNextPage = false
                viewModel.getVehicles(page = currentPage.toString())
            }
        }
    }

    override fun observerViewModel() {
        super.observerViewModel()
        observeApiResult(viewModel.vehiclesResponse) {
            vehiclesList.addAll(it.results)
            vehiclesAdapter.setData(vehiclesList)
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