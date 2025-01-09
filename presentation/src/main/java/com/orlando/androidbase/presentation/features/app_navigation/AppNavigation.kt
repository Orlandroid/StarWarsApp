package com.orlando.androidbase.presentation.features.app_navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.orlando.androidbase.presentation.base.BaseComposeScreen
import com.orlando.androidbase.presentation.features.components.ToolbarConfiguration
import com.orlando.androidbase.presentation.features.flims.MoviesScreen
import com.orlando.androidbase.presentation.features.home.HomeScreen
import com.orlando.androidbase.presentation.features.home.HomeViewModel
import com.orlando.androidbase.presentation.features.planets.PlanetsScreen
import com.orlando.androidbase.presentation.features.species.CharacterScreen
import com.orlando.androidbase.presentation.features.species.SpeciesScreen
import com.orlando.androidbase.presentation.features.starships.StarShipScreen
import com.orlando.androidbase.presentation.features.vehicles.VehiclesScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppNavigationRoutes.HomeScreenRoute
    ) {
        composable<AppNavigationRoutes.HomeScreenRoute> {
            val homeViewModel: HomeViewModel = hiltViewModel()
            BaseComposeScreen(
                navController = navController,
                toolbarConfiguration = ToolbarConfiguration(showToolbar = false)
            ) {
                HomeScreen(modifier = Modifier, menus = homeViewModel.getMenus())
            }
        }
        composable<AppNavigationRoutes.CharactersScreenRoute> {
            CharacterScreen()
        }
        composable<AppNavigationRoutes.MoviesScreenRoute> {
            MoviesScreen()
        }
        composable<AppNavigationRoutes.PlanetsScreenRoute> {
            PlanetsScreen()
        }
        composable<AppNavigationRoutes.SpeciesScreenRoute> {
            SpeciesScreen()
        }
        composable<AppNavigationRoutes.StarShipScreenRoute> {
            StarShipScreen()
        }
        composable<AppNavigationRoutes.VehiclesScreenRoute> {
            VehiclesScreen()
        }
    }
}