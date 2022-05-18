package dev.alimansour.googleauthapp.presentation.screen.login

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.alimansour.googleauthapp.domain.mdel.MessageBarState

@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    val signedInState by loginViewModel.singInState
    val messageBarState by loginViewModel.messageBarState

    Scaffold(topBar = {
        LoginTopBar()
    }) {
        LoginContent(
            signInState = signedInState,
            messageBarState = messageBarState
        ) {
            loginViewModel.saveSignedInState(signedIn = true)
        }
    }
}