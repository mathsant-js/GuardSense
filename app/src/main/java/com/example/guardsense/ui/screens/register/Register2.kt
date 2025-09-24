package com.example.guardsense.ui.screens.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.guardsense.ui.BackgroundContainer
import com.example.guardsense.ui.components.Logo
import com.example.guardsense.ui.components.OutlinedTextFieldCommom
import com.example.guardsense.ui.navigation.Routes
import com.example.guardsense.ui.ralewayFont
import com.example.guardsense.ui.theme.PrimaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register2(navController: NavController) {
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
                ExtendedFloatingActionButton(
                    onClick = { navController.popBackStack() },
                    containerColor = Color.White,
                    icon = {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            "Botão para voltar",
                            tint = PrimaryBlue
                        )
                    },
                    text = {
                        Text(
                            text = "Voltar",
                            color = PrimaryBlue,
                            fontFamily = ralewayFont,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                    },
                    modifier = Modifier
                        .width(150.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp)
                )
                ExtendedFloatingActionButton(
                    onClick = { navController.navigate(Routes.Register3) },
                    containerColor = PrimaryBlue,
                    icon = {},
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Próximo",
                                color = Color.White,
                                fontFamily = ralewayFont,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Botão para avançar", tint = Color.White)
                        }
                    },
                    modifier = Modifier
                        .width(150.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp)
                )
            }
        }
    }
}