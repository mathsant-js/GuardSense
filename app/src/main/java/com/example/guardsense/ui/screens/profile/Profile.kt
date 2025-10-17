package com.example.guardsense.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.guardsense.R
import com.example.guardsense.ui.components.Header
import com.example.guardsense.ui.components.SettingsProfile
import com.example.guardsense.ui.navigation.Routes
import com.example.guardsense.ui.ralewayFont
import com.example.guardsense.ui.theme.CyanPrimary
import com.example.guardsense.ui.theme.PrimaryBlue

@Composable
fun Profile(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Header()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CardProfile()
            }
            ButtonChangeTheme()
            Column(Modifier.padding(top = 20.dp, bottom = 0.dp, start = 8.dp, end = 8.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 5.dp)
                ) {
                    Text(
                        text = "Configurações",
                        color = PrimaryBlue,
                        fontSize = 22.sp,
                        fontFamily = ralewayFont,
                        fontWeight = FontWeight.SemiBold
                    )
                    IconButton(
                        onClick = { navController.navigate(Routes.Settings) }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Abrir configurações",
                            tint = PrimaryBlue,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                }
                HorizontalDivider(
                    thickness = 1.dp,
                    color = PrimaryBlue
                )
            }
            CardSettings()
        }
    }
}

@Composable
fun CardProfile() {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CyanPrimary),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 8.dp,
                bottom = 0.dp,
                start = 8.dp,
                end = 8.dp
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Button(
                onClick = { },
                shape = RoundedCornerShape(100),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp), // remove padding interno do botão
                modifier = Modifier.size(80.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_profile),
                    contentDescription = "Foto de Perfil",
                    colorFilter = ColorFilter.tint(PrimaryBlue),
                    modifier = Modifier.size(120.dp)
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text(
                    text = "Nome da Pessoa",
                    fontFamily = ralewayFont,
                    fontSize = 22.sp,
                    color = PrimaryBlue,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "Plano Premium",
                    fontFamily = ralewayFont,
                    fontSize = 16.sp,
                    color = PrimaryBlue
                )
            }
        }
    }
}

@Composable
fun ButtonChangeTheme() {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 8.dp)
            .height(48.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Alterar tema",
                fontFamily = ralewayFont,
                color = Color.White,
                fontSize = 16.sp
            )
            Icon(
                painter = painterResource(R.drawable.ic_sun),
                contentDescription = "Alterar tema",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun CardSettings() {
    Row {
        Column(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 5.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CyanPrimary),
            ) {
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    SettingsProfile(
                        text = "Editar informações",
                        textColor = PrimaryBlue,
                        icon = R.drawable.ic_edit
                    )
                    SettingsProfile(
                        text = "Alterar senha da conta",
                        textColor = PrimaryBlue,
                        icon = R.drawable.ic_account_password
                    )
                    SettingsProfile(
                        text = "Alterar senha da tranca",
                        textColor = PrimaryBlue,
                        icon = R.drawable.ic_keypad,
                        showDivider = false
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    Profile(navController = rememberNavController())
}