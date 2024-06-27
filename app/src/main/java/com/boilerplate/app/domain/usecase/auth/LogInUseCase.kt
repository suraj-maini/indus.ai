package com.boilerplate.app.domain.usecase.auth

import com.boilerplate.app.data.models.auth.model.LoginResponse
import com.boilerplate.app.data.models.auth.request.LogInRequest
import com.boilerplate.app.domain.repository.auth.AuthRepository
import com.boilerplate.app.data.BaseResponse
import com.boilerplate.app.domain.utils.Resource
import com.boilerplate.app.utils.EncryptedPrefs
import com.boilerplate.app.utils.SharedPreferencesHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LogInUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val encryptedPrefs: EncryptedPrefs,
    private val sharedPrefs: SharedPreferencesHelper
) {

    operator fun invoke(request: LogInRequest): Flow<Resource<BaseResponse<LoginResponse>>> = flow {
        emit(Resource.Loading)

        val result = authRepository.logIn(request)
        if (result is Resource.Success) {
            cacheTokens(result)
            cacheUserInfo(result.value.result)
        }
        emit(result)
    }.flowOn(Dispatchers.IO)

    private fun cacheTokens(result: Resource.Success<BaseResponse<LoginResponse>>) {
        // Save tokens securely
        encryptedPrefs.saveTokens(result.value.result.authToken, result.value.result.refreshToken)
    }

    private fun cacheUserInfo(user: LoginResponse) {
        sharedPrefs.saveUser(user)
    }
}