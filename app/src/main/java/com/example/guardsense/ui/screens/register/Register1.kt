package com.example.guardsense.ui.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.guardsense.R
import com.example.guardsense.ui.BackgroundContainer
import com.example.guardsense.ui.navigation.Routes
import com.example.guardsense.ui.ralewayFont
import com.example.guardsense.ui.theme.PrimaryBlue

@Composable
fun Register1(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }

    BackgroundContainer {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Logo",
                modifier = Modifier.size(140.dp).padding(bottom = 30.dp)
            )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = {
                    Text("Nome", fontFamily = ralewayFont, fontWeight = FontWeight.SemiBold)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedLabelColor = PrimaryBlue,
                    unfocusedLabelColor = Color.Gray,
                    focusedIndicatorColor = PrimaryBlue,
                    unfocusedIndicatorColor = Color.Gray
                )
            )

            OutlinedTextField(
                value = cpf,
                onValueChange = { cpf = it },
                label = {
                    Text("CPF", fontFamily = ralewayFont, fontWeight = FontWeight.SemiBold)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedLabelColor = PrimaryBlue,
                    unfocusedLabelColor = Color.Gray,
                    focusedIndicatorColor = PrimaryBlue,
                    unfocusedIndicatorColor = Color.Gray
                )
            )

            OutlinedTextField(
                value = endereco,
                onValueChange = { endereco = it },
                label = {
                    Text("Endereço", fontFamily = ralewayFont, fontWeight = FontWeight.SemiBold)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedLabelColor = PrimaryBlue,
                    unfocusedLabelColor = Color.Gray,
                    focusedIndicatorColor = PrimaryBlue,
                    unfocusedIndicatorColor = Color.Gray
                )
            )

            OutlinedTextField(
                value = telefone,
                onValueChange = { telefone = it },
                label = {
                    Text("Telefone", fontFamily = ralewayFont, fontWeight = FontWeight.SemiBold)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedLabelColor = PrimaryBlue,
                    unfocusedLabelColor = Color.Gray,
                    focusedIndicatorColor = PrimaryBlue,
                    unfocusedIndicatorColor = Color.Gray
                )
            )

            Button(
                onClick = { navController.navigate(Routes.Register2 /* A tela Register 2 não existe ainda, crie ela antes de apertar o botão */) },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Próximo",
                    color = Color.White,
                    fontFamily = ralewayFont,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            }
        }
    }
}