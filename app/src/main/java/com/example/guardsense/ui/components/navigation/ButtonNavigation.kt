package com.example.guardsense.ui.components.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.guardsense.ui.ralewayFont
import com.example.guardsense.ui.theme.PrimaryBlue

@Composable
fun ButtonNavigation(textButton: String, route: String) {
    val navController: NavController = rememberNavController()
    Button(
        onClick = { navController.navigate(route) },
        colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = textButton,
            color = Color.White,
            fontFamily = ralewayFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
    }
}