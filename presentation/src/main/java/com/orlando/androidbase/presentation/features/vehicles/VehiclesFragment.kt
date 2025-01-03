package com.orlando.androidbase.presentation.features.vehicles

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.orlando.androidbase.R
import com.orlando.androidbase.databinding.FragmentVehiclesBinding
import com.orlando.androidbase.entities.remote.ResultVehicle
import com.orlando.androidbase.presentation.base.BaseFragment
import com.orlando.androidbase.presentation.extensions.getError
import com.orlando.androidbase.presentation.extensions.gone
import com.orlando.androidbase.presentation.extensions.showError
import com.orlando.androidbase.presentation.extensions.showErrorApi
import com.orlando.androidbase.presentation.extensions.toJson
import com.orlando.androidbase.presentation.extensions.visible
import com.orlando.androidbase.presentation.features.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class VehiclesFragment : BaseFragment<FragmentVehiclesBinding>(R.layout.fragment_vehicles) {

    private val viewModel: VehiclesViewModel by viewModels()
    private val vehiclesAdapter = VehiclesAdapter { clickOnVehicle(it) }

    private fun clickOnVehicle(vehicle: ResultVehicle) {
        findNavController().navigate(
            VehiclesFragmentDirections.actionVehiclesFragmentToVehicleDetailFragment(
                vehicle.toJson()
            )
        )
    }

    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true, toolbarTitle = getString(R.string.vehicles)
    )

    override fun setUpUi() = with(binding) {
        binding.progressBar.visible()
        recycler.adapter = vehiclesAdapter
        getCharacters()
        listenerAdapter()
    }

    private fun getCharacters() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getVehiclesPagingSource.collectLatest { characters ->
                    vehiclesAdapter.submitData(lifecycle, characters)
                }
            }
        }
    }

    private fun listenerAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vehiclesAdapter.addLoadStateListener { loadState ->
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