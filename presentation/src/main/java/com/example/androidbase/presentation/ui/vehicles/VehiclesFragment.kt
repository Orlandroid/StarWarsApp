package com.example.androidbase.presentation.ui.vehicles

import androidx.fragment.app.viewModels
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentVehiclesBinding
import com.example.androidbase.entities.remote.ResultVehicle
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.myOnScrolled
import com.example.androidbase.presentation.extensions.observeApiResult
import com.example.androidbase.presentation.ui.MainActivity
import com.example.androidbase.presentation.util.getCurrentPage
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class VehiclesFragment : BaseFragment<FragmentVehiclesBinding>(R.layout.fragment_vehicles) {

    private val viewModel: VehiclesViewModel by viewModels()
    private val vehiclesAdapter = VehiclesAdapter { clickOnVehicle(it) }
    private var currentPage: Int? = 1
    private var canCallToTheNextPage = true
    private var vehiclesList: ArrayList<ResultVehicle> = arrayListOf()
    private var isFirstTimeOnTheView: Boolean = true

    private fun clickOnVehicle(vehicle: ResultVehicle) {

    }

    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true, toolbarTitle = getString(R.string.vehicles)
    )

    override fun setUpUi() = with(binding) {
        if (isFirstTimeOnTheView) {
            isFirstTimeOnTheView = false
            viewModel.getVehicles(currentPage.toString())
        }
        binding.recycler.adapter = vehiclesAdapter
        recycler.myOnScrolled {
            if (!canCallToTheNextPage) {
                return@myOnScrolled
            }
            currentPage?.let {
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

}