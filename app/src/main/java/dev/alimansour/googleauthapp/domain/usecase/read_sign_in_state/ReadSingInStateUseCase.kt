package dev.alimansour.googleauthapp.domain.usecase.read_sign_in_state

import dev.alimansour.googleauthapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadSingInStateUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Boolean> = repository.readSignedInState()
}