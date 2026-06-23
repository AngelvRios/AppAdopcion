package com.app.project.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.project.presentation.screens.HomeScreen
import com.app.project.presentation.screens.LoginScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                viewModel = hiltViewModel(),
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = { /* Navigate to register */ }
            )
        }
        composable("home") {
            HomeScreen(
                viewModel = hiltViewModel(),
                onPetClick = { /* Navigate to detail */ }
            )
        }
    }
}
