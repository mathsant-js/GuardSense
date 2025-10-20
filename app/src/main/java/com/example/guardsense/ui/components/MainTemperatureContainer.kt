package com.example.guardsense.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guardsense.R
import com.example.guardsense.ui.ralewayFont
import com.example.guardsense.ui.theme.Orange
import com.example.guardsense.ui.theme.PrimaryBlue
import com.example.guardsense.ui.theme.TextDark

@Composable
fun MainTemperatureContainer() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
            .padding(vertical = 15.dp)
            .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(16.dp))
            .padding(15.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Título
            Text(
                text = "Temperatura média da casa",
                fontFamily = ralewayFont,
                fontSize = 14.sp,
                color = PrimaryBlue,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_temperature),
                    contentDescription = "Temperatura",
                    modifier = Modifier.size(55.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "27 °C",
                    fontSize = 55.sp,
                    color = Orange,
                    fontWeight = FontWeight.Bold
                )
            }

            // Histórico
            Text(
                text = "Histórico",
                fontFamily = ralewayFont,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(2.dp))

            // Linha do histórico
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HistoryItem("Seg", "27 °C")
                HistoryItem("Ter","27 °C")
                HistoryItem("Qua", "27 °C")
                HistoryItem("Qui", "27 °C")
                HistoryItem("Sex", "27 °C")
            }
        }
    }
}

@Composable
fun HistoryItem(day: String, temperature: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(50.dp)
    ) {
        Text(
            text = day,
            fontFamily = ralewayFont,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = temperature,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold
        )
    }
}