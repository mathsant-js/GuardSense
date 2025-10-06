package com.example.guardsense.ui.screens.LogIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.guardsense.R
import com.example.guardsense.ui.components.Logo
import com.example.guardsense.ui.components.OutlinedTextFieldCommom
import com.example.guardsense.ui.components.navigation.ButtonNavigation
import com.example.guardsense.ui.components.navigation.ExtendedFloatingActionButtonCommon
import com.example.guardsense.ui.components.navigation.ExtendedFloatingActionButtonIconRight
import com.example.guardsense.ui.navigation.Routes
import com.example.guardsense.ui.theme.DarkBlue
import com.example.guardsense.ui.theme.PrimaryBlue

val ralewayFont = FontFamily(
    Font(R.font.raleway_semibold, FontWeight.SemiBold)
)
@Composable
fun BackgroundContainer(content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(PrimaryBlue, DarkBlue)
                )
            ),
        contentAlignment = Alignment.Center
    ) { content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val senha = remember { mutableStateOf("") }

    BackgroundContainer {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Logo(140, 30)

            OutlinedTextFieldCommom("Email", type = "email", email.value, { email.value = it })

            OutlinedTextFieldCommom("Senha", type = "password", senha.value, { senha.value = it })

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ButtonNavigation("Entrar", navController, Routes.LogInScreen)
            }
        }
    }
}