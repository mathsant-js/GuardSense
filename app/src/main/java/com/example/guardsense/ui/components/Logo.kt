package com.example.guardsense.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.guardsense.R

@Composable
fun Logo(size: Int, padding: Int) {
    Image(
        painter = painterResource(id = R.drawable.ic_logo),
        contentDescription = "Logo",
        Modifier
            .size(size.dp)
            .padding(bottom = padding.dp)
    )
}