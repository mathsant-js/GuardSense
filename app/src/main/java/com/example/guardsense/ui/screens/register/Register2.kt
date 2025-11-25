package com.example.guardsense.ui.screens.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.guardsense.ui.BackgroundContainer
import com.example.guardsense.ui.components.Logo
import com.example.guardsense.ui.components.OutlinedTextFieldCommom
import com.example.guardsense.ui.components.navigation.ExtendedFloatingActionButtonCommon
import com.example.guardsense.ui.components.navigation.ExtendedFloatingActionButtonIconRight
import com.example.guardsense.ui.theme.PrimaryBlue
import com.example.guardsense.viewmodel.AuthState
import com.example.guardsense.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register2(navController: NavController, viewModel: AuthViewModel) {
    val state = viewModel.uiState

    val email = remember { mutableStateOf(viewModel.email) }
    val senha = remember { mutableStateOf(viewModel.password) }
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

            OutlinedTextFieldCommom(
                "Confimar Senha",
                type = "password",
                confirmarSenha.value,
                { confirmarSenha.value = it })

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ExtendedFloatingActionButtonCommon(
                    "Voltar",
                    "Botão para voltar",
                    Color.White,
                    PrimaryBlue,
                    navController
                )

                ExtendedFloatingActionButtonIconRight(
                    "Próximo", "Botão para avançar", PrimaryBlue, Color.White,
                    onClick = {
                        viewModel.updateEmail(email.value)
                        viewModel.updatePassword(senha.value)
                        viewModel.register()
                    }
                )
            }
            when (state) {
                is AuthState.Loading -> CircularProgressIndicator()
                is AuthState.Success -> {
                    Text("Cadastro concluído! Verifique seu e-mail.")
                }

                is AuthState.Error -> {
                    Text("Erro: ${state.message}")
                }

                else -> {
                    println("[CASO INESPERADO] - State: $state")
                }
            }
        }
    }
}