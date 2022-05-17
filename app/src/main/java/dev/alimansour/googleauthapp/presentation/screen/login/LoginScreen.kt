package dev.alimansour.googleauthapp.presentation.screen.login

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import dev.alimansour.googleauthapp.domain.mdel.MessageBarState

@Composable
fun LoginScreen(
    navController: NavHostController
) {
    Scaffold(topBar = {
        LoginTopBar()
    }) {
        LoginContent(
            signInState = false,
            messageBarState = MessageBarState()
        ) {}
    }
}