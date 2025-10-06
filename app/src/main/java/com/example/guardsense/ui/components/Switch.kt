package com.example.guardsense.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.guardsense.ui.theme.PrimaryBlue

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
        thumbContent = if (checked.value) {
            {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize),
                    tint = PrimaryBlue
                )
            }
        } else {
            null
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = checkedThumbColor,
            checkedTrackColor = checkedTrackColor,
            checkedBorderColor = checkedBorderColor,
            uncheckedThumbColor = uncheckedThumbColor,
            uncheckedTrackColor = uncheckedTrackColor,
            uncheckedBorderColor = uncheckedBorderColor,
        )
    )
}