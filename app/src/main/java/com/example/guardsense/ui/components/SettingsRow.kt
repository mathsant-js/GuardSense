package com.example.guardsense.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guardsense.R
import com.example.guardsense.ui.theme.PrimaryBlue
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun SettingsRow(
    text: String,
    textColor: Color = MaterialTheme.colorScheme.surface,
    icon: Int = R.drawable.ic_logo_mini,
    showDivider: Boolean = true
) {
    val firebaseFieldName: String = getFirebaseFieldName(text)

    var switchState by remember { mutableStateOf(false) }

    LaunchedEffect(firebaseFieldName) {
        getFirebaseValue(firebaseFieldName) { fetchedValue ->
            switchState = fetchedValue
        }
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = textColor,
                    modifier = Modifier.size(28.dp)
                )
                Text(
                    text = text,
                    color = textColor,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Switch(
                checked = switchState,
                onCheckedChange = { isChecked ->
                    switchState = isChecked
                    switchFirebaseValue(firebaseFieldName)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.surface,
                    checkedTrackColor = PrimaryBlue,
                    checkedBorderColor = MaterialTheme.colorScheme.surface,
                    checkedIconColor = PrimaryBlue,
                    uncheckedThumbColor = MaterialTheme.colorScheme.surface,
                    uncheckedTrackColor = PrimaryBlue,
                    uncheckedBorderColor = MaterialTheme.colorScheme.surface
                ),
                thumbContent = if (switchState) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                        )
                    }
                } else {
                    null
                }
            )
        }
        if (showDivider) {
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp),
                thickness = 1.dp,
                color = textColor
            )
        }
    }
}

fun getFirebaseFieldName(
    rowText: String
): String {
    val fieldName: String = when (rowText) {
        "Sensor de presença" -> "presenceSensorEnabled"
        "Sensor de gás" -> "gasSensorEnabled"
        "Sensor de temp./umid." -> "tempHumiditySensorEnabled"
        "Sensor de vibração" -> "vibrationSensorEnabled"
        "Sensor de alagamento" -> "waterSensorEnabled"
        "Tranca da porta" -> "doorLockEnabled"
        "Desbloqueio por digital" -> "fingerPrintSensorEnabled"
        "Desbloqueio por rec. facial" -> "facialRecognitionEnabled"
        "Desbloqueio por senha" -> "keyPadEnabled"
        // Fields da tela de DoorManagement.kt
        "Utilizar Sensor de Digital" -> "fingerPrintSensorEnabled"
        "Reconhecimento Facial" -> "facialRecognitionEnabled"
        else -> "naofaznada"
    }

    return fieldName
}

fun getFirebaseValue(
    fieldName: String,
    onSuccess: (Boolean) -> Unit
) {
    if (fieldName != "naofaznada") {
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("Sensors").document("testsensors").collection("sensorstate")
            .document("testsensor")

        docRef.get()
            .addOnSuccessListener { documentSnapshot ->
                val value = if (documentSnapshot.exists()) {
                    documentSnapshot.getBoolean(fieldName) ?: false // Default to false if not found
                } else {
                    false // Default to false if document doesn't exist
                }
                onSuccess(value)
            }
            .addOnFailureListener { e ->
                println("Error getting document: $e")
                onSuccess(false) // Handle failure by returning a default value
            }
    }
}

fun switchFirebaseValue(
    fieldName: String
) {
    if (fieldName != "naofaznada") {
        val db = FirebaseFirestore.getInstance()

        val docRef = db.collection("Sensors").document("testsensors").collection("sensorstate").document("testsensor")

        docRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val currentValue = documentSnapshot.getBoolean(fieldName) ?: false // Default to false if not found
                    val newValue = !currentValue

                    docRef.update(fieldName, newValue)
                }
            }
            .addOnFailureListener { e ->
                println("Error getting document: $e")
            }
    }
}