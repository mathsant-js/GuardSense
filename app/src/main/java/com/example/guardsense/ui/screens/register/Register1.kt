package com.example.guardsense.ui.screens.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.guardsense.ui.BackgroundContainer
import com.example.guardsense.ui.components.ButtonNavigation
import com.example.guardsense.ui.components.Logo
import com.example.guardsense.ui.components.OutlinedTextFieldCommom
import com.example.guardsense.ui.navigation.Routes

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
                "Nome", name.value, { name.value = it }
            )

            OutlinedTextFieldCommom(
                "CPF", cpf.value, { cpf.value = it }
            )

            OutlinedTextFieldCommom(
                "Endereço", endereco.value, { endereco.value = it }
            )

            OutlinedTextFieldCommom(
                "Telefone", telefone.value, { telefone.value = it }
            )

            ButtonNavigation("Próximo", navController, Routes.Register2)
        }
    }
}