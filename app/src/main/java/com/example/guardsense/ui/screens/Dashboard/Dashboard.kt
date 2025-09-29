package com.example.guardsense.ui.screens.Dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.guardsense.R
import com.example.guardsense.ui.components.BottomNavBar
import com.example.guardsense.ui.components.Header
import com.example.guardsense.ui.components.HomeContentCard
import com.example.guardsense.ui.components.MainTemperatureContainer

@Composable
fun Dashboard(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Imagem de fundo cobrindo TUDO
        Image(
            painter = painterResource(id = R.drawable.ic_background_home),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Header()

            Spacer(modifier = Modifier.height(35.dp))

            MainTemperatureContainer()


            HomeContentCard()

            Spacer(modifier = Modifier.height(70.dp))
        }

        // Bottom Navigation Bar componentizada
        BottomNavBar(
            navController = navController,
            currentRoute = "home",
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}