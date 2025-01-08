package com.orlando.androidbase.presentation.features.home

import androidx.lifecycle.ViewModel
import com.orlando.androidbase.R

class HomeViewModel : ViewModel() {

    fun getMenus(): List<HomeAdapter.ItemMenu> {
        val characters = HomeAdapter.ItemMenu(R.drawable.character, R.string.character)
        val films = HomeAdapter.ItemMenu(R.drawable.films, R.string.flims)
        val planets = HomeAdapter.ItemMenu(R.drawable.planets, R.string.planets)
        val species = HomeAdapter.ItemMenu(R.drawable.species, R.string.species)
        val starships = HomeAdapter.ItemMenu(R.drawable.starships, R.string.startships)
        val vehicles = HomeAdapter.ItemMenu(R.drawable.vehicles, R.string.vehicles)
        return listOf(characters, films, planets, species, starships, vehicles)
    }
}