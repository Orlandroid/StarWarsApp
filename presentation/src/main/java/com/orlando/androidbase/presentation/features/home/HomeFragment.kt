package com.orlando.androidbase.presentation.features.home

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.orlando.androidbase.R
import com.orlando.androidbase.databinding.FragmentHomeBinding
import com.orlando.androidbase.presentation.base.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {


    private val homeAdapter = HomeAdapter { clickOnMenu(it) }

    override fun setUpUi() = with(binding) {
        recycler.adapter = homeAdapter
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        homeAdapter.setData(setMenu())
    }

    private fun setMenu(): List<HomeAdapter.ItemMenu> {
        val characters = HomeAdapter.ItemMenu(R.drawable.character, R.string.character)
        val films = HomeAdapter.ItemMenu(R.drawable.films, R.string.flims)
        val planets = HomeAdapter.ItemMenu(R.drawable.planets, R.string.planets)
        val species = HomeAdapter.ItemMenu(R.drawable.species, R.string.species)
        val starships = HomeAdapter.ItemMenu(R.drawable.starships, R.string.startships)
        val vehicles = HomeAdapter.ItemMenu(R.drawable.vehicles, R.string.vehicles)
        return listOf(characters, films, planets, species, starships, vehicles)
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