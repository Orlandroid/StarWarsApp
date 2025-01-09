package com.orlando.androidbase.presentation.features.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.orlando.androidbase.R
import com.orlando.androidbase.databinding.FragmentHomeBinding
import com.orlando.androidbase.presentation.base.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {


    private val homeAdapter = HomeAdapter { clickOnMenu(it) }
    private val viewModel: HomeViewModel by viewModels()

    override fun setUpUi() = with(binding) {
        recycler.adapter = homeAdapter
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        homeAdapter.setData(viewModel.getMenus())
    }

    private fun clickOnMenu(menu: HomeAdapter.ItemMenu) {
        when (getString(menu.title)) {
            getString(R.string.character) -> {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToUsersFragment())
            }

            getString(R.string.flims) -> {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFilmsFragment())
            }

            getString(R.string.planets) -> {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPlanetsFragment())
            }

            getString(R.string.species) -> {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSpeciesFragment())
            }

            getString(R.string.startships) -> {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToStarshipsFragment())
            }

            getString(R.string.vehicles) -> {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToVehiclesFragment())
            }

        }
    }


}