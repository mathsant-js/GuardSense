package com.example.guardsense.ui.components.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.guardsense.ui.ralewayFont

@Composable
fun TextButtonBack(text: String, textColor: Color) {
    val navController: NavController = rememberNavController()
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