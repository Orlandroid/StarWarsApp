package com.orlando.androidbase.presentation.features.app_navigation

import kotlinx.serialization.Serializable

sealed class AppNavigationRoutes {
    @Serializable
    data object HomeScreenRoute

    @Serializable
    data object CharactersScreenRoute

    @Serializable
    data object MoviesScreenRoute

    @Serializable
    data object PlanetsScreenRoute

    @Serializable
    data object SpeciesScreenRoute

    @Serializable
    data object StarShipScreenRoute

    @Serializable
    data object VehiclesScreenRoute
}