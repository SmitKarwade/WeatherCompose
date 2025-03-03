package com.example.jetweather.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetweather.screens.MainScreen
import com.example.jetweather.screens.SearchScreen
import com.example.jetweather.screens.SplashScreen
import com.example.jetweather.viewmodel.WeatherViewModel


@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }

        composable("main_screen") {
            val viewModel = hiltViewModel<WeatherViewModel>()
            MainScreen(navController = navController, viewModel)
        }

        composable("search_screen") {
            SearchScreen(navController = navController)
        }
    }
}
