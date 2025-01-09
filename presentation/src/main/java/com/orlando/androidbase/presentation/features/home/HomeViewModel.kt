package com.orlando.androidbase.presentation.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orlando.androidbase.R
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _homeScreenSideEffects = Channel<HomeScreenSideEffects>()
    val homeScreenSideEffects = _homeScreenSideEffects.receiveAsFlow()

    fun onEvents(event: HomeScreenEvents) = viewModelScope.launch {
        when (event) {
            HomeScreenEvents.OnClickOnCharacters -> {
                sendSideEffect(HomeScreenSideEffects.GotoCharacters)
            }

            HomeScreenEvents.OnClickOnMovies -> {
                sendSideEffect(HomeScreenSideEffects.GoToMovies)
            }

            HomeScreenEvents.OnClickOnPlanets -> {
                sendSideEffect(HomeScreenSideEffects.GoToPlanets)
            }

            HomeScreenEvents.OnClickOnSpecies -> {
                sendSideEffect(HomeScreenSideEffects.GoToSpecies)
            }

            HomeScreenEvents.OnClickOnStarShips -> {
                sendSideEffect(HomeScreenSideEffects.GoToStarShips)
            }

            HomeScreenEvents.OnClickOnVehicles -> {
                sendSideEffect(HomeScreenSideEffects.GoToVehicles)
            }
        }
    }

    private fun sendSideEffect(effect: HomeScreenSideEffects) = viewModelScope.launch {
        _homeScreenSideEffects.send(effect)
    }

    fun getMenus(): List<HomeAdapter.ItemMenu> {
        val characters = HomeAdapter.ItemMenu(
            image = R.drawable.character,
            title = R.string.character,
            menuName = HomeAdapter.MenuName.CHARACTERS
        )
        val films = HomeAdapter.ItemMenu(
            image = R.drawable.films,
            title = R.string.flims,
            menuName = HomeAdapter.MenuName.MOVIES
        )
        val planets = HomeAdapter.ItemMenu(
            image = R.drawable.planets,
            title = R.string.planets,
            menuName = HomeAdapter.MenuName.PLANETS
        )
        val species = HomeAdapter.ItemMenu(
            image = R.drawable.species,
            title = R.string.species,
            menuName = HomeAdapter.MenuName.SPECIES
        )
        val starships =
            HomeAdapter.ItemMenu(
                image = R.drawable.starships,
                title = R.string.startships,
                menuName = HomeAdapter.MenuName.STARSHIPS
            )
        val vehicles = HomeAdapter.ItemMenu(
            image = R.drawable.vehicles,
            title = R.string.vehicles,
            menuName = HomeAdapter.MenuName.VEHICLES
        )
        return listOf(characters, films, planets, species, starships, vehicles)
    }
}