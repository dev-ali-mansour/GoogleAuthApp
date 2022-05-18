package dev.alimansour.googleauthapp.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import dev.alimansour.googleauthapp.domain.loacal.DataStoreOperations
import dev.alimansour.googleauthapp.util.Constants.PREFERENCES_SIGNED_IN_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStoreOperationsImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreOperations {
    private companion object {
        val signedInKey = booleanPreferencesKey(name = PREFERENCES_SIGNED_IN_KEY)
    }

    override suspend fun saveSignInState(signedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[signedInKey] = signedIn
        }
    }

    override fun readSignInState(): Flow<Boolean> =
        dataStore.data
            .catch { exception ->
                when (exception) {
                    is IOException -> emit(emptyPreferences())
                    else -> throw exception
                }
            }.map { preferences ->
                val signedInState = preferences[signedInKey] ?: false
                signedInState
            }
}