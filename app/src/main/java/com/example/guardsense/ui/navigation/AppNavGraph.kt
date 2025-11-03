
package com.example.guardsense.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.guardsense.ui.SignInScreen
import com.example.guardsense.ui.screens.Dashboard.Dashboard
import com.example.guardsense.ui.screens.DoorManagement.DoorManagement
import com.example.guardsense.ui.screens.LogIn.LogInScreen
import com.example.guardsense.ui.screens.Monitoring.Monitoring
import com.example.guardsense.ui.screens.Settings.Settings
import com.example.guardsense.ui.screens.profile.Profile
import com.example.guardsense.ui.screens.register.Register1
import com.example.guardsense.ui.screens.register.Register2
import com.example.guardsense.ui.screens.register.Register3
@Composable
fun AppNavGraph(
    navController: NavHostController,
    onToggleTheme: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Dashboard
    ) {
        composable(Routes.SignInScreen) {
            SignInScreen(navController)
        }
        composable(Routes.Register1) {
            Register1(navController)
        }
        composable(Routes.Register2) {
            Register2(navController)
        }
        composable(Routes.Register3) {
            Register3(navController)
        }
        composable(Routes.LogInScreen) {
            LogInScreen(navController)
        }
        composable(Routes.Dashboard) {
            Dashboard(navController)
        }
        composable(Routes.Monitoring) {
            Monitoring(navController)
        }
        composable(Routes.DoorManagement) {
            DoorManagement(navController)
        }
        composable(Routes.Profile) {
            Profile(navController, onToggleTheme)
        }
        composable(Routes.Settings) {
            Settings(navController)
        }
    }
}
