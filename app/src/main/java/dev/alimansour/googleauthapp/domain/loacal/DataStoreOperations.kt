package dev.alimansour.googleauthapp.domain.loacal

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun saveSignInState(signedIn: Boolean)
    fun readSignInState(): Flow<Boolean>
}