package com.example.guardsense.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
            .padding(16.dp)
    ) {


        Spacer(modifier = Modifier.height(16.dp))


        StatusContainersGrid()
    }
}

@Composable
fun StatusContainersGrid() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Primeira linha
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StatusContainer(
                title = "Umidade do ar",
                value = "Comum",
                backgroundColor = PrimaryBlue,
                titleColor = White,
                valueColor = GreenSuccess,
                iconRes = R.drawable.ic_humidity,
                modifier = Modifier.weight(1f).padding(end = 6.dp) // divide espaço igualmente
            )

            StatusContainer(
                title = "Umidade do ar",
                value = "Normal",
                backgroundColor = White,
                titleColor = TextDark,
                valueColor = GreenSuccess,
                iconRes = R.drawable.ic_drop,
                modifier = Modifier.weight(1f).padding(start = 6.dp)
            )
        }


        StatusContainer(
            title = "Alagamento",
            value = "Não",
            backgroundColor = White,
            titleColor = TextDark,
            valueColor = Orange,
            iconRes = R.drawable.ic_flood,
            cornerRadius = 20.dp // mais arredondado
        )

        // Terceira linha
        StatusContainer(
            title = "Presença detectada",
            value = "Não",
            backgroundColor = PrimaryBlue,
            titleColor = White,
            valueColor = Orange,
            iconRes = R.drawable.ic_presence,
            height = 100.dp
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
    height: Dp = 80.dp,
    cornerRadius: Dp = 12.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(backgroundColor, RoundedCornerShape(cornerRadius))
            .border(1.dp, BorderGray, RoundedCornerShape(cornerRadius))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = title,
                    fontFamily = ralewayFont,
                    fontSize = 12.sp,
                    color = titleColor,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = value,
                    fontSize = 14.sp,
                    color = valueColor,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
