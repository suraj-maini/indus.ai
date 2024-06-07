package com.boilerplate.app.domain.usecase.auth

import com.boilerplate.app.data.models.auth.model.LoginResponse
import com.boilerplate.app.data.models.auth.request.LogInRequest
import com.boilerplate.app.domain.repository.auth.AuthRepository
import com.boilerplate.app.data.BaseResponse
import com.boilerplate.app.domain.utils.Resource
import com.boilerplate.app.utils.EncryptedPrefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LogInUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val encryptedPrefs: EncryptedPrefs
) {

    operator fun invoke(request: LogInRequest): Flow<Resource<BaseResponse<LoginResponse>>> = flow {
        emit(Resource.Loading)

        val result = authRepository.logIn(request)
        /*if (result is Resource.Success) {
          saveUserToLocalUseCase(result.value.result)
        }*/
        if (result is Resource.Success) {
            saveTokens(result)
        }
        emit(result)
    }.flowOn(Dispatchers.IO)

    private fun saveTokens(result: Resource.Success<BaseResponse<LoginResponse>>) {
        // Save tokens securely
        encryptedPrefs.putString("authToken", result.value.result.authToken)
        encryptedPrefs.putString("refreshToken", result.value.result.refreshToken)
    }
}