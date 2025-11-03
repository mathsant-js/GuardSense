package com.example.guardsense.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun MonitoringRow(
    field: String,
    textColor: Color = Color.White,
    icon: Int = R.drawable.ic_logo_mini,
    showDivider: Boolean = true
) {
    val text = when (field) {
        "temperatureReading" -> "Temperatura: "
        "airHumidityReading" -> "Umidade do ar: "
        "gasReading" -> "Nível de gás no ar: "
        "presenceReading" -> "Presença detectada: "
        "waterLevelReading" -> "Alagamento detectado: "
        else -> "naofaznada"
    }
    var displayValue by remember { mutableStateOf("Carregando...") }

    LaunchedEffect(key1 = field) {
        getValue(field) { fetchedValue ->
            displayValue = valueTreatment(fetchedValue, field)
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
                    text = text + displayValue,
                    color = textColor,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
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


// talvez colocar tudo isso abaixo em um arquivo separado
fun getValue(
    fieldName: String,
    onSuccess: (Any?) -> Unit
) {
    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("Sensors").document("testsensors")

    docRef.addSnapshotListener { snapshot, e ->
       if (snapshot != null && snapshot.exists()) {
            val value: Any? = snapshot.get(fieldName)
            onSuccess(value) // Pass the fetched value to the callback
        }
    }
}

fun valueTreatment(
    value: Any?,
    fieldName: String
): String {
    if (value == null) {
        return "N/A"
    }

    val display: String = when (fieldName) {
        "temperatureReading" -> value.toString() +"ºC"

        "airHumidityReading" -> value.toString() +"%"

        "gasReading" -> (
            if (value is Number) {
                if (value.toInt() > 300) {"Anormal"} else {"Normal"}
            } else {
                ""
            }
        )

        "presenceReading" -> (
            if (value is Boolean) {
                if (value == true) {"Sim"} else {"Não"}
            } else {
                ""
            }
        )

        "waterLevelReading" -> (
            if (value is Number) {
                if (value.toInt() > 600) {"Sim"} else {"Não"}
            } else {
                ""
            }
        )
        else -> "naofaznada"
    }
    return display;
}