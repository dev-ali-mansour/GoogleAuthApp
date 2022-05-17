package dev.alimansour.googleauthapp.presentation.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dev.alimansour.googleauthapp.R
import dev.alimansour.googleauthapp.domain.mdel.MessageBarState
import dev.alimansour.googleauthapp.presentation.component.GoogleButton
import dev.alimansour.googleauthapp.presentation.component.MessageBar
import dev.alimansour.googleauthapp.presentation.theme.EXTRA_LARGE_PADDING
import dev.alimansour.googleauthapp.presentation.theme.LARGE_PADDING
import dev.alimansour.googleauthapp.presentation.theme.LogoSize
import dev.alimansour.googleauthapp.presentation.theme.TINY_PADDING

@Composable
fun LoginContent(
    signInState: Boolean,
    messageBarState: MessageBarState,
    onButtonClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier.weight(1f)) {
            MessageBar(messageBarState = messageBarState)
        }
        Column(
            modifier = Modifier
                .weight(9f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CentralContent(
                signInState = signInState,
                onButtonClicked = onButtonClicked
            )
        }
    }
}

@Composable
fun CentralContent(
    signInState: Boolean,
    onButtonClicked: () -> Unit
) {
    Image(
        modifier = Modifier
            .padding(LARGE_PADDING)
            .size(LogoSize),
        painter = painterResource(id = R.drawable.ic_google_logo),
        contentDescription = stringResource(id = R.string.google_logo)
    )
    Text(
        text = stringResource(R.string.login_title),
        fontSize = MaterialTheme.typography.h5.fontSize,
        fontWeight = FontWeight.Bold
    )
    Text(
        modifier = Modifier
            .alpha(ContentAlpha.medium)
            .padding(bottom = EXTRA_LARGE_PADDING, top = TINY_PADDING),
        text = stringResource(R.string.login_description),
        fontSize = MaterialTheme.typography.subtitle1.fontSize,
        textAlign = TextAlign.Center
    )
    GoogleButton(
        loadingState = signInState,
        onClick = onButtonClicked
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginContentPreview() {
    LoginContent(
        signInState = false,
        messageBarState = MessageBarState()
    ) {}
}