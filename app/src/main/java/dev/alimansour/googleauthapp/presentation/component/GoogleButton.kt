package dev.alimansour.googleauthapp.presentation.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.alimansour.googleauthapp.R
import dev.alimansour.googleauthapp.presentation.theme.*

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    loadingState: Boolean = false,
    primaryText: String = "Sign in with Google",
    secondaryText: String = "Please wait...",
    icon: Int = R.drawable.ic_google_logo,
    shape: Shape = Shapes.medium,
    borderColor: Color = Color.LightGray,
    backgroundColor: Color = MaterialTheme.colors.surface,
    progressIndicatorColor: Color = LoadingBlue,
    onClick: () -> Unit
) {
    var buttonText by remember { mutableStateOf(primaryText) }

    LaunchedEffect(key1 = loadingState) {
        buttonText = if (loadingState) secondaryText else primaryText
    }

    Surface(
        modifier = modifier
            .clickable(enabled = !loadingState) {
                onClick()
            },
        shape = shape,
        border = BorderStroke(width = ButtonBorderWidth, color = borderColor),
        color = backgroundColor
    ) {
        Row(
            modifier = modifier
                .padding(
                    start = SMALL_PADDING,
                    end = MEDIUM_PADDING,
                    top = MEDIUM_PADDING,
                    bottom = MEDIUM_PADDING
                )
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = stringResource(R.string.google_logo),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(EXTRA_SMALL_PADDING))
            Text(text = buttonText)
            if (loadingState) {
                Spacer(modifier = Modifier.width(MEDIUM_PADDING))
                CircularProgressIndicator(
                    modifier = Modifier.size(MEDIUM_PADDING),
                    strokeWidth = ProgressIndicatorWidth,
                    color = progressIndicatorColor
                )
            }
        }
    }
}

@Preview
@Composable
fun GoogleButtonPreview() {
    GoogleButton {}
}