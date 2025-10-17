package com.example.guardsense

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.guardsense.data.local.preferences.Preferences
import com.example.guardsense.ui.components.BottomNavBar
import com.example.guardsense.ui.navigation.AppNavGraph
import com.example.guardsense.ui.theme.GuardSenseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferences = Preferences(this)

        setContent {
            var isDarkTheme by remember {
                mutableStateOf(preferences.getColorTheme() == "dark")
            }

            GuardSenseTheme(darkTheme = isDarkTheme) {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                // código para a barra de navegação abaixo apareça em algumas telas e não em outras.
                val showBottomNavBar = when (currentRoute) {
                    "signInScreen" -> false
                    "register1" -> false
                    "register2" -> false
                    "register3" -> false
                    "login" -> false
                    else -> true
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (showBottomNavBar) {
                            BottomNavBar(navController = navController, currentRoute = "home", modifier = Modifier)
                        }
                    }
                ) { paddingValues ->
                    AppNavGraph(
                        navController = navController,
                        onToggleTheme = {
                            isDarkTheme = !isDarkTheme
                            preferences.setColorTheme(if (isDarkTheme) "dark" else "light")
                        }
                    )
                }
            }
        }
    }
}

