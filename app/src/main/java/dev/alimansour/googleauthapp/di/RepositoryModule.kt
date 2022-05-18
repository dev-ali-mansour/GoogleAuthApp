package dev.alimansour.googleauthapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.alimansour.googleauthapp.data.repository.RepositoryImpl
import dev.alimansour.googleauthapp.domain.loacal.DataStoreOperations
import dev.alimansour.googleauthapp.domain.repository.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(dataStore: DataStoreOperations): Repository =
        RepositoryImpl(dataStore = dataStore)
}