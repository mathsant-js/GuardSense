package com.example.guardsense.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.guardsense.ui.ralewayFont
import com.example.guardsense.ui.theme.PrimaryBlue

@Composable
fun OutlinedTextFieldCommom(
    labelText: String,
    type: String = "text",
    initialValue: String = "",
    onValueChange: (String) -> Unit = {}
) {
    var text by rememberSaveable { mutableStateOf(initialValue) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val (keyboardOptions, visualTransformation) = when (type.lowercase()) {
        "email" -> KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ) to VisualTransformation.None

        "password" -> KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ) to if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()

        "number" -> KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ) to VisualTransformation.None

        "numberlasttextfield" -> KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ) to VisualTransformation.None

        "textlasttextfield" -> KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ) to VisualTransformation.None

        else -> KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ) to VisualTransformation.None
    }


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
        ),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        trailingIcon = {
            if (type.lowercase() == "password") {
                val image = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(image, contentDescription = null)
                }
            }
        }
    )
}