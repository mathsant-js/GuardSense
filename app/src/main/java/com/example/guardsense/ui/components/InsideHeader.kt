package com.example.guardsense.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.guardsense.R
import com.example.guardsense.ui.theme.PrimaryBlue
import com.example.guardsense.ui.theme.ralewayFont

@Composable
fun InsideHeader(
    navController: NavController,
    title: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(60.dp)
            .background(MaterialTheme.colorScheme.background)
            .drawBehind {
                val strokeWidth = 2.dp.toPx()
                val y = size.height - strokeWidth / 2
                drawLine(
                    color = PrimaryBlue,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = strokeWidth
                )
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Voltar",
                        tint = PrimaryBlue,
                        modifier = Modifier.size(40.dp)
                    )
                }
                Text(
                    text = title,
                    color = PrimaryBlue,
                    fontFamily = ralewayFont,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            }

            // Ícone de notificação
            IconButton(
                onClick = { TODO("Criar a ação do ícone de notificações") }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_notifications),
                    contentDescription = "Notificações",
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun InsideHeaderPreview() {
    InsideHeader(navController = rememberNavController(), "test")
}