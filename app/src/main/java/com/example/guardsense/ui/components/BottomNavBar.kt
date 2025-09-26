package com.example.guardsense.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.guardsense.R
import com.example.guardsense.ui.ralewayFont
import com.example.guardsense.ui.theme.*

@Composable
fun BottomNavBar(
    navController: NavController,
    currentRoute: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(White)
            .border(1.dp, BorderGray)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavItem(
                title = "Home",
                iconRes = R.drawable.ic_home,
                isSelected = currentRoute == "home",
                onClick = { if (currentRoute != "home") navController.navigate("home") }
            )

            NavItem(
                title = "Segurança",
                iconRes = R.drawable.ic_security,
                isSelected = currentRoute == "security",
                onClick = { if (currentRoute != "security") navController.navigate("security") }
            )

            NavItem(
                title = "Perfil",
                iconRes = R.drawable.ic_profile,
                isSelected = currentRoute == "profile",
                onClick = { if (currentRoute != "profile") navController.navigate("profile") }
            )
        }
    }
}

@Composable
fun NavItem(
    title: String,
    iconRes: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = title,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = title,
            fontFamily = ralewayFont,
            fontSize = 12.sp,
            color = if (isSelected) PrimaryBlue else TextGray,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
        )
    }
}