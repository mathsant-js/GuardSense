package com.example.guardsense.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        // Primeira linha - dois containers lado a lado
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(13.dp)
            
        ) {
            StatusContainerTopTitle(
                title = "Nível de gás",
                value = "Comum",
                backgroundColor = PrimaryBlue,
                titleColor = White,
                valueColor = GreenSuccess,
                iconRes = R.drawable.ic_humidity,
                modifier = Modifier.weight(1f),
                height = 100.dp
            )

            StatusContainerTopTitle(
                title = "Umidade do ar",
                value = "Normal",
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                titleColor = PrimaryBlue,
                valueColor = GreenSuccess,
                iconRes = R.drawable.ic_drop,
                modifier = Modifier.weight(1f),
                height = 100.dp
            )
        }

        // Container individual - Alagamento
        StatusContainerHorizontal(
            title = "Alagamento",
            value = "Não",
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            titleColor = PrimaryBlue,
            valueColor = Orange,
            iconRes = R.drawable.ic_flood,
            height = 60.dp,
            cornerRadius = 20.dp
        )

        // Container individual - Presença detectada
        StatusContainerHorizontal(
            title = "Presença detectada",
            value = "Não",
            backgroundColor = PrimaryBlue,
            titleColor = White,
            valueColor = GreenSuccess,
            iconRes = R.drawable.ic_presence,
            height = 95.dp,
            cornerRadius = 20.dp
        )
    }
}

@Composable
fun StatusContainerTopTitle(
    title: String,
    value: String,
    backgroundColor: Color,
    titleColor: Color,
    valueColor: Color,
    iconRes: Int,
    modifier: Modifier = Modifier,
    height: Dp = 110.dp,
    cornerRadius: Dp = 20.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(backgroundColor, RoundedCornerShape(cornerRadius))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = title,
                fontFamily = ralewayFont,
                fontSize = 14.sp,
                color = titleColor,
                fontWeight = FontWeight.Medium,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(10.dp))


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = title,
                    modifier = Modifier.size(32.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = value,
                    fontSize = 22.sp,
                    color = valueColor,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun StatusContainerHorizontal(
    title: String,
    value: String,
    backgroundColor: Color,
    titleColor: Color,
    valueColor: Color,
    iconRes: Int,
    modifier: Modifier = Modifier,
    height: Dp = 70.dp,
    cornerRadius: Dp = 20.dp
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
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(
                    if (title == "Presença detectada") 42.dp else 34.dp
                )
            )

            Spacer(modifier = Modifier.width(14.dp))


            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "$title:",
                    fontFamily = ralewayFont,
                    fontSize = 18.sp,
                    color = titleColor,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = value,
                    fontSize = 18.sp,
                    color = valueColor,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
            }
        }
    }
}