package com.example.guardsense.ui.components

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color

@Composable
fun Switch(
    checked: MutableState<Boolean>,
    checkedThumbColor: Color,
    checkedTrackColor: Color,
    checkedBorderColor: Color,
    uncheckedThumbColor: Color,
    uncheckedTrackColor: Color,
    uncheckedBorderColor: Color
) {
    Switch(
        checked = checked.value,
        onCheckedChange = {
            checked.value = it
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = checkedThumbColor,
            checkedTrackColor = checkedTrackColor,
            checkedBorderColor = checkedBorderColor,
            uncheckedThumbColor = uncheckedThumbColor,
            uncheckedTrackColor = uncheckedTrackColor,
            uncheckedBorderColor = uncheckedBorderColor
        )
    )
}