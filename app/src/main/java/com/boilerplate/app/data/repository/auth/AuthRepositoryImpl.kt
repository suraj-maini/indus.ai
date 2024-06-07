package com.boilerplate.app.data.repository.auth

import com.boilerplate.app.data.BaseResponse
import com.boilerplate.app.data.datasource.auth.AuthRemoteDataSource
import com.boilerplate.app.data.models.auth.model.SignUpResponse
import com.boilerplate.app.data.models.auth.request.LogInRequest
import com.boilerplate.app.data.models.auth.request.SignUpRequest
import com.boilerplate.app.domain.repository.auth.AuthRepository
import com.boilerplate.app.domain.utils.Resource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override
    suspend fun logIn(request: LogInRequest) = remoteDataSource.logIn(request)
    override suspend fun signUp(request: SignUpRequest): Resource<BaseResponse<SignUpResponse>> =
        remoteDataSource.signUp(request)
}