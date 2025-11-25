package com.example.guardsense.ui.components.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.guardsense.ui.theme.ralewayFont

@Composable
fun ExtendedFloatingActionButtonCommon(text: String, altText: String, color: Color, textColor: Color, navController: NavController,) {
    ExtendedFloatingActionButton(
        onClick = { navController.popBackStack() },
        containerColor = color,
        icon = {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                altText,
                tint = textColor
            )
        },
        text = {
            Text(
                text = text,
                color = textColor,
                fontFamily = ralewayFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        },
        modifier = Modifier
            .width(150.dp)
            .height(50.dp),
        shape = RoundedCornerShape(12.dp)
    )
}