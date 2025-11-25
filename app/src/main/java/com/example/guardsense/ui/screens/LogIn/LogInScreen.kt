package com.example.guardsense.ui.screens.LogIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.guardsense.R
import com.example.guardsense.ui.navigation.Routes
import com.example.guardsense.ui.theme.DarkBlue
import com.example.guardsense.ui.theme.PrimaryBlue
import com.example.guardsense.ui.theme.ralewayFont


// Container com gradiente
@Composable
fun BackgroundContainer(content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(PrimaryBlue, DarkBlue)
                )
            ),
        contentAlignment = Alignment.TopCenter
    ) { content() }
}


@Composable
fun LogInScreen(navController: NavController) {
    BackgroundContainer {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .padding(top = 120.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            // Logo
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(160.dp)
                    .padding(bottom = 10.dp)
            )

            Text(
                text = "Como você deseja entrar?",
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = ralewayFont,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "Escolha um método de login abaixo",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 15.sp,
                fontFamily = ralewayFont
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { navController.navigate(Routes.EntryScreen) },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Continuar com E-mail",
                    color = Color.White,
                    fontFamily = ralewayFont,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }

            // Divisor "Ou"
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 1.dp,
                    color = Color.White.copy(alpha = 0.7f)
                )
                Text(
                    text = "Ou",
                    color = Color.White,
                    fontFamily = ralewayFont,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 1.dp,
                    color = Color.White.copy(alpha = 0.7f)
                )
            }

            Button(
                onClick = { /* Google */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "Google",
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "Continuar com Google",
                        color = PrimaryBlue,
                        fontFamily = ralewayFont,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
            }

            Button(
                onClick = { /* Microsoft */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_microsoft),
                        contentDescription = "Microsoft",
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "Continuar com Microsoft",
                        color = PrimaryBlue,
                        fontFamily = ralewayFont,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
            }

            // Criar conta
            TextButton(
                onClick = { navController.navigate(Routes.SignInScreen) }
            ) {
                Text(
                    text = "Não tenho conta",
                    color = Color.White,
                    textDecoration = TextDecoration.Underline,
                    fontFamily = ralewayFont,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
        }
    }
}
