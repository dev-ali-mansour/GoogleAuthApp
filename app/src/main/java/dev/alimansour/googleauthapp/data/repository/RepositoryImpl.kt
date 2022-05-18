package dev.alimansour.googleauthapp.data.repository

import dev.alimansour.googleauthapp.domain.loacal.DataStoreOperations
import dev.alimansour.googleauthapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataStore: DataStoreOperations
) : Repository {
    override suspend fun saveSignedInState(signedIn: Boolean) {
        dataStore.saveSignInState(signedIn = signedIn)
    }

    override fun readSignedInState(): Flow<Boolean> =
        dataStore.readSignInState()
}