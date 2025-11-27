package com.example.guardsense.ui.screens.register

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.guardsense.ui.BackgroundContainer
import com.example.guardsense.ui.components.BirthDateTextField
import com.example.guardsense.ui.components.Logo
import com.example.guardsense.ui.components.OutlinedTextFieldCommom
import com.example.guardsense.ui.components.navigation.ExtendedFloatingActionButtonCommon
import com.example.guardsense.ui.components.navigation.ExtendedFloatingActionButtonIconRight
import com.example.guardsense.ui.navigation.Routes
import com.example.guardsense.ui.theme.PrimaryBlue
import com.example.guardsense.viewmodel.AuthViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Register1(navController: NavController, viewModel: AuthViewModel) {
    val name = remember { mutableStateOf(viewModel.name) }
    val cpf = remember { mutableStateOf(viewModel.cpf) }
    val endereco = remember { mutableStateOf(viewModel.address) }
    val telefone = remember { mutableStateOf(viewModel.telephone) }

    var birthDateInput by remember { mutableStateOf("") }
    var validDate by remember { mutableStateOf<LocalDate?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

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

            BirthDateTextField(
                value = birthDateInput,
                onValueChange = { birthDateInput = it },
                onDateValidated = { date, error ->
                    validDate = date
                    errorMessage = error
                }
            )

            if (errorMessage != null) {
                Text(
                    text = errorMessage!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ExtendedFloatingActionButtonCommon("Voltar", "Botão para voltar", Color.White, PrimaryBlue, navController)

                ExtendedFloatingActionButtonIconRight("Próximo", "Botão para avançar", PrimaryBlue, Color.White,
                    onClick = {
                        if (validDate != null) {
                            viewModel.updateName(name.value)
                            viewModel.updateCPF(cpf.value)
                            viewModel.updateAddress(endereco.value)
                            viewModel.updateTelephone(telefone.value)
                            validDate?.let {
                                val formattedDate = it.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                                viewModel.updateBirthDate(formattedDate)
                            }
                            navController.navigate(Routes.Register2)
                        }
                    }
                )
            }
        }
    }
}