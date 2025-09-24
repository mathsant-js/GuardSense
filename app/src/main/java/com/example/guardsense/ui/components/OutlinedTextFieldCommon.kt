package com.example.guardsense.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.guardsense.ui.ralewayFont
import com.example.guardsense.ui.theme.PrimaryBlue


@Composable
fun OutlinedTextFieldCommom(
    labelText: String,
    initialValue: String = "",
    onValueChange: (String) -> Unit = {}
) {
    var text by rememberSaveable { mutableStateOf(initialValue) }
    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it) // notifica quem chamou a função
        },
        label = {
            Text(labelText, fontFamily = ralewayFont, fontWeight = FontWeight.SemiBold)
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedLabelColor = PrimaryBlue,
            unfocusedLabelColor = Color.Gray,
            focusedIndicatorColor = PrimaryBlue,
            unfocusedIndicatorColor = Color.Gray
        )
    )
}