package dev.alimansour.googleauthapp.presentation.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Gray200 = Color(0xFF819ca9)
val Gray500 = Color(0xFF546e7a)
val Gray700 = Color(0xFF29434e)
val Teal200 = Color(0xFF03DAC5)
val LoadingBlue = Color(0xFF1A73E8)
val ErrorRed = Color(0xFFFF6C60)
val InfoGreen = Color(0xFF00C096)


val Colors.topAppBarBackgroundColor: Color
    @Composable
    get() = if (isLight) Gray500 else Color.Black

val Colors.topAppBarContentColor: Color
    @Composable
    get() = if (isLight) Color.White else Color.LightGray
