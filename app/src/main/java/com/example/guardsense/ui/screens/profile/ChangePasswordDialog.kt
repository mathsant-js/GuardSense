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
import com.example.guardsense.ui.theme.PrimaryBlue
import com.example.guardsense.viewmodel.AuthViewModel
import com.example.guardsense.viewmodel.ProfileState

@Composable
fun ChangePasswordDialog(
    authViewModel: AuthViewModel,
    onDismiss: () -> Unit
) {
    val profileState = authViewModel.profileUiState

    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Alterar Senha", color = PrimaryBlue) },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (profileState is ProfileState.Loading) {
                    CircularProgressIndicator()
                } else {
                    OutlinedTextField(
                        value = newPassword,
                        onValueChange = { newPassword = it },
                        label = { Text("Nova Senha") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = { Text("Confirmar Nova Senha") },
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
                    if (newPassword == confirmPassword) {
                        authViewModel.updatePasswordAccount(newPassword)
                        Toast.makeText(context, "Senha atualizada com sucesso!", Toast.LENGTH_SHORT).show()
                    } else {
                        error = "As senhas não coincidem."
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
                onDismiss()
                authViewModel.resetProfileState()
            }
        }
        is ProfileState.Error -> {
            error = profileState.message
            println(error)
        }
        else -> {}
    }
}