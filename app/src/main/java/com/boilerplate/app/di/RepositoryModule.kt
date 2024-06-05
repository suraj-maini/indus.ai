package com.boilerplate.app.di

import com.boilerplate.app.data.datasource.auth.AuthRemoteDataSource
import com.boilerplate.app.data.repository.auth.AuthRepositoryImpl
import com.boilerplate.app.domain.repository.auth.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        remoteDataSource: AuthRemoteDataSource,
    ): AuthRepository = AuthRepositoryImpl(remoteDataSource)

}