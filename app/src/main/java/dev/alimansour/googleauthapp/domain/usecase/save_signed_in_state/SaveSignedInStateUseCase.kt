package dev.alimansour.googleauthapp.domain.usecase.save_signed_in_state

import dev.alimansour.googleauthapp.domain.repository.Repository
import javax.inject.Inject

class SaveSignedInStateUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(signedIn: Boolean) {
        repository.saveSignedInState(signedIn = signedIn)
    }
}