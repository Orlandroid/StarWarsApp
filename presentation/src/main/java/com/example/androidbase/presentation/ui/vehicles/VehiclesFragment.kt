package com.example.androidbase.presentation.ui.vehicles

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentVehiclesBinding
import com.example.androidbase.entities.remote.ResultPeople
import com.example.androidbase.entities.remote.ResultVehicle
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.getError
import com.example.androidbase.presentation.extensions.gone
import com.example.androidbase.presentation.extensions.showError
import com.example.androidbase.presentation.extensions.showErrorApi
import com.example.androidbase.presentation.extensions.toJson
import com.example.androidbase.presentation.extensions.visible
import com.example.androidbase.presentation.ui.MainActivity
import com.example.androidbase.presentation.ui.people.PeopleFragmentDirections
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
        recycler.adapter = vehiclesAdapter
        getCharacters()
        listenerAdapter()
    }

    private fun clickOnPeople(result: ResultPeople) {
        findNavController().navigate(
            PeopleFragmentDirections.actionUsersFragmentToCharacterDetailFragment(
                result.toJson()
            )
        )
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