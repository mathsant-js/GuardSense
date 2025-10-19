package com.example.guardsense.ui.screens.Settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.guardsense.R
import com.example.guardsense.ui.components.InsideHeader
import com.example.guardsense.ui.components.SettingsRow
import com.example.guardsense.ui.theme.PrimaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(navController: NavController) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Row {
            InsideHeader(navController, "Configurações")
        }

        Row(Modifier.padding(bottom = 48.dp)){
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = PrimaryBlue),
                    ) {
                        Column(modifier = Modifier.padding(vertical = 8.dp)) {
                            SettingsRow(text = "Câmera 1- Sala de Estar", icon = R.drawable.ic_camera)
                            SettingsRow(text = "Câmera 2- Quarto", icon = R.drawable.ic_camera)
                            SettingsRow(text = "Câmera 3- Cozinha", icon = R.drawable.ic_camera, showDivider = false)
                        }
                    }
                }

                item {
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(vertical = 8.dp)) {
                            SettingsRow(text = "Sensor de presença", icon = R.drawable.ic_presence, textColor = PrimaryBlue)
                            SettingsRow(text = "Sensor de gás", icon = R.drawable.ic_humidity, textColor = PrimaryBlue)
                            SettingsRow(text = "Sensor de temperatura", icon = R.drawable.ic_thermometer, textColor = PrimaryBlue)
                            SettingsRow(text = "Sensor de umidade", icon = R.drawable.ic_humiditydrop, textColor = PrimaryBlue)
                            SettingsRow(text = "Sensor de alagamento", icon = R.drawable.ic_flood, showDivider = false, textColor = PrimaryBlue)
                        }
                    }
                }

                item {
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = PrimaryBlue),
                    ) {
                        Column(modifier = Modifier.padding(vertical = 8.dp)) {
                            SettingsRow(text = "Tranca da porta", icon = R.drawable.ic_lock)
                            SettingsRow(text = "Desbloqueio por digital", icon = R.drawable.ic_fingerprint)
                            SettingsRow(text = "Desbloqueio por rec. facial", icon = R.drawable.ic_facialrecog)
                            SettingsRow(text = "Desbloqueio por senha", icon = R.drawable.ic_keypad, showDivider = false)
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(65.dp)) // Add space at the bottom
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    Settings(navController = rememberNavController())
}