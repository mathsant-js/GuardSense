package com.example.guardsense

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.guardsense.ui.Register0
import com.example.guardsense.ui.Register1
//import com.example.guardsense.ui.Register2
//import com.example.guardsense.ui.Register3

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "register0") {
                composable("register0") { Register0(navController) }
                composable("register1") { Register1(navController) }
//                composable("register2") { Register2(navController) }
//                composable("register3") { Register3(navController) }

            }
        }
    }
}

