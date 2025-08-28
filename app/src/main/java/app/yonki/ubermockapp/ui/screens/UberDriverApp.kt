package app.yonki.ubermockapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun UberDriverApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("corrida") { RideScreen(navController) }
        composable("ganhos") { EarningsScreen(navController) }
        composable("conta") { AccountScreen(navController) }
    }
}


