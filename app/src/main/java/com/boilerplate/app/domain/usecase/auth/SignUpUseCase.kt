package com.boilerplate.app.domain.usecase.auth

import com.boilerplate.app.data.BaseResponse
import com.boilerplate.app.data.models.auth.model.SignUpResponse
import com.boilerplate.app.data.models.auth.request.SignUpRequest
import com.boilerplate.app.domain.repository.auth.AuthRepository
import com.boilerplate.app.domain.utils.Resource
import com.boilerplate.app.utils.EncryptedPrefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val encryptedPrefs: EncryptedPrefs
) {

    operator fun invoke(request: SignUpRequest): Flow<Resource<BaseResponse<SignUpResponse>>> =
        flow {
            emit(Resource.Loading)

            val result = authRepository.signUp(request)
            if (result is Resource.Success) {
                saveToken(result)
            }

            emit(result)
        }.flowOn(Dispatchers.IO)

    private fun saveToken(result: Resource.Success<BaseResponse<SignUpResponse>>) {
        // Save tokens securely
        encryptedPrefs.putString("authToken", result.value.result.authToken)
        encryptedPrefs.putString("refreshToken", result.value.result.refreshToken)
    }
}