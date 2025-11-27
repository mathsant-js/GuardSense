package com.example.guardsense.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.guardsense.ui.SignInScreen
import com.example.guardsense.ui.screens.Dashboard.Dashboard
import com.example.guardsense.ui.screens.DoorManagement.DoorManagement
import com.example.guardsense.ui.screens.EntryScreen.EntryScreen
import com.example.guardsense.ui.screens.LogIn.LogInScreen
import com.example.guardsense.ui.screens.Monitoring.Monitoring
import com.example.guardsense.ui.screens.Settings.Settings
import com.example.guardsense.ui.screens.profile.Profile
import com.example.guardsense.ui.screens.register.Register1
import com.example.guardsense.ui.screens.register.Register2
import com.example.guardsense.ui.screens.register.Register3
import com.example.guardsense.viewmodel.AuthState
import com.example.guardsense.viewmodel.AuthViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavGraph(
    navController: NavHostController,
    onToggleTheme: () -> Unit
) {
    val authViewModel : AuthViewModel = viewModel()
    val authState = authViewModel.uiState

    // This LaunchedEffect will handle automatic navigation based on the auth state.
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Success -> {
                // When login is successful, navigate to Dashboard and clear the back stack.
                navController.navigate(Routes.Dashboard) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
            }
            is AuthState.Error, AuthState.Idle -> {
                // If the user is logged out or an error occurs, navigate to the login screen.
                if (navController.currentDestination?.route != Routes.LogInScreen) {
                    navController.navigate(Routes.LogInScreen) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            }

            AuthState.NeedsEmailVerification -> {
                navController.navigate(Routes.LogInScreen) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
            }

            AuthState.Loading -> {
                // The loading indicator is shown globally below.
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = Routes.LogInScreen
        ) {
            composable(Routes.SignInScreen) {
                SignInScreen(navController)
            }
            composable(Routes.Register1) {
                Register1(navController, authViewModel)
            }
            composable(Routes.Register2) {
                Register2(navController, authViewModel)
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
                Profile(navController, authViewModel, onToggleTheme)
            }
            composable(Routes.Settings) {
                Settings(navController)
            }
            composable(Routes.EntryScreen) {
                EntryScreen(navController, authViewModel)
            }
        }
        if (authState == AuthState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}