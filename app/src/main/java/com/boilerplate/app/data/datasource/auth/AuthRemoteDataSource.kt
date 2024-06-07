package com.boilerplate.app.data.datasource.auth

import com.boilerplate.app.data.models.auth.request.LogInRequest
import com.boilerplate.app.data.models.auth.request.SignUpRequest
import com.boilerplate.app.data.remote.BaseRemoteDataSource
import com.boilerplate.app.data.service.ApiService
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val apiService: ApiService) :
    BaseRemoteDataSource() {
    suspend fun logIn(request: LogInRequest) = safeApiCall {
        apiService.logIn(request)
    }
    suspend fun signUp(request: SignUpRequest) = safeApiCall {
        apiService.signup(request)
    }
}