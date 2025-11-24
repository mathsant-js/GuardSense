package com.example.guardsense.ui.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.guardsense.R
import com.example.guardsense.ui.BackgroundContainer
import com.example.guardsense.ui.components.navigation.ButtonNavigation
import com.example.guardsense.ui.components.Logo
import com.example.guardsense.ui.components.OutlinedTextFieldCommom
import com.example.guardsense.ui.components.navigation.ExtendedFloatingActionButtonCommon
import com.example.guardsense.ui.components.navigation.ExtendedFloatingActionButtonIconRight
import com.example.guardsense.ui.navigation.Routes
import com.example.guardsense.ui.theme.PrimaryBlue

@Composable
fun Register1(navController: NavController) {
    val name = remember { mutableStateOf("") }
    val cpf = remember { mutableStateOf("") }
    val endereco = remember { mutableStateOf("") }
    val telefone = remember { mutableStateOf("") }

    BackgroundContainer {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {

            Logo(140, 30)

            OutlinedTextFieldCommom(
                "Nome", type = "text", name.value, { name.value = it }
            )

            OutlinedTextFieldCommom(
                "CPF", type = "number", cpf.value, { cpf.value = it }
            )

            OutlinedTextFieldCommom(
                "Endereço", type = "text", endereco.value, { endereco.value = it }
            )

            OutlinedTextFieldCommom(
                "Telefone", type = "numberlasttextfield",  telefone.value, { telefone.value = it }
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ExtendedFloatingActionButtonCommon("Voltar", "Botão para voltar", Color.White, PrimaryBlue, navController)

                ExtendedFloatingActionButtonIconRight("Próximo", "Botão para avançar", PrimaryBlue, Color.White, navController, Routes.Register2)
            }
        }

    }
}