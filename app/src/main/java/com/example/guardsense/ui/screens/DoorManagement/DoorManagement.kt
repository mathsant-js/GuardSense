package com.example.guardsense.ui.screens.DoorManagement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.guardsense.ui.BackgroundContainer
import com.example.guardsense.ui.components.Header
import com.example.guardsense.ui.components.InsideHeader
import com.example.guardsense.ui.components.TopHeaderBar

@Composable
fun DoorManagement(navController: NavController) {
    Column {
        InsideHeader(navController)
    }
}

@Preview
@Composable
fun DoorManagementPreview() {
    DoorManagement(navController = rememberNavController())
}