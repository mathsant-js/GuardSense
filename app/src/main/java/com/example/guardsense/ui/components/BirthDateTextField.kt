package com.example.guardsense.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import com.example.guardsense.ui.theme.PrimaryBlue
import com.example.guardsense.ui.visualtransformations.DateVisualTransformation
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BirthDateTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onDateValidated: (LocalDate?, String?) -> Unit
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
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
        value = value,
        onValueChange = {
            if (it.length <= 8) {
                onValueChange(it)
                if (it.length == 8) {
                    try {
                        val formatter = DateTimeFormatter.ofPattern("ddMMyyyy")
                        val date = LocalDate.parse(it, formatter)
                        val age = Period.between(date, LocalDate.now()).years
                        if (age >= 18) {
                            onDateValidated(date, null)
                        } else {
                            onDateValidated(null, "Você deve ter no mínimo 18 anos.")
                        }
                    } catch (e: DateTimeParseException) {
                        onDateValidated(null, "Data inválida.")
                        println("[ERRO] Algo de errado com a data: $e")
                    }
                } else {
                    onDateValidated(null, null)
                }
            }
        },
        label = { Text("Data de Nascimento") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = DateVisualTransformation()
    )
}