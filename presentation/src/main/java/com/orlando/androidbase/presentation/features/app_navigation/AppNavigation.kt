package com.orlando.androidbase.presentation.features.app_navigation


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
import com.orlando.androidbase.presentation.base.BaseComposeScreen
import com.orlando.androidbase.presentation.extensions.navigationCustomArgument
import com.orlando.androidbase.presentation.features.character_detail.CharacterDetailScreen
import com.orlando.androidbase.presentation.features.components.ToolbarConfiguration
import com.orlando.androidbase.presentation.features.flims.MovieDetailScreen
import com.orlando.androidbase.presentation.features.flims.MoviesScreen
import com.orlando.androidbase.presentation.features.home.HomeScreen
import com.orlando.androidbase.presentation.features.home.HomeScreenSideEffects
import com.orlando.androidbase.presentation.features.home.HomeViewModel
import com.orlando.androidbase.presentation.features.people.CharacterScreen
import com.orlando.androidbase.presentation.features.planets.PlanetsScreen
import com.orlando.androidbase.presentation.features.planets.PlanetsViewModel
import com.orlando.androidbase.presentation.features.species.SpeciesScreen
import com.orlando.androidbase.presentation.features.species.SpeciesViewModel
import com.orlando.androidbase.presentation.features.starships.StarShipScreen
import com.orlando.androidbase.presentation.features.starships.StarshipViewModel
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
            val homeViewModel: HomeViewModel = hiltViewModel()
            LaunchedEffect(homeViewModel) {
                homeViewModel.homeScreenSideEffects.collect { effect ->
                    when (effect) {
                        HomeScreenSideEffects.GoToMovies -> {
                            navController.navigate(AppNavigationRoutes.MoviesScreenRoute)
                        }

                        HomeScreenSideEffects.GoToPlanets -> {
                            navController.navigate(AppNavigationRoutes.PlanetsScreenRoute)
                        }

                        HomeScreenSideEffects.GoToSpecies -> {
                            navController.navigate(AppNavigationRoutes.SpeciesScreenRoute)
                        }

                        HomeScreenSideEffects.GoToStarShips -> {
                            navController.navigate(AppNavigationRoutes.StarShipScreenRoute)
                        }

                        HomeScreenSideEffects.GoToVehicles -> {
                            navController.navigate(AppNavigationRoutes.VehiclesScreenRoute)
                        }

                        HomeScreenSideEffects.GotoCharacters -> {
                            navController.navigate(AppNavigationRoutes.CharactersScreenRoute)
                        }
                    }
                }
            }
            BaseComposeScreen(
                navController = navController,
                toolbarConfiguration = ToolbarConfiguration(showToolbar = false)
            ) {
                HomeScreen(
                    modifier = Modifier,
                    menus = homeViewModel.getMenus(),
                    onEvents = { event ->
                        homeViewModel.onEvents(event)
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
            BaseComposeScreen(navController = navController) {
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
                toolbarConfiguration = ToolbarConfiguration(title = stringResource(R.string.flims))
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
                        navController.navigate(AppNavigationRoutes.PlanetsScreenRoute)
                    }
                )
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
                    clickOnItem = {}
                )
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
                    clickOnItem = {}
                )
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
                    clickOnItem = {}
                )
            }
        }
    }
}