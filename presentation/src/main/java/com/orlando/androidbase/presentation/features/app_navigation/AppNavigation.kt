package com.orlando.androidbase.presentation.features.app_navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.orlando.androidbase.presentation.features.home.HomeScreen
import com.orlando.androidbase.presentation.features.home.HomeViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppNavigationRoutes.HomeScreenRoute
    ) {
        composable<AppNavigationRoutes.HomeScreenRoute> {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(modifier = Modifier, menus = homeViewModel.getMenus())
        }
    }
}