package com.example.guardsense.ui.screens.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.guardsense.viewmodel.AuthViewModel
import com.example.guardsense.viewmodel.ProfileState
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun UpdateInfoUser(
    authViewModel: AuthViewModel,
    onDismiss: () -> Unit
) {
    val profileState = authViewModel.profileUiState
    val user = (authViewModel.uiState as? com.example.guardsense.viewmodel.AuthState.Success)?.user

    var name by remember { mutableStateOf(user?.name ?: "") }
    var cpf by remember { mutableStateOf(user?.cpf ?: "") }
    var address by remember { mutableStateOf(user?.address ?: "") }
    var telephone by remember { mutableStateOf(user?.telephone ?: "") }
    val dateFormat = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }
    var birthDate by remember { mutableStateOf(user?.birthDate?.let { dateFormat.format(it) } ?: "") }
    var error by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Informações") },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (profileState is ProfileState.Loading) {
                    CircularProgressIndicator()
                } else {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Nome") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = cpf,
                        onValueChange = { cpf = it },
                        label = { Text("CPF") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = address,
                        onValueChange = { address = it },
                        label = { Text("Endereço") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = telephone,
                        onValueChange = { telephone = it },
                        label = { Text("Telefone") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = birthDate,
                        onValueChange = { birthDate = it },
                        label = { Text("Data de Nascimento (dd/MM/yyyy)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (error != null) {
                        Text(text = error!!)
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val date = try {
                        dateFormat.parse(birthDate)
                    } catch (e: Exception) {
                        println(e)
                        null
                    }
                    if (date == null) {
                        error = "Data de nascimento inválida."
                    } else {
                        authViewModel.updateUserInfo(name, cpf, address, telephone, date)
                    }
                }
            ) {
                Text("Atualizar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )

    when (profileState) {
        is ProfileState.Success -> {
            LaunchedEffect(Unit) {
                Toast.makeText(context, "Informações atualizadas com sucesso!", Toast.LENGTH_SHORT).show()
                onDismiss()
                authViewModel.resetProfileState()
            }
        }
        is ProfileState.Error -> {
            error = profileState.message
            println(error)
        }
        else -> {
            println("ProfileState: $profileState")
        }
    }
}