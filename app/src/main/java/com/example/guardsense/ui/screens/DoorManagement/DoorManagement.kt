package com.example.guardsense.ui.screens.DoorManagement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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
import com.example.guardsense.R
import com.example.guardsense.ui.components.ButtonCommom
import com.example.guardsense.ui.components.InsideHeader
import com.example.guardsense.ui.components.OutlinedTextFieldCommom
import com.example.guardsense.ui.components.SettingsRow
import com.example.guardsense.ui.components.Switch
import com.example.guardsense.ui.theme.PrimaryBlue
import com.example.guardsense.ui.theme.ralewayFont

@Composable
fun DoorManagement(navController: NavController) {
    val checked = remember { mutableStateOf(false) }
    val alterarSenhaTranca = remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
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
                Switch(
                    checked = checked,
                    checkedThumbColor = Color.White,
                    checkedTrackColor = PrimaryBlue,
                    checkedBorderColor = Color.White,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = PrimaryBlue,
                    uncheckedBorderColor = Color.White
                )
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
                    top = 16.dp,
                    bottom = 10.dp,
                    start = 30.dp,
                    end = 30.dp
                )
        ) {
            HorizontalDivider(color = PrimaryBlue, thickness = 2.dp)
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 6.dp,
                    bottom = 10.dp,
                    start = 2.dp,
                    end = 2.dp
                )
        ) {
            CardDoorManagement()
        }
    }
}

@Composable
fun CardDoorManagement() {
    Row {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            ) {
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    SettingsRow(
                        text = "Utilizar Sensor de Digital",
                        icon = R.drawable.ic_lock,
                        textColor = PrimaryBlue
                    )
                    SettingsRow(
                        text = "Desbloqueio por Aplicativo",
                        icon = R.drawable.ic_fingerprint,
                        textColor = PrimaryBlue
                    )
                    SettingsRow(
                        text = "Reconhecimento Facial",
                        icon = R.drawable.ic_facialrecog,
                        textColor = PrimaryBlue
                    )
                    SettingsRow(
                        text = "Notificação de 2ºEtapa",
                        icon = R.drawable.ic_notifications,
                        showDivider = false,
                        textColor = PrimaryBlue
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun DoorManagementPreview() {
    DoorManagement(navController = rememberNavController())
}