package com.boilerplate.app.di

import com.boilerplate.app.domain.repository.auth.AuthRepository
import com.boilerplate.app.domain.usecase.auth.LogInUseCase
import com.boilerplate.app.domain.usecase.auth.SignUpUseCase
import com.boilerplate.app.utils.EncryptedPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideLogInUseCase(
        authRepository: AuthRepository,
        encryptedPrefs: EncryptedPrefs
    ): LogInUseCase = LogInUseCase(authRepository, encryptedPrefs)

    @Provides
    @Singleton
    fun provideSignUpUseCase(
        authRepository: AuthRepository,
        encryptedPrefs: EncryptedPrefs
    ): SignUpUseCase = SignUpUseCase(authRepository, encryptedPrefs)

}