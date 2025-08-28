package com.orlando.androidbase.presentation.features.app_navigation


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.paging.compose.collectAsLazyPagingItems
import com.orlando.androidbase.R
import com.orlando.androidbase.entities.remote.Movie
import com.orlando.androidbase.entities.remote.People
import com.orlando.androidbase.entities.remote.Planet
import com.orlando.androidbase.entities.remote.Specie
import com.orlando.androidbase.entities.remote.Starship
import com.orlando.androidbase.entities.remote.Vehicle
import com.orlando.androidbase.presentation.base.BaseComposeScreen
import com.orlando.androidbase.presentation.extensions.navigationCustomArgument
import com.orlando.androidbase.presentation.features.character_detail.CharacterDetailScreen
import com.orlando.androidbase.presentation.features.components.ToolbarConfiguration
import com.orlando.androidbase.presentation.features.movies.MovieDetailScreen
import com.orlando.androidbase.presentation.features.movies.MoviesScreen
import com.orlando.androidbase.presentation.features.home.HomeScreen
import com.orlando.androidbase.presentation.features.characters.CharacterScreen
import com.orlando.androidbase.presentation.features.home.HomeScreenEvents
import com.orlando.androidbase.presentation.features.home.getMenus
import com.orlando.androidbase.presentation.features.home.homeScreenNavigate
import com.orlando.androidbase.presentation.features.planets.PlanetDetailScreen
import com.orlando.androidbase.presentation.features.planets.PlanetsScreen
import com.orlando.androidbase.presentation.features.planets.PlanetsViewModel
import com.orlando.androidbase.presentation.features.species.SpeciesDetailScreenScreen
import com.orlando.androidbase.presentation.features.species.SpeciesScreen
import com.orlando.androidbase.presentation.features.species.SpeciesViewModel
import com.orlando.androidbase.presentation.features.starships.StarShipDetailScreenScreen
import com.orlando.androidbase.presentation.features.starships.StarShipScreen
import com.orlando.androidbase.presentation.features.starships.StarshipViewModel
import com.orlando.androidbase.presentation.features.vehicles.VehicleDetailScreen
import com.orlando.androidbase.presentation.features.vehicles.VehiclesScreen
import com.orlando.androidbase.presentation.features.vehicles.VehiclesViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppNavigationRoutes.HomeScreenRoute
    ) {
        composable<AppNavigationRoutes.HomeScreenRoute> {
            BaseComposeScreen(
                navController = navController,
                toolbarConfiguration = ToolbarConfiguration(showToolbar = false)
            ) {
                HomeScreen(
                    modifier = Modifier,
                    menus = getMenus(),
                    onEvents = { event ->
                        navController.homeScreenNavigate(event)
                    }
                )
            }
        }
        composable<AppNavigationRoutes.CharactersScreenRoute> {
            BaseComposeScreen(
                navController = navController,
                toolbarConfiguration = ToolbarConfiguration(title = stringResource(R.string.characters))
            ) {
                CharacterScreen {
                    Log.w("com.example.androidbase", "NavigateToHomeScreen")
                    navController.navigate(AppNavigationRoutes.CharactersScreenDetailRoute(it))
                }
            }
        }
        composable<AppNavigationRoutes.CharactersScreenDetailRoute>(
            typeMap = mapOf(
                navigationCustomArgument<People>()
            )
        ) {
            val arguments = it.toRoute<AppNavigationRoutes.CharactersScreenDetailRoute>()
            BaseComposeScreen(
                navController = navController,
                toolbarConfiguration = ToolbarConfiguration(title = arguments.people.name)
            ) {
                CharacterDetailScreen(people = arguments.people)
            }
        }
        composable<AppNavigationRoutes.MoviesScreenRoute> {
            BaseComposeScreen(
                navController = navController,
                toolbarConfiguration = ToolbarConfiguration(title = stringResource(R.string.flims))
            ) {
                MoviesScreen {
                    navController.navigate(AppNavigationRoutes.MovieDetailScreenRoute(it))
                }
            }
        }
        composable<AppNavigationRoutes.MovieDetailScreenRoute>(
            typeMap = mapOf(
                navigationCustomArgument<Movie>()
            )
        ) {
            val arguments = it.toRoute<AppNavigationRoutes.MovieDetailScreenRoute>()
            BaseComposeScreen(
                navController = navController,
                toolbarConfiguration = ToolbarConfiguration(title = arguments.movie.title)
            ) {
                MovieDetailScreen(movie = arguments.movie)
            }
        }
        composable<AppNavigationRoutes.PlanetsScreenRoute> {
            val viewModel: PlanetsViewModel = hiltViewModel()
            val planets = viewModel.getPlanetsPagingSource.collectAsLazyPagingItems()
            BaseComposeScreen(
                navController = navController,
                toolbarConfiguration = ToolbarConfiguration(title = stringResource(R.string.planets))
            ) {
                PlanetsScreen(
                    planets = planets,
                    clickOnItem = {
                        navController.navigate(AppNavigationRoutes.PlanetDetailScreenRoute(planet = it))
                    }
                )
            }
        }
        composable<AppNavigationRoutes.PlanetDetailScreenRoute>(
            typeMap = mapOf(
                navigationCustomArgument<Planet>()
            )
        ) {
            val arguments = it.toRoute<AppNavigationRoutes.PlanetDetailScreenRoute>()
            BaseComposeScreen(
                navController = navController,
                toolbarConfiguration = ToolbarConfiguration(title = arguments.planet.name)
            ) {
                PlanetDetailScreen(planet = arguments.planet)
            }
        }
        composable<AppNavigationRoutes.SpeciesScreenRoute> {
            val viewModel: SpeciesViewModel = hiltViewModel()
            val species = viewModel.getSpeciesPagingSource.collectAsLazyPagingItems()
            BaseComposeScreen(
                navController = navController,
                toolbarConfiguration = ToolbarConfiguration(title = stringResource(R.string.species))
            ) {
                SpeciesScreen(
                    species = species,
                    clickOnItem = {
                        navController.navigate(AppNavigationRoutes.SpeciesDetailScreenRoute(specie = it))
                    }
                )
            }
        }
        composable<AppNavigationRoutes.SpeciesDetailScreenRoute>(
            typeMap = mapOf(
                navigationCustomArgument<Specie>()
            )
        ) {
            val arguments = it.toRoute<AppNavigationRoutes.SpeciesDetailScreenRoute>()
            BaseComposeScreen(
                navController = navController,
                toolbarConfiguration = ToolbarConfiguration()
            ) {
                SpeciesDetailScreenScreen(specie = arguments.specie)
            }
        }
        composable<AppNavigationRoutes.StarShipScreenRoute> {
            val viewModel: StarshipViewModel = hiltViewModel()
            val starShips = viewModel.getStarshipsPagingSource.collectAsLazyPagingItems()
            BaseComposeScreen(
                navController = navController,
                toolbarConfiguration = ToolbarConfiguration(title = stringResource(R.string.startships))
            ) {
                StarShipScreen(
                    starShips = starShips,
                    clickOnItem = {
                        navController.navigate(
                            AppNavigationRoutes.StarShipDetailScreenRoute(
                                starship = it
                            )
                        )
                    }
                )
            }
        }
        composable<AppNavigationRoutes.StarShipDetailScreenRoute>(
            typeMap = mapOf(
                navigationCustomArgument<Starship>()
            )
        ) {
            val arguments = it.toRoute<AppNavigationRoutes.StarShipDetailScreenRoute>()
            BaseComposeScreen(
                navController = navController,
                toolbarConfiguration = ToolbarConfiguration(title = arguments.starship.name)
            ) {
                StarShipDetailScreenScreen(starship = arguments.starship)
            }
        }
        composable<AppNavigationRoutes.VehiclesScreenRoute> {
            val viewModel: VehiclesViewModel = hiltViewModel()
            val vehicles = viewModel.getVehiclesPagingSource.collectAsLazyPagingItems()
            BaseComposeScreen(
                navController = navController,
                toolbarConfiguration = ToolbarConfiguration(title = stringResource(R.string.vehicles))
            ) {
                VehiclesScreen(
                    vehicles = vehicles,
                    clickOnItem = {
                        navController.navigate(AppNavigationRoutes.VehiclesScreenDetailRoute(vehicle = it))
                    }
                )
            }
        }
        composable<AppNavigationRoutes.VehiclesScreenDetailRoute>(
            typeMap = mapOf(
                navigationCustomArgument<Vehicle>()
            )
        ) {
            val arguments = it.toRoute<AppNavigationRoutes.VehiclesScreenDetailRoute>()
            BaseComposeScreen(
                navController = navController,
                toolbarConfiguration = ToolbarConfiguration(title = arguments.vehicle.name)
            ) {
                VehicleDetailScreen(vehicle = arguments.vehicle)
            }
        }
    }
}