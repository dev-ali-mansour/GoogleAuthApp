package dev.alimansour.googleauthapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.alimansour.googleauthapp.data.local.DataStoreOperationsImpl
import dev.alimansour.googleauthapp.domain.loacal.DataStoreOperations
import dev.alimansour.googleauthapp.util.Constants.PREFERENCES_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideDataStorePreferences(
        @ApplicationContext context: Context
    ): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(PREFERENCES_NAME) }
        )

    @Singleton
    @Provides
    fun provideDataStoreOperations(dataStore: DataStore<Preferences>): DataStoreOperations =
        DataStoreOperationsImpl(dataStore = dataStore)
}