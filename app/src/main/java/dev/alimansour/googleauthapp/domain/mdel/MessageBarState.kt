package dev.alimansour.googleauthapp.domain.mdel

data class MessageBarState(
    val message: String? = null,
    val error: Exception? = null
)
