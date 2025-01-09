package com.orlando.androidbase.presentation.features.home


sealed class HomeScreenSideEffects {
    data object GotoCharacters : HomeScreenSideEffects()
    data object GoToMovies : HomeScreenSideEffects()
    data object GoToPlanets : HomeScreenSideEffects()
    data object GoToSpecies : HomeScreenSideEffects()
    data object GoToStarShips : HomeScreenSideEffects()
    data object GoToVehicles : HomeScreenSideEffects()
}