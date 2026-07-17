package com.app.project.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.project.presentation.screens.*
import com.app.project.presentation.viewmodel.AuthViewModel
import com.app.project.presentation.viewmodel.PetViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            val authViewModel: AuthViewModel = hiltViewModel()
            LoginScreen(
                viewModel = authViewModel,
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = { 
                    navController.navigate("register")
                }
            )
        }
        composable("register") {
            val authViewModel: AuthViewModel = hiltViewModel()
            RegisterScreen(
                viewModel = authViewModel,
                onRegisterSuccess = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }
        composable("home") {
            val petViewModel: PetViewModel = hiltViewModel()
            HomeScreen(
                viewModel = petViewModel,
                onPetClick = { pet ->
                    navController.navigate("pet_detail/${pet.id}")
                },
                onAddPetClick = {
                    navController.navigate("add_edit_pet")
                }
            )
        }
        composable(
            route = "pet_detail/{petId}",
            arguments = listOf(navArgument("petId") { type = NavType.IntType })
        ) { backStackEntry ->
            val petId = backStackEntry.arguments?.getInt("petId") ?: return@composable
            val petViewModel: PetViewModel = hiltViewModel()
            PetDetailScreen(
                petId = petId,
                viewModel = petViewModel,
                onBack = { navController.popBackStack() },
                onEditPet = { id ->
                    navController.navigate("add_edit_pet?petId=$id")
                },
                onDeletePetSuccess = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = "add_edit_pet?petId={petId}",
            arguments = listOf(navArgument("petId") { 
                type = NavType.IntType
                defaultValue = -1 
            })
        ) { backStackEntry ->
            val petId = backStackEntry.arguments?.getInt("petId") ?: -1
            val petViewModel: PetViewModel = hiltViewModel()
            
            // Trigger load if editing
            androidx.compose.runtime.LaunchedEffect(petId) {
                if (petId != -1) {
                    petViewModel.getPetById(petId)
                }
            }

            AddEditPetScreen(
                viewModel = petViewModel,
                onBack = { navController.popBackStack() },
                onSaveSuccess = { navController.popBackStack() }
            )
        }
    }
}
