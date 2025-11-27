package com.example.guardsense.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.guardsense.R
import com.example.guardsense.ui.components.Header
import com.example.guardsense.ui.components.SettingsProfile
import com.example.guardsense.ui.navigation.Routes
import com.example.guardsense.ui.theme.GuardSenseTheme
import com.example.guardsense.ui.theme.PrimaryBlue
import com.example.guardsense.ui.theme.ralewayFont
import com.example.guardsense.viewmodel.AuthViewModel

@Composable
fun Profile(
    navController: NavController,
    viewModel: AuthViewModel,
    onToggleTheme: () -> Unit
) {
    var showChangePasswordDialog by remember { mutableStateOf(false) }

    if (showChangePasswordDialog) {
        ChangePasswordDialog(
            authViewModel = viewModel,
            onDismiss = { showChangePasswordDialog = false }
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
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
            ButtonChangeTheme(onToggleTheme)
            Column(Modifier.padding(top = 20.dp, bottom = 0.dp, start = 8.dp, end = 8.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 10.dp)
                        .clickable { navController.navigate(Routes.Settings) }
                ) {
                    Text(
                        text = "Configurações",
                        color = PrimaryBlue,
                        fontSize = 22.sp,
                        fontFamily = ralewayFont,
                        fontWeight = FontWeight.SemiBold
                    )
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Abrir configurações",
                        tint = PrimaryBlue,
                        modifier = Modifier.size(30.dp)
                    )
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    color = PrimaryBlue
                )
            }
            CardSettings(
                navController = navController,
                viewModel = viewModel,
                onChangePasswordClick = { showChangePasswordDialog = true } 
            )
        }
    }
}

@Composable
fun CardProfile() {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
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
fun ButtonChangeTheme(onToggleTheme: () -> Unit) {
    Button(
        onClick = { onToggleTheme() },
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
fun CardSettings(
    navController: NavController,
    viewModel: AuthViewModel,
    onChangePasswordClick: () -> Unit
) {
    Row {
        Column(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 5.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            ) {
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    SettingsProfile(
                        onClick = { /* Todo("Fazer ação para editar informações de usuário") */ },
                        text = "Editar informações",
                        textColor = PrimaryBlue,
                        icon = R.drawable.ic_edit
                    )
                    SettingsProfile(
                        onClick = { onChangePasswordClick() },
                        text = "Alterar senha da conta",
                        textColor = PrimaryBlue,
                        icon = R.drawable.ic_account_password
                    )
                    SettingsProfile(
                        onClick = { navController.navigate(Routes.DoorManagement) },
                        text = "Alterar senha da tranca",
                        textColor = PrimaryBlue,
                        icon = R.drawable.ic_keypad
                    )
                    SettingsProfile(
                        onClick = { viewModel.logout() },
                        text = "Sair da Conta",
                        textColor = PrimaryBlue,
                        icon = R.drawable.ic_arrow_left,
                        showDivider = false
                    ) // -> Provisório
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreviewInteractive() {
    var isDarkTheme by remember { mutableStateOf(true) }
    val navController = rememberNavController()

    GuardSenseTheme(darkTheme = isDarkTheme) {
        Profile(
            navController = navController,
            onToggleTheme = { isDarkTheme = !isDarkTheme }, // alterna tema no preview
            viewModel = viewModel()
        )
    }
}
