package com.example.guardsense.ui.screens.DoorManagement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.guardsense.ui.components.ButtonCommom
import com.example.guardsense.ui.components.InsideHeader
import com.example.guardsense.ui.components.OutlinedTextFieldCommom
import com.example.guardsense.ui.components.Switch
import com.example.guardsense.ui.ralewayFont
import com.example.guardsense.ui.theme.PrimaryBlue

@Composable
fun DoorManagement(navController: NavController) {
    val checked = remember { mutableStateOf(true) }
    val alterarSenhaTranca = remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        InsideHeader(navController, "Alterar Senha da Tranca")
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 60.dp,
                        bottom = 10.dp,
                        start = 30.dp,
                        end = 30.dp
                    )
            ) {
                Text(
                    text = "Notificações",
                    color = PrimaryBlue,
                    fontFamily = ralewayFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                )
                Switch(checked = checked, checkedThumbColor = PrimaryBlue, checkedTrackColor = Color.White, checkedBorderColor = PrimaryBlue, uncheckedThumbColor = PrimaryBlue, uncheckedTrackColor = Color.White, uncheckedBorderColor = PrimaryBlue)
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 0.dp,
                        bottom = 10.dp,
                        start = 30.dp,
                        end = 30.dp
                    )
            ) {
                OutlinedTextFieldCommom(
                    "Redefinir a senha da tranca",
                    type = "textlasttextfield",
                    initialValue = alterarSenhaTranca.value,
                    onValueChange = { alterarSenhaTranca.value = it }
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 6.dp,
                        bottom = 10.dp,
                        start = 30.dp,
                        end = 30.dp
                    )
            ) {
                ButtonCommom(textButton = "Concluído")
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 6.dp,
                    bottom = 10.dp,
                    start = 30.dp,
                    end = 30.dp
                )
        ) {
            HorizontalDivider(color = PrimaryBlue, thickness = 2.dp)
        }
    }
}

@Preview
@Composable
fun DoorManagementPreview() {
    DoorManagement(navController = rememberNavController())
}