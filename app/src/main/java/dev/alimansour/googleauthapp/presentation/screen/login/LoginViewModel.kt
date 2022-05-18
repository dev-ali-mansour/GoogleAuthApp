package dev.alimansour.googleauthapp.presentation.screen.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.alimansour.googleauthapp.domain.mdel.MessageBarState
import dev.alimansour.googleauthapp.domain.usecase.read_signed_in_state.ReadSingedInStateUseCase
import dev.alimansour.googleauthapp.domain.usecase.save_signed_in_state.SaveSignedInStateUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val saveSignedInStateUseCase: SaveSignedInStateUseCase,
    private val readSignInStateUseCase: ReadSingedInStateUseCase
) : ViewModel() {

    private val _signInState: MutableState<Boolean> = mutableStateOf(false)
    val singInState: State<Boolean> = _signInState

    private val _messageBarState: MutableState<MessageBarState> = mutableStateOf(MessageBarState())
    val messageBarState: State<MessageBarState> = _messageBarState

    init {
        viewModelScope.launch {
            readSignInStateUseCase().collect { completed ->
                _signInState.value = completed
            }
        }
    }

    fun saveSignedInState(signedIn: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            saveSignedInStateUseCase(signedIn = signedIn)
        }
    }

}