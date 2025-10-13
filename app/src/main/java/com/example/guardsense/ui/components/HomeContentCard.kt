package com.example.guardsense.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guardsense.R
import com.example.guardsense.ui.ralewayFont
import com.example.guardsense.ui.theme.*

@Composable
fun HomeContentCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        StatusContainersGrid()
    }
}

@Composable
fun StatusContainersGrid() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Primeira linha - dois containers lado a lado
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(13.dp)
        ) {
            StatusContainer(
                title = "Nível de gás",
                value = "Comum",
                backgroundColor = PrimaryBlue,
                titleColor = White,
                valueColor = GreenSuccess,
                iconRes = R.drawable.ic_humidity,
                modifier = Modifier.weight(1f),
                height = 110.dp
            )

            StatusContainer(
                title = "Umidade do ar",
                value = "Normal",
                backgroundColor = White,
                titleColor = PrimaryBlue,
                valueColor = GreenSuccess,
                iconRes = R.drawable.ic_drop,
                modifier = Modifier.weight(1f),
                height = 110.dp
            )
        }

        // Container individual - Alagamento
        StatusContainer(
            title = "Alagamento",
            value = "Não",
            backgroundColor = White,
            titleColor = PrimaryBlue,
            valueColor = Orange,
            iconRes = R.drawable.ic_flood,
            height = 100.dp,
            cornerRadius = 20.dp,
            showColon = true
        )

        // Container individual - Presença detectada
        StatusContainer(
            title = "Presença detectada",
            value = "Não",
            backgroundColor = PrimaryBlue,
            titleColor = White,
            valueColor = Orange,
            iconRes = R.drawable.ic_presence,
            height = 100.dp,
            cornerRadius = 20.dp,
            showColon = true
        )
    }
}

@Composable
fun StatusContainer(
    title: String,
    value: String,
    backgroundColor: Color,
    titleColor: Color,
    valueColor: Color,
    iconRes: Int,
    modifier: Modifier = Modifier,
    height: Dp = 100.dp,
    cornerRadius: Dp = 20.dp,
    showColon: Boolean = false
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(backgroundColor, RoundedCornerShape(cornerRadius))

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(44.dp)
            )

            Spacer(modifier = Modifier.width(20.dp))

            Column {
                Text(
                    text = if (showColon) "$title:" else title,
                    fontFamily = ralewayFont,
                    fontSize = 15.sp,
                    color = titleColor,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = value,
                    fontSize = 20.sp,
                    color = valueColor,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}