package com.example.guardsense.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.guardsense.ui.SignInScreen
import com.example.guardsense.ui.screens.register.Register1

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.SignInScreen
    ) {
        composable(Routes.SignInScreen) {
            SignInScreen(navController)
        }
        composable(Routes.Register1) {
            Register1(navController)
        }
        composable(Routes.Register2) {
            // Tela Register #2
        }
        composable(Routes.Register3) {
            // Tela Register #3
        }
        composable(Routes.Dashboard) {
            // Tela de Dashboard
        }
        composable(Routes.Monitoring) {
            // Tela de monitoramento
        }
        composable(Routes.DoorManagement) {
            // Tela de Gerenciamento da porta
        }
        composable(Routes.Perfil) {
            // Tela de Perfil
        }
        composable(Routes.Configurarions) {
            // Tela de Configurações
        }
    }
}