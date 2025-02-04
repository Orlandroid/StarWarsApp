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

    fun getMenus(): List<ItemMenu> {
        val characters = ItemMenu(
            image = R.drawable.character,
            title = R.string.character,
            menuName = MenuName.CHARACTERS
        )
        val films = ItemMenu(
            image = R.drawable.films,
            title = R.string.flims,
            menuName = MenuName.MOVIES
        )
        val planets = ItemMenu(
            image = R.drawable.planets,
            title = R.string.planets,
            menuName = MenuName.PLANETS
        )
        val species = ItemMenu(
            image = R.drawable.species,
            title = R.string.species,
            menuName = MenuName.SPECIES
        )
        val starships =
            ItemMenu(
                image = R.drawable.starships,
                title = R.string.startships,
                menuName = MenuName.STARSHIPS
            )
        val vehicles = ItemMenu(
            image = R.drawable.vehicles,
            title = R.string.vehicles,
            menuName = MenuName.VEHICLES
        )
        return listOf(characters, films, planets, species, starships, vehicles)
    }
}