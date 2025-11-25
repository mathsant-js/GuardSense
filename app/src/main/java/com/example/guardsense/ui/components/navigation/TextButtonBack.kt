package com.example.guardsense.ui.components.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.guardsense.ui.theme.ralewayFont

@Composable
fun TextButtonBack(text: String, textColor: Color, navController: NavController) {
    TextButton(
        onClick = { navController.popBackStack() }
    ) {
        Icon(
            Icons.AutoMirrored.Filled.ArrowBack,
            "Botão para voltar",
            tint = Color.White)
        Spacer(Modifier.width(3.dp))
        Text(
            text = text,
            color = textColor,
            fontFamily = ralewayFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
    }
}