package dev.alimansour.googleauthapp.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import dev.alimansour.googleauthapp.R
import dev.alimansour.googleauthapp.domain.mdel.MessageBarState
import dev.alimansour.googleauthapp.presentation.theme.*
import kotlinx.coroutines.delay
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun MessageBar(
    messageBarState: MessageBarState
) {

    var startAnimation by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(key1 = messageBarState) {
        messageBarState.error?.let { it ->
            errorMessage = when (it) {
                is SocketTimeoutException -> "Connection Timeout Exception!"
                is ConnectException -> "Internet Connection Unavailable!"
                else -> "${it.message}"
            }
        }
        startAnimation = true
        delay(3000)
        startAnimation = false
    }

    AnimatedVisibility(
        visible = (messageBarState.error != null || messageBarState.message != null)
                && startAnimation,
        enter = expandVertically(
            animationSpec = tween(300),
            expandFrom = Alignment.Top
        ),
        exit = shrinkVertically(
            animationSpec = tween(300),
            shrinkTowards = Alignment.Top
        )
    ) {
        Message(messageBarState, errorMessage)
    }
}

@Composable
private fun Message(
    messageBarState: MessageBarState,
    errorMessage: String = ""
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (messageBarState.error != null) ErrorRed else InfoGreen)
            .padding(horizontal = LARGE_PADDING)
            .height(EXTRA_LARGE_PADDING),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (messageBarState.error != null) Icons.Default.Warning
            else Icons.Default.Check,
            contentDescription = stringResource(R.string.message_bar_icon),
            tint = Color.White
        )
        Divider(modifier = Modifier.width(SMALL_PADDING), color = Color.Transparent)
        Text(
            text = if (messageBarState.error != null) errorMessage
            else messageBarState.message.toString(),
            color = Color.White,
            fontSize = MaterialTheme.typography.button.fontSize,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun MessageBarPreview() {
    Message(messageBarState = MessageBarState(message = "Successfully Updated."))
}

@Preview
@Composable
fun MessageBarErrorPreview() {
    Message(
        messageBarState = MessageBarState(error = SocketTimeoutException()),
        errorMessage = "Socket Timeout Exception!"
    )
}