package com.example.guardsense.ui.screens.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.guardsense.ui.BackgroundContainer
import com.example.guardsense.ui.components.Logo
import com.example.guardsense.ui.components.OutlinedTextFieldCommom
import com.example.guardsense.ui.components.navigation.ExtendedFloatingActionButtonCommon
import com.example.guardsense.ui.components.navigation.ExtendedFloatingActionButtonIconRight
import com.example.guardsense.ui.navigation.Routes
import com.example.guardsense.ui.theme.PrimaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register2(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val senha = remember { mutableStateOf("") }
    val confirmarSenha = remember { mutableStateOf("") }

    BackgroundContainer {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth(0.8f)

        ) {
            Logo(140, 30)

            Spacer(Modifier.height(16.dp))

            OutlinedTextFieldCommom("Email", type = "email", email.value, { email.value = it })

            OutlinedTextFieldCommom("Senha", type = "password", senha.value, { senha.value = it })

            OutlinedTextFieldCommom("Confimar Senha", type = "password", confirmarSenha.value, { confirmarSenha.value = it })

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ExtendedFloatingActionButtonCommon("Voltar", "Botão para voltar", Color.White, PrimaryBlue, navController)

                ExtendedFloatingActionButtonIconRight("Próximo", "Botão para avançar", PrimaryBlue, Color.White, navController, Routes.Register3)
            }
        }
    }
}

@Preview
@Composable
fun Register2Preview() {
    Register2(navController = rememberNavController())
}