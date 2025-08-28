package com.orlando.androidbase.presentation.features.home

import androidx.navigation.NavController
import com.orlando.androidbase.R
import com.orlando.androidbase.presentation.features.app_navigation.AppNavigationRoutes

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

fun NavController.homeScreenNavigate(events: HomeScreenEvents) {
    when (events) {
        HomeScreenEvents.OnClickOnCharacters -> {
            navigate(AppNavigationRoutes.CharactersScreenRoute)
        }

        HomeScreenEvents.OnClickOnMovies -> {
            navigate(AppNavigationRoutes.MoviesScreenRoute)
        }

        HomeScreenEvents.OnClickOnPlanets -> {
            navigate(AppNavigationRoutes.PlanetsScreenRoute)
        }

        HomeScreenEvents.OnClickOnSpecies -> {
            navigate(AppNavigationRoutes.SpeciesScreenRoute)
        }

        HomeScreenEvents.OnClickOnStarShips -> {
            navigate(AppNavigationRoutes.StarShipScreenRoute)
        }

        HomeScreenEvents.OnClickOnVehicles -> {
            navigate(AppNavigationRoutes.VehiclesScreenRoute)
        }
    }
}