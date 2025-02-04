package com.orlando.androidbase.presentation.features.home


sealed class HomeScreenEvents {
    data object OnClickOnCharacters : HomeScreenEvents()
    data object OnClickOnMovies : HomeScreenEvents()
    data object OnClickOnPlanets : HomeScreenEvents()
    data object OnClickOnSpecies : HomeScreenEvents()
    data object OnClickOnStarShips : HomeScreenEvents()
    data object OnClickOnVehicles : HomeScreenEvents()
}