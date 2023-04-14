package com.example.androidbase.presentation.ui.home

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentHomeBinding
import com.example.androidbase.presentation.base.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {


    private val homeAdapter = HomeAdapter { clickOnMenu(it) }

    override fun setUpUi() = with(binding) {
        recycler.adapter = homeAdapter
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        homeAdapter.setData(setMenu())
    }

    private fun setMenu(): List<HomeAdapter.ItemMenu> {
        val characters = HomeAdapter.ItemMenu(R.drawable.character, getString(R.string.character))
        val films = HomeAdapter.ItemMenu(R.drawable.films, getString(R.string.flims))
        val planets = HomeAdapter.ItemMenu(R.drawable.planets, getString(R.string.planets))
        val species = HomeAdapter.ItemMenu(R.drawable.species, getString(R.string.species))
        val starships = HomeAdapter.ItemMenu(R.drawable.starships, getString(R.string.startships))
        val vehicles = HomeAdapter.ItemMenu(R.drawable.vehicles, getString(R.string.vehicles))
        return listOf(characters, films, planets, species, starships, vehicles)
    }

    private fun clickOnMenu(menu: HomeAdapter.ItemMenu) {
        when (menu.title) {
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