package com.orlando.androidbase.presentation.features.app_navigation

import com.orlando.androidbase.entities.remote.Movie
import com.orlando.androidbase.entities.remote.People
import com.orlando.androidbase.entities.remote.Planet
import com.orlando.androidbase.entities.remote.Specie
import com.orlando.androidbase.entities.remote.Starship
import com.orlando.androidbase.entities.remote.Vehicle
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
    data class PlanetDetailScreenRoute(val planet: Planet)

    @Serializable
    data object SpeciesScreenRoute

    @Serializable
    data class SpeciesDetailScreenRoute(val specie: Specie)

    @Serializable
    data object StarShipScreenRoute

    @Serializable
    data class StarShipDetailScreenRoute(val starship: Starship)

    @Serializable
    data object VehiclesScreenRoute

    @Serializable
    data class VehiclesScreenDetailRoute(val vehicle: Vehicle)
}