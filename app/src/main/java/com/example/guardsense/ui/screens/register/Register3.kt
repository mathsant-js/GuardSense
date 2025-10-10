package com.example.guardsense.ui.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.guardsense.ui.BackgroundContainer
import com.example.guardsense.ui.components.Logo
import com.example.guardsense.ui.components.navigation.TextButtonBack
import com.example.guardsense.ui.ralewayFont
import com.example.guardsense.ui.theme.LightBlue
import com.example.guardsense.ui.theme.LittleDarkBlue

@Composable
fun Register3(navController: NavController) {
    BackgroundContainer {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.padding(40.dp))
            Logo(140, 30)
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButtonBack("Voltar", Color.White, navController)
            }
            PlanoDeluxeCard()
            Spacer(Modifier.padding(5.dp))
            PlanoComum()
            Spacer(Modifier.padding(20.dp))
        }
    }
}

@Composable
fun PlanoDeluxeCard() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .border(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.White, Color.White)
                ),
                width = 1.dp,
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(LightBlue, LittleDarkBlue)
                ),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        // HEADER BRANCO
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Plano Deluxe",
                    fontFamily = ralewayFont,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0083B0)
                )
            }
        }

        // CORPO DO CARD
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "R$ 69,99 / mês",
                fontFamily = ralewayFont,
                fontSize = 16.sp,
                color = Color.White
            )
            Spacer(Modifier.padding(5.dp))
            Button(
                onClick = { /* TODO: ação do botão */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Assine agora!", color = Color(0xFF0083B0))
            }

            Spacer(modifier = Modifier.height(16.dp))

            val beneficios = listOf(
                "4 Câmeras (monitoramento avançado)",
                "Sensor de presença",
                "Sensor de gás",
                "Sensor de temperatura",
                "Sensor de umidade",
                "Sensor de alagamento",
                "Tranca da porta",
                "Desbloqueio por digital",
                "Desbloqueio por reconhecimento facial",
                "Desbloqueio por senha",
                "Assistência profissional dedicada"
            )

            beneficios.forEach { beneficio ->
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = beneficio,
                            color = Color.White,
                            fontFamily = ralewayFont,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PlanoComum() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .border(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.White, Color.White)
                ),
                width = 1.dp,
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(LightBlue, LittleDarkBlue)
                ),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        // HEADER BRANCO
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Plano Comum",
                    fontFamily = ralewayFont,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0083B0)
                )
            }
        }

        // CORPO DO CARD
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "R$ 19,99 / mês",
                fontFamily = ralewayFont,
                fontSize = 16.sp,
                color = Color.White
            )
            Spacer(Modifier.padding(5.dp))
            Button(
                onClick = { /* TODO: ação do botão */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Assine agora!", color = Color(0xFF0083B0))
            }

            Spacer(modifier = Modifier.height(16.dp))

            val beneficios = listOf(
                "2 Câmeras (monitoramento básico)",
                "Sensor de presença",
                "Sensor de gás",
                "Tranca da porta (controle básico de acesso)",
                "Desbloqueio por senha"
            )

            beneficios.forEach { beneficio ->
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = beneficio,
                            color = Color.White,
                            fontFamily = ralewayFont,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun Register3Preview() {
    Register3(navController = rememberNavController())
}