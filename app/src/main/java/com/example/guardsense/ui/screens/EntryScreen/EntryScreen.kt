package com.example.guardsense.ui.screens.EntryScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.guardsense.R
import com.example.guardsense.ui.components.Logo
import com.example.guardsense.ui.components.OutlinedTextFieldCommom
import com.example.guardsense.ui.components.navigation.ButtonNavigation
import com.example.guardsense.ui.navigation.Routes
import com.example.guardsense.ui.theme.DarkBlue
import com.example.guardsense.ui.theme.PrimaryBlue
import com.example.guardsense.ui.theme.ralewayFont

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
        contentAlignment = Alignment.Center
    ) { content() }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryScreen(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val senha = remember { mutableStateOf("") }

    BackgroundContainer {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 10.dp)
        ) {
            Logo(140, 30)

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "Voltar",
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .clickable { navController.popBackStack() }
                )
                Text(
                    text = "Voltar",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = ralewayFont,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { navController.popBackStack() }
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextFieldCommom("Email", type = "email", email.value, { email.value = it })

                Column {
                    OutlinedTextFieldCommom("Senha", type = "password", senha.value, { senha.value = it })

                    TextButton(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 1.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            textDecoration = TextDecoration.Underline,
                                            fontFamily = ralewayFont,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 14.sp
                                        )
                                    ) { append("Esqueci a minha senha") }
                                },
                                color = Color.White
                            )
                        }
                    }
                }
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ButtonNavigation("Entrar", navController, Routes.Dashboard)
            }
        }
    }
}
