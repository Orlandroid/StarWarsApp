package com.orlando.androidbase.presentation.features.app_navigation

import com.orlando.androidbase.entities.remote.Movie
import com.orlando.androidbase.entities.remote.People
import com.orlando.androidbase.entities.remote.Planet
import kotlinx.serialization.Serializable

sealed class AppNavigationRoutes {
    @Serializable
    data object HomeScreenRoute

    @Serializable
    data object CharactersScreenRoute

    @Serializable
    data class CharactersScreenDetailRoute(val people: People)

    @Serializable
    data object MoviesScreenRoute

    @Serializable
    data class MovieDetailScreenRoute(val movie: Movie)

    @Serializable
    data object PlanetsScreenRoute

    @Serializable
    data class PlanetDetailScreen(val planet: Planet)

    @Serializable
    data object SpeciesScreenRoute

    @Serializable
    data object StarShipScreenRoute

    @Serializable
    data object VehiclesScreenRoute
}