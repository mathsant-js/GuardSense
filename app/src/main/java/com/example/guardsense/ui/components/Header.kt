package com.example.guardsense.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guardsense.R
import com.example.guardsense.ui.ralewayFont
import com.example.guardsense.ui.theme.*

@Composable
fun Header() {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopHeaderBar()
    }
}

@Composable
fun TopHeaderBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(White)
            .border(2.dp, BorderGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_logo_mini),
                contentDescription = "Logo",
                modifier = Modifier.size(50.dp)
            )

            // Ícone de notificação
            Image(
                painter = painterResource(id = R.drawable.ic_notifications),
                contentDescription = "Notificações",
                modifier = Modifier.size(40.dp)
            )
        }
    }
}
